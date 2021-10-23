package com.diegolima.marvelheroes.ui.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.marvelheroes.R
import com.diegolima.marvelheroes.domain.models.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter(
    private var characters: List<Character>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], onItemClicked)
    }

    override fun getItemCount() = characters.size

    fun filterCharactersList(filteredList: List<Character>) {
        characters = filteredList
        notifyDataSetChanged()
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Character, onItemClicked: (Int) -> Unit){
            val imagePath = "${item.thumbnail.path}.${item.thumbnail.extension}"

            Picasso.get().load(imagePath).into(itemView.ivCharacter)
            itemView.tvCharacterName.text = item.name
            itemView.setOnClickListener{onItemClicked(item.id)}
        }
    }
}