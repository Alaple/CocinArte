package com.bifrost.cocinarte.adapters

<<<<<<< HEAD
import android.util.Log
=======
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
<<<<<<< HEAD
import com.bifrost.cocinarte.entities.Filter
import com.google.android.material.chip.Chip

class ButtonListAdapter (
    private var buttonsList: MutableList<Filter>
=======

class ButtonListAdapter (
    private var buttonsList: MutableList<Button>
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
        ): RecyclerView.Adapter<ButtonListAdapter.ButtonHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonListAdapter.ButtonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_list_item, parent,false)
        return (ButtonHolder(view))
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
<<<<<<< HEAD
        holder.setName(buttonsList[position].name)


        holder.getCardLayout().setOnClickListener() {
           //TODO  onItemClick(buttonsList[position].nombreVino)

=======
        // TODO holder.setName(buttonsList[position].nombreVino)


        holder.getCardLayout().setOnClickListener() {
           //TODO  onItemClick(buttonsList[position].nombreVino)
<<<<<<< HEAD
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
=======

>>>>>>> 61a0fb1 (COC-24- Stable pre test)
        }
    }

    class ButtonHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view:View

        init{
            this.view = v
        }

        fun setName(name: String){
<<<<<<< HEAD
            val txt : Button = view.findViewById(R.id.toggleButton)
            txt.setText(name)
        }

        fun getCardLayout (): Button {
            return view.findViewById(R.id.toggleButton)
        }

        fun onClick(){
            val chip: Chip = view.findViewById(R.id.toggleButton)
            chip.closeIconTint

=======
            //TODO  val txt : TextView = view.findViewById(R.id.wineName)
            //txt.text = name
        }

        fun getCardLayout (): CardView {
           //TODO return view.findViewById(R.id.cardView)//TODO
<<<<<<< HEAD
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
=======
            return view.findViewById(R.id.buttonsRecView)
>>>>>>> 61a0fb1 (COC-24- Stable pre test)
        }

    }

    override fun getItemCount(): Int {
<<<<<<< HEAD
<<<<<<< HEAD
        return buttonsList.size
=======
        TODO("Not yet implemented")
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
=======
        return buttonsList.size
>>>>>>> 61a0fb1 (COC-24- Stable pre test)
    }
}