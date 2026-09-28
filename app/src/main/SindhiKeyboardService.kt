package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {

        val text = TextView(this)

        text.text = "SINDHI KEYBOARD SERVICE IS RUNNING"
        text.textSize = 24f
        text.setTextColor(Color.WHITE)
        text.setBackgroundColor(Color.RED)
        text.gravity = Gravity.CENTER
        text.setPadding(20, 40, 20, 40)

        return text
    }
}
