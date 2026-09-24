package com.shahbazdeedar555.sindhipoetickeyboard

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

        view.findViewById<Button>(R.id.key_space)?.setOnClickListener {
            commitText(" ")
        }

        view.findViewById<Button>(R.id.key_delete)?.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }

        // Shift ۽ 123 هن مرحلي ۾ صرف visual buttons آهن.
        // انهن کي پوءِ variants ۽ numbers سان connect ڪنداسين.
    }

    private fun setKey(
        view: View,
        id: Int,
        character: String
    ) {
        view.findViewById<Button>(id)?.setOnClickListener {
            commitText(character)
        }
    }

    private fun commitText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }

    override fun onDestroyInputView() {
        keyboardView = null
        super.onDestroyInputView()
    }

    override fun onDestroy() {
        keyboardView = null
        super.onDestroy()
    }
}
