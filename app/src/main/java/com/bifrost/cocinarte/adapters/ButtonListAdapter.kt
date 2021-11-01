package com.bifrost.cocinarte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.Filter
import com.google.android.material.chip.Chip

class ButtonListAdapter(
    private var buttonsList: MutableList<Filter>,
    val onItemsClick: (Int, String) -> Unit


        ): RecyclerView.Adapter<ButtonListAdapter.ButtonHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonListAdapter.ButtonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_list_item, parent,false)
        return (ButtonHolder(view))
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {

        holder.setName(buttonsList[position].name)


        holder.getCardLayout().setOnClickListener() {
           //TODO  onItemClick(buttonsList[position].nombreVino)
            onItemsClick(position, "")
        }
    }

    class ButtonHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view:View

        init{
            this.view = v
        }

        fun setName(name: String){

            val txt : Button = view.findViewById(R.id.toggleButton)
            txt.setText(name)
        }

        fun getCardLayout (): Button {
            return view.findViewById(R.id.toggleButton)
        }

        fun onClick(){
            val chip: Chip = view.findViewById(R.id.toggleButton)
            chip.closeIconTint


            //TODO  val txt : TextView = view.findViewById(R.id.wineName)
            //txt.text = name
        }


            val txt : Button = view.findViewById(R.id.toggleButton)

    }

        override fun getItemCount(): Int {

        return buttonsList.size

            }
        }




