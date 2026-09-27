package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.View
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val text = TextView(this)

        text.text = "SINDHI KEYBOARD SERVICE WORKS"
        text.textSize = 24f
        text.setTextColor(Color.WHITE)
        text.setBackgroundColor(Color.RED)
        text.gravity = Gravity.CENTER
        text.setPadding(20, 40, 20, 40)

        return text
    }
}
