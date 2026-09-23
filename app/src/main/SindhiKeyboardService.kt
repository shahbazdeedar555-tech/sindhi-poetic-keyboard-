package com.shahbazdeedar555.sindhipoetickeyboard

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

class SindhiKeyboardService : InputMethodService() {

    private lateinit var webView: WebView

    override fun onCreateInputView(): View {

        webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.setBackgroundColor(Color.WHITE)

        webView.webViewClient = WebViewClient()

        webView.addJavascriptInterface(
            KeyboardBridge(),
            "AndroidKeyboard"
        )

        webView.loadUrl("file:///android_asset/index.html")

        return webView
    }

    inner class KeyboardBridge {

        @JavascriptInterface
        fun typeText(text: String) {
            currentInputConnection?.commitText(text, 1)
        }

        @JavascriptInterface
        fun deleteText() {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }

        @JavascriptInterface
        fun sendEnter() {
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
    }
}
