package com.example.onlineshop.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ProfileMenuTabBinding

class ProfileMenuTab(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: ProfileMenuTabBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.profile_menu_tab, this, true)
        binding = ProfileMenuTabBinding.bind(this)
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProfileMenuTab,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val tabIconRes = typedArray.getResourceId(R.styleable.ProfileMenuTab_tabIcon, 0)
            if (tabIconRes != 0)
                tabIcon.setImageResource(tabIconRes)

            val tabTextRes = typedArray.getResourceId(R.styleable.ProfileMenuTab_tabText, 0)
            if (tabTextRes != 0)
                tabText.text = context.getString(tabTextRes)

            val arrowVisibility = typedArray.getBoolean(R.styleable.ProfileMenuTab_hasArrow, false)
            when (arrowVisibility) {
                true -> arrow.visibility = VISIBLE
                false -> arrow.visibility = GONE
            }

            val balanceVisibility =
                typedArray.getBoolean(R.styleable.ProfileMenuTab_hasBalance, false)
            when (balanceVisibility) {
                true -> balance.visibility = VISIBLE
                false -> balance.visibility = GONE
            }

            val balanceRes = typedArray.getString(R.styleable.ProfileMenuTab_balance)
            balance.text = balanceRes

        }

        typedArray.recycle()
    }
}