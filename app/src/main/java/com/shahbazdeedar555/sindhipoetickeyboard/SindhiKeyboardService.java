
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;
    private boolean shiftOn = false;

    // =========================================
    // PAGE 1
    // =========================================

    private final int[] PAGE1_KEYS = {
            R.id.key_alif,
            R.id.key_bay,
            R.id.key_bay2,
            R.id.key_pay,
            R.id.key_bhe,
            R.id.key_te,
            R.id.key_tay,
            R.id.key_ain,
            R.id.key_ghain,
            R.id.key_waw,
            R.id.key_jeem,
            R.id.key_kaf,
            R.id.key_lam,
            R.id.key_khay,
            R.id.key_ngaf,
            R.id.key_ddal,
            R.id.key_qaf,
            R.id.key_sad,
            R.id.key_ye,
            R.id.key_re,
            R.id.key_seen,
            R.id.key_dal,
            R.id.key_fay,
            R.id.key_gaf,
            R.id.key_he,
            R.id.key_zay,
            R.id.key_khay2,
            R.id.key_ttay,
            R.id.key_bhay,
            R.id.key_bay3,
            R.id.key_noon,
            R.id.key_meem,
            R.id.key_hamza
    };

    // =========================================
    // PAGE 1 OUTPUT
    // =========================================

    private final String[] PAGE1_CHARS = {
            "ت",
            "ٿ",
            "ع",
            "ڳ",
            "و",
            "پ",
            "ڇ",
            "چ",
            "ج",
            "ڪ",

            "ل",
            "ک",
            "ڱ",
            "ڍ",
            "ق",
            "ص",
            "ي",
            "ر",
            "ا",
            "س",

            "د",
            "ف",
            "گ",
            "ه",
            "ز",
            "خ",
            "ط",
            "ڀ",
            "ب",
            "ن",

            "م",
            "ئ",
            "ء"
    };

    // =========================================
    // PAGE 2 / SHIFT
    // =========================================

    private final int[] PAGE2_KEYS = {
            R.id.shift_je,
            R.id.shift_nje,
            R.id.shift_fay,
            R.id.shift_he,
            R.id.shift_ghain,
            R.id.shift_say,
            R.id.shift_ttay,
            R.id.shift_rtay,
            R.id.shift_dad,
            R.id.shift_ttay2,
            R.id.shift_dhay,
            R.id.shift_dday,
            R.id.shift_and,
            R.id.shift_hay,
            R.id.shift_fay2,
            R.id.shift_dal,
            R.id.shift_sheen,
            R.id.shift_alif_madd,

            R.id.shift_meem,
            R.id.shift_noon,
            R.id.shift_bay,
            R.id.shift_hamza,
            R.id.shift_zay2,
            R.id.shift_zaal
    };

    // =========================================
    // PAGE 2 OUTPUT
    // =========================================

    private final String[] PAGE2_CHARS = {
            "ڄ",
            "ڃ",
            "ڦ",
            "ھ",
            "غ",
            "ث",
            "ٽ",
            "ڙ",
            "ض",
            "ٺ",
            "ڌ",
            "ڏ",
            "۽",
            "ح",
            "ڦ",
            "ڊ",
            "ش",
            "آ",

            "۾",
            "ڻ",
            "ٻ",
            "ء",
            "ظ",
            "ذ"
    };

    // =========================================
    // CREATE KEYBOARD
    // =========================================

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupPage1();
        setupPage2();
        setupControlButtons();

        showPage1();

        return keyboard;
    }

    // =========================================
    // PAGE 1 SETUP
    // =========================================

    private void setupPage1() {

        for (int i = 0; i < PAGE1_KEYS.length; i++) {

            final int index = i;

            View view = keyboard.findViewById(PAGE1_KEYS[i]);

            if (view instanceof Button) {

                Button button = (Button) view;

                button.setOnClickListener(v -> {

                    if (index < PAGE1_CHARS.length) {
                        commitText(PAGE1_CHARS[index]);
                    }

                });
            }
        }
    }

    // =========================================
    // PAGE 2 SETUP
    // =========================================

    private void setupPage2() {

        for (int i = 0; i < PAGE2_KEYS.length; i++) {

            final int index = i;

            View view = keyboard.findViewById(PAGE2_KEYS[i]);

            if (view instanceof Button) {

                Button button = (Button) view;

                button.setOnClickListener(v -> {

                    if (index < PAGE2_CHARS.length) {
                        commitText(PAGE2_CHARS[index]);
                    }

                });
            }
        }
    }

    // =========================================
    // CONTROL BUTTONS
    // =========================================

    private void setupControlButtons() {

        // SHIFT
        View shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {

            shift.setOnClickListener(v -> {

                shiftOn = !shiftOn;

                if (shiftOn) {
                    showPage2();
                } else {
                    showPage1();
                }

            });
        }

        // DELETE
        View delete = keyboard.findViewById(R.id.key_delete);

        if (delete != null) {

            delete.setOnClickListener(v -> deleteOneCharacter());
        }

        // ENTER
        View enter = keyboard.findViewById(R.id.key_enter);

        if (enter != null) {

            enter.setOnClickListener(v -> {

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

            });
        }
    }

    // =========================================
    // SHOW PAGE 1
    // =========================================

    private void showPage1() {

        View page1 = keyboard.findViewById(R.id.keyboard_page1);
        View page2 = keyboard.findViewById(R.id.keyboard_page2);

        if (page1 != null) {
            page1.setVisibility(View.VISIBLE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }

        updateShiftText();
    }

    // =========================================
    // SHOW PAGE 2
    // =========================================

    private void showPage2() {

        View page1 = keyboard.findViewById(R.id.keyboard_page1);
        View page2 = keyboard.findViewById(R.id.keyboard_page2);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.VISIBLE);
        }

        updateShiftText();
    }

    // =========================================
    // SHIFT BUTTON TEXT
    // =========================================

    private void updateShiftText() {

        View view = keyboard.findViewById(R.id.key_shift);

        if (view instanceof Button) {

            Button shift = (Button) view;

            shift.setText("SHIFT");
        }
    }

    // =========================================
    // COMMIT TEXT
    // =========================================

    private void commitText(String text) {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection().commitText(
                    text,
                    1
            );
        }
    }

    // =========================================
    // DELETE
    // =========================================

    private void deleteOneCharacter() {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection()
                    .deleteSurroundingText(1, 0);
        }
    }
}
