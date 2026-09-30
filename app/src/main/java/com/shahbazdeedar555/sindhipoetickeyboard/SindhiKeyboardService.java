package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {

        View keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setKey(keyboard, R.id.key_alif, "ا");
        setKey(keyboard, R.id.key_bay, "ب");
        setKey(keyboard, R.id.key_pay, "پ");

        return keyboard;
    }

    private void setKey(View keyboard, int id, final String text) {

        Button button = keyboard.findViewById(id);

        if (button != null) {
            button.setText(text);

            button.setOnClickListener(v -> {
                getCurrentInputConnection().commitText(text, 1);
            });
        }
    }
}
