package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(
            this,
            "SERVICE CREATED",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreateInputView(): View {

        Toast.makeText(
            this,
            "INPUT VIEW CREATED",
            Toast.LENGTH_LONG
        ).show()

        val textView = TextView(this)

        textView.text = "SINDHI KEYBOARD TEST"
        textView.textSize = 28f
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundColor(Color.RED)
        textView.gravity = Gravity.CENTER
        textView.setPadding(20, 20, 20, 20)

        return textView
    }
}
