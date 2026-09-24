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

        val keys = mapOf(
            R.id.key_alif to "ا",
            R.id.key_bay to "ب",
            R.id.key_pay to "پ",
            R.id.key_tay to "ت",
            R.id.key_ttay to "ٽ",

            R.id.key_jim to "ج",
            R.id.key_chay to "چ",
            R.id.key_hay to "ح",
            R.id.key_khay to "خ",
            R.id.key_dal to "د",

            R.id.key_rray to "ر",
            R.id.key_rray2 to "ڙ",
            R.id.key_sin to "س",
            R.id.key_shin to "ش",
            R.id.key_swad to "ص",

            R.id.key_tuay to "ط",
            R.id.key_ain to "ع",
            R.id.key_ghain to "غ",
            R.id.key_fay to "ف",
            R.id.key_qaf to "ق",

            R.id.key_kaf to "ڪ",
            R.id.key_gaf to "گ",
            R.id.key_lam to "ل",
            R.id.key_meem to "م",
            R.id.key_noon to "ن",

            R.id.key_waw to "و",
            R.id.key_hay2 to "ه",
            R.id.key_yay to "ي",
            R.id.key_ye to "ے"
        )

        for ((id, character) in keys) {

            val button = keyboardView.findViewById<Button>(id)

            button.setOnClickListener {
                currentInputConnection?.commitText(
                    character,
                    1
                )
            }
        }

        val spaceButton = keyboardView.findViewById<Button>(
            R.id.key_space
        )

        spaceButton.setOnClickListener {
            currentInputConnection?.commitText(
                " ",
                1
            )
        }

        val deleteButton = keyboardView.findViewById<Button>(
            R.id.key_delete
        )

        deleteButton.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(
                1,
                0
            )
        }

        return keyboardView
    }
}
