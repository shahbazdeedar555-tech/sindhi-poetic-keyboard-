
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

        // سنڌي اکر
        setKey(keyboard, R.id.key_alif, "ا");
        setKey(keyboard, R.id.key_bay, "ب");
        setKey(keyboard, R.id.key_pay, "ٻ");

        setKey(keyboard, R.id.key_pe, "پ");
        setKey(keyboard, R.id.key_bhe, "ڀ");
        setKey(keyboard, R.id.key_te, "ت");
        setKey(keyboard, R.id.key_tte, "ٽ");
        setKey(keyboard, R.id.key_the, "ٿ");
        setKey(keyboard, R.id.key_tthe, "ٺ");
        setKey(keyboard, R.id.key_se, "ث");

        setKey(keyboard, R.id.key_jeem, "ج");
        setKey(keyboard, R.id.key_jjeem, "ڄ");
        setKey(keyboard, R.id.key_jje, "ڃ");
        setKey(keyboard, R.id.key_che, "چ");
        setKey(keyboard, R.id.key_chhe, "ڇ");

        setKey(keyboard, R.id.key_he, "ح");
        setKey(keyboard, R.id.key_khe, "خ");

        setKey(keyboard, R.id.key_dal, "د");
        setKey(keyboard, R.id.key_dhal, "ڌ");
        setKey(keyboard, R.id.key_ddal, "ڏ");
        setKey(keyboard, R.id.key_dde, "ڊ");
        setKey(keyboard, R.id.key_ddeh, "ڍ");
        setKey(keyboard, R.id.key_zaal, "ذ");

        setKey(keyboard, R.id.key_re, "ر");
        setKey(keyboard, R.id.key_rre, "ڙ");

        setKey(keyboard, R.id.key_zay, "ز");
        setKey(keyboard, R.id.key_ze, "ژ");

        setKey(keyboard, R.id.key_seen, "س");
        setKey(keyboard, R.id.key_sheen, "ش");

        setKey(keyboard, R.id.key_sad, "ص");
        setKey(keyboard, R.id.key_zad, "ض");

        setKey(keyboard, R.id.key_to, "ط");
        setKey(keyboard, R.id.key_zo, "ظ");

        setKey(keyboard, R.id.key_ain, "ع");
        setKey(keyboard, R.id.key_ghain, "غ");

        setKey(keyboard, R.id.key_fa, "ف");
        setKey(keyboard, R.id.key_fhe, "ڦ");

        setKey(keyboard, R.id.key_qaf, "ق");
        setKey(keyboard, R.id.key_kaf, "ڪ");
        setKey(keyboard, R.id.key_gaf, "گ");
        setKey(keyboard, R.id.key_ghaf, "ڳ");
        setKey(keyboard, R.id.key_ng, "ڱ");

        setKey(keyboard, R.id.key_lam, "ل");
        setKey(keyboard, R.id.key_meem, "م");
        setKey(keyboard, R.id.key_noon, "ن");
        setKey(keyboard, R.id.key_noon_gunna, "ڻ");

        setKey(keyboard, R.id.key_waw, "و");
        setKey(keyboard, R.id.key_he2, "ه");
        setKey(keyboard, R.id.key_ye, "ي");
        setKey(keyboard, R.id.key_ya, "ء");

        // خاص سنڌي لفظي نشان
        setKey(keyboard, R.id.key_special_1, "ـ");
        setKey(keyboard, R.id.key_special_2, "َ");
        setKey(keyboard, R.id.key_special_3, "ِ");
        setKey(keyboard, R.id.key_special_4, "ُ");
        setKey(keyboard, R.id.key_special_5, "ْ");
        setKey(keyboard, R.id.key_special_6, "ّ");
        setKey(keyboard, R.id.key_special_7, "ٰ");

        return keyboard;
    }

    private void setKey(View keyboard, int id, final String text) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setText(text);
        button.setEnabled(true);
        button.setClickable(true);

        button.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {
                getCurrentInputConnection().commitText(text, 1);
            }

        });
    }
}
