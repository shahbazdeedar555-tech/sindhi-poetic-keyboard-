
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

        val spaceButton = keyboardView.findViewById<Button>(R.id.spaceButton)
        val enterButton = keyboardView.findViewById<Button>(R.id.enterButton)
        val backspaceButton = keyboardView.findViewById<Button>(R.id.backspaceButton)

        spaceButton?.setOnClickListener {
            currentInputConnection?.commitText(" ", 1)
        }

        enterButton?.setOnClickListener {
            currentInputConnection?.sendKeyEvent(
                android.view.KeyEvent(
                    android.view.KeyEvent.ACTION_DOWN,
                    android.view.KeyEvent.KEYCODE_ENTER
                )
            )

            currentInputConnection?.sendKeyEvent(
                android.view.KeyEvent(
                    android.view.KeyEvent.ACTION_UP,
                    android.view.KeyEvent.KEYCODE_ENTER
                )
            )
        }

        backspaceButton?.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }

        return keyboardView
    }
}
