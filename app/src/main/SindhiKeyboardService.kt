
package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    private var keyboardView: View? = null

    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        setupKeys()

        return keyboardView!!
    }

    private fun setupKeys() {

        val view = keyboardView ?: return

        view.findViewById<Button>(R.id.key_alif).setOnClickListener {
            typeText("ا")
        }

        view.findViewById<Button>(R.id.key_bay).setOnClickListener {
            typeText("ب")
        }

        view.findViewById<Button>(R.id.key_pay).setOnClickListener {
            typeText("پ")
        }

        view.findViewById<Button>(R.id.key_tay).setOnClickListener {
            typeText("ت")
        }

        view.findViewById<Button>(R.id.key_jeem).setOnClickListener {
            typeText("ج")
        }

        view.findViewById<Button>(R.id.key_chay).setOnClickListener {
            typeText("چ")
        }

        view.findViewById<Button>(R.id.key_daal).setOnClickListener {
            typeText("د")
        }

        view.findViewById<Button>(R.id.key_raa).setOnClickListener {
            typeText("ر")
        }

        view.findViewById<Button>(R.id.key_seen).setOnClickListener {
            typeText("س")
        }

        view.findViewById<Button>(R.id.key_sheen).setOnClickListener {
            typeText("ش")
        }

        view.findViewById<Button>(R.id.key_kaaf).setOnClickListener {
            typeText("ڪ")
        }

        view.findViewById<Button>(R.id.key_lam).setOnClickListener {
            typeText("ل")
        }

        view.findViewById<Button>(R.id.key_meem).setOnClickListener {
            typeText("م")
        }

        view.findViewById<Button>(R.id.key_noon).setOnClickListener {
            typeText("ن")
        }

        view.findViewById<Button>(R.id.key_wao).setOnClickListener {
            typeText("و")
        }

        view.findViewById<Button>(R.id.key_ye).setOnClickListener {
            typeText("ي")
        }

        view.findViewById<Button>(R.id.key_space).setOnClickListener {
            typeText(" ")
        }

        view.findViewById<Button>(R.id.key_backspace).setOnClickListener {
            deleteText()
        }

        view.findViewById<Button>(R.id.key_enter).setOnClickListener {
            currentInputConnection?.sendKeyEvent(
                android.view.KeyEvent(
                    android.view.KeyEvent.ACTION_DOWN,
                    android.view.KeyEvent.KEYCODE_ENTER
                )
            )
        }

        view.findViewById<Button>(R.id.key_shift).setOnClickListener {
            Toast.makeText(
                this,
                "Shift test",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun typeText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    private fun deleteText() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }
}
