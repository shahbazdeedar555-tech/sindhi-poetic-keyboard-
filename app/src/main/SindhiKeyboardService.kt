package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.View
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val testView = TextView(this)

        testView.text = "سنڌي شاعري ڪي بورڊ"
        testView.textSize = 28f
        testView.setTextColor(Color.BLACK)
        testView.setBackgroundColor(Color.LTGRAY)
        testView.gravity = android.view.Gravity.CENTER
        testView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            400
        )

        return testView
    }
}
