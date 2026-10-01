package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {

        View keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        // =========================
        // PAGE 1 — EXACT XML ORDER
        // =========================

        setKey(keyboard, R.id.key_alif, "چ");
        setKey(keyboard, R.id.key_bay, "ڇ");
        setKey(keyboard, R.id.key_bay2, "پ");
        setKey(keyboard, R.id.key_pay, "و");
        setKey(keyboard, R.id.key_bhe, "ڳ");
        setKey(keyboard, R.id.key_te, "ع");
        setKey(keyboard, R.id.key_the, "ٿ");
        setKey(keyboard, R.id.key_tt, "ت");

        setKey(keyboard, R.id.key_say, "ر");
        setKey(keyboard, R.id.key_fay, "ي");
        setKey(keyboard, R.id.key_fhay, "ص");
        setKey(keyboard, R.id.key_gaf, "ق");
        setKey(keyboard, R.id.key_gaf2, "ڍ");
        setKey(keyboard, R.id.key_gn, "ڱ");
        setKey(keyboard, R.id.key_kaf, "ک");
        setKey(keyboard, R.id.key_yay, "ل");

        setKey(keyboard, R.id.key_dal, "ڪ");
        setKey(keyboard, R.id.key_dhal, "ج");
        setKey(keyboard, R.id.key_dhad, "ه");
        setKey(keyboard, R.id.key_dde, "گ");
        setKey(keyboard, R.id.key_dd, "ف");
        setKey(keyboard, R.id.key_ddh, "د");
        setKey(keyboard, R.id.key_hay, "س");
        setKey(keyboard, R.id.key_jeem, "ا");

        setKey(keyboard, R.id.key_jay, "ئ");
        setKey(keyboard, R.id.key_nje, "م");
        setKey(keyboard, R.id.key_chay, "ن");
        setKey(keyboard, R.id.key_chhe, "ب");
        setKey(keyboard, R.id.key_khay, "ڀ");
        setKey(keyboard, R.id.key_ain, "ط");
        setKey(keyboard, R.id.key_ghain, "خ");
        setKey(keyboard, R.id.key_ray, "ز");


        // =========================
        // PAGE 2 — SHIFT
        // =========================

        setKey(keyboard, R.id.key_rre, "ڄ");
        setKey(keyboard, R.id.key_meem, "ڃ");
        setKey(keyboard, R.id.key_nun, "ڦ");
        setKey(keyboard, R.id.key_lam, "ھ");
        setKey(keyboard, R.id.key_sin, "غ");
        setKey(keyboard, R.id.key_sheen, "ث");
        setKey(keyboard, R.id.key_sad, "ٽ");
        setKey(keyboard, R.id.key_dad, "ڙ");

        setKey(keyboard, R.id.key_tay, "ض");
        setKey(keyboard, R.id.key_zay, "ٺ");
        setKey(keyboard, R.id.key_nnoon, "ڌ");
        setKey(keyboard, R.id.key_waw, "ڏ");
        setKey(keyboard, R.id.key_hay2, "۽");
        setKey(keyboard, R.id.key_jhay, "ح");
        setKey(keyboard, R.id.key_kay, "ڦ");
        setKey(keyboard, R.id.key_ghay, "ڊ");

        setKey(keyboard, R.id.key_hamza, "ش");
        setKey(keyboard, R.id.key_he, "آ");
        setKey(keyboard, R.id.key_ya, "۾");
        setKey(keyboard, R.id.key_yeh, "ڻ");
        setKey(keyboard, R.id.key_waw2, "ٻ");
        setKey(keyboard, R.id.key_zhay, "ء");
        setKey(keyboard, R.id.key_yay2, "ظ");
        setKey(keyboard, R.id.key_shift_extra, "ذ");


        // =========================
        // SPECIAL MARKS
        // =========================

        setKey(keyboard, R.id.key_mark_1, "ـ");
        setKey(keyboard, R.id.key_mark_2, "َ");
        setKey(keyboard, R.id.key_mark_3, "ِ");
        setKey(keyboard, R.id.key_mark_4, "ُ");
        setKey(keyboard, R.id.key_mark_5, "ْ");
        setKey(keyboard, R.id.key_mark_6, "ّ");
        setKey(keyboard, R.id.key_mark_7, "ٰ");
        setKey(keyboard, R.id.key_mark_8, "آ");
        setKey(keyboard, R.id.key_mark_9, "ي");
        setKey(keyboard, R.id.key_mark_10, "ئ");
        setKey(keyboard, R.id.key_mark_11, "ى");


        // =========================
        // CONTROLS
        // =========================

        setDeleteKey(keyboard, R.id.key_delete);
        setSpaceKey(keyboard, R.id.key_space);
        setEnterKey(keyboard, R.id.key_enter);

        // SHIFT
        Button shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {
            shift.setEnabled(true);
            shift.setClickable(true);

            shift.setOnClickListener(v -> {

                View page1 = keyboard.findViewById(R.id.keyboard_page1);
                View page2 = keyboard.findViewById(R.id.keyboard_page2);

                if (page1 != null && page2 != null) {

                    if (page1.getVisibility() == View.VISIBLE) {
                        page1.setVisibility(View.GONE);
                        page2.setVisibility(View.VISIBLE);
                    } else {
                        page1.setVisibility(View.VISIBLE);
                        page2.setVisibility(View.GONE);
                    }
                }
            });
        }

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
                        new KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_ENTER
                        )
                );

                getCurrentInputConnection().sendKeyEvent(
                        new KeyEvent(
                                KeyEvent.ACTION_UP,
                                KeyEvent.KEYCODE_ENTER
                        )
                );
            }
        });
    }
}
