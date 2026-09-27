package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val view = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        connectKey(view, R.id.key_alif, "ا")
        connectKey(view, R.id.key_bay, "ب")
        connectKey(view, R.id.key_pay, "پ")
        connectKey(view, R.id.key_tay, "ت")

        connectKey(view, R.id.key_jeem, "ج")
        connectKey(view, R.id.key_chay, "چ")
        connectKey(view, R.id.key_daal, "د")
        connectKey(view, R.id.key_raa, "ر")

        connectKey(view, R.id.key_seen, "س")
        connectKey(view, R.id.key_sheen, "ش")
        connectKey(view, R.id.key_kaaf, "ڪ")
        connectKey(view, R.id.key_lam, "ل")

        connectKey(view, R.id.key_meem, "م")
        connectKey(view, R.id.key_noon, "ن")
        connectKey(view, R.id.key_wao, "و")
        connectKey(view, R.id.key_ye, "ي")

        view.findViewById<Button>(R.id.key_space).setOnClickListener {
            currentInputConnection?.commitText(" ", 1)
        }

        view.findViewById<Button>(R.id.key_backspace).setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1, 0)
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
                "Shift ready",
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }

    private fun connectKey(
        view: View,
        id: Int,
        text: String
    ) {
        view.findViewById<Button>(id).setOnClickListener {
            currentInputConnection?.commitText(text, 1)
        }
    }
}
