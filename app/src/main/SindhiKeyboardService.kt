package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
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

        val text = TextView(this)

        text.text = "سنڌي شاعري ڪي بورڊ\nSERVICE IS WORKING"
        text.textSize = 24f
        text.setPadding(20, 40, 20, 40)

        return text
    }
}
