package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;
    private View page1;
    private View page2;

    private boolean shiftOn = false;

    // DELETE LONG PRESS
    private final Handler deleteHandler =
            new Handler(Looper.getMainLooper());

    private Runnable deleteRunnable;
    private boolean deleteHolding = false;

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        page1 = keyboard.findViewById(
                R.id.keyboard_page1
        );

        page2 = keyboard.findViewById(
                R.id.keyboard_page2
        );

        setupPage1();
        setupPage2();
        setupNumbers();

        showPage1();

        return keyboard;
    }

    // =========================================================
    // PAGE 1
    // =========================================================

    private void setupPage1() {

        // ROW 1
        setKey(R.id.key_ch, "چ");
        setKey(R.id.key_chh, "ڇ");
        setKey(R.id.key_pay, "پ");
        setKey(R.id.key_waw, "و");
        setKey(R.id.key_ghain, "ڳ");
        setKey(R.id.key_ain, "ع");
        setKey(R.id.key_th, "ٿ");
        setKey(R.id.key_ta1, "ت");
        setKey(R.id.key_ray, "ر");
        setKey(R.id.key_yay, "ي");
        setKey(R.id.key_sad, "ص");
        setKey(R.id.key_qaf, "ق");

        // ROW 2
        setKey(R.id.key_ddal, "ڍ");
        setKey(R.id.key_ng, "ڱ");
        setKey(R.id.key_khay, "ک");
        setKey(R.id.key_lam, "ل");
        setKey(R.id.key_kaf, "ڪ");
        setKey(R.id.key_jeem, "ج");
        setKey(R.id.key_hay, "ه");
        setKey(R.id.key_gaf, "گ");
        setKey(R.id.key_fay, "ف");
        setKey(R.id.key_dal, "د");
        setKey(R.id.key_seen, "س");
        setKey(R.id.key_alif, "ا");

        // ROW 3
        setKey(R.id.key_hamza_y, "ئ");
        setKey(R.id.key_meem, "م");
        setKey(R.id.key_noon, "ن");
        setKey(R.id.key_bay, "ب");
        setKey(R.id.key_bh, "ڀ");
        setKey(R.id.key_ta2, "ط");
        setKey(R.id.key_kha, "خ");
        setKey(R.id.key_za, "ز");

        // PUNCTUATION
        setArrow(R.id.key_arrow);

        setKey(R.id.key_dot, "۔");
        setKey(R.id.key_comma, "،");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_exclamation, "!");
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_semicolon, "؛");
        setKey(R.id.key_quotes, "\"");

        // SPECIALS
        setKey(R.id.key_tatweel, "ـ");
        setKey(R.id.key_baha, "بہ");
        setKey(R.id.key_taha, "تہ");
        setKey(R.id.key_noha, "نہ");
        setKey(R.id.key_yeh_alt, "ى");
        setKey(R.id.key_heh_alt, "ہ");
        setKey(R.id.key_waw_hamza, "ؤ");
        setKey(R.id.key_teh_marbuta, "ة");

        // DIACRITICS
        setKey(R.id.key_zabar1, "َ");
        setKey(R.id.key_zer1, "ِ");
        setKey(R.id.key_pesh1, "ُ");
        setKey(R.id.key_shadd1, "ّ");
        setKey(R.id.key_jazm1, "ْ");
        setKey(R.id.key_alif_khanjari1, "ٰ");
        setKey(R.id.key_alif_khanjari2, "ٖ");
        setKey(R.id.key_alif_khanjari3, "ٗ");

        // NEW CONTROLS
        setShift(R.id.key_shift_bottom);
        setDelete(R.id.key_delete_bottom);
        setSpace(R.id.key_space_bottom);
        setEnter(R.id.key_enter_bottom);
    }

    // =========================================================
    // PAGE 2 / SHIFT
    // =========================================================

    private void setupPage2() {

        // ROW 1
        setKey(R.id.s1, "ڄ");
        setKey(R.id.s2, "ڃ");
        setKey(R.id.s16, "ڦ");
        setKey(R.id.s4, "ھ");
        setKey(R.id.s5, "غ");
        setKey(R.id.s6, "ث");
        setKey(R.id.s7, "ٽ");
        setKey(R.id.s8, "ڙ");
        setKey(R.id.s9, "ض");
        setKey(R.id.s10, "ٺ");
        setKey(R.id.s11, "ڌ");
        setKey(R.id.s12, "ڏ");

        // ROW 2
        setKey(R.id.s13, "۽");
        setKey(R.id.s14, "ح");
        setKey(R.id.s15, "گھ");
        setKey(R.id.s17, "ڊ");
        setKey(R.id.s24, "ش");
        setKey(R.id.s25, "آ");

        // ROW 3
        setKey(R.id.s18, "۾");
        setKey(R.id.s19, "ڻ");
        setKey(R.id.s20, "ٻ");
        setKey(R.id.s21, "ء");
        setKey(R.id.s22, "ظ");
        setKey(R.id.s23, "ذ");

        // SPECIALS
        setKey(R.id.key_tatweel2, "ـ");
        setKey(R.id.key_baha2, "بہ");
        setKey(R.id.key_taha2, "تہ");
        setKey(R.id.key_noha2, "نہ");
        setKey(R.id.key_yeh_alt2, "ى");
        setKey(R.id.key_heh_alt2, "ہ");
        setKey(R.id.key_waw_hamza2, "ؤ");
        setKey(R.id.key_teh_marbuta2, "ة");

        // DIACRITICS
        setKey(R.id.key_zabar2, "َ");
        setKey(R.id.key_zer2, "ِ");
        setKey(R.id.key_pesh2, "ُ");
        setKey(R.id.key_shadd2, "ّ");
        setKey(R.id.key_jazm2, "ْ");
        setKey(R.id.key_alif_khanjari2b, "ٰ");
        setKey(R.id.key_alif_khanjari2c, "ٖ");
        setKey(R.id.key_alif_khanjari2d, "ٗ");

        // NEW CONTROLS
        setShiftBack(R.id.key_shift_bottom2);
        setDelete(R.id.key_delete_bottom2);
        setSpace(R.id.key_space_bottom2);
        setEnter(R.id.key_enter_bottom2);
    }

    // =========================================================
    // NUMBERS 0 - 9
    // =========================================================

    private void setupNumbers() {

    setNumber(R.id.key_num0, "0");
    setNumber(R.id.key_num1, "1");
    setNumber(R.id.key_num2, "2");
    setNumber(R.id.key_num3, "3");
    setNumber(R.id.key_num4, "4");
    setNumber(R.id.key_num5, "5");
    setNumber(R.id.key_num6, "6");
    setNumber(R.id.key_num7, "7");
    setNumber(R.id.key_num8, "8");
    setNumber(R.id.key_num9, "9");
}

    private void setNumber(int id, final String number) {

    View view = keyboard.findViewById(id);

    if (!(view instanceof Button)) {
        return;
    }

    Button button = (Button) view;

    button.setOnClickListener(v -> {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic != null) {

            ic.commitText(
                    number,
                    1
            );
        }
    });
}

    // =========================================================
    // NORMAL KEY
    // =========================================================

    private void setKey(
            int id,
            final String text
    ) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            InputConnection ic =
                    getCurrentInputConnection();

            if (ic == null) {
                return;
            }

            String output = text;

            // ا + SHIFT = آ
            if (shiftOn && text.equals("ا")) {
                output = "آ";
            }

            ic.commitText(
                    output,
                    1
            );

            // SHIFT صرف هڪ اکر لاءِ
            if (shiftOn) {
                shiftOn = false;
                showPage1();
            }
        });
    }

    // =========================================================
    // SHIFT PAGE 1 -> PAGE 2
    // =========================================================

    private void setShift(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            shiftOn = true;

            showPage2();
        });
    }

    // =========================================================
    // SHIFT PAGE 2 -> PAGE 1
    // =========================================================

    private void setShiftBack(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            shiftOn = false;

            showPage1();
        });
    }

    // =========================================================
    // DELETE
    // TAP + LONG PRESS
    // =========================================================

    private void setDelete(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            deleteOne();
        });

        button.setOnLongClickListener(v -> {

            deleteHolding = true;

            deleteOne();

            deleteRunnable = new Runnable() {

                @Override
                public void run() {

                    if (!deleteHolding) {
                        return;
                    }

                    deleteOne();

                    deleteHandler.postDelayed(
                            this,
                            70
                    );
                }
            };

            deleteHandler.postDelayed(
                    deleteRunnable,
                    250
            );

            return true;
        });

        button.setOnTouchListener(
                (v, event) -> {

                    if (event.getAction() ==
                            MotionEvent.ACTION_UP ||
                        event.getAction() ==
                            MotionEvent.ACTION_CANCEL) {

                        deleteHolding = false;

                        if (deleteRunnable != null) {

                            deleteHandler.removeCallbacks(
                                    deleteRunnable
                            );
                        }
                    }

                    return false;
                }
        );
    }

    // =========================================================
    // DELETE ONE
    // =========================================================

    private void deleteOne() {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic != null) {

            ic.deleteSurroundingText(
                    1,
                    0
            );
        }
    }

    // =========================================================
    // SPACE
    // =========================================================

    private void setSpace(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            InputConnection ic =
                    getCurrentInputConnection();

            if (ic != null) {

                ic.commitText(
                        " ",
                        1
                );
            }
        });
    }

    // =========================================================
    // ENTER
    // =========================================================

    private void setEnter(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            InputConnection ic =
                    getCurrentInputConnection();

            if (ic != null) {

                ic.sendKeyEvent(
                        new KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_ENTER
                        )
                );

                ic.sendKeyEvent(
                        new KeyEvent(
                                KeyEvent.ACTION_UP,
                                KeyEvent.KEYCODE_ENTER
                        )
                );
            }
        });
    }

    // =========================================================
    // ARROW
    // =========================================================

    private void setArrow(int id) {

        View view = keyboard.findViewById(id);

        if (!(view instanceof Button)) {
            return;
        }

        Button button = (Button) view;

        button.setOnClickListener(v -> {

            InputConnection ic =
                    getCurrentInputConnection();

            if (ic != null) {

                ic.sendKeyEvent(
                        new KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_DPAD_LEFT
                        )
                );

                ic.sendKeyEvent(
                        new KeyEvent(
                                KeyEvent.ACTION_UP,
                                KeyEvent.KEYCODE_DPAD_LEFT
                        )
                );
            }
        });
    }

    // =========================================================
    // SHOW PAGE 1
    // =========================================================

    private void showPage1() {

        if (page1 != null) {
            page1.setVisibility(
                    View.VISIBLE
            );
        }

        if (page2 != null) {
            page2.setVisibility(
                    View.GONE
            );
        }
    }

    // =========================================================
    // SHOW PAGE 2
    // =========================================================

    private void showPage2() {

        if (page1 != null) {
            page1.setVisibility(
                    View.GONE
            );
        }

        if (page2 != null) {
            page2.setVisibility(
                    View.VISIBLE
            );
        }
    }

    // =========================================================
    // BACKSPACE SUPPORT
    // =========================================================

    @Override
    public boolean onKeyDown(
            int keyCode,
            KeyEvent event
    ) {

        if (keyCode == KeyEvent.KEYCODE_DEL) {

            InputConnection ic =
                    getCurrentInputConnection();

            if (ic != null) {

                ic.deleteSurroundingText(
                        1,
                        0
                );

                return true;
            }
        }

        return super.onKeyDown(
                keyCode,
                event
        );
    }

    // =========================================================
    // CLEANUP
    // =========================================================

    @Override
    public void onFinishInput() {

        super.onFinishInput();

        shiftOn = false;

        deleteHolding = false;

        if (deleteRunnable != null) {

            deleteHandler.removeCallbacks(
                    deleteRunnable
            );
        }
    }
}
