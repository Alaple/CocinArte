package com.bifrost.cocinarte.models

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
    }



}