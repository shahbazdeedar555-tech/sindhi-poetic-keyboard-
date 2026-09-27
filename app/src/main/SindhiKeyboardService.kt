package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
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
            "KEYBOARD VIEW STARTED",
            Toast.LENGTH_LONG
        ).show()

        val view = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        val keys = mapOf(
            R.id.key_alif to "ا",
            R.id.key_bay to "ب",
            R.id.key_pay to "پ"
        )

        for ((id, text) in keys) {
            view.findViewById<Button>(id).setOnClickListener {
                currentInputConnection.commitText(text, 1)
            }
        }

        return view
    }
}
