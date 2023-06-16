package id.plantaniteam.plantaniapp.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import id.plantaniteam.plantaniapp.R

class PlanTaniEditText : AppCompatEditText {
    constructor(context: Context) : super(context) {  }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {  }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {  }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        background = ContextCompat.getDrawable(context, R.drawable.plantani_edit_text) as Drawable
    }
}