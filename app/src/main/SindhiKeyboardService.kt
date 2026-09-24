package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val keyboardView = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        val spaceButton = keyboardView.findViewById<Button>(
            R.id.key_space
        )

        val deleteButton = keyboardView.findViewById<Button>(
            R.id.key_delete
        )

        spaceButton.setOnClickListener {
            currentInputConnection?.commitText(" ", 1)
        }

        deleteButton.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }

        return keyboardView
    }
}
