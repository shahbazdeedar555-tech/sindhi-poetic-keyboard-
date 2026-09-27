package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.TextView
import android.graphics.Color

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val text = TextView(this)

        text.text = "سنڌي شاعري ڪي بورڊ\nSERVICE IS WORKING"
        text.textSize = 24f
        text.setTextColor(Color.BLACK)
        text.setBackgroundColor(Color.YELLOW)
        text.gravity = android.view.Gravity.CENTER
        text.setPadding(20, 20, 20, 20)

        return text
    }
}
