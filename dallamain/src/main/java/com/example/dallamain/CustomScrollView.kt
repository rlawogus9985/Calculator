package com.example.dallamain

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView

class CustomScrollView(context: Context, attributeSet: AttributeSet? = null)
    : NestedScrollView(context, attributeSet), ViewTreeObserver.OnGlobalLayoutListener {

    init {
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    var tabLayoutSection: View? = null
        set(value) {
            field = value
            field?.let {
                it.translationZ = 1f
            }
        }
    var actionBarW: View? = null
        set(value) {
            field = value
        }


    private var isTabLayoutSticked = false
    private var tabLayoutInitPosition = 0f
    private var actionbarWHeight = 0f

    override fun onGlobalLayout() {
        tabLayoutInitPosition = tabLayoutSection?.top?.toFloat() ?: 0f
        actionbarWHeight = actionBarW?.height?.toFloat() ?: 0f

        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onScrollChanged(scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY)
            Log.d("dfsdf","${tabLayoutInitPosition}, ${actionbarWHeight}")
        if (scrollY > tabLayoutInitPosition - actionbarWHeight) {
            stickHeader(scrollY - tabLayoutInitPosition + actionbarWHeight)
        } else {
            freeHeader()
        }
    }

    private fun stickHeader(position: Float){
        tabLayoutSection?.translationY = position
//        if (!isTabLayoutSticked){
//            isTabLayoutSticked = true
//        }
    }

    private fun freeHeader(){
        tabLayoutSection?.translationY = 0f
//        if(isTabLayoutSticked){
//            isTabLayoutSticked = false
//        }
    }

}