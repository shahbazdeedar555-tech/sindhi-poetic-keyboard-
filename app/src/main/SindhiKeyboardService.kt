
package com.shahbazdeedar555.sindhipoetickeyboard
import android.inputmethodservice.InputMethodService import android.view.KeyEvent import android.view.View import android.widget.Button import android.widget.Toast
class SindhiKeyboardService : InputMethodService() {
private var keyboardView: View? = null

override fun onCreateInputView(): View {

    keyboardView = layoutInflater.inflate(
        R.layout.keyboard_view,
        null
    )

    keyboardView?.visibility = View.VISIBLE
    keyboardView?.alpha = 1.0f

    setupKeys()

    Toast.makeText(
        this,
        "Keyboard View Loaded",
        Toast.LENGTH_SHORT
    ).show()

    return keyboardView!!
}

override fun onStartInputView(
    info: android.view.inputmethod.EditorInfo?,
    restarting: Boolean
) {
    super.onStartInputView(info, restarting)

    keyboardView?.visibility = View.VISIBLE
    keyboardView?.alpha = 1.0f
}

override fun onEvaluateInputViewShown(): Boolean {
    return true
}

private fun setupKeys() {

    val view = keyboardView ?: return

    setKey(view, R.id.key_alif, "ا")
    setKey(view, R.id.key_bay, "ب")
    setKey(view, R.id.key_pay, "پ")
    setKey(view, R.id.key_tay, "ت")

    setKey(view, R.id.key_jeem, "ج")
    setKey(view, R.id.key_chay, "چ")
    setKey(view, R.id.key_daal, "د")
    setKey(view, R.id.key_raa, "ر")

    setKey(view, R.id.key_seen, "س")
    setKey(view, R.id.key_sheen, "ش")
    setKey(view, R.id.key_kaaf, "ڪ")
    setKey(view, R.id.key_lam, "ل")

    setKey(view, R.id.key_meem, "م")
    setKey(view, R.id.key_noon, "ن")
    setKey(view, R.id.key_wao, "و")
    setKey(view, R.id.key_ye, "ي")

    setKey(view, R.id.key_space, " ")

    view.findViewById<Button>(R.id.key_backspace)?.setOnClickListener {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }

    view.findViewById<Button>(R.id.key_enter)?.setOnClickListener {
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

    view.findViewById<Button>(R.id.key_shift)?.setOnClickListener {
        Toast.makeText(
            this,
            "Shift test",
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun setKey(
    view: View,
    id: Int,
    text: String
) {
    view.findViewById<Button>(id)?.setOnClickListener {
        currentInputConnection?.commitText(text, 1)
    }
}

