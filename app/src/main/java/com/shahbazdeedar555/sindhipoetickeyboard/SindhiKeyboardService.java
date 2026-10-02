package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    private boolean shiftOn = false;

    private final int[] PAGE1_KEYS = {
            R.id.key_alif, R.id.key_bay, R.id.key_bay2, R.id.key_pay,
            R.id.key_bhe, R.id.key_te, R.id.key_the, R.id.key_tt,

            R.id.key_say, R.id.key_fay, R.id.key_fhay, R.id.key_gaf,
            R.id.key_gaf2, R.id.key_gn, R.id.key_kaf, R.id.key_yay,

            R.id.key_dal, R.id.key_dhal, R.id.key_dhad, R.id.key_dde,
            R.id.key_dd, R.id.key_ddh, R.id.key_hay, R.id.key_jeem,

            R.id.key_jay, R.id.key_nje, R.id.key_chay, R.id.key_chhe,
            R.id.key_khay, R.id.key_ain, R.id.key_ghain, R.id.key_ray,

            R.id.key_mark_1, R.id.key_mark_2, R.id.key_mark_3,
            R.id.key_mark_4, R.id.key_mark_5, R.id.key_mark_6,
            R.id.key_mark_7, R.id.key_mark_8, R.id.key_mark_9,
            R.id.key_mark_10, R.id.key_mark_11,

            R.id.key_period, R.id.key_comma, R.id.key_question,
            R.id.key_quote_open, R.id.key_quote_close, R.id.key_colon,
            R.id.key_exclamation, R.id.key_tatweel,

            R.id.key_num_0, R.id.key_num_1, R.id.key_num_2,
            R.id.key_num_3, R.id.key_num_4, R.id.key_num_5,
            R.id.key_num_6, R.id.key_num_7, R.id.key_num_8,
            R.id.key_num_9
    };

    private final int[] PAGE2_KEYS = {
            R.id.key_rre, R.id.key_meem, R.id.key_nun, R.id.key_lam,
            R.id.key_sin, R.id.key_sheen, R.id.key_sad, R.id.key_dad,

            R.id.key_tay, R.id.key_zay, R.id.key_nnoon, R.id.key_waw,
            R.id.key_hay2, R.id.key_jhay, R.id.key_kay, R.id.key_ghay,

            R.id.key_hamza, R.id.key_he, R.id.key_ya, R.id.key_yeh,
            R.id.key_waw2, R.id.key_zhay, R.id.key_yay2,
            R.id.key_shift_extra
    };

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupPage1();
        setupPage2();
        setupBottomControls();

        return keyboard;
    }

    // ==========================================
    // PAGE 1
    // ==========================================

    private void setupPage1() {

        for (int id : PAGE1_KEYS) {

            Button button = keyboard.findViewById(id);

            if (button != null) {

                button.setOnClickListener(v -> {

                    Button b = (Button) v;

                    String text = b.getText().toString();

                    if (!text.isEmpty()) {
                        commitText(text);
                    }
                });
            }
        }
    }

    // ==========================================
    // PAGE 2 / SHIFT
    // ==========================================

    private void setupPage2() {

        for (int id : PAGE2_KEYS) {

            Button button = keyboard.findViewById(id);

            if (button != null) {

                button.setOnClickListener(v -> {

                    Button b = (Button) v;

                    String text = b.getText().toString();

                    if (!text.isEmpty()) {
                        commitText(text);
                    }
                });
            }
        }
    }

    // ==========================================
    // BOTTOM BUTTONS
    // ==========================================

    private void setupBottomControls() {

        // SPACE
        Button space = keyboard.findViewById(R.id.key_space);

        if (space != null) {

            space.setOnClickListener(v -> {
                commitText(" ");
            });
        }

        // ENTER
        Button enter = keyboard.findViewById(R.id.key_enter);

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

        // DELETE
        Button delete = keyboard.findViewById(R.id.key_delete);

        if (delete != null) {

            delete.setOnClickListener(v -> {
                deleteOneCharacter();
            });
        }

        // SHIFT
        Button shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {

            shift.setOnClickListener(v -> {

                shiftOn = !shiftOn;

                View page1 = keyboard.findViewById(
                        R.id.keyboard_page1
                );

                View page2 = keyboard.findViewById(
                        R.id.keyboard_page2
                );

                if (shiftOn) {

                    page1.setVisibility(View.GONE);
                    page2.setVisibility(View.VISIBLE);

                } else {

                    page1.setVisibility(View.VISIBLE);
                    page2.setVisibility(View.GONE);
                }
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
