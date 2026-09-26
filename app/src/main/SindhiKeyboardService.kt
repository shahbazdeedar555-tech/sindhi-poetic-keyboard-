package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val keyboardView = layoutInflater.inflate(
            R.layout.keyboard_view,
            null
        )

        val buttons = findButtons(keyboardView)

        for (button in buttons) {

            button.setOnClickListener {

                val text = button.text.toString()
                val tag = button.tag?.toString()?.uppercase()

                when (tag) {

                    "BACKSPACE" -> {
                        currentInputConnection?.deleteSurroundingText(1, 0)
                    }

                    "ENTER" -> {
                        currentInputConnection?.commitText("\n", 1)
                    }

                    "SPACE" -> {
                        currentInputConnection?.commitText(" ", 1)
                    }

                    "SHIFT" -> {
                        Toast.makeText(
                            this,
                            "Shift",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    "NUMBERS" -> {
                        Toast.makeText(
                            this,
                            "123",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    "CLEAR" -> {
                        currentInputConnection?.deleteSurroundingText(1000, 0)
                    }

                    else -> {
                        if (text.isNotEmpty()) {
                            currentInputConnection?.commitText(text, 1)
                        }
                    }
                }
            }
        }

        Toast.makeText(
            this,
            "سنڌي ڪي بورڊ تيار آهي",
            Toast.LENGTH_SHORT
        ).show()

        return keyboardView
    }

    private fun findButtons(view: View): List<Button> {

        val result = mutableListOf<Button>()

        if (view is Button) {
            result.add(view)
        }

        if (view is android.view.ViewGroup) {
            for (i in 0 until view.childCount) {
                result.addAll(
                    findButtons(view.getChildAt(i))
                )
            }
        }

        return result
    }
}
