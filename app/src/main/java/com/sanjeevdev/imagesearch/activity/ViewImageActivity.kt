package com.sanjeevdev.imagesearch.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.sanjeevdev.imagesearch.R
import com.sanjeevdev.imagesearch.adapter.CardStackAdapter
import com.sanjeevdev.imagesearch.callbacks.ImagesDiffCallback
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.utils.Constants
import com.sanjeevdev.imagesearch.utils.Controller
import com.sanjeevdev.imagesearch.viewModel.ImagesViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_view_image.*

class ViewImageActivity : AppCompatActivity(), CardStackListener {

    val TAG = "ViewImageActivity"
    private lateinit var manager: CardStackLayoutManager
    private lateinit var cardStackAdapter: CardStackAdapter
    private var imageList: ArrayList<ImageRootItem> = ArrayList()
    private lateinit var imageRootItem: ImageRootItem
    lateinit var imageViewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)

        imageRootItem = intent.getSerializableExtra(Constants.IMAGE) as ImageRootItem
        imageList = Controller.imageList
        Log.e(TAG, "onCreate: "+imageList )

        imageViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)

        cardStackAdapter = CardStackAdapter(imageList,this)


        manager = CardStackLayoutManager(this, this)
        for (i in 0 until imageList.size) {
            if (imageRootItem.id == imageList[i].id) {
                manager.topPosition = i
            }
        }
        manager.setStackFrom(StackFrom.Bottom)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(12.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        card_stack_view.layoutManager = manager
        card_stack_view.adapter = cardStackAdapter
        card_stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        restore_btn.setOnClickListener {
            card_stack_view.rewind()
        }

        button_favorite_card.setOnClickListener {
            imageViewModel.insertImage(this,imageList[manager.topPosition])
        }

    }

    //override methods
    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (manager.topPosition == cardStackAdapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    private fun paginate() {
        val old = cardStackAdapter.getSpots()
        val new = old.plus(imageList)
        val callback = ImagesDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        cardStackAdapter.setSpots(new as ArrayList<ImageRootItem>)
        result.dispatchUpdatesTo(cardStackAdapter)
    }

    private fun addFirst(size: Int) {
        val old = cardStackAdapter.getSpots()
        val new = mutableListOf<ImageRootItem>().apply {
            addAll(old)
            for (i in 0 until size) {
                add(manager.topPosition, imageList[0])
            }
        }
        val callback = ImagesDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        cardStackAdapter.setSpots(new as ArrayList<ImageRootItem>)
        result.dispatchUpdatesTo(cardStackAdapter)
    }

    private fun addLast(size: Int) {
        val old = cardStackAdapter.getSpots()
        val new = mutableListOf<ImageRootItem>().apply {
            addAll(old)
            addAll(List(size) { imageList[0] })
        }
        val callback = ImagesDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        cardStackAdapter.setSpots(new as ArrayList<ImageRootItem>)
        result.dispatchUpdatesTo(cardStackAdapter)
    }

}