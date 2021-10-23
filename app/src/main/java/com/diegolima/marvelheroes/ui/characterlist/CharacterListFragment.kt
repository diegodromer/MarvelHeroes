package com.diegolima.marvelheroes.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegolima.marvelheroes.R
import com.diegolima.marvelheroes.core.network.NetworkResponse
import com.diegolima.marvelheroes.domain.models.Character
import com.diegolima.marvelheroes.ui.MainActivity
import com.diegolima.marvelheroes.ui.MainRouter
import com.diegolima.marvelheroes.ui.characterdetail.CharacterDetailFragment
import com.diegolima.marvelheroes.utils.showAlertError
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {
    private val characterListViewModel: CharacterListViewModel by viewModel()

    private lateinit var charactersList: List<Character>

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(
            charactersList,
            this::characterClick
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListViewModel.getCharacters()
        registerSearchListener()
        registerObserver()
    }

    private fun registerSearchListener() {
        etCharacterName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                characterListViewModel.filterCharactersList(
                    charactersList = charactersList,
                    adapter = characterListAdapter,
                    query = etCharacterName.text.toString()
                )
            }
            false
        }
    }

    private fun registerObserver() =
        characterListViewModel.responseLiveData.observe(
            viewLifecycleOwner,
            Observer { networkResponse ->
                when (networkResponse?.status) {
                    NetworkResponse.Status.LOADING -> containerLoading.visibility = View.VISIBLE
                    NetworkResponse.Status.SUCCESS -> showSuccess(networkResponse.data?.marvelData?.results)
                    NetworkResponse.Status.ERROR -> showError()
                }
            })

    private fun showSuccess(results: List<Character>?) {
        containerLoading.visibility = View.GONE

        results?.let { characters ->
            charactersList = characters
            rvCharacterList.layoutManager = LinearLayoutManager(activity)
            rvCharacterList.adapter = characterListAdapter
        }
    }

    private fun showError() {
        containerLoading.visibility = View.GONE
        requireActivity().showAlertError { characterListViewModel.getCharacters() }
    }

    private fun characterClick(id: Int) {
        MainRouter.goToFragment(
            activity = activity as MainActivity,
            fragment = CharacterDetailFragment.newInstance(id)
        )
    }
}