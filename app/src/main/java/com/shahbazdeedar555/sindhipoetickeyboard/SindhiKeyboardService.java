
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

    private void setKey(View keyboard, int id, String text) {

        Button key = keyboard.findViewById(id);

        if (key != null) {
            key.setOnClickListener(v -> {

                getCurrentInputConnection()
                        .commitText(text, 1);
            });
        }
    }
}
