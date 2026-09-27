package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {

        val text = TextView(this)

        text.text = "SINDHI KEYBOARD TEST"
        text.textSize = 24f
        text.setTextColor(Color.WHITE)
        text.setBackgroundColor(Color.RED)
        text.gravity = Gravity.CENTER

        text.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            300
        )

        return text
    }
}
