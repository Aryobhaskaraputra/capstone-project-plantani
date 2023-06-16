package id.plantaniteam.plantaniapp.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import id.plantaniteam.plantaniapp.R

class PlanTaniButton : AppCompatButton {
    constructor(context: Context) : super(context) { init() }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) { init() }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) { init() }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = ContextCompat.getDrawable(context, R.drawable.plantani_button) as Drawable
    }

    private fun init() {
    }
}