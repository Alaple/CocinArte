package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.RecipeUrlViewModel

class RecipeUrlFragment : Fragment() {

    lateinit var v: View

    lateinit var webView: WebView

    companion object {
        fun newInstance() = RecipeUrlFragment()
    }

    private lateinit var viewModel: RecipeUrlViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recipe_url_fragment, container, false)

        webView = v.findViewById(R.id.recipeUrlWebView)

        return v
    }

    override fun onStart() {
        super.onStart()

        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(RecipeUrlFragmentArgs.fromBundle(requireArguments()).recipeUrl)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeUrlViewModel::class.java)
        // TODO: Use the ViewModel
    }

}