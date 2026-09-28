package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val textView = TextView(this)

        textView.text = "SINDHI KEYBOARD IS WORKING"
        textView.textSize = 30f
        textView.setBackgroundColor(0xFFFF0000.toInt())
        textView.setTextColor(0xFFFFFFFF.toInt())
        textView.gravity = android.view.Gravity.CENTER

        return textView
    }
}
