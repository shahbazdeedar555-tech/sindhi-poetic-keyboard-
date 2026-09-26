package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
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

        // Make sure the keyboard itself is visible
        view.visibility = View.VISIBLE

        setupKeyboard(view)

        return view
    }

    private fun setupKeyboard(view: View) {

        // =========================
        // Sindhi letters
        // =========================

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

        // Second Qaf button in XML
        setKey(view, R.id.key_qaf2, "ق")

        setKey(view, R.id.key_kaf, "ڪ")
        setKey(view, R.id.key_gaf, "گ")
        setKey(view, R.id.key_lam, "ل")
        setKey(view, R.id.key_meem, "م")
        setKey(view, R.id.key_noon, "ن")

        setKey(view, R.id.key_waw, "و")

        // Second Waw button in XML
        setKey(view, R.id.key_waw2, "و")

        setKey(view, R.id.key_hay2, "ه")
        setKey(view, R.id.key_yay, "ي")

        // Second Yay button in XML
        setKey(view, R.id.key_yay2, "ي")

        setKey(view, R.id.key_ye, "ے")


        // =========================
        // Space
        // =========================

        findButton(view, R.id.key_space)?.setOnClickListener {
            commitText(" ")
        }


        // =========================
        // Backspace
        // =========================

        findButton(view, R.id.key_backspace)?.setOnClickListener {
            deleteText()
        }


        // =========================
        // Shift
        // =========================

        findButton(view, R.id.key_shift)?.setOnClickListener {
            commitText("آ")
        }


        // =========================
        // Numbers
        // =========================

        findButton(view, R.id.key_numbers)?.setOnClickListener {
            commitText("1234567890")
        }


        // =========================
        // Enter
        // =========================

        findButton(view, R.id.key_enter)?.setOnClickListener {
            sendEnter()
        }


        // =========================
        // Punctuation
        // =========================

        findButton(view, R.id.key_comma)?.setOnClickListener {
            commitText("،")
        }

        findButton(view, R.id.key_dot)?.setOnClickListener {
            commitText(".")
        }

        findButton(view, R.id.key_question)?.setOnClickListener {
            commitText("?")
        }

        findButton(view, R.id.key_exclamation)?.setOnClickListener {
            commitText("!")
        }
    }


    // =========================
    // Normal character key
    // =========================

    private fun setKey(
        view: View,
        id: Int,
        character: String
    ) {
        val button = findButton(view, id) ?: return

        button.text = character
        button.visibility = View.VISIBLE

        button.setOnClickListener {
            commitText(character)
        }
    }


    // =========================
    // Find button
    // =========================

    private fun findButton(
        view: View,
        id: Int
    ): Button? {
        return view.findViewById(id)
    }


    // =========================
    // Type text
    // =========================

    private fun commitText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }


    // =========================
    // Delete
    // =========================

    private fun deleteText() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }


    // =========================
    // Enter
    // =========================

    private fun sendEnter() {

        currentInputConnection?.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_ENTER
            )
        )

        currentInputConnection?.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_ENTER
            )
        )
    }


    // =========================
    // Destroy keyboard view
    // =========================

    override fun onDestroyInputView() {
        keyboardView = null
        super.onDestroyInputView()
    }
}
