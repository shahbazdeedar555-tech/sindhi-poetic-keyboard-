
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard, page1, page2;
    private LinearLayout root, englishPage, englishSymbolsPage;
    private LinearLayout urduPage, urduPage2;
    private Button languageButton;

    private boolean shiftOn = false;
    private boolean englishShift = false;
    private boolean urduSecondPage = false;

    private final Handler deleteHandler =
            new Handler(Looper.getMainLooper());

    private Runnable deleteRunnable;
    private boolean deleteHolding = false;
    private Runnable languageDeleteRunnable;
    private boolean languageDeleteHolding = false;

    private final int green = Color.rgb(23, 63, 53);
    private final int black = Color.BLACK;
    private final int white = Color.WHITE;

    @Override
    public View onCreateInputView() {
        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view, null);

        root = keyboard.findViewById(R.id.keyboard_root);
        page1 = keyboard.findViewById(R.id.keyboard_page1);
        page2 = keyboard.findViewById(R.id.keyboard_page2);

        setupPage1();
        setupPage2();
        setupNumbers();

        createLanguageButton();
        createEnglishPage();
        createEnglishSymbolsPage();
        createUrduPage();
        createUrduPage2();

        showPage1();
        return keyboard;
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density);
    }

    // LANGUAGE MENU

    private void createLanguageButton() {
        languageButton = new Button(this);
        languageButton.setText("🌐  ٻولي / Language");
        languageButton.setTextColor(white);
        languageButton.setTextSize(15);
        languageButton.setAllCaps(false);
        languageButton.setBackgroundColor(green);

        root.addView(languageButton, 0,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(42)));

        languageButton.setOnClickListener(v -> openLanguageMenu());
    }

    private void openLanguageMenu() {
        if (languageButton == null) return;

        android.widget.PopupMenu menu =
                new android.widget.PopupMenu(this, languageButton);

        menu.getMenu().add("سنڌي");
        menu.getMenu().add("English");
        menu.getMenu().add("اردو");

        menu.setOnMenuItemClickListener(item -> {
            String selected = item.getTitle().toString();

            if (selected.equals("سنڌي")) {
                showPage1();
            } else if (selected.equals("English")) {
                showEnglishPage();
            } else {
                showUrduPage();
            }
            return true;
        });

        menu.show();
    }

    // SINDHI PAGE 1

    private void setupPage1() {
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

        setArrow(R.id.key_arrow);

        setKey(R.id.key_dot, "۔");
        setKey(R.id.key_comma, "،");
        setKey(R.id.key_question, "؟");
        setKey(R.id.key_exclamation, "!");
        setKey(R.id.key_colon, ":");
        setKey(R.id.key_semicolon, "؛");
        setKey(R.id.key_quotes, "\"");

        setKey(R.id.key_tatweel, "ـ");
        setKey(R.id.key_baha, "بہ");
        setKey(R.id.key_taha, "تہ");
        setKey(R.id.key_noha, "نہ");
        setKey(R.id.key_yeh_alt, "ى");
        setKey(R.id.key_heh_alt, "ہ");
        setKey(R.id.key_waw_hamza, "ؤ");
        setKey(R.id.key_teh_marbuta, "ة");

        setKey(R.id.key_zabar1, "َ");
        setKey(R.id.key_zer1, "ِ");
        setKey(R.id.key_pesh1, "ُ");
        setKey(R.id.key_shadd1, "ّ");
        setKey(R.id.key_jazm1, "ْ");
        setKey(R.id.key_alif_khanjari1, "ٰ");
        setKey(R.id.key_alif_khanjari2, "_");
        setKey(R.id.key_alif_khanjari3, "ٗ");

        setShift(R.id.key_shift_bottom);
        setDelete(R.id.key_delete_bottom);
        setSpace(R.id.key_space_bottom);
        setEnter(R.id.key_enter_bottom);
    }

    // SINDHI PAGE 2

    private void setupPage2() {
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

        setKey(R.id.s13, "۽");
        setKey(R.id.s14, "ح");
        setKey(R.id.s15, "گھ");
        setKey(R.id.s17, "ڊ");
        setKey(R.id.s24, "ش");
        setKey(R.id.s25, "آ");

        setKey(R.id.s18, "۾");
        setKey(R.id.s19, "ڻ");
        setKey(R.id.s20, "ٻ");
        setKey(R.id.s21, "ء");
        setKey(R.id.s22, "ظ");
        setKey(R.id.s23, "ذ");

        setKey(R.id.key_tatweel2, "ـ");
        setKey(R.id.key_baha2, "بہ");
        setKey(R.id.key_taha2, "تہ");
        setKey(R.id.key_noha2, "نہ");
        setKey(R.id.key_yeh_alt2, "ى");
        setKey(R.id.key_heh_alt2, "ہ");
        setKey(R.id.key_waw_hamza2, "ؤ");
        setKey(R.id.key_teh_marbuta2, "ة");

        setKey(R.id.key_zabar2, "َ");
        setKey(R.id.key_zer2, "ِ");
        setKey(R.id.key_pesh2, "ُ");
        setKey(R.id.key_shadd2, "ّ");
        setKey(R.id.key_jazm2, "ْ");
        setKey(R.id.key_alif_khanjari2b, "ٰ");
        setKey(R.id.key_alif_khanjari2c, "ٖ");
        setKey(R.id.key_alif_khanjari2d, "ٗ");

        setShiftBack(R.id.key_shift_bottom2);
        setDelete(R.id.key_delete_bottom2);
        setSpace(R.id.key_space_bottom2);
        setEnter(R.id.key_enter_bottom2);
    }

    // NUMBERS

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

    private void setNumber(int id, String number) {
        View view = keyboard.findViewById(id);
        if (view instanceof Button) {
            view.setOnClickListener(v -> commit(number));
        }
    }

    private void setKey(int id, String text) {
        View view = keyboard.findViewById(id);
        if (!(view instanceof Button)) return;

        view.setOnClickListener(v -> {
            String output = text;
            if (shiftOn && text.equals("ا")) output = "آ";

            commit(output);

            if (shiftOn) {
                shiftOn = false;
                showPage1();
            }
        });
    }

    private void commit(String text) {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) ic.commitText(text, 1);
    }

    // ENGLISH LETTER PAGE

    private void createEnglishPage() {
        englishPage = new LinearLayout(this);
        englishPage.setOrientation(LinearLayout.VERTICAL);
        englishPage.setBackgroundColor(green);
        englishPage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        englishPage.setVisibility(View.GONE);

        root.addView(englishPage,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        addEnglishRow("qwertyuiop");
        addEnglishRow("asdfghjkl");

        LinearLayout third = new LinearLayout(this);
        third.setOrientation(LinearLayout.HORIZONTAL);
        third.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        addSpecialButton(third, "⇧", () -> {
            englishShift = !englishShift;
            refreshEnglishLabels();
        });

        for (char c : "zxcvbnm".toCharArray()) {
            addEnglishLetter(third, String.valueOf(c));
        }

        addSpecialButton(third, "⌫", this::deleteOne);
        englishPage.addView(third);

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        addSpecialButton(bottom, "🌐", this::openLanguageMenu);
        addSpecialButton(bottom, "?123", this::showEnglishSymbolsPage);
        addSpecialButton(bottom, ",", () -> commit(","));
        addSpecialButton(bottom, ".", () -> commit("."));
        addSpecialButton(bottom, "SPACE", () -> commit(" "));
        addSpecialButton(bottom, "ENTER", this::pressEnter);

        englishPage.addView(bottom);
    }

    private void addEnglishRow(String letters) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        for (char c : letters.toCharArray()) {
            addEnglishLetter(row, String.valueOf(c));
        }
        englishPage.addView(row);
    }

    private void addEnglishLetter(LinearLayout row, String letter) {
        Button button = makeButton(letter);
        row.addView(button, new LinearLayout.LayoutParams(0, dp(48), 1));

        button.setOnClickListener(v -> {
            String output = englishShift
                    ? letter.toUpperCase()
                    : letter.toLowerCase();

            commit(output);

            if (englishShift) {
                englishShift = false;
                refreshEnglishLabels();
            }
        });
    }

    private void refreshEnglishLabels() {
        if (englishPage != null) refreshEnglishView(englishPage);
    }

    private void refreshEnglishView(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String label = button.getText().toString();

            if (label.matches("[a-zA-Z]")) {
                button.setText(englishShift
                        ? label.toUpperCase()
                        : label.toLowerCase());
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                refreshEnglishView(group.getChildAt(i));
            }
        }
    }

    // ENGLISH PUNCTUATION PAGE

    private void createEnglishSymbolsPage() {
        englishSymbolsPage = new LinearLayout(this);
        englishSymbolsPage.setOrientation(LinearLayout.VERTICAL);
        englishSymbolsPage.setBackgroundColor(green);
        englishSymbolsPage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        englishSymbolsPage.setVisibility(View.GONE);

        root.addView(englishSymbolsPage,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        addSymbolRow("1234567890");
        addSymbolRow("@#$%&*-+()");
        addSymbolRow(".,?!':;\"/");

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        addSpecialButton(bottom, "ABC", this::showEnglishPage);
        addSpecialButton(bottom, "🌐", this::openLanguageMenu);
        addSpecialButton(bottom, "⌫", this::deleteOne);
        addSpecialButton(bottom, "SPACE", () -> commit(" "));
        addSpecialButton(bottom, "ENTER", this::pressEnter);

        englishSymbolsPage.addView(bottom);
    }

    private void addSymbolRow(String symbols) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        for (int i = 0; i < symbols.length(); i++) {
            String symbol = String.valueOf(symbols.charAt(i));
            Button button = makeButton(symbol);

            row.addView(button,
                    new LinearLayout.LayoutParams(0, dp(48), 1));

            button.setOnClickListener(v -> commit(symbol));
        }

        englishSymbolsPage.addView(row);
    }

    // URDU PAGE 1

    private void createUrduPage() {
        urduPage = new LinearLayout(this);
        urduPage.setOrientation(LinearLayout.VERTICAL);
        urduPage.setBackgroundColor(green);
        urduPage.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        urduPage.setVisibility(View.GONE);

        root.addView(urduPage,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        addUrduRow(urduPage, "ابتپٽث");
        addUrduRow(urduPage, "جچحخدڈ");
        addUrduRow(urduPage, "ذرڑزژس");
        addUrduRow(urduPage, "شصضطظع");
        addUrduRow(urduPage, "غفقکگل");
        addUrduRow(urduPage, "منںوہھ");

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        addSpecialButton(bottom, "⇧", this::showUrduPage2);
        addSpecialButton(bottom, "🌐", this::openLanguageMenu);
        addSpecialButton(bottom, "؟", () -> commit("؟"));
        addSpecialButton(bottom, "،", () -> commit("،"));
        addSpecialButton(bottom, "⌫", this::deleteOne);
        addSpecialButton(bottom, "SPACE", () -> commit(" "));
        addSpecialButton(bottom, "ENTER", this::pressEnter);

        urduPage.addView(bottom);
    }

    // URDU PAGE 2

    private void createUrduPage2() {
        urduPage2 = new LinearLayout(this);
        urduPage2.setOrientation(LinearLayout.VERTICAL);
        urduPage2.setBackgroundColor(green);
        urduPage2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        urduPage2.setVisibility(View.GONE);

        root.addView(urduPage2,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        addUrduRow(urduPage2, "یءے");
        addUrduRow(urduPage2, "آأإٱ");
        addUrduRow(urduPage2, "ؤئئےۓ");
        addUrduRow(urduPage2, "ھہۃة");
        addUrduRow(urduPage2, "ىٰـ");
        addUrduRow(urduPage2, "ًٌٍَُِ");
        addUrduRow(urduPage2, "ّْٕٓٔ");
        addUrduRow(urduPage2, "… ( ) ' \" ! : ؛ ؟ ۔");
        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        addSpecialButton(bottom, "⇧", this::showUrduPage);
        addSpecialButton(bottom, "🌐", this::openLanguageMenu);
        addSpecialButton(bottom, "؟", () -> commit("؟"));
        addSpecialButton(bottom, "،", () -> commit("،"));
        addSpecialButton(bottom, "⌫", this::deleteOne);
        addSpecialButton(bottom, "SPACE", () -> commit(" "));
        addSpecialButton(bottom, "ENTER", this::pressEnter);

        urduPage2.addView(bottom);
    }

    private void addUrduRow(LinearLayout target, String letters) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters.charAt(i));
            Button button = makeButton(letter);

            row.addView(button,
                    new LinearLayout.LayoutParams(0, dp(48), 1));

            button.setOnClickListener(v -> commit(letter));
        }

        target.addView(row);
    }

    // SHARED BUTTONS

    private Button makeButton(String label) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextColor(white);
        button.setTextSize(18);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setPadding(0, 0, 0, 0);

        GradientDrawable background = new GradientDrawable();
        background.setColor(black);
        background.setCornerRadius(dp(4));
        background.setStroke(dp(1), green);
        button.setBackground(background);

        return button;
    }

    private void addSpecialButton(
            LinearLayout row, String label, Runnable action) {

        Button button = makeButton(label);

        if (label.length() > 2) button.setTextSize(12);

        row.addView(button, new LinearLayout.LayoutParams(
                0, dp(48), label.equals("SPACE") ? 2 : 1));

        if (label.equals("⌫")) {
            button.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    languageDeleteHolding = true;
                    deleteOne();

                    languageDeleteRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if (!languageDeleteHolding) return;
                            deleteOne();
                            deleteHandler.postDelayed(this, 45);
                        }
                    };

                    deleteHandler.postDelayed(
                            languageDeleteRunnable, 250);
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_UP
                        || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    languageDeleteHolding = false;

                    if (languageDeleteRunnable != null) {
                        deleteHandler.removeCallbacks(
                                languageDeleteRunnable);
                        languageDeleteRunnable = null;
                    }
                    return true;
                }
                return true;
            });
        } else {
            button.setOnClickListener(v -> action.run());
        }
    }

    // SINDHI SHIFT

    private void setShift(int id) {
        View view = keyboard.findViewById(id);
        if (view != null) {
            view.setOnClickListener(v -> {
                shiftOn = true;
                showPage2();
            });
        }
    }

    private void setShiftBack(int id) {
        View view = keyboard.findViewById(id);
        if (view != null) {
            view.setOnClickListener(v -> {
                shiftOn = false;
                showPage1();
            });
        }
    }

    // SINDHI DELETE

    private void setDelete(int id) {
        View view = keyboard.findViewById(id);
        if (!(view instanceof Button)) return;

        Button button = (Button) view;
        button.setOnClickListener(v -> deleteOne());

        button.setOnLongClickListener(v -> {
            deleteHolding = true;
            deleteOne();

            deleteRunnable = new Runnable() {
                @Override
                public void run() {
                    if (!deleteHolding) return;
                    deleteOne();
                    deleteHandler.postDelayed(this, 70);
                }
            };

            deleteHandler.postDelayed(deleteRunnable, 250);
            return true;
        });

        button.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                deleteHolding = false;

                if (deleteRunnable != null) {
                    deleteHandler.removeCallbacks(deleteRunnable);
                }
            }
            return false;
        });
    }

    private void deleteOne() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) ic.deleteSurroundingText(1, 0);
    }

    private void setSpace(int id) {
        View view = keyboard.findViewById(id);
        if (view != null) view.setOnClickListener(v -> commit(" "));
    }

    private void setEnter(int id) {
        View view = keyboard.findViewById(id);
        if (view != null) view.setOnClickListener(v -> pressEnter());
    }

    private void pressEnter() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.sendKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            ic.sendKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
        }
    }

    private void setArrow(int id) {
        View view = keyboard.findViewById(id);
        if (view != null) {
            view.setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) {
                    ic.sendKeyEvent(new KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_LEFT));
                    ic.sendKeyEvent(new KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_LEFT));
                }
            });
        }
    }

    // SHOW PAGES

    private void hideAllPages() {
        page1.setVisibility(View.GONE);
        page2.setVisibility(View.GONE);
        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.GONE);
    }

    private void showPage1() {
        hideAllPages();
        page1.setVisibility(View.VISIBLE);
        shiftOn = false;
    }

    private void showPage2() {
        hideAllPages();
        page2.setVisibility(View.VISIBLE);
    }

    private void showEnglishPage() {
        hideAllPages();
        englishPage.setVisibility(View.VISIBLE);
    }

    private void showEnglishSymbolsPage() {
        hideAllPages();
        englishSymbolsPage.setVisibility(View.VISIBLE);
    }

    private void showUrduPage() {
        hideAllPages();
        urduPage.setVisibility(View.VISIBLE);
        urduSecondPage = false;
    }

    private void showUrduPage2() {
        hideAllPages();
        urduPage2.setVisibility(View.VISIBLE);
        urduSecondPage = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.deleteSurroundingText(1, 0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();

        shiftOn = false;
        englishShift = false;
        urduSecondPage = false;

        deleteHolding = false;
        languageDeleteHolding = false;

        if (deleteRunnable != null) {
            deleteHandler.removeCallbacks(deleteRunnable);
        }

        if (languageDeleteRunnable != null) {
            deleteHandler.removeCallbacks(languageDeleteRunnable);
            languageDeleteRunnable = null;
        }
    }
}
