
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    // ==========================================
    // CREATE KEYBOARD
    // ==========================================

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupLetters();
        setupBottomControls();

        return keyboard;
    }

    // ==========================================
    // PAGE 1 LETTERS
    // ==========================================

    private void setupLetters() {

        // ROW 1
        setKey(R.id.key_ch, "چ");
        setKey(R.id.key_chh, "ڇ");
        setKey(R.id.key_pay, "پ");
        setKey(R.id.key_waw, "و");
        setKey(R.id.key_ghain, "ڳ");
        setKey(R.id.key_ain, "ع");
        setKey(R.id.key_th, "ٿ");
        setKey(R.id.key_tay, "ت");

        // ROW 2
        setKey(R.id.key_ray, "ر");
        setKey(R.id.key_yay, "ي");
        setKey(R.id.key_sad, "ص");
        setKey(R.id.key_qaf, "ق");
        setKey(R.id.key_ddal, "ڍ");
        setKey(R.id.key_ng, "ڱ");
        setKey(R.id.key_khay, "ک");
        setKey(R.id.key_lam, "ل");
        setKey(R.id.key_kaf, "ڪ");
        setKey(R.id.key_jeem, "ج");

        // ROW 3
        setKey(R.id.key_hay, "ه");
        setKey(R.id.key_gaf, "گ");
        setKey(R.id.key_fay, "ف");
        setKey(R.id.key_dal, "د");
        setKey(R.id.key_seen, "س");
        setKey(R.id.key_alif, "ا");

        // ROW 4
        setKey(R.id.key_hamza_y, "ئ");
        setKey(R.id.key_meem, "م");
        setKey(R.id.key_noon, "ن");
        setKey(R.id.key_bay, "ب");
        setKey(R.id.key_bh, "ڀ");
        setKey(R.id.key_ta, "ط");
        setKey(R.id.key_kha, "خ");
        setKey(R.id.key_za, "ز");
    }

    // ==========================================
    // SET ONE KEY
    // ==========================================

    private void setKey(int id, String text) {

        Button button = keyboard.findViewById(id);

        if (button != null) {

            button.setOnClickListener(v -> {
                commitText(text);
            });
        }
    }

    // ==========================================
    // BOTTOM CONTROLS
    // ==========================================

    private void setupBottomControls() {

        // --------------------------------------
        // SPACE
        // --------------------------------------

        Button space = keyboard.findViewById(
                R.id.key_space
        );

        if (space != null) {

            space.setOnClickListener(v -> {
                commitText(" ");
            });
        }

        // --------------------------------------
        // ENTER
        // --------------------------------------

        Button enter = keyboard.findViewById(
                R.id.key_enter
        );

        if (enter != null) {

            enter.setOnClickListener(v -> {

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

        // --------------------------------------
        // DELETE
        // --------------------------------------

        Button delete = keyboard.findViewById(
                R.id.key_delete
        );

        if (delete != null) {

            delete.setOnClickListener(v -> {
                deleteOneCharacter();
            });
        }

        // --------------------------------------
        // SHIFT
        // --------------------------------------

        Button shift = keyboard.findViewById(
                R.id.key_shift
        );

        if (shift != null) {

            shift.setOnClickListener(v -> {

                /*
                 * PAGE 2 اڃا XML ۾ شامل ناهي.
                 * ان ڪري هن مرحلي تي Shift صرف موجود آهي.
                 *
                 * PAGE 2 شامل ڪرڻ وقت هتي Shift
                 * جو اصل ڪم لڳايو ويندو.
                 */
            });
        }

        // --------------------------------------
        // ARROW
        // --------------------------------------

        Button arrow = keyboard.findViewById(
                R.id.key_arrow
        );

        if (arrow != null) {

            arrow.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection().sendKeyEvent(
                            new KeyEvent(
                                    KeyEvent.ACTION_DOWN,
                                    KeyEvent.KEYCODE_DPAD_LEFT
                            )
                    );

                    getCurrentInputConnection().sendKeyEvent(
                            new KeyEvent(
                                    KeyEvent.ACTION_UP,
                                    KeyEvent.KEYCODE_DPAD_LEFT
                            )
                    );
                }
            });
        }

        // --------------------------------------
        // SYMBOLS
        // --------------------------------------

        Button symbols = keyboard.findViewById(
                R.id.key_symbols
        );

        if (symbols != null) {

            symbols.setOnClickListener(v -> {

                // خاص نشانيون ايندڙ حصي ۾ شامل ڪنداسين.

            });
        }

        // --------------------------------------
        // 123
        // --------------------------------------

        Button numbers = keyboard.findViewById(
                R.id.key_123
        );

        if (numbers != null) {

            numbers.setOnClickListener(v -> {

                // 123 وارو حصو ايندڙ مرحلي ۾ شامل ڪنداسين.

            });
        }
    }

    // ==========================================
    // WRITE TEXT
    // ==========================================

    private void commitText(String text) {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection().commitText(
                    text,
                    1
            );
        }
    }

    // ==========================================
    // DELETE ONE CHARACTER
    // ==========================================

    private void deleteOneCharacter() {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection().deleteSurroundingText(
                    1,
                    0
            );
        }
    }
}
