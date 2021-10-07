package com.bifrost.cocinarte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R

class ButtonListAdapter (
    private var buttonsList: MutableList<Button>
        ): RecyclerView.Adapter<ButtonListAdapter.ButtonHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonListAdapter.ButtonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_list_item, parent,false)
        return (ButtonHolder(view))
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
        // TODO holder.setName(buttonsList[position].nombreVino)

        holder.getCardLayout().setOnClickListener() {
           //TODO  onItemClick(buttonsList[position].nombreVino)
        }
    }

    class ButtonHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view:View

        init{
            this.view = v
        }

        fun setName(name: String){
            //TODO  val txt : TextView = view.findViewById(R.id.wineName)
            txt.text = name
        }

        fun getCardLayout (): CardView {
           //TODO return view.findViewById(R.id.cardView)//TODO
        }

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}