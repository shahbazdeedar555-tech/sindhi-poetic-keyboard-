
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    private boolean page2 = false;
    private boolean shiftOn = false;

    private final Handler deleteHandler =
            new Handler(Looper.getMainLooper());

    private boolean fastDeleting = false;

    private final Runnable deleteRunnable = new Runnable() {
        @Override
        public void run() {
            if (fastDeleting) {
                deleteOne();
                deleteHandler.postDelayed(this, 70);
            }
        }
    };

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setupPage1();
        setupPage2();

        showPage1();

        return keyboard;
    }

    private View find(int id) {
        if (keyboard == null) {
            return null;
        }
        return keyboard.findViewById(id);
    }

    // =====================================================
    // PAGE 1
    // =====================================================

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
        setupFastDelete(R.id.key_delete);

        setKey(R.id.key_hamza_y, "ئ");
        setKey(R.id.key_meem, "م");
        setKey(R.id.key_noon, "ن");
        setKey(R.id.key_bay, "ب");
        setKey(R.id.key_bh, "ڀ");
        setKey(R.id.key_ta2, "ط");
        setKey(R.id.key_kha, "خ");
        setKey(R.id.key_za, "ز");

        setupShift(R.id.key_shift);

        // ROW 4
        setupEnter(R.id.key_enter);
        setupArrow(R.id.key_arrow);

        setKey(R.id.key_dot, "۔");
        setKey(R.id.key_comma, "،");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_exclamation, "!");
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_semicolon, "؛");
        setKey(R.id.key_quotes, "\"");

        setupSpace(R.id.key_space);

        // ROW 5 - SPECIAL LETTERS
        setupTatweel(R.id.key_tatweel);

        setKey(R.id.key_baha, "بہ");
        setKey(R.id.key_taha, "تہ");
        setKey(R.id.key_noha, "نہ");
        setKey(R.id.key_yeh_alt, "ى");
        setKey(R.id.key_heh_alt, "ہ");
        setKey(R.id.key_waw_hamza, "ؤ");
        setKey(R.id.key_teh_marbuta, "ة");

        // ROW 6 - DIACRITICS
        setKey(R.id.key_zabar1, "َ");
        setKey(R.id.key_zer1, "ِ");
        setKey(R.id.key_pesh1, "ُ");
        setKey(R.id.key_shadd1, "ّ");
        setKey(R.id.key_jazm1, "ْ");
        setKey(R.id.key_alif_khanjari1, "ٰ");

        setKey(R.id.key_alif_khanjari2, "ٖ");
        setKey(R.id.key_alif_khanjari3, "ٗ");

        // ROW 7 - NUMBERS
        setKey(R.id.key_num0, "0");
        setKey(R.id.key_num9, "9");
        setKey(R.id.key_num8, "8");
        setKey(R.id.key_num7, "7");
        setKey(R.id.key_num6, "6");
        setKey(R.id.key_num5, "5");
        setKey(R.id.key_num4, "4");
        setKey(R.id.key_num3, "3");
        setKey(R.id.key_num2, "2");
        setKey(R.id.key_num1, "1");
    }

    // =====================================================
    // PAGE 2 / SHIFT
    // =====================================================

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
        setKey(R.id.s15, "ڦ");
        setKey(R.id.s17, "ڊ");
        setKey(R.id.s24, "ش");
        setKey(R.id.s25, "آ");

        // ROW 3
        setupFastDelete(R.id.key_delete2);

        setKey(R.id.s18, "۾");
        setKey(R.id.s19, "ڻ");
        setKey(R.id.s20, "ٻ");
        setKey(R.id.s21, "ء");
        setKey(R.id.s22, "ظ");
        setKey(R.id.s23, "ذ");

        setupShift(R.id.key_shift2);

        // ROW 4 - SAME BASIC CONTROLS
        setupEnter(R.id.key_enter2);
        setupArrow(R.id.key_arrow2);

        setKey(R.id.key_dot2, "۔");
        setKey(R.id.key_comma2, "،");
        setKey(R.id.key_question2, "؟");
        setKey(R.id.key_exclamation2, "!");
        setKey(R.id.key_quotes2, "\"");

        setupSpace(R.id.key_space2);

        // ROW 5 - SPECIAL LETTERS
        setupTatweel(R.id.key_tatweel2);

        setKey(R.id.key_baha2, "بہ");
        setKey(R.id.key_taha2, "تہ");
        setKey(R.id.key_noha2, "نہ");
        setKey(R.id.key_yeh_alt2, "ى");
        setKey(R.id.key_heh_alt2, "ہ");
        setKey(R.id.key_waw_hamza2, "ؤ");
        setKey(R.id.key_teh_marbuta2, "ة");

        // ROW 6 - DIACRITICS
        setKey(R.id.key_zabar2, "َ");
        setKey(R.id.key_zer2, "ِ");
        setKey(R.id.key_pesh2, "ُ");
        setKey(R.id.key_shadd2, "ّ");
        setKey(R.id.key_jazm2, "ْ");
        setKey(R.id.key_alif_khanjari2b, "ٰ");
        setKey(R.id.key_alif_khanjari2c, "ٖ");
        setKey(R.id.key_alif_khanjari2d, "ٗ");
    }

    // =====================================================
    // NORMAL KEY
    // =====================================================

    private void setKey(int id, final String text) {

        View view = find(id);

        if (view instanceof Button) {

            Button button = (Button) view;

            button.setOnClickListener(
                    v -> commit(text)
            );
        }
    }

    // =====================================================
    // TATWEEL
    // =====================================================

    private void setupTatweel(int id) {

        View view = find(id);

        if (view != null) {
            view.setOnClickListener(
                    v -> commit("ـ")
            );
        }
    }

    // =====================================================
    // SPACE
    // =====================================================

    private void setupSpace(int id) {

        View view = find(id);

        if (view != null) {
            view.setOnClickListener(
                    v -> commit(" ")
            );
        }
    }

    // =====================================================
    // SHIFT
    // =====================================================

    private void setupShift(int id) {

        View view = find(id);

        if (view == null) {
            return;
        }

        view.setOnClickListener(v -> {

            if (page2) {
                showPage1();
                shiftOn = false;
            } else {
                showPage2();
                shiftOn = true;
            }

            updateShiftButton();
        });
    }

    private void updateShiftButton() {

        View v1 = find(R.id.key_shift);
        View v2 = find(R.id.key_shift2);

        if (v1 instanceof Button) {
            ((Button) v1).setText(
                    shiftOn ? "SHIFT ✓" : "SHIFT"
            );
        }

        if (v2 instanceof Button) {
            ((Button) v2).setText(
                    shiftOn ? "SHIFT ✓" : "SHIFT"
            );
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    private void setupFastDelete(int id) {

        View view = find(id);

        if (view == null) {
            return;
        }

        view.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    deleteOne();

                    fastDeleting = true;

                    deleteHandler.postDelayed(
                            deleteRunnable,
                            350
                    );

                    return true;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    fastDeleting = false;

                    deleteHandler.removeCallbacks(
                            deleteRunnable
                    );

                    return true;

                default:
                    return true;
            }
        });
    }

    private void deleteOne() {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic != null) {
            ic.deleteSurroundingText(1, 0);
        }
    }

    // =====================================================
    // COMMIT
    // =====================================================

    private void commit(String text) {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic != null) {
            ic.commitText(text, 1);
        }
    }

    // =====================================================
    // ENTER
    // =====================================================

    private void setupEnter(int id) {

        View view = find(id);

        if (view != null) {
            view.setOnClickListener(
                    v -> pressEnter()
            );
        }
    }

    private void pressEnter() {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic == null) {
            return;
        }

        EditorInfo info =
                getCurrentInputEditorInfo();

        if (info != null) {

            int action =
                    info.imeOptions
                            & EditorInfo.IME_MASK_ACTION;

            if (action != EditorInfo.IME_ACTION_NONE
                    && action != EditorInfo.IME_ACTION_UNSPECIFIED) {

                ic.performEditorAction(action);
                return;
            }
        }

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

    // =====================================================
    // ARROW
    // =====================================================

    private void setupArrow(int id) {

        View view = find(id);

        if (view != null) {
            view.setOnClickListener(
                    v -> moveCursorLeft()
            );
        }
    }

    private void moveCursorLeft() {

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
    }

    // =====================================================
    // PAGE 1
    // =====================================================

    private void showPage1() {

        page2 = false;

        View p1 = find(R.id.keyboard_page1);
        View p2 = find(R.id.keyboard_page2);

        if (p1 != null) {
            p1.setVisibility(View.VISIBLE);
        }

        if (p2 != null) {
            p2.setVisibility(View.GONE);
        }
    }

    // =====================================================
    // PAGE 2
    // =====================================================

    private void showPage2() {

        page2 = true;

        View p1 = find(R.id.keyboard_page1);
        View p2 = find(R.id.keyboard_page2);

        if (p1 != null) {
            p1.setVisibility(View.GONE);
        }

        if (p2 != null) {
            p2.setVisibility(View.VISIBLE);
        }
    }

    // =====================================================
    // START INPUT
    // =====================================================

    @Override
    public void onStartInput(
            EditorInfo attribute,
            boolean restarting) {

        super.onStartInput(
                attribute,
                restarting
        );

        page2 = false;
        shiftOn = false;
        fastDeleting = false;

        deleteHandler.removeCallbacks(
                deleteRunnable
        );

        if (keyboard != null) {
            showPage1();
            updateShiftButton();
        }
    }

    // =====================================================
    // DESTROY
    // =====================================================

    @Override
    public void onDestroy() {

        fastDeleting = false;
        shiftOn = false;

        deleteHandler.removeCallbacks(
                deleteRunnable
        );

        super.onDestroy();
    }
}
2. keyboard_view.xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/keyboard_root"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:layoutDirection="rtl"
    android:background="#173F35"
    android:padding="2dp">

    <!-- ================================================= -->
    <!-- PAGE 1 -->
    <!-- ================================================= -->

    <LinearLayout
        android:id="@+id/keyboard_page1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layoutDirection="rtl">

        <!-- ROW 1 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_ch" android:text="چ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_chh" android:text="ڇ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_pay" android:text="پ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_waw" android:text="و" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ghain" android:text="ڳ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ain" android:text="ع" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_th" android:text="ٿ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ta1" android:text="ت" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ray" android:text="ر" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_yay" android:text="ي" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_sad" android:text="ص" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_qaf" android:text="ق" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 2 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_ddal" android:text="ڍ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ng" android:text="ڱ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_khay" android:text="ک" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_lam" android:text="ل" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_kaf" android:text="ڪ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_jeem" android:text="ج" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_hay" android:text="ه" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_gaf" android:text="گ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_fay" android:text="ف" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_dal" android:text="د" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_seen" android:text="س" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif" android:text="ا" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 3 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_delete" android:text="DELETE" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_hamza_y" android:text="ئ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_meem" android:text="م" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_noon" android:text="ن" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_bay" android:text="ب" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_bh" android:text="ڀ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_ta2" android:text="ط" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_kha" android:text="خ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_za" android:text="ز" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_shift" android:text="SHIFT" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 4 - PUNCTUATION -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_enter" android:text="ENTER" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_arrow" android:text="←" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_dot" android:text="۔" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_comma" android:text="،" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_question" android:text="؟" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_exclamation" android:text="!" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_colon" android:text=":" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_semicolon" android:text="؛" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_quotes" android:text="&quot;" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_space" android:text="SPACE" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="2" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 5 - SPECIAL -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_tatweel" android:text="ـ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_baha" android:text="بہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_taha" android:text="تہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_noha" android:text="نہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_yeh_alt" android:text="ى" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_heh_alt" android:text="ہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_waw_hamza" android:text="ؤ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_teh_marbuta" android:text="ة" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 6 - DIACRITICS -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_zabar1" android:text="َ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_zer1" android:text="ِ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_pesh1" android:text="ُ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_shadd1" android:text="ّ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_jazm1" android:text="ْ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari1" android:text="ٰ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari2" android:text="ٖ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari3" android:text="ٗ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 7 - NUMBERS -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_num0" android:text="0" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num9" android:text="9" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num8" android:text="8" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num7" android:text="7" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num6" android:text="6" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num5" android:text="5" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num4" android:text="4" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num3" android:text="3" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num2" android:text="2" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_num1" android:text="1" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

    </LinearLayout>


    <!-- ================================================= -->
    <!-- PAGE 2 / SHIFT -->
    <!-- ================================================= -->

    <LinearLayout
        android:id="@+id/keyboard_page2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layoutDirection="rtl"
        android:visibility="gone">

        <!-- ROW 1 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/s1" android:text="ڄ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s2" android:text="ڃ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s16" android:text="ڦ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s4" android:text="ھ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s5" android:text="غ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s6" android:text="ث" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s7" android:text="ٽ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s8" android:text="ڙ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s9" android:text="ض" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s10" android:text="ٺ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s11" android:text="ڌ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s12" android:text="ڏ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 2 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/s13" android:text="۽" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s14" android:text="ح" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s15" android:text="ڦ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s17" android:text="ڊ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s24" android:text="ش" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s25" android:text="آ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 3 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_delete2" android:text="DELETE" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s18" android:text="۾" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s19" android:text="ڻ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s20" android:text="ٻ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s21" android:text="ء" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s22" android:text="ظ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/s23" android:text="ذ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_shift2" android:text="SHIFT" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 4 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_enter2" android:text="ENTER" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_arrow2" android:text="←" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_dot2" android:text="۔" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_comma2" android:text="،" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_question2" android:text="؟" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_exclamation2" android:text="!" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_quotes2" android:text="&quot;" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="0.8" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_space2" android:text="SPACE" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="2" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 5 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_tatweel2" android:text="ـ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.3" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_baha2" android:text="بہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_taha2" android:text="تہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_noha2" android:text="نہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_yeh_alt2" android:text="ى" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_heh_alt2" android:text="ہ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_waw_hamza2" android:text="ؤ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_teh_marbuta2" android:text="ة" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

        <!-- ROW 6 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:orientation="horizontal"
            android:layoutDirection="rtl">

            <Button android:id="@+id/key_zabar2" android:text="َ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_zer2" android:text="ِ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_pesh2" android:text="ُ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_shadd2" android:text="ّ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_jazm2" android:text="ْ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari2b" android:text="ٰ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari2c" android:text="ٖ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
            <Button android:id="@+id/key_alif_khanjari2d" android:text="ٗ" style="@style/KeyboardButton" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:minWidth="0dp" android:padding="0dp"/>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>
