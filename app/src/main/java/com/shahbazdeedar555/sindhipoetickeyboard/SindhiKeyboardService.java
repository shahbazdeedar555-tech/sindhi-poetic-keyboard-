package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;
    private boolean shiftOn = false;

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupPage1();
        setupPage2();
        setupControls();

        showPage1();

        return keyboard;
    }

    // =================================================
    // PAGE 1
    // =================================================

    private void setupPage1() {

        setKey(R.id.key_ta1, "ت");
        setKey(R.id.key_th, "ٿ");
        setKey(R.id.key_ain, "ع");
        setKey(R.id.key_ghain, "ڳ");
        setKey(R.id.key_waw, "و");
        setKey(R.id.key_pay, "پ");
        setKey(R.id.key_chh, "ڇ");
        setKey(R.id.key_ch, "چ");
        setKey(R.id.key_jeem, "ج");
        setKey(R.id.key_kaf, "ڪ");

        setKey(R.id.key_lam, "ل");
        setKey(R.id.key_khay, "ک");
        setKey(R.id.key_ng, "ڱ");
        setKey(R.id.key_ddal, "ڍ");
        setKey(R.id.key_qaf, "ق");
        setKey(R.id.key_sad, "ص");
        setKey(R.id.key_yay, "ي");
        setKey(R.id.key_ray, "ر");
        setKey(R.id.key_alif, "ا");
        setKey(R.id.key_seen, "س");

        setKey(R.id.key_dal, "د");
        setKey(R.id.key_fay, "ف");
        setKey(R.id.key_gaf, "گ");
        setKey(R.id.key_hay, "ه");
        setKey(R.id.key_za, "ز");
        setKey(R.id.key_kha, "خ");
        setKey(R.id.key_ta2, "ط");
        setKey(R.id.key_bh, "ڀ");
        setKey(R.id.key_bay, "ب");
        setKey(R.id.key_noon, "ن");

        setKey(R.id.key_meem, "م");
        setKey(R.id.key_hamza_y, "ئ");
    }

    // =================================================
    // PAGE 2 / SHIFT
    // =================================================

    private void setupPage2() {

        setKey(R.id.s1, "ڄ");
        setKey(R.id.s2, "ڃ");
        setKey(R.id.s3, "ڦ");
        setKey(R.id.s4, "ھ");
        setKey(R.id.s5, "غ");
        setKey(R.id.s6, "ث");
        setKey(R.id.s7, "ٽ");
        setKey(R.id.s8, "ڙ");

        setKey(R.id.s9, "ض");
        setKey(R.id.s10, "ٺ");
        setKey(R.id.s11, "ڌ");
        setKey(R.id.s12, "ڏ");
        setKey(R.id.s13, "۽");
        setKey(R.id.s14, "ح");
        setKey(R.id.s15, "ڊ");
        setKey(R.id.s16, "ش");

        setKey(R.id.s17, "آ");
        setKey(R.id.s18, "۾");
        setKey(R.id.s19, "ڻ");
        setKey(R.id.s20, "ٻ");
        setKey(R.id.s21, "ء");
        setKey(R.id.s22, "ظ");
        setKey(R.id.s23, "ذ");
    }

    // =================================================
    // NORMAL KEY
    // =================================================

    private void setKey(int id, String text) {

        View view = keyboard.findViewById(id);

        if (view instanceof Button) {

            Button button = (Button) view;

            button.setOnClickListener(v -> commitText(text));
        }
    }

    // =================================================
    // CONTROLS
    // =================================================

    private void setupControls() {

        // SHIFT PAGE 1
        setClick(R.id.key_shift, v -> {

            shiftOn = true;
            showPage2();

        });

        // SHIFT PAGE 2
        setClick(R.id.key_shift2, v -> {

            shiftOn = false;
            showPage1();

        });

        // DELETE PAGE 1
        setClick(R.id.key_delete, v -> deleteOneCharacter());

        // DELETE PAGE 2
        setClick(R.id.key_delete2, v -> deleteOneCharacter());

        // ENTER PAGE 1
        setClick(R.id.key_enter, v -> pressEnter());

        // ENTER PAGE 2
        setClick(R.id.key_enter2, v -> pressEnter());

        // SPACE PAGE 1
        setClick(R.id.key_space, v -> commitText(" "));

        // SPACE PAGE 2
        setClick(R.id.key_space2, v -> commitText(" "));

        // SYMBOLS PAGE 1
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_semicolon, "؛");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_quote1, "“");
        setKey(R.id.key_quote2, "”");
        setKey(R.id.key_underscore, "_");
        setKey(R.id.key_exclamation, "!");
        setKey(R.id.key_ellipsis, "…");

        // SYMBOLS PAGE 2
        setKey(R.id.key_colon2, ":");
        setKey(R.id.key_semicolon2, "؛");
        setKey(R.id.key_question2, "؟");
        setKey(R.id.key_exclamation2, "!");
    }

    // =================================================
    // SIMPLE CLICK
    // =================================================

    private void setClick(int id, View.OnClickListener listener) {

        View view = keyboard.findViewById(id);

        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    // =================================================
    // PAGE 1
    // =================================================

    private void showPage1() {

        View page1 = keyboard.findViewById(
                R.id.keyboard_page1
        );

        View page2 = keyboard.findViewById(
                R.id.keyboard_page2
        );

        if (page1 != null) {
            page1.setVisibility(View.VISIBLE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }
    }

    // =================================================
    // PAGE 2
    // =================================================

    private void showPage2() {

        View page1 = keyboard.findViewById(
                R.id.keyboard_page1
        );

        View page2 = keyboard.findViewById(
                R.id.keyboard_page2
        );

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.VISIBLE);
        }
    }

    // =================================================
    // TYPE
    // =================================================

    private void commitText(String text) {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection().commitText(
                    text,
                    1
            );
        }
    }

    // =================================================
    // DELETE
    // =================================================

    private void deleteOneCharacter() {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection()
                    .deleteSurroundingText(1, 0);
        }
    }

    // =================================================
    // ENTER
    // =================================================

    private void pressEnter() {

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
    }
}
