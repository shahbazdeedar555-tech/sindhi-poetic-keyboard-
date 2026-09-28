package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): ViewGroup {

        val box = TextView(this)

        box.text = "SINDHI KEYBOARD\nSERVICE IS RUNNING"
        box.textSize = 24f
        box.setTextColor(Color.WHITE)
        box.setBackgroundColor(Color.RED)
        box.gravity = Gravity.CENTER
        box.setPadding(20, 20, 20, 20)

        return box
    }
}
