package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {

        val textView = TextView(this)

        textView.text = "SINDHI KEYBOARD IS RUNNING"
        textView.textSize = 24f
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundColor(Color.RED)
        textView.gravity = Gravity.CENTER
        textView.setPadding(20, 20, 20, 20)

        return textView
    }
}
