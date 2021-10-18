package com.bifrost.cocinarte.models

<<<<<<< HEAD
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.Filter

class ButtonsViewModel: ViewModel() {
    var buttonsList : MutableList<Filter> = ArrayList()

    //TEST
    lateinit var button1 : Filter
    lateinit var button2: Filter
    lateinit var button3: Filter
    lateinit var button4: Filter
    lateinit var button5: Filter
    lateinit var button6: Filter


    fun cargarTest(){

        button1 = Filter("Vegetarian", "vegetarian")

        button2 = Filter("Celiac", "celiac")

        button3 = Filter("Amigo", "amigo")

        button4 = Filter("Carnivoro", "carnivoro")

        button5 = Filter("Kosher", "kosher")

        button6 = Filter("Otro", "otro")


        buttonsList.add(button1)
        buttonsList.add(button2)
        buttonsList.add(button3)
        buttonsList.add(button4)
        buttonsList.add(button5)
        buttonsList.add(button6)


=======
import android.widget.Button
import androidx.lifecycle.ViewModel

class ButtonsViewModel: ViewModel() {
    var buttonsList : MutableList<Button> = ArrayList()

    //TEST
    lateinit var button1 : Button
    lateinit var button2: Button
    lateinit var button3: Button


    fun cargarTest(){
        button1.setText("Botoncito 1")
        button2.setText("Botoncito 2")
        button3.setText("Botoncito 3")
        buttonsList.add(button1)
        buttonsList.add(button2)
        buttonsList.add(button3)
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
    }



}