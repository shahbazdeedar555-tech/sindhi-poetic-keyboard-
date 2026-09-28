package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {

        val test = TextView(this)

        test.text = "SINDHI KEYBOARD\n\nSERVICE IS WORKING"
        test.textSize = 28f
        test.setTextColor(Color.WHITE)
        test.setBackgroundColor(Color.RED)
        test.gravity = Gravity.CENTER
        test.setPadding(20, 20, 20, 20)

        return test
    }
}
