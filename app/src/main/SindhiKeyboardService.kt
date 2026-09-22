package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        return keyboardView
    }

    fun typeText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    fun deleteText() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
