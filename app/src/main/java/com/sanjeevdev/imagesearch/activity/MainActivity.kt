package com.sanjeevdev.imagesearch.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.VolleyError
import com.sanjeevdev.imagesearch.R
import com.sanjeevdev.imagesearch.adapter.ImageAdapter
import com.sanjeevdev.imagesearch.callbacks.ResponseCallback
import com.sanjeevdev.imagesearch.listener.ObjectClickListener
import com.sanjeevdev.imagesearch.models.ImageRoot
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.urlsbinders.UrlsBinder
import com.sanjeevdev.imagesearch.utils.Constants
import com.sanjeevdev.imagesearch.utils.Controller
import com.sanjeevdev.imagesearch.viewModel.ImagesViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var query: String = ""
    var imageResult: ArrayList<ImageRootItem> = ArrayList()
    private lateinit var adapter: ImageAdapter;
    var page = 1;
    lateinit var imageViewModel: ImagesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting recyclerView and adapter
        adapter = ImageAdapter(imageResult, this, objectClickListener)
        imageRV.setHasFixedSize(true)
        imageRV.layoutManager = StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.VERTICAL
        )
        imageRV.adapter = adapter

        imageViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)

        getImages(page)

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    Toast.makeText(this, "Fav", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search_bar -> {
                    if (searchLayout.visibility == View.GONE) {
                        visibleSearchLayout()
                    } else {
                        goneSearchLayout()
                    }
                    true
                }
                else -> false
            }
        }

        search_close_btn.setOnClickListener {
            goneSearchLayout()
        }

        searchTextInputEditText.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                query = searchTextInputEditText.text.toString().trim()
                if (query.isEmpty()) {
                    Toast.makeText(this, "Search Nothing", Toast.LENGTH_SHORT).show()
                } else {
                    imageResult.clear()
                    getImages(page)
                }
            }
            return@setOnEditorActionListener true
        }

        nestedSV.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nestedSV.getChildAt(0).measuredHeight - view.measuredHeight) {
                page += 1
                getImages(page)
            }
        }


    }

    private fun visibleSearchLayout() {
        searchLayout.visibility = View.VISIBLE
        searchTextInputEditText.requestFocus()
    }

    private fun goneSearchLayout() {
        searchLayout.visibility = View.GONE
    }

    private fun getImages(page: Int) {
        Log.e(TAG, "getImages: " + UrlsBinder.bindUrl(query, page))
        progressBar.visibility = View.VISIBLE
        imageViewModel.getImages(UrlsBinder.bindUrl(query, page),this,object : ResponseCallback{
            override fun onSuccess(imagesRoot: ImageRoot) {
                imageResult.addAll(imagesRoot)
                adapter.refreshImages(imageResult)
            }

        })
    }

    private val objectClickListener = object : ObjectClickListener {
        override fun onClick(imageRootItem: ImageRootItem) {
            Log.e(TAG, "onClick: " + imageResult)
            Controller.imageList = imageResult

            val intent = Intent(this@MainActivity, ViewImageActivity::class.java)
            intent.putExtra(Constants.IMAGE, imageRootItem)
            startActivity(intent)
        }
    }
}