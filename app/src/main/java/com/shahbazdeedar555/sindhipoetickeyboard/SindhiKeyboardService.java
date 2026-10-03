
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;
    private boolean page2 = false;

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
        return keyboard.findViewById(id);
    }

    // =====================================================
    // PAGE 1
    // =====================================================

    private void setupPage1() {

        // اکر — صحيح ترتيب
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

        setKey(R.id.key_hamza_y, "ئ");
        setKey(R.id.key_meem, "م");
        setKey(R.id.key_noon, "ن");
        setKey(R.id.key_bay, "ب");
        setKey(R.id.key_bh, "ڀ");
        setKey(R.id.key_ta2, "ط");
        setKey(R.id.key_kha, "خ");
        setKey(R.id.key_za, "ز");

        // حرڪتون
        setKey(R.id.key_zabar1, "َ");
        setKey(R.id.key_zer1, "ِ");
        setKey(R.id.key_pesh1, "ُ");
        setKey(R.id.key_jazm1, "ْ");
        setKey(R.id.key_shadd1, "ّ");
        setKey(R.id.key_alif_khanjari1, "ٰ");

        // اضافي اکر
        setKey(R.id.key_and, "۽");
        setKey(R.id.key_ma, "۾");
        setKey(R.id.key_waw_hamza, "ؤ");
        setKey(R.id.key_ta_marbuta, "ة");
        setKey(R.id.key_heh, "ہ");
        setKey(R.id.key_alif_maqsura, "ى");
        setKey(R.id.key_alif_khanjari, "ٰ");

        // تطويل
        setKey(R.id.key_tatweel, "ـ");

        // Punctuation
        setKey(R.id.key_underscore, "_");
        setKey(R.id.key_comma, "،");
        setKey(R.id.key_dot, ".");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_semicolon, "؛");
        setKey(R.id.key_exclamation, "!");

        // English Numbers
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

        // Controls
        View shift = find(R.id.key_shift);
        if (shift != null) {
            shift.setOnClickListener(v -> togglePage());
        }

        View enter = find(R.id.key_enter);
        if (enter != null) {
            enter.setOnClickListener(v -> pressEnter());
        }

        View space = find(R.id.key_space);
        if (space != null) {
            space.setOnClickListener(v -> commit(" "));
        }

        setupFastDelete(R.id.key_delete);
        setupSettings(R.id.key_settings);
    }

    // =====================================================
    // PAGE 2
    // =====================================================

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
        setKey(R.id.s13, "ح");
        setKey(R.id.s14, "ڊ");
        setKey(R.id.s15, "ش");
        setKey(R.id.s16, "ڦ");
        setKey(R.id.s17, "آ");

        setKey(R.id.s18, "۾");
        setKey(R.id.s19, "ڻ");
        setKey(R.id.s20, "ٻ");
        setKey(R.id.s21, "ء");
        setKey(R.id.s22, "ظ");
        setKey(R.id.s23, "ذ");

        // حرڪتون
        setKey(R.id.key_zabar, "َ");
        setKey(R.id.key_zer, "ِ");
        setKey(R.id.key_pesh, "ُ");
        setKey(R.id.key_jazm, "ْ");
        setKey(R.id.key_shadd, "ّ");

        // Controls
        View shift = find(R.id.key_shift2);
        if (shift != null) {
            shift.setOnClickListener(v -> togglePage());
        }

        View enter = find(R.id.key_enter2);
        if (enter != null) {
            enter.setOnClickListener(v -> pressEnter());
        }

        View space = find(R.id.key_space2);
        if (space != null) {
            space.setOnClickListener(v -> commit(" "));
        }

        setupFastDelete(R.id.key_delete2);
        setupSettings(R.id.key_settings2);
    }

    // =====================================================
    // NORMAL KEY
    // =====================================================

    private void setKey(int id, String text) {

        View view = find(id);

        if (view instanceof Button) {

            Button button = (Button) view;

            button.setOnClickListener(
                    v -> commit(text)
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

        view.setOnTouchListener(
                (v, event) -> {

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
                    }

                    return true;
                }
        );
    }

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

    // =====================================================
    // COMMIT TEXT
    // =====================================================

    private void commit(String text) {

        InputConnection ic =
                getCurrentInputConnection();

        if (ic != null) {

            ic.commitText(
                    text,
                    1
            );
        }
    }

    // =====================================================
    // ENTER
    // =====================================================

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
    // SETTINGS
    // =====================================================

    private void setupSettings(int id) {

        View view = find(id);

        if (view == null) {
            return;
        }

        view.setOnClickListener(
                v -> openSettings()
        );
    }

    private void openSettings() {

        try {

            Intent intent =
                    new Intent(
                            Settings.ACTION_INPUT_METHOD_SETTINGS
                    );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
            );

            startActivity(intent);

        } catch (Exception ignored) {
        }
    }

    // =====================================================
    // PAGE SWITCH
    // =====================================================

    private void togglePage() {

        if (page2) {

            showPage1();

        } else {

            showPage2();
        }
    }

    private void showPage1() {

        page2 = false;

        View p1 =
                find(R.id.keyboard_page1);

        View p2 =
                find(R.id.keyboard_page2);

        if (p1 != null) {
            p1.setVisibility(View.VISIBLE);
        }

        if (p2 != null) {
            p2.setVisibility(View.GONE);
        }
    }

    private void showPage2() {

        page2 = true;

        View p1 =
                find(R.id.keyboard_page1);

        View p2 =
                find(R.id.keyboard_page2);

        if (p1 != null) {
            p1.setVisibility(View.GONE);
        }

        if (p2 != null) {
            p2.setVisibility(View.VISIBLE);
        }
    }

    // =====================================================
    // NEW INPUT
    // =====================================================

    @Override
    public void onStartInput(
            EditorInfo attribute,
            boolean restarting
    ) {

        super.onStartInput(
                attribute,
                restarting
        );

        page2 = false;

        fastDeleting = false;

        deleteHandler.removeCallbacks(
                deleteRunnable
        );
    }

    // =====================================================
    // DESTROY
    // =====================================================

    @Override
    public void onDestroy() {

        fastDeleting = false;

        deleteHandler.removeCallbacks(
                deleteRunnable
        );

        super.onDestroy();
    }
}
