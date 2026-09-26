package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(Color.LTGRAY)

        val testButton = Button(this)
        testButton.text = "TEST سنڌي"
        testButton.textSize = 24f

        testButton.setOnClickListener {
            currentInputConnection?.commitText("سنڌي", 1)
        }

        layout.addView(
            testButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                120
            )
        )

        return layout
    }
}
