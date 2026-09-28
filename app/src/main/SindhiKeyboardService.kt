package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val keyboard = LinearLayout(this)
        keyboard.orientation = LinearLayout.VERTICAL
        keyboard.setBackgroundColor(Color.rgb(23, 63, 53))
        keyboard.setPadding(4, 4, 4, 4)

        addRow(
            keyboard,
            listOf("ا", "ب", "پ", "ت", "ٽ", "ث", "ج", "ڄ")
        )

        addRow(
            keyboard,
            listOf("چ", "ڇ", "د", "ڊ", "ذ", "ر", "ڙ", "ز")
        )

        addRow(
            keyboard,
            listOf("س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ")
        )

        addRow(
            keyboard,
            listOf("ف", "ق", "ڪ", "ک", "گ", "ڳ", "ل", "م")
        )

        addRow(
            keyboard,
            listOf("ن", "ڻ", "و", "ه", "ء", "ي", "ٻ", "ڀ")
        )

        addRow(
            keyboard,
            listOf("ڦ", "ـ", "َ", "ِ", "ُ", "ْ", "ّ", "ٰ")
        )

        val bottomRow = LinearLayout(this)
        bottomRow.orientation = LinearLayout.HORIZONTAL
        bottomRow.gravity = Gravity.CENTER

        addKey(bottomRow, "SPACE", 4f) {
            currentInputConnection?.commitText(" ", 1)
        }

        addKey(bottomRow, "⌫", 1f) {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }

        addKey(bottomRow, "ENTER", 2f) {
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

        keyboard.addView(
            bottomRow,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                60
            )
        )

        return keyboard
    }

    private fun addRow(
        keyboard: LinearLayout,
        letters: List<String>
    ) {
        val row = LinearLayout(this)
        row.orientation = LinearLayout.HORIZONTAL

        for (letter in letters) {
            addKey(row, letter, 1f) {
                currentInputConnection?.commitText(letter, 1)
            }
        }

        keyboard.addView(
            row,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                60
            )
        )
    }

    private fun addKey(
        row: LinearLayout,
        text: String,
        weight: Float,
        action: () -> Unit
    ) {
        val button = Button(this)

        button.text = text
        button.textSize = 20f
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(Color.rgb(50, 95, 82))
        button.setOnClickListener {
            action()
        }

        val params = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.MATCH_PARENT,
            weight
        )

        params.setMargins(2, 2, 2, 2)

        row.addView(button, params)
    }
}
