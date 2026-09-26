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

        setupKeys(keyboardView!!)

        return keyboardView!!
    }

    private fun setupKeys(view: View) {

        val buttons = findButtons(view)

        for (button in buttons) {

            button.setOnClickListener {

                val text = button.text.toString()

                when (text) {

                    "⌫", "Backspace" -> {
                        currentInputConnection?.deleteSurroundingText(1, 0)
                    }

                    "Space" -> {
                        currentInputConnection?.commitText(" ", 1)
                    }

                    "Enter", "↵" -> {
                        currentInputConnection?.sendKeyEvent(
                            android.view.KeyEvent(
                                android.view.KeyEvent.ACTION_DOWN,
                                android.view.KeyEvent.KEYCODE_ENTER
                            )
                        )
                    }

                    else -> {
                        currentInputConnection?.commitText(text, 1)
                    }
                }
            }
        }
    }

    private fun findButtons(view: View): List<Button> {

        val result = mutableListOf<Button>()

        if (view is Button) {
            result.add(view)
        }

        if (view is android.view.ViewGroup) {

            for (i in 0 until view.childCount) {
                result.addAll(findButtons(view.getChildAt(i)))
            }
        }

        return result
    }

    override fun onStartInputView(
        info: android.view.inputmethod.EditorInfo?,
        restarting: Boolean
    ) {
        super.onStartInputView(info, restarting)

        Toast.makeText(
            this,
            "Sindhi Keyboard Loaded",
            Toast.LENGTH_SHORT
        ).show()
    }
}
