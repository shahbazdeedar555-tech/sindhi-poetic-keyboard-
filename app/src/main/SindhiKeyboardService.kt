package com.shahbazdeedar555.sindhipoetickeyboard

import android.inputmethodservice.InputMethodService
import android.widget.TextView

class SindhiKeyboardService : InputMethodService() {

    override fun onCreateInputView(): android.view.View {
        val text = TextView(this)

        text.text = "SINDHI KEYBOARD SERVICE IS RUNNING"
        text.textSize = 24f
        text.setPadding(30, 30, 30, 30)

        return text
    }
}
