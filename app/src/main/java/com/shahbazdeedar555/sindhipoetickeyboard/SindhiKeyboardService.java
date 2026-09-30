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

        // Row 1
        setKey(keyboard, R.id.key_alif, "ا");
        setKey(keyboard, R.id.key_bay, "ب");
        setKey(keyboard, R.id.key_bay2, "ٻ");
        setKey(keyboard, R.id.key_pay, "پ");
        setKey(keyboard, R.id.key_bhe, "ڀ");
        setKey(keyboard, R.id.key_te, "ت");
        setKey(keyboard, R.id.key_the, "ٿ");
        setKey(keyboard, R.id.key_tt, "ٽ");

        // Row 2
        setKey(keyboard, R.id.key_say, "ث");
        setKey(keyboard, R.id.key_fay, "ف");
        setKey(keyboard, R.id.key_fhay, "ڦ");
        setKey(keyboard, R.id.key_gaf, "گ");
        setKey(keyboard, R.id.key_gaf2, "ڳ");
        setKey(keyboard, R.id.key_gn, "ڱ");
        setKey(keyboard, R.id.key_kaf, "ک");
        setKey(keyboard, R.id.key_yay, "ي");

        // Row 3
        setKey(keyboard, R.id.key_dal, "د");
        setKey(keyboard, R.id.key_dhal, "ذ");
        setKey(keyboard, R.id.key_dhad, "ڌ");
        setKey(keyboard, R.id.key_dde, "ڏ");
        setKey(keyboard, R.id.key_dd, "ڊ");
        setKey(keyboard, R.id.key_ddh, "ڍ");
        setKey(keyboard, R.id.key_hay, "ح");
        setKey(keyboard, R.id.key_jeem, "ج");

        // Row 4
        setKey(keyboard, R.id.key_jay, "ڄ");
        setKey(keyboard, R.id.key_nje, "ڃ");
        setKey(keyboard, R.id.key_chay, "چ");
        setKey(keyboard, R.id.key_chhe, "ڇ");
        setKey(keyboard, R.id.key_khay, "خ");
        setKey(keyboard, R.id.key_ain, "ع");
        setKey(keyboard, R.id.key_ghain, "غ");
        setKey(keyboard, R.id.key_ray, "ر");

        // Row 5
        setKey(keyboard, R.id.key_rre, "ڙ");
        setKey(keyboard, R.id.key_meem, "م");
        setKey(keyboard, R.id.key_nun, "ن");
        setKey(keyboard, R.id.key_lam, "ل");
        setKey(keyboard, R.id.key_sin, "س");
        setKey(keyboard, R.id.key_sheen, "ش");
        setKey(keyboard, R.id.key_sad, "ص");
        setKey(keyboard, R.id.key_dad, "ض");

        // Row 6
        setKey(keyboard, R.id.key_tay, "ط");
        setKey(keyboard, R.id.key_zay, "ظ");
        setKey(keyboard, R.id.key_nnoon, "ڻ");
        setKey(keyboard, R.id.key_waw, "و");
        setKey(keyboard, R.id.key_hay2, "ھ");
        setKey(keyboard, R.id.key_jhay, "جھ");
        setKey(keyboard, R.id.key_kay, "ڪ");
        setKey(keyboard, R.id.key_ghay, "گھ");

        // Row 7
        setKey(keyboard, R.id.key_hamza, "ء");
        setKey(keyboard, R.id.key_he, "ه");
        setKey(keyboard, R.id.key_ya, "ى");
        setKey(keyboard, R.id.key_yeh, "ئ");
        setKey(keyboard, R.id.key_waw2, "ؤ");
        setKey(keyboard, R.id.key_zhay, "ژ");
        setKey(keyboard, R.id.key_yay2, "ے");

        // Controls
        setDeleteKey(keyboard, R.id.key_delete);
        setSpaceKey(keyboard, R.id.key_space);
        setEnterKey(keyboard, R.id.key_enter);

        return keyboard;
    }

    private void setKey(View keyboard, int id, final String text) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setEnabled(true);
        button.setClickable(true);

        button.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {
                getCurrentInputConnection().commitText(text, 1);
            }

        });
    }

    private void setDeleteKey(View keyboard, int id) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setEnabled(true);
        button.setClickable(true);

        button.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {
                getCurrentInputConnection().deleteSurroundingText(1, 0);
            }

        });
    }

    private void setSpaceKey(View keyboard, int id) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setEnabled(true);
        button.setClickable(true);

        button.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {
                getCurrentInputConnection().commitText(" ", 1);
            }

        });
    }

    private void setEnterKey(View keyboard, int id) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setEnabled(true);
        button.setClickable(true);

        button.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {
                getCurrentInputConnection().sendKeyEvent(
                        new android.view.KeyEvent(
                                android.view.KeyEvent.ACTION_DOWN,
                                android.view.KeyEvent.KEYCODE_ENTER
                        )
                );

                getCurrentInputConnection().sendKeyEvent(
                        new android.view.KeyEvent(
                                android.view.KeyEvent.ACTION_UP,
                                android.view.KeyEvent.KEYCODE_ENTER
                        )
                );
            }

        });
    }
}
