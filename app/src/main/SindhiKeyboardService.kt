package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): TextView {

        val text = TextView(this)

        text.text = "SINDHI KEYBOARD TEST"
        text.textSize = 28f
        text.setTextColor(Color.RED)
        text.setBackgroundColor(Color.YELLOW)
        text.gravity = Gravity.CENTER
        text.minimumHeight = 300

        return text
    }
}
