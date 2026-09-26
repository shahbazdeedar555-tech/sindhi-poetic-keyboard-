package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val keyboard = LinearLayout(this)
        keyboard.orientation = LinearLayout.VERTICAL
        keyboard.setPadding(8, 8, 8, 8)
        keyboard.setBackgroundColor(Color.LTGRAY)

        val row = LinearLayout(this)
        row.orientation = LinearLayout.HORIZONTAL
        row.gravity = Gravity.CENTER

        val testButton = Button(this)
        testButton.text = "سنڌي"
        testButton.textSize = 22f
        testButton.setTypeface(null, Typeface.BOLD)

        testButton.setOnClickListener {
            currentInputConnection?.commitText("سنڌي", 1)
        }

        val testButton2 = Button(this)
        testButton2.text = "اَ"
        testButton2.textSize = 22f

        testButton2.setOnClickListener {
            currentInputConnection?.commitText("اَحساس", 1)
        }

        val spaceButton = Button(this)
        spaceButton.text = "Space"
        spaceButton.setOnClickListener {
            currentInputConnection?.commitText(" ", 1)
        }

        row.addView(
            testButton,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        row.addView(
            testButton2,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        row.addView(
            spaceButton,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        keyboard.addView(
            row,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        return keyboard
    }

    override fun onStartInputView(
        info: android.view.inputmethod.EditorInfo?,
        restarting: Boolean
    ) {
        super.onStartInputView(info, restarting)
    }
}
