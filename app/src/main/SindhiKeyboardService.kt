package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {

        Toast.makeText(
            this,
            "KEYBOARD VIEW STARTED",
            Toast.LENGTH_LONG
        ).show()

        val text = TextView(this)

        text.text = "SINDHI KEYBOARD TEST"
        text.textSize = 28f
        text.setTextColor(Color.WHITE)
        text.setBackgroundColor(Color.RED)
        text.gravity = Gravity.CENTER

        return text
    }
}
