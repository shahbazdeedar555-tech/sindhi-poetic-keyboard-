package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
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

        return layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )
    }
}
