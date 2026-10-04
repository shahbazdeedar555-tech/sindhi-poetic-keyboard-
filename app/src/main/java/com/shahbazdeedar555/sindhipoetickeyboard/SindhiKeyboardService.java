package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;
    private boolean shiftActive = false;

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupKeys();

        return keyboard;
    }

    private void setupKeys() {

        // =====================================================
        // PAGE 1 — سنڌي اکر
        // =====================================================

        setKey(R.id.key_alif, "چ");
        setKey(R.id.key_bay, "ڇ");
        setKey(R.id.key_bay2, "پ");
        setKey(R.id.key_pay, "و");
        setKey(R.id.key_bhe, "ڳ");
        setKey(R.id.key_te, "ع");
        setKey(R.id.key_tay, "ٿ");
        setKey(R.id.key_ray, "ت");
        setKey(R.id.key_jeem, "ر");
        setKey(R.id.key_ye, "ي");
        setKey(R.id.key_se, "ص");
        setKey(R.id.key_qaf, "ق");

        setKey(R.id.key_dal, "ڍ");
        setKey(R.id.key_dhal, "ڱ");
        setKey(R.id.key_kaf, "ک");
        setKey(R.id.key_lam, "ل");
        setKey(R.id.key_kaf2, "ڪ");
        setKey(R.id.key_jim, "ج");
        setKey(R.id.key_he, "ه");
        setKey(R.id.key_gaf, "گ");
        setKey(R.id.key_fay, "ف");
        setKey(R.id.key_dad, "د");
        setKey(R.id.key_seen, "س");
        setKey(R.id.key_alif2, "ا");

        setKey(R.id.key_alif_mad, "ئ");
        setKey(R.id.key_meem, "م");
        setKey(R.id.key_noon, "ن");
        setKey(R.id.key_bay3, "ب");
        setKey(R.id.key_bhe2, "ڀ");
        setKey(R.id.key_tay2, "ط");
        setKey(R.id.key_khay, "خ");
        setKey(R.id.key_zay, "ز");


        // =====================================================
        // PAGE 1 — خاص نشانيون
        // =====================================================

        setSpecialKey(R.id.key_zabar, "َ");
        setSpecialKey(R.id.key_zer, "ِ");
        setSpecialKey(R.id.key_pesh, "ُ");
        setSpecialKey(R.id.key_shad, "ّ");
        setSpecialKey(R.id.key_jazm, "ْ");
        setSpecialKey(R.id.key_khanjari, "ٰ");


        // =====================================================
        // PUNCTUATION
        // =====================================================

        setSpecialKey(R.id.key_dot, ".");
        setSpecialKey(R.id.key_comma, "،");
        setSpecialKey(R.id.key_question, "؟");
        setSpecialKey(R.id.key_quotes, "“”");
        setSpecialKey(R.id.key_colon, ":");
        setSpecialKey(R.id.key_exclamation, "!");


        // =====================================================
        // LONG ALIF
        // =====================================================

        setSpecialKey(R.id.key_long_alif, "ٰ");


        // =====================================================
        // SHIFT PAGE
        // =====================================================

        setSpecialKey(R.id.key_jeem2, "ڄ");
        setSpecialKey(R.id.key_jeem3, "ڃ");
        setSpecialKey(R.id.key_fay2, "ڦ");
        setSpecialKey(R.id.key_he2, "ھ");
        setSpecialKey(R.id.key_ghain, "غ");
        setSpecialKey(R.id.key_say, "ث");
        setSpecialKey(R.id.key_tay3, "ٽ");
        setSpecialKey(R.id.key_ray2, "ڙ");
        setSpecialKey(R.id.key_dad2, "ض");
        setSpecialKey(R.id.key_tay4, "ٺ");
        setSpecialKey(R.id.key_dhal2, "ڌ");
        setSpecialKey(R.id.key_dal2, "ڏ");
        setSpecialKey(R.id.key_and, "۽");
        setSpecialKey(R.id.key_he3, "ح");
        setSpecialKey(R.id.key_fay3, "ڦ");
        setSpecialKey(R.id.key_dal3, "ڊ");
        setSpecialKey(R.id.key_sheen, "ش");
        setSpecialKey(R.id.key_alif_mad2, "آ");

        setSpecialKey(R.id.key_meem2, "۾");
        setSpecialKey(R.id.key_noon2, "ڻ");
        setSpecialKey(R.id.key_bay4, "ٻ");
        setSpecialKey(R.id.key_hamza, "ء");
        setSpecialKey(R.id.key_zo, "ظ");
        setSpecialKey(R.id.key_zal, "ذ");


        // =====================================================
        // DELETE
        // =====================================================

        Button delete = keyboard.findViewById(R.id.key_delete);

        if (delete != null) {
            delete.setOnClickListener(v -> deleteText());
        }


        // =====================================================
        // SHIFT
        // =====================================================

        Button shift = keyboard.findViewById(R.id.key_shift);

        if (shift != null) {

            shift.setOnClickListener(v -> {

                shiftActive = !shiftActive;

                if (shiftActive) {
                    showShiftPage();
                } else {
                    showPage1();
                }
            });
        }


        // =====================================================
        // ENTER
        // =====================================================

        Button enter = keyboard.findViewById(R.id.key_enter);

        if (enter != null) {

            enter.setOnClickListener(v -> {

                InputConnection ic = getCurrentInputConnection();

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


        // =====================================================
        // SPACE
        // =====================================================

        Button space = keyboard.findViewById(R.id.key_space);

        if (space != null) {

            space.setOnClickListener(v -> {

                InputConnection ic = getCurrentInputConnection();

                if (ic != null) {
                    ic.commitText(" ", 1);
                }
            });
        }
    }


    // =========================================================
    // عام سنڌي اکر
    // =========================================================

    private void setKey(int id, String text) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setOnClickListener(v -> {

            InputConnection ic = getCurrentInputConnection();

            if (ic != null) {
                ic.commitText(text, 1);
            }
        });
    }


    // =========================================================
    // خاص نشاني
    // =========================================================

    private void setSpecialKey(int id, String text) {

        Button button = keyboard.findViewById(id);

        if (button == null) {
            return;
        }

        button.setOnClickListener(v -> {

            InputConnection ic = getCurrentInputConnection();

            if (ic != null) {
                ic.commitText(text, 1);
            }
        });
    }


    // =========================================================
    // DELETE / BACKSPACE
    // =========================================================

    private void deleteText() {

        InputConnection ic = getCurrentInputConnection();

        if (ic == null) {
            return;
        }

        CharSequence selected = ic.getSelectedText(0);

        if (selected != null && selected.length() > 0) {
            ic.commitText("", 0);
            return;
        }

        CharSequence before = ic.getTextBeforeCursor(1, 0);

        if (before != null && before.length() > 0) {
            ic.deleteSurroundingText(1, 0);
        }
    }


    // =========================================================
    // PAGE 1
    // =========================================================

    private void showPage1() {

        View page1 = keyboard.findViewById(R.id.keyboard_page1);
        View page2 = keyboard.findViewById(R.id.keyboard_page2);

        if (page1 != null) {
            page1.setVisibility(View.VISIBLE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }
    }


    // =========================================================
    // SHIFT PAGE
    // =========================================================

    private void showShiftPage() {

        View page1 = keyboard.findViewById(R.id.keyboard_page1);
        View page2 = keyboard.findViewById(R.id.keyboard_page2);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.VISIBLE);
        }
    }


    // =========================================================
    // نئون input شروع ٿيڻ وقت Page 1
    // =========================================================

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {

        super.onStartInput(attribute, restarting);

        shiftActive = false;

        if (keyboard != null) {
            showPage1();
        }
    }


    // =========================================================
    // input ختم
    // =========================================================

    @Override
    public void onFinishInput() {

        super.onFinishInput();

        shiftActive = false;
    }
}
