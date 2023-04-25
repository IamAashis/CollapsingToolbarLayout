package com.android.collapsingtoolbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    var appBarLayout: AppBarLayout? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    var lltViewNew: TextView? = null
    var txvItemTotal: TextView? = null
    var toolbar: androidx.appcompat.widget.Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBarLayout = findViewById(R.id.app_bar)
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout)
        lltViewNew = findViewById(R.id.txvVegetable)
        txvItemTotal = findViewById(R.id.txvItemTotal)
        toolbar = findViewById(R.id.toolbar)
        initAppBarLayoutListener()
    }

    private fun initAppBarLayoutListener() {
        appBarLayout?.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (kotlin.math.abs(verticalOffset) != appBarLayout.totalScrollRange) {
                lltViewNew?.visibility = View.VISIBLE
                txvItemTotal?.visibility = View.VISIBLE
            } else {
                lltViewNew?.visibility = View.INVISIBLE
                txvItemTotal?.visibility = View.INVISIBLE
            }
        }

        collapsingToolbarLayout?.title = ""
        collapsingToolbarLayout?.setExpandedTitleColor(Color.TRANSPARENT)
        collapsingToolbarLayout?.setCollapsedTitleTextColor(Color.BLACK)

        appBarLayout?.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShown = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout?.totalScrollRange ?: 0
                }

                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout?.title = "Title"
                    isShown = true
                } else if (isShown) {
                    collapsingToolbarLayout?.title = ""
                    isShown = false
                }
            }
        })
    }
}