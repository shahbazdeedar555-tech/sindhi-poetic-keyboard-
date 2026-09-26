package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class SindhiKeyboardService : InputMethodService() {

    private var keyboardView: View? = null

    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null)
        setupKeys(keyboardView!!)
        return keyboardView!!
    }

    private fun setupKeys(view: View) {
        val buttons = findButtons(view)

        for (button in buttons) {
            button.setOnClickListener {
                val tag = button.tag as? String
                val text = button.text.toString()

                when (tag) {
                    "BACKSPACE" -> {
                        currentInputConnection?.deleteSurroundingText(1, 0)
                    }
                    "SPACE" -> {
                        currentInputConnection?.commitText(" ", 1)
                    }
                    "ENTER" -> {
                        // Preferred way for most editors
                        currentInputConnection?.commitText("\n", 1)
                        // Alternative (uncomment if you prefer key event):
                        // currentInputConnection?.sendKeyEvent(
                        //     KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
                        // )
                    }
                    "SHIFT" -> {
                        // TODO: implement shift / capital mode later
                        Toast.makeText(this, "Shift (coming soon)", Toast.LENGTH_SHORT).show()
                    }
                    "NUMBERS" -> {
                        // TODO: switch to number/symbol layout later
                        Toast.makeText(this, "Numbers layout (coming soon)", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Normal letter / punctuation key
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

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                result.addAll(findButtons(view.getChildAt(i)))
            }
        }
        return result
    }

    override fun onStartInputView(info: android.view.inputmethod.EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        // Optional: remove this Toast in production
        // Toast.makeText(this, "Sindhi Keyboard Loaded", Toast.LENGTH_SHORT).show()
    }
}
