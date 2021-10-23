package com.diegolima.marvelheroes.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegolima.marvelheroes.R
import com.diegolima.marvelheroes.core.network.NetworkResponse
import com.diegolima.marvelheroes.domain.models.Character
import com.diegolima.marvelheroes.domain.models.MarvelResponse
import com.diegolima.marvelheroes.ui.MainActivity
import com.diegolima.marvelheroes.utils.showAlertError
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.*
import kotlinx.android.synthetic.main.character_item.ivCharacter
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val CHARACTER_ID = "ARG_CHARACTER_ID"

class CharacterDetailFragment : Fragment(){
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel()

    private var characterId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { characterId = it.getInt(CHARACTER_ID) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =  inflater.inflate(R.layout.fragment_character_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCharacterDetails()
    }

    private fun getCharacterDetails() =
        characterDetailViewModel.responseLiveData.observe(
            viewLifecycleOwner,
            Observer<NetworkResponse<MarvelResponse>> { networkResponse ->
                when(networkResponse?.status){
                    NetworkResponse.Status.LOADING -> containerLoading.visibility = View.VISIBLE
                    NetworkResponse.Status.SUCCESS -> showSuccess(networkResponse.data?.marvelData?.results?.first())
                    NetworkResponse.Status.ERROR -> showError()
                }
            }
        )

    private fun showError() {
        containerLoading.visibility = View.GONE
        (activity as MainActivity).showAlertError { getCharacterDetails() }
    }

    private fun showSuccess(character: Character?){
        containerLoading.visibility = View.GONE

        character?.let {
            val imagePath = "${character.thumbnail.path}.${character.thumbnail.extension}"

            Picasso.get().load(imagePath).into(ivCharacter)
            tvCharcterNameValue.text = character.name
            tvCharacterDescriptionValue.text = character.description
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_ID, id)
                }
            }
    }
}