package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button

class SindhiKeyboardService : InputMethodService() {

    private var keyboardView: View? = null

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        keyboardView = view

        setupKeys(view)

        return view
    }

    private fun setupKeys(view: View) {

        setKey(view, R.id.key_alif, "ا")
        setKey(view, R.id.key_bay, "ب")
        setKey(view, R.id.key_pay, "پ")
        setKey(view, R.id.key_tay, "ت")
        setKey(view, R.id.key_ttay, "ٽ")

        setKey(view, R.id.key_jim, "ج")
        setKey(view, R.id.key_chay, "چ")
        setKey(view, R.id.key_hay, "ح")
        setKey(view, R.id.key_khay, "خ")
        setKey(view, R.id.key_dal, "د")

        setKey(view, R.id.key_rray, "ر")
        setKey(view, R.id.key_rray2, "ڙ")
        setKey(view, R.id.key_sin, "س")
        setKey(view, R.id.key_shin, "ش")
        setKey(view, R.id.key_swad, "ص")

        setKey(view, R.id.key_tuay, "ط")
        setKey(view, R.id.key_ain, "ع")
        setKey(view, R.id.key_ghain, "غ")
        setKey(view, R.id.key_fay, "ف")
        setKey(view, R.id.key_qaf, "ق")

        setKey(view, R.id.key_kaf, "ڪ")
        setKey(view, R.id.key_gaf, "گ")
        setKey(view, R.id.key_lam, "ل")
        setKey(view, R.id.key_meem, "م")
        setKey(view, R.id.key_noon, "ن")

        setKey(view, R.id.key_waw, "و")
        setKey(view, R.id.key_hay2, "ه")
        setKey(view, R.id.key_yay, "ي")
        setKey(view, R.id.key_ye, "ے")

        findButton(view, R.id.key_space)?.setOnClickListener {
            commitText(" ")
        }

        findButton(view, R.id.key_delete)?.setOnClickListener {
            deleteText()
        }

        findButton(view, R.id.key_shift)?.setOnClickListener {
            commitText("آ")
        }

        findButton(view, R.id.key_numbers)?.setOnClickListener {
            commitText("1234567890")
        }
    }

    private fun setKey(
        view: View,
        id: Int,
        character: String
    ) {
        val button = findButton(view, id) ?: return

        button.text = character
        button.setTextColor(Color.BLACK)

        button.setOnClickListener {
            commitText(character)
        }
    }

    private fun findButton(
        view: View,
        id: Int
    ): Button? {
        return view.findViewById(id)
    }

    private fun commitText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    private fun deleteText() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }

    override fun onDestroyInputView() {
        keyboardView = null
        super.onDestroyInputView()
    }
}
