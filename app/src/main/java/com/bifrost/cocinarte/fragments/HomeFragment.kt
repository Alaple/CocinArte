package com.bifrost.cocinarte.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.GridView

import com.bifrost.cocinarte.models.HomeViewModel
import com.bifrost.cocinarte.R

class HomeFragment : Fragment() {

    private lateinit var v: View
    private lateinit var viewModel: HomeViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)

        return v;
    }

    override fun onStart() {
        super.onStart()

        // Initialize image variables
        // imageLogin.setImageResource(R.drawable.ic_launcher_background)

        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {

    }

    private fun initializeButtons() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gridView = v.findViewById(R.id.gridRecommended) as GridView
        gridView.adapter = RecipeAdapter(this.requireContext())
    }

    private val mOnItemClickListener =
        OnItemClickListener { adapterView, view, position, id ->

        }

    private class RecipeAdapter(context: Context) : BaseAdapter() {

        private val mItems: MutableList<RecipeItem> = ArrayList<RecipeItem>()
        private val mInflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return mItems.size
        }

        override fun getItem(i: Int): RecipeAdapter.RecipeItem {
            return mItems[i]
        }

        override fun getItemId(i: Int): Long {
            return mItems[i].drawableId as Long
        }

        override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {

            /*
            var v = view
            if (v == null) {
                v = mInflater.inflate(R.layout.recipe_item, viewGroup, false)
                v.setTag(R.id.imgRecipe, v.findViewById(R.id.imgRecipe))
                v.setTag(R.id.txtRecipe, v.findViewById(R.id.txtRecipe))
            }
            */

            val item: RecipeItem = getItem(i)
            val imgView = view.getTag(R.id.imgRecipe) as ImageView
            val txtView = view.getTag(R.id.txtRecipe) as TextView
            imgView.setImageResource(item.drawableId)
            txtView.text = item.name

            return view
        }

        private class RecipeItem internal constructor(val name: String, val drawableId: Int)

        init {
            mItems.add(RecipeItem("lacinato_salad", R.drawable.lacinato_salad))
            mItems.add(RecipeItem("shaved_salad", R.drawable.shaved_salad))
            mItems.add(RecipeItem("shredded_salad", R.drawable.shredded_salad))
            mItems.add(RecipeItem("thai_salad", R.drawable.thai_salad))
        }
    }
}