

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

        // Sindhi letters
        setKey(keyboard, R.id.key_alif, "ا");
        setKey(keyboard, R.id.key_bay, "ب");
        setKey(keyboard, R.id.key_pay, "پ");
        setKey(keyboard, R.id.key_tay, "ت");
        setKey(keyboard, R.id.key_ttay, "ٽ");
        setKey(keyboard, R.id.key_thay, "ث");
        setKey(keyboard, R.id.key_jeem, "ج");
        setKey(keyboard, R.id.key_chay, "چ");
        setKey(keyboard, R.id.key_jhay, "ڄ");
        setKey(keyboard, R.id.key_jay, "ج");
        setKey(keyboard, R.id.key_khay, "خ");
        setKey(keyboard, R.id.key_daal, "د");
        setKey(keyboard, R.id.key_ddaal, "ڊ");
        setKey(keyboard, R.id.key_zaal, "ذ");
        setKey(keyboard, R.id.key_ray, "ر");
        setKey(keyboard, R.id.key_rr, "ڙ");
        setKey(keyboard, R.id.key_zay, "ز");
        setKey(keyboard, R.id.key_seen, "س");
        setKey(keyboard, R.id.key_sheen, "ش");
        setKey(keyboard, R.id.key_saad, "ص");
        setKey(keyboard, R.id.key_zaad, "ض");
        setKey(keyboard, R.id.key_toay, "ط");
        setKey(keyboard, R.id.key_zoay, "ظ");
        setKey(keyboard, R.id.key_ain, "ع");
        setKey(keyboard, R.id.key_ghain, "غ");
        setKey(keyboard, R.id.key_fay, "ف");
        setKey(keyboard, R.id.key_qaaf, "ق");
        setKey(keyboard, R.id.key_kaaf, "ڪ");
        setKey(keyboard, R.id.key_gaaf, "گ");
        setKey(keyboard, R.id.key_lam, "ل");
        setKey(keyboard, R.id.key_meem, "م");
        setKey(keyboard, R.id.key_noon, "ن");
        setKey(keyboard, R.id.key_waw, "و");
        setKey(keyboard, R.id.key_hay, "ه");
        setKey(keyboard, R.id.key_ye, "ي");
        setKey(keyboard, R.id.key_yay, "ي");
        setKey(keyboard, R.id.key_ڃ, "ڃ");
        setKey(keyboard, R.id.key_ڦ, "ڦ");

        // Space
        Button space = keyboard.findViewById(R.id.key_space);
        if (space != null) {
            space.setOnClickListener(v ->
                    getCurrentInputConnection().commitText(" ", 1)
            );
        }

        // Delete
        Button delete = keyboard.findViewById(R.id.key_delete);
        if (delete != null) {
            delete.setOnClickListener(v ->
                    getCurrentInputConnection().deleteSurroundingText(1, 0)
            );
        }

        // Enter
        Button enter = keyboard.findViewById(R.id.key_enter);
        if (enter != null) {
            enter.setOnClickListener(v ->
                    getCurrentInputConnection().sendKeyEvent(
                            new android.view.KeyEvent(
                                    android.view.KeyEvent.ACTION_DOWN,
                                    android.view.KeyEvent.KEYCODE_ENTER
                            )
                    )
            );
        }

        return keyboard;
    }

    private void setKey(View keyboard, int id, String text) {

        Button key = keyboard.findViewById(id);

        if (key != null) {
            key.setOnClickListener(v ->
                    getCurrentInputConnection().commitText(text, 1)
            );
        }
    }
}
