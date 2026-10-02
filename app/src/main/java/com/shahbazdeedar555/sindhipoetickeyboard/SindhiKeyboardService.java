
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    private final Handler deleteHandler =
            new Handler(Looper.getMainLooper());

    private boolean fastDeleting = false;

    private final Runnable deleteRunnable = new Runnable() {
        @Override
        public void run() {
            if (!fastDeleting) {
                return;
            }

            deleteOneCharacter();
            deleteHandler.postDelayed(this, 70);
        }
    };

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setAllButtonsStyle(keyboard);

        // NORMAL PAGE

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

        // TATWEEL

        setKey(R.id.key_tatweel, "ـ");

        // COMMA AND PERIOD

        setKey(R.id.key_comma, "،");
        setKey(R.id.key_period, ".");

        // SHIFT PAGE

        setKey(R.id.key_rre, "ڄ");
        setKey(R.id.key_meem, "ڃ");
        setKey(R.id.key_nun, "ڦ");
        setKey(R.id.key_shift_fatha, "ُ");
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
        setKey(R.id.key_jhay, "ۡ");
        setKey(R.id.key_kay, "ح");
        setKey(R.id.key_ghay, "ڦ");
        setKey(R.id.key_hamza, "ڊ");
        setKey(R.id.key_he, "ش");
        setKey(R.id.key_ya, "آ");

        setKey(R.id.key_yeh, "۾");
        setKey(R.id.key_waw2, "ڻ");
        setKey(R.id.key_zhay, "ٻ");
        setKey(R.id.key_yay2, "ء");
        setKey(R.id.key_shift_extra, "ظ");
        setKey(R.id.key_shift_diacritic, "ّ");
        setKey(R.id.key_shift_dhal, "ذ");

        // NUMBER PAGE

        setKey(R.id.key_num_0, "0");
        setKey(R.id.key_num_1, "1");
        setKey(R.id.key_num_2, "2");
        setKey(R.id.key_num_3, "3");
        setKey(R.id.key_num_4, "4");
        setKey(R.id.key_num_5, "5");
        setKey(R.id.key_num_6, "6");
        setKey(R.id.key_num_7, "7");
        setKey(R.id.key_num_8, "8");
        setKey(R.id.key_num_9, "9");

        setKey(R.id.key_s1, "(");
        setKey(R.id.key_s2, ")");
        setKey(R.id.key_s3, "=");
        setKey(R.id.key_s4, "-");
        setKey(R.id.key_s5, "*");
        setKey(R.id.key_s6, "ٰ");
        setKey(R.id.key_s7, "ة");
        setKey(R.id.key_s8, "ؤ");
        setKey(R.id.key_s9, "ہ");
        setKey(R.id.key_s10, "بہ");
        setKey(R.id.key_s11, "ى");

        setKey(R.id.key_s12, "ٖ");
        setKey(R.id.key_s13, "ً");
        setKey(R.id.key_s14, "'");
        setKey(R.id.key_s15, "؟");
        setKey(R.id.key_s16, "ٗ");
        setKey(R.id.key_s17, "؛");
        setKey(R.id.key_s18, ":");

        // DELETE

        setupDelete(R.id.key_delete);
        setupDelete(R.id.key_delete2);
        setupDelete(R.id.key_num_delete);

        // ENTER

        setupEnter(R.id.key_enter);
        setupEnter(R.id.key_enter2);
        setupEnter(R.id.key_num_enter);

        // SPACE

        setupSpace(R.id.key_space);
        setupSpace(R.id.key_space2);
        setupSpace(R.id.key_num_space);

        // NORMAL SHIFT BUTTON

        Button shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {
            shift.setOnClickListener(v -> {
                View page1 =
                        keyboard.findViewById(R.id.keyboard_page1);

                View page2 =
                        keyboard.findViewById(R.id.keyboard_page2);

                View page3 =
                        keyboard.findViewById(R.id.keyboard_page3);

                if (page1 != null) {
                    page1.setVisibility(View.GONE);
                }

                if (page3 != null) {
                    page3.setVisibility(View.GONE);
                }

                if (page2 != null) {
                    page2.setVisibility(View.VISIBLE);
                }
            });
        }

        // SHIFT BACK BUTTON

        Button shiftBack =
                keyboard.findViewById(R.id.key_shift_back);

        if (shiftBack != null) {
            shiftBack.setOnClickListener(v -> showPage1());
        }

        // NUMBER BUTTON FROM NORMAL

        Button numberButton =
                keyboard.findViewById(R.id.key_123);

        if (numberButton != null) {
            numberButton.setOnClickListener(v -> showPage3());
        }

        // NUMBER BUTTON FROM SHIFT

        Button numberShift =
                keyboard.findViewById(R.id.key_123_shift);

        if (numberShift != null) {
            numberShift.setOnClickListener(v -> showPage3());
        }

        // NUMBER PAGE BACK TO SHIFT

        Button numberShiftBack =
                keyboard.findViewById(R.id.key_num_shift);

        if (numberShiftBack != null) {
            numberShiftBack.setOnClickListener(v -> showPage2());
        }

        // NUMBER PAGE BACK TO NORMAL

        Button ibt =
                keyboard.findViewById(R.id.key_ibt);

        if (ibt != null) {
            ibt.setOnClickListener(v -> showPage1());
        }

        return keyboard;
    }

    private void setKey(int id, String text) {

        Button button = keyboard.findViewById(id);

        if (button != null) {

            button.setEnabled(true);
            button.setClickable(true);

            button.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection().commitText(
                            text,
                            1
                    );
                }
            });
        }
    }

    private void deleteOneCharacter() {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection().deleteSurroundingText(
                    1,
                    0
            );
        }
    }

    private void setupDelete(int id) {

        Button delete =
                keyboard.findViewById(id);

        if (delete == null) {
            return;
        }

        delete.setOnClickListener(v -> {
            deleteOneCharacter();
        });

        delete.setOnLongClickListener(v -> {

            fastDeleting = true;

            deleteOneCharacter();

            deleteHandler.postDelayed(
                    deleteRunnable,
                    180
            );

            return true;
        });

        delete.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP ||
                    event.getAction() == MotionEvent.ACTION_CANCEL) {

                fastDeleting = false;

                deleteHandler.removeCallbacks(
                        deleteRunnable
                );
            }

            return false;
        });
    }

    private void setupEnter(int id) {

        Button enter =
                keyboard.findViewById(id);

        if (enter == null) {
            return;
        }

        enter.setOnClickListener(v -> {

            if (getCurrentInputConnection() == null) {
                return;
            }

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

    private void setupSpace(int id) {

        Button space =
                keyboard.findViewById(id);

        if (space == null) {
            return;
        }

        space.setOnClickListener(v -> {

            if (getCurrentInputConnection() != null) {

                getCurrentInputConnection().commitText(
                        " ",
                        1
                );
            }
        });
    }

    private void showPage1() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.VISIBLE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }

        if (page3 != null) {
            page3.setVisibility(View.GONE);
        }
    }

    private void showPage2() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.VISIBLE);
        }

        if (page3 != null) {
            page3.setVisibility(View.GONE);
        }
    }

    private void showPage3() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }

        if (page3 != null) {
            page3.setVisibility(View.VISIBLE);
        }
    }

    private void setAllButtonsStyle(View view) {

        if (view instanceof Button) {

            Button button =
                    (Button) view;

            button.setBackgroundColor(
                    Color.BLACK
            );

            button.setTextColor(
                    Color.WHITE
            );

        } else if (view instanceof android.view.ViewGroup) {

            android.view.ViewGroup group =
                    (android.view.ViewGroup) view;

            for (int i = 0;
                    i < group.getChildCount();
                    i++) {

                setAllButtonsStyle(
                        group.getChildAt(i)
                );
            }
        }
    }
}
