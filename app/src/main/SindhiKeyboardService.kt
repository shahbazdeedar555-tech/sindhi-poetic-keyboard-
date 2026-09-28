package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
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

    override fun onCreateInputView(): android.view.View {

        Toast.makeText(
            this,
            "INPUT VIEW CREATED",
            Toast.LENGTH_LONG
        ).show()

        val testView = TextView(this)

        testView.text = "SINDHI KEYBOARD\nSERVICE IS WORKING"
        testView.textSize = 28f
        testView.setTextColor(Color.WHITE)
        testView.setBackgroundColor(Color.RED)
        testView.gravity = Gravity.CENTER
        testView.setPadding(20, 20, 20, 20)

        return testView
    }
}
