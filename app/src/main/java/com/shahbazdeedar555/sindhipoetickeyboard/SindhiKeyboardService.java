package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        // =========================
        // MAKE ALL BUTTONS BLACK
        // TEXT WHITE
        // =========================

        setAllButtonsStyle(keyboard);


        // =========================
        // PAGE 1 — 32 LETTERS
        // =========================

        setKey(R.id.key_alif, "چ");
        setKey(R.id.key_bay, "ڇ");
        setKey(R.id.key_bay2, "پ");
        setKey(R.id.key_pay, "و");
        setKey(R.id.key_bhe, "ڳ");
        setKey(R.id.key_te, "ع");
        setKey(R.id.key_the, "ٿ");
        setKey(R.id.key_tt, "ت");

        setKey(R.id.key_say, "ر");
        setKey(R.id.key_fay, "ي");
        setKey(R.id.key_fhay, "ص");
        setKey(R.id.key_gaf, "ق");
        setKey(R.id.key_gaf2, "ڍ");
        setKey(R.id.key_gn, "ڱ");
        setKey(R.id.key_kaf, "ک");
        setKey(R.id.key_yay, "ل");

        setKey(R.id.key_dal, "ڪ");
        setKey(R.id.key_dhal, "ج");
        setKey(R.id.key_dhad, "ه");
        setKey(R.id.key_dde, "گ");
        setKey(R.id.key_dd, "ف");
        setKey(R.id.key_ddh, "د");
        setKey(R.id.key_hay, "س");
        setKey(R.id.key_jeem, "ا");

        setKey(R.id.key_jay, "ئ");
        setKey(R.id.key_nje, "م");
        setKey(R.id.key_chay, "ن");
        setKey(R.id.key_chhe, "ب");
        setKey(R.id.key_khay, "ڀ");
        setKey(R.id.key_ain, "ط");
        setKey(R.id.key_ghain, "خ");
        setKey(R.id.key_ray, "ز");


        // =========================
        // SPECIAL MARKS
        // =========================

        setKey(R.id.key_mark_1, "ة");
        setKey(R.id.key_mark_2, "َ");
        setKey(R.id.key_mark_3, "ِ");
        setKey(R.id.key_mark_4, "ُ");
        setKey(R.id.key_mark_5, "ْ");
        setKey(R.id.key_mark_6, "ّ");
        setKey(R.id.key_mark_7, "ٰ");
        setKey(R.id.key_mark_8, "آ");
        setKey(R.id.key_mark_9, "ي");
        setKey(R.id.key_mark_10, "ؤ");
        setKey(R.id.key_mark_11, "ى");


        // =========================
        // PUNCTUATION
        // =========================

        setKey(R.id.key_period, ".");
        setKey(R.id.key_comma, "،");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_quote_open, "“");
        setKey(R.id.key_quote_close, "”");
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_exclamation, "!");


        // =========================
        // SHIFT PAGE
        // =========================

        setKey(R.id.key_rre, "ڄ");
        setKey(R.id.key_meem, "ڃ");
        setKey(R.id.key_nun, "ڦ");
        setKey(R.id.key_lam, "ھ");
        setKey(R.id.key_sin, "غ");
        setKey(R.id.key_sheen, "ث");
        setKey(R.id.key_sad, "ٽ");
        setKey(R.id.key_dad, "ڙ");

        setKey(R.id.key_tay, "ض");
        setKey(R.id.key_zay, "ٺ");
        setKey(R.id.key_nnoon, "ڌ");
        setKey(R.id.key_waw, "ڏ");
        setKey(R.id.key_hay2, "۽");
        setKey(R.id.key_jhay, "ح");
        setKey(R.id.key_kay, "ہ");
        setKey(R.id.key_ghay, "ڊ");

        setKey(R.id.key_hamza, "ش");
        setKey(R.id.key_he, "آ");
        setKey(R.id.key_ya, "۾");
        setKey(R.id.key_yeh, "ڻ");
        setKey(R.id.key_waw2, "ٻ");
        setKey(R.id.key_zhay, "ء");
        setKey(R.id.key_yay2, "ظ");
        setKey(R.id.key_shift_extra, "ذ");


        // =========================
        // DELETE
        // =========================

        Button delete = keyboard.findViewById(R.id.key_delete);

        if (delete != null) {

            delete.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection()
                            .deleteSurroundingText(1, 0);
                }
            });
        }


        // =========================
        // SPACE
        // =========================

        Button space = keyboard.findViewById(R.id.key_space);

        if (space != null) {

            space.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection()
                            .commitText(" ", 1);
                }
            });
        }


        // =========================
        // ENTER
        // =========================

        Button enter = keyboard.findViewById(R.id.key_enter);

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


        // =========================
        // SHIFT
        // =========================

        Button shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {

            shift.setOnClickListener(v -> {

                View page1 =
                        keyboard.findViewById(R.id.keyboard_page1);

                View page2 =
                        keyboard.findViewById(R.id.keyboard_page2);

                if (page1.getVisibility() == View.VISIBLE) {

                    page1.setVisibility(View.GONE);
                    page2.setVisibility(View.VISIBLE);

                } else {

                    page1.setVisibility(View.VISIBLE);
                    page2.setVisibility(View.GONE);
                }
            });
        }

        return keyboard;
    }


    // =========================
    // NORMAL KEY
    // =========================

    private void setKey(int id, String text) {

        Button button = keyboard.findViewById(id);

        if (button != null) {

            button.setEnabled(true);
            button.setClickable(true);

            button.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection()
                            .commitText(text, 1);
                }
            });
        }
    }


    // =========================
    // BLACK BUTTONS
    // WHITE TEXT
    // =========================

    private void setAllButtonsStyle(View view) {

        if (view instanceof Button) {

            Button button = (Button) view;

            button.setBackgroundColor(Color.BLACK);
            button.setTextColor(Color.WHITE);

        } else if (view instanceof android.view.ViewGroup) {

            android.view.ViewGroup group =
                    (android.view.ViewGroup) view;

            for (int i = 0; i < group.getChildCount(); i++) {

                setAllButtonsStyle(
                        group.getChildAt(i)
                );
            }
        }
    }
}
