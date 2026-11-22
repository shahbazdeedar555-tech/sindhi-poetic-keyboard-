package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.widget.PopupMenu;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.SharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Locale;

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard, page1, page2;

    private LinearLayout root;
    private LinearLayout englishPage, englishSymbolsPage;
    private LinearLayout urduPage, urduPage2;
    private LinearLayout emojiPage;
    private LinearLayout suggestionBar;

    private String currentCheckedWord = "";

    private Button languageButton;

    private PersonalDictionary personalDictionary;

    // Custom Literary Phrases
private SharedPreferences phrasePreferences;
private LinearLayout customPhraseContainer;
private LinearLayout phrasePage;
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

    if (personalDictionary == null) {
        personalDictionary = new PersonalDictionary(this);
    }

    keyboard = getLayoutInflater().inflate(
            R.layout.keyboard_view, null);

    root = keyboard.findViewById(R.id.keyboard_root);
    page1 = keyboard.findViewById(R.id.keyboard_page1);
    page2 = keyboard.findViewById(R.id.keyboard_page2);

    setupPage1();
    setupPage2();
    setupNumbers();

    
   createEnglishPage();
createEnglishSymbolsPage();
createUrduPage();
createUrduPage2();
createEmojiPage();


// LANGUAGE BUTTON
createLanguageButton();

// ENGLISH PREDICTION BAR
createSuggestionBar();

showPage1();

    return keyboard;
}



private int dp(int value) {
    return (int) (value *
    getResources().getDisplayMetrics().density + 0.5f);
}

private GradientDrawable makeBackground(
        int color, int radius) {

    GradientDrawable drawable = new GradientDrawable();
    drawable.setColor(color);
    drawable.setCornerRadius(dp(radius));
    drawable.setStroke(dp(1), Color.LTGRAY);

    return drawable;
}

private Button createButton(
        String text,
        LinearLayout parent,
        int backgroundColor,
        int textColor) {

    Button button = new Button(this);

    button.setText(text);
    button.setTextColor(textColor);
    button.setTextSize(18);
    button.setAllCaps(false);
    button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

    button.setBackground(
            makeBackground(backgroundColor, 6));

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    0, dp(48), 1);

    params.setMargins(dp(2), dp(2), dp(2), dp(2));

    parent.addView(button, params);

    return button;
}

private LinearLayout createRow(LinearLayout parent) {

    LinearLayout row = new LinearLayout(this);

    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER);

    parent.addView(row,
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

    return row;
}

private TextView createLabel(
        String text, LinearLayout parent) {

    TextView label = new TextView(this);

    label.setText(text);
    label.setTextSize(18);
    label.setTextColor(black);
    label.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
    label.setGravity(Gravity.CENTER);

    label.setPadding(dp(5), dp(8), dp(5), dp(8));

    parent.addView(label);

    return label;
}

    
private void showPage1() {

    page1.setVisibility(View.VISIBLE);
    page2.setVisibility(View.GONE);

    hideOtherPages();
}

private void showPage2() {

    page1.setVisibility(View.GONE);
    page2.setVisibility(View.VISIBLE);

    hideOtherPages();
}

private void hideSindhiPages() {

    page1.setVisibility(View.GONE);
    page2.setVisibility(View.GONE);
}

private void hideOtherPages() {

    if (englishPage != null)
        englishPage.setVisibility(View.GONE);

    if (englishSymbolsPage != null)
        englishSymbolsPage.setVisibility(View.GONE);

    if (urduPage != null)
        urduPage.setVisibility(View.GONE);

    if (urduPage2 != null)
        urduPage2.setVisibility(View.GONE);

    if (emojiPage != null)
        emojiPage.setVisibility(View.GONE);

    // Hide English predictions
if (suggestionBar != null) {
    suggestionBar.removeAllViews();
    suggestionBar.setVisibility(View.GONE);
}
  }  



private void showEnglishPage() {

    hideSindhiPages();
    hideOtherPages();

    englishPage.setVisibility(View.VISIBLE);

    updatePredictionBar();
}

private void showEnglishSymbolsPage() {

    hideSindhiPages();
    hideOtherPages();

    englishSymbolsPage.setVisibility(View.VISIBLE);

    englishPage.setVisibility(View.VISIBLE);
    updatePredictionBar();
}

private void showUrduPage() {

    hideSindhiPages();
    hideOtherPages();

    urduPage.setVisibility(View.VISIBLE);
}

private void showUrduPage2() {

    hideSindhiPages();
    hideOtherPages();

    urduPage2.setVisibility(View.VISIBLE);
}

private void showEmojiPage() {

    hideSindhiPages();
    hideOtherPages();

    emojiPage.setVisibility(View.VISIBLE);
}


    
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

    // ROW 4 : PUNCTUATION
    setKey(R.id.key_dot, ".");
    setKey(R.id.key_comma, "،");
    setKey(R.id.key_question, "؟");
    setKey(R.id.key_exclamation, "!");
    setKey(R.id.key_colon, ":");
    setKey(R.id.key_semicolon, "؛");
    setKey(R.id.key_quotes, "\"");

    setArrow();

    // ROW 5 : SPECIALS
    setKey(R.id.key_tatweel, "ـ");
    setKey(R.id.key_baha, "بہ");
    setKey(R.id.key_taha, "تہ");
    setKey(R.id.key_noha, "نہ");
    setKey(R.id.key_yeh_alt, "ى");
    setKey(R.id.key_heh_alt, "ہ");
    setKey(R.id.key_waw_hamza, "ؤ");
    setKey(R.id.key_teh_marbuta, "ة");

    // ROW 6 : DIACRITICS
    setKey(R.id.key_zabar1, "َ");
    setKey(R.id.key_zer1, "ِ");
    setKey(R.id.key_pesh1, "ُ");
    setKey(R.id.key_shadd1, "ّ");
    setKey(R.id.key_jazm1, "ْ");
    setKey(R.id.key_alif_khanjari1, "ٰ");
    setKey(R.id.key_alif_khanjari2, "_");
    setKey(R.id.key_alif_khanjari3, "ٗ");
    
// SHIFT: PAGE 1 TO PAGE 2
Button shift1 = keyboard.findViewById(
        R.id.key_shift_bottom);

if (shift1 != null) {
    shift1.setOnClickListener(v -> {
        showPage2();
    });
}
    // ROW 7 : CONTROLS

    
Button delete1 = keyboard.findViewById(
        R.id.key_delete_bottom);

setRepeatDelete(delete1);
    keyboard.findViewById(R.id.key_space_bottom)
            .setOnClickListener(v -> setSpace());

    keyboard.findViewById(R.id.key_enter_bottom)
            .setOnClickListener(v -> setEnter());
}

    

private void setupPage2() {

    // EXTRA SINDHI LETTERS
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

    // NUMBERS
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

    
    // CONTROLS

    // SHIFT: PAGE 2 TO PAGE 1
Button shift2 = keyboard.findViewById(
        R.id.key_shift_bottom2);

if (shift2 != null) {
    shift2.setOnClickListener(v -> {
        showPage1();
    });
}

    // DELETE
    Button delete2 = keyboard.findViewById(
            R.id.key_delete_bottom2);

    setRepeatDelete(delete2);

    // SPACE
    keyboard.findViewById(R.id.key_space_bottom2)
            .setOnClickListener(v -> setSpace());

    
    // ENTER
    keyboard.findViewById(R.id.key_enter_bottom2)
            .setOnClickListener(v -> setEnter());
}


// SETUP NUMBERS
private void setupNumbers() {
    // Numbers are already connected in setupPage2().
}

private void createLanguageButton() {

    languageButton = new Button(this);

    languageButton.setText("🌐  ٻولي / Language");
    languageButton.setTextColor(white);
    languageButton.setTextSize(15);
    languageButton.setAllCaps(false);
    languageButton.setTypeface(
            Typeface.DEFAULT,
            Typeface.BOLD
    );

    languageButton.setBackground(
            makeBackground(green, 8)
    );

    languageButton.setGravity(Gravity.CENTER);

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dp(42)
            );

    params.setMargins(
            dp(3), dp(3), dp(3), dp(3)
    );

    root.addView(languageButton, 0, params);

    languageButton.setOnClickListener(v ->
            openLanguageMenu()
    );
}


// LANGUAGE POPUP MENU

private void openLanguageMenu() {

    if (languageButton == null) return;

    PopupMenu menu = new PopupMenu(
            this,
            languageButton
    );

    menu.getMenu().add("🌐  سنڌي");
    menu.getMenu().add("🇬🇧  English");
    menu.getMenu().add("🇵🇰  اردو");
    menu.getMenu().add("😀  Emoji");
    menu.setOnMenuItemClickListener(item -> {

        String selected = item.getTitle().toString();

        if (selected.contains("سنڌي")) {

            showPage1();
            return true;
        }

        if (selected.contains("English")) {

            showEnglishPage();
            return true;
        }

        if (selected.contains("اردو")) {

            showUrduPage();
            return true;
        }
        if (selected.contains("Emoji")) {
            showEmojiPage();
            return true;
        }

        return false;
    });

    menu.show();
}


    
private void setKey(int id, String text) {

    Button button = keyboard.findViewById(id);

    if (button == null) return;

    button.setOnClickListener(v -> commit(text));
}

private void setNumber(int id, String text) {

    Button button = keyboard.findViewById(id);

    if (button == null) return;

    button.setOnClickListener(v -> commit(text));
}


private void setArrow() {

    Button button = keyboard.findViewById(
            R.id.key_arrow);

    if (button == null) return;

    button.setOnClickListener(v -> {

        InputConnection connection =
                getCurrentInputConnection();

        if (connection != null) {

            connection.sendKeyEvent(
                    new KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_LEFT
                    )
            );

            connection.sendKeyEvent(
                    new KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_LEFT
                    )
            );
        }
    });
}
// =====================================================
// PART 5: ENGLISH QWERTY KEYBOARD
// =====================================================

private void createEnglishPage() {

    englishPage = new LinearLayout(this);
    englishPage.setOrientation(LinearLayout.VERTICAL);
    englishPage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    englishPage.setBackgroundColor(Color.parseColor("#173F35"));
    englishPage.setVisibility(View.GONE);

    addEnglishRow(englishPage, "q", "w", "e", "r", "t", "y", "u", "i", "o", "p");
    addEnglishRow(englishPage, "a", "s", "d", "f", "g", "h", "j", "k", "l");
    addEnglishRow(englishPage, "SHIFT", "z", "x", "c", "v", "b", "n", "m", "DELETE");

    LinearLayout controls = new LinearLayout(this);
    controls.setOrientation(LinearLayout.HORIZONTAL);
    controls.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    addEnglishControl(controls, "?123", 1, v -> showEnglishSymbols());
    addEnglishControl(controls, ",", 1, v -> commit(","));
    addEnglishControl(controls, "SPACE", 4, v -> commit(" "));
    addEnglishControl(controls, ".", 1, v -> commit("."));
    addEnglishControl(controls, "ENTER", 2, v -> setEnter());

    englishPage.addView(controls);
    root.addView(englishPage);
}


// =====================================================
// ENGLISH SYMBOLS PAGE
// =====================================================

private void createEnglishSymbolsPage() {

    englishSymbolsPage = new LinearLayout(this);
    englishSymbolsPage.setOrientation(LinearLayout.VERTICAL);
    englishSymbolsPage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    englishSymbolsPage.setBackgroundColor(Color.parseColor("#173F35"));
    englishSymbolsPage.setVisibility(View.GONE);

    addEnglishRow(englishSymbolsPage, "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    addEnglishRow(englishSymbolsPage, "@", "#", "$", "%", "&", "-", "+", "(", ")");
    addEnglishRow(englishSymbolsPage, "*", "\"", "'", ":", ";", "!", "?", "DELETE");

    LinearLayout controls = new LinearLayout(this);
    controls.setOrientation(LinearLayout.HORIZONTAL);
    controls.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    addEnglishControl(controls, "ABC", 2, v -> displayEnglishPage());
    addEnglishControl(controls, ",", 1, v -> commit(","));
    addEnglishControl(controls, "SPACE", 4, v -> commit(" "));
    addEnglishControl(controls, ".", 1, v -> commit("."));
    addEnglishControl(controls, "ENTER", 2, v -> setEnter());

    englishSymbolsPage.addView(controls);
    root.addView(englishSymbolsPage);
}


// =====================================================
// ENGLISH KEY ROW
// =====================================================

private void addEnglishRow(LinearLayout parent, String... keys) {

    LinearLayout row = new LinearLayout(this);
    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    for (String key : keys) {

        Button button = createEnglishButton(key);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        dpEnglish(46),
                        key.equals("SHIFT") || key.equals("DELETE") ? 1.5f : 1f
                );

        params.setMargins(dpEnglish(1), dpEnglish(2),
                dpEnglish(1), dpEnglish(2));

        row.addView(button, params);
    }

    parent.addView(row);
}


// =====================================================
// CREATE ENGLISH BUTTON
// =====================================================

private Button createEnglishButton(String key) {

    Button button = new Button(this);
    button.setText(key);
    button.setTextSize(17);
    button.setTextColor(Color.WHITE);
    button.setAllCaps(false);
    button.setPadding(0, 0, 0, 0);
    button.setTag(key);

    button.setBackgroundColor(Color.rgb(35, 85, 70));

    if ("DELETE".equals(key)) {

    button.setText("DEL");
    button.setTextSize(15);
    button.setSingleLine(true);
    button.setTranslationY(-dpEnglish(5));

    setRepeatDelete(button);

} else {

    button.setOnClickListener(v -> {

        String value = (String) button.getTag();

        if ("SHIFT".equals(value)) {

            englishShift = !englishShift;
            updateEnglishShift(englishPage);
            return;
        }

        if (value != null) {
            commit(englishShift
                    ? value.toUpperCase(Locale.ROOT)
                    : value);
        }
    });
}

    return button;
}


// =====================================================
// ENGLISH CONTROL BUTTON
// =====================================================

private void addEnglishControl(
        LinearLayout parent,
        String text,
        float weight,
        View.OnClickListener listener) {

    Button button = new Button(this);
    button.setText(text);
    button.setTextSize(13);
    button.setTextColor(Color.WHITE);
    button.setAllCaps(false);
    button.setPadding(0, 0, 0, 0);
    button.setBackgroundColor(Color.rgb(25, 65, 52));
    button.setOnClickListener(listener);

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    0,
                    dpEnglish(46),
                    weight
            );

    params.setMargins(dpEnglish(1), dpEnglish(2),
            dpEnglish(1), dpEnglish(2));

    parent.addView(button, params);
}


// =====================================================
// SHIFT LETTERS
// =====================================================

private void updateEnglishShift(LinearLayout layout) {

    if (layout == null) return;

    for (int i = 0; i < layout.getChildCount(); i++) {

        View child = layout.getChildAt(i);

        if (child instanceof Button) {

            Button button = (Button) child;
            Object tag = button.getTag();

            if (tag instanceof String) {

                String key = (String) tag;

                if (key.length() == 1 &&
                        key.matches("[a-zA-Z]")) {

                    button.setText(
                            englishShift
                                    ? key.toUpperCase()
                                    : key.toLowerCase()
                    );
                }
            }
        }

        if (child instanceof LinearLayout) {
            updateEnglishShift((LinearLayout) child);
        }
    }
}


// =====================================================
// SHOW ENGLISH PAGES
// =====================================================

private void displayEnglishPage() {

    hideSindhiPages();
    hideOtherPages();

    if (englishPage != null) {
        englishPage.setVisibility(View.VISIBLE);
        updatePredictionBar();
    }
}

private void showEnglishSymbols() {

    hideSindhiPages();
    hideOtherPages();

    if (englishSymbolsPage != null) {
        englishSymbolsPage.setVisibility(View.VISIBLE);
    }
}


// =====================================================
// DP CONVERSION
// =====================================================

private int dpEnglish(int value) {

    return (int) (value *
            getResources().getDisplayMetrics().density + 0.5f);
}

// =====================================================
// PART 3: URDU KEYBOARD - PAGE 1
// =====================================================

private void createUrduPage() {

    urduPage = new LinearLayout(this);
    urduPage.setOrientation(LinearLayout.VERTICAL);
    urduPage.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    urduPage.setBackgroundColor(green);
    urduPage.setVisibility(View.GONE);

    // ROW 1
    addUrduRow(urduPage,
            "ق", "و", "ع", "ر", "ت",
            "ے", "ء", "ی", "ہ", "پ");

    // ROW 2
    addUrduRow(urduPage,
            "ا", "س", "د", "ف", "گ",
            "ح", "ج", "ک", "ل");

    // ROW 3
    addUrduRow(urduPage,
            "ز", "ش", "چ", "ط", "ب",
            "ن", "م", "ں", "خ");

    // ROW 4: ADDITIONAL URDU LETTERS
addUrduRow(urduPage,
        "ث", "ص", "ض", "ذ", "ڈ",
        "ڑ", "ٹ", "غ", "آ", "ھ");

    // ROW 5: CONTROLS
    LinearLayout controls = createRow(urduPage);

    Button pageButton = createButton(
            "123", controls, Color.DKGRAY, white);

    pageButton.setOnClickListener(v ->
            showUrduPage2());

    Button commaButton = createButton(
            "،", controls, green, white);

    commaButton.setOnClickListener(v ->
            commit("،"));

    Button spaceButton = createButton(
            "Space", controls, green, white);

    spaceButton.setOnClickListener(v ->
            commit(" "));

    Button deleteButton = createButton(
            "⌫", controls, Color.RED, white);
deleteButton.setTranslationY(-5f);
    setRepeatDelete(deleteButton);

    Button enterButton = createButton(
            "↵", controls, Color.DKGRAY, white);

    enterButton.setOnClickListener(v ->
            setEnter());

    root.addView(urduPage);
}



// =====================================================
// URDU KEYBOARD - PAGE 2
// =====================================================

private void createUrduPage2() {

    urduPage2 = new LinearLayout(this);
    urduPage2.setOrientation(LinearLayout.VERTICAL);
    urduPage2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    urduPage2.setBackgroundColor(green);
    urduPage2.setVisibility(View.GONE);

    // ROW 1: NUMBERS
    addUrduRow(urduPage2,
            "۱", "۲", "۳", "۴", "۵",
            "۶", "۷", "۸", "۹", "۰");

    // ROW 2: SYMBOLS
    addUrduRow(urduPage2,
            "@", "#", "$", "%", "&",
            "-", "+", "(", ")");

    // ROW 3: PUNCTUATION
    addUrduRow(urduPage2,
            ".", "،", "؟", "!", "؛",
            ":", "\"", "'");

    // ROW 4: ADDITIONAL SYMBOLS
    addUrduRow(urduPage2,
            "*", "/", "=", "_", "ـ",
            "٪", "۔", "ء");

    // ROW 5: ADDITIONAL URDU LETTERS
    addUrduRow(urduPage2,
            "ئ", "ؤ", "ٰ");

    // ROW 6: CONTROLS
    LinearLayout controls = createRow(urduPage2);

    Button abcButton = createButton(
            "ABC", controls, Color.DKGRAY, white);

    abcButton.setOnClickListener(v ->
            showUrduPage());

    Button commaButton = createButton(
            "،", controls, green, white);

    commaButton.setOnClickListener(v ->
            commit("،"));

    Button spaceButton = createButton(
            "Space", controls, green, white);

    spaceButton.setOnClickListener(v ->
            commit(" "));

    Button deleteButton = createButton(
            "⌫", controls, Color.RED, white);

    setRepeatDelete(deleteButton);

    Button enterButton = createButton(
            "↵", controls, Color.DKGRAY, white);

    enterButton.setOnClickListener(v ->
            setEnter());

    root.addView(urduPage2);
}


// =====================================================
// URDU KEY ROW
// =====================================================

private void addUrduRow(
        LinearLayout parent, String... keys) {

    LinearLayout row = new LinearLayout(this);

    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    row.setGravity(Gravity.CENTER);

    for (String key : keys) {

        Button button = createButton(
                key, row, Color.rgb(35, 85, 70), white);

        button.setTextSize(19);

        button.setOnClickListener(v ->
                commit(key));
    }

    parent.addView(row);
}

    
// =====================================================
// PART 4: EMOJI KEYBOARD
// =====================================================

private void createEmojiPage() {

    emojiPage = new LinearLayout(this);
    emojiPage.setOrientation(LinearLayout.VERTICAL);
    emojiPage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    emojiPage.setBackgroundColor(green);
    emojiPage.setVisibility(View.GONE);

    // EMOJI CATEGORY TITLE
    TextView title = createLabel(
            "😀  EMOJI KEYBOARD", emojiPage);

    title.setTextColor(white);

    // FACES
    addEmojiRow(emojiPage,
            "😀", "😃", "😄", "😁", "😆",
            "😅", "😂", "🤣", "😊", "😇");

    addEmojiRow(emojiPage,
            "🙂", "🙃", "😉", "😍", "🥰",
            "😘", "😗", "😙", "😚", "😋");

    addEmojiRow(emojiPage,
            "😜", "🤪", "😎", "🥳", "😭",
            "😢", "😡", "😱", "🤔", "🤗");

    // HANDS AND PEOPLE
    addEmojiRow(emojiPage,
            "👍", "👎", "👏", "🙏", "🤝",
            "👌", "✌️", "🤞", "🤟", "👋");

    addEmojiRow(emojiPage,
            "💪", "🫶", "❤️", "🧡", "💛",
            "💚", "💙", "💜", "🖤", "🤍");

    // NATURE AND ANIMALS
    addEmojiRow(emojiPage,
            "🐱", "🐶", "🐭", "🐼", "🦁",
            "🐯", "🐸", "🐵", "🐦", "🦋");

    // FOOD
    addEmojiRow(emojiPage,
            "🍎", "🍌", "🍇", "🍉", "🍓",
            "🍒", "🥭", "🍍", "🍕", "🍔");

    // OBJECTS AND SYMBOLS
    addEmojiRow(emojiPage,
            "⚽", "🏏", "🏆", "🎁", "🎂",
            "📚", "✏️", "📱", "💡", "⭐");

    // CONTROLS
    LinearLayout controls = createRow(emojiPage);

    Button sindhiButton = createButton(
            "سنڌي", controls, Color.DKGRAY, white);

    sindhiButton.setOnClickListener(v -> {
        languageButton.setText("سنڌي ▼");
        showPage1();
    });

    Button englishButton = createButton(
            "English", controls, Color.DKGRAY, white);

    englishButton.setOnClickListener(v -> {
        languageButton.setText("English ▼");
        showEnglishPage();
    });

    Button deleteButton = createButton(
            "⌫", controls, Color.RED, white);

    deleteButton.setOnClickListener(v ->
            deleteOne());

    Button spaceButton = createButton(
            "Space", controls, green, white);

    spaceButton.setOnClickListener(v ->
            commit(" "));

    Button enterButton = createButton(
            "↵", controls, Color.DKGRAY, white);

    enterButton.setOnClickListener(v ->
            setEnter());

    root.addView(emojiPage);
}


// =====================================================
// ADD EMOJI ROW
// =====================================================

private void addEmojiRow(
        LinearLayout parent, String... emojis) {

    LinearLayout row = new LinearLayout(this);

    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER);
    row.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    for (String emoji : emojis) {

        Button button = createButton(
                emoji, row, Color.rgb(35, 85, 70), white);

        button.setTextSize(22);

        button.setOnClickListener(v ->
                commit(emoji));
    }

    parent.addView(row);
}


 // =====================================================
// PART 5 & 6: BASIC KEY ACTIONS
// =====================================================

private void createSuggestionBar() {

    suggestionBar = new LinearLayout(this);
    suggestionBar.setOrientation(LinearLayout.HORIZONTAL);
    suggestionBar.setGravity(Gravity.CENTER);
    suggestionBar.setLayoutDirection(
            View.LAYOUT_DIRECTION_LTR);

    suggestionBar.setBackgroundColor(
            Color.rgb(245, 241, 232));

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dp(38));

    root.addView(suggestionBar, 1, params);
    suggestionBar.setVisibility(View.GONE);
}


    private void updatePredictionBar() {

    if (suggestionBar == null) return;

    suggestionBar.removeAllViews();

    boolean englishMode =
            englishPage != null &&
            englishPage.getVisibility() == View.VISIBLE;

    if (!englishMode) {
        suggestionBar.setVisibility(View.GONE);
        return;
    }

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) {
        suggestionBar.setVisibility(View.GONE);
        return;
    }

    CharSequence before =
            connection.getTextBeforeCursor(100, 0);

    if (before == null) {
        suggestionBar.setVisibility(View.GONE);
        return;
    }

    ArrayList<String> predictions =
            new ArrayList<>(
                    SmartEnglishPrediction.getSuggestions(
                            before.toString()));

    int count = Math.min(3, predictions.size());

    for (int i = 0; i < count; i++) {

        final String prediction = predictions.get(i);

        Button button = new Button(this);
        button.setText(prediction.trim());
        button.setTextSize(14);
        button.setTextColor(green);
        button.setAllCaps(false);
        button.setPadding(dp(3), 0, dp(3), 0);
        button.setBackgroundColor(
                Color.rgb(245, 241, 232));

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        0, dp(36), 1);

        suggestionBar.addView(button, buttonParams);

        button.setOnClickListener(v ->
                applyPrediction(prediction));
    }

    suggestionBar.setVisibility(
            count > 0 ? View.VISIBLE : View.GONE);
}

private void applyPrediction(String prediction) {

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) return;

    CharSequence before =
            connection.getTextBeforeCursor(100, 0);

    if (before == null) return;

    String text = before.toString();

    if (prediction.startsWith(" ")) {

        connection.commitText(
                prediction.trim() + " ", 1);

    } else {

        if (!text.isEmpty() &&
                !Character.isWhitespace(
                        text.charAt(text.length() - 1))) {

            int start = text.length();

            while (start > 0 &&
                    !Character.isWhitespace(
                            text.charAt(start - 1))) {
                start--;
            }

            connection.deleteSurroundingText(
                    text.length() - start, 0);
        }

        connection.commitText(prediction + " ", 1);
    }

    updatePredictionBar();
}

    
// INSERT TEXT WITH ENGLISH AUTO CAPITALIZATION

private void commit(String text) {

    if (text == null) return;

InputConnection connection =
        getCurrentInputConnection();

if (connection == null) {
    android.widget.Toast.makeText(
            this,
            "InputConnection is NULL!",
            android.widget.Toast.LENGTH_LONG
    ).show();
    return;
}
    boolean englishMode =
            (englishPage != null &&
             englishPage.getVisibility() == View.VISIBLE) ||
            (englishSymbolsPage != null &&
             englishSymbolsPage.getVisibility() == View.VISIBLE);

    if (englishMode) {

        if (text.equals(" ") ||
                text.matches("[.,!?;:]")) {

            CharSequence before =
                    connection.getTextBeforeCursor(20, 0);

            if (before != null &&
                    before.toString().matches("(?s).*\\bi$")) {

                connection.deleteSurroundingText(1, 0);
                connection.commitText("I", 1);
            }

            connection.commitText(text, 1);
            updatePredictionBar();
            return;
        }

        if (text.length() == 1 &&
                text.matches("[a-z]") &&
                isSentenceStart(connection)) {

            text = text.toUpperCase(Locale.ROOT);
        }
    }

    connection.commitText(text, 1);
    updatePredictionBar();
}


// CHECK WHETHER THE CURSOR IS AT SENTENCE START
private boolean isSentenceStart(
        InputConnection connection) {

    CharSequence before =
            connection.getTextBeforeCursor(50, 0);

    if (before == null || before.length() == 0) {
        return true;
    }

    String previous = before.toString().trim();

    if (previous.isEmpty()) {
        return true;
    }

    return previous.matches(
            "(?s).*([.!?][\"'”’)]*)$");
}

    // =====================================================
// REPEAT DELETE
// =====================================================

private void setRepeatDelete(Button button) {

    if (button == null) return;

    button.setOnTouchListener((v, event) -> {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                deleteHolding = true;

                deleteOne();

                deleteRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if (deleteHolding) {
                            deleteOne();
                            deleteHandler.postDelayed(this,30 );
                        }
                    }
                };

                deleteHandler.postDelayed(deleteRunnable, 150);

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                deleteHolding = false;

                if (deleteRunnable != null) {
                    deleteHandler.removeCallbacks(deleteRunnable);
                    deleteRunnable = null;
                }

                return true;
        }

        return false;
    });
}

private void deleteOne() {

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) return;

    CharSequence before =
            connection.getTextBeforeCursor(2, 0);

    if (before == null || before.length() == 0) return;

    int length = before.length();

    if (length >= 2) {
        char last = before.charAt(length - 1);
        char previous = before.charAt(length - 2);

        if (Character.isLowSurrogate(last) &&
                Character.isHighSurrogate(previous)) {
            connection.deleteSurroundingText(2, 0);
            updatePredictionBar();
            return;
        }
    }

    connection.deleteSurroundingText(1, 0);
    updatePredictionBar();
}

// ENTER - SUPPORT ALL KEYBOARD LANGUAGES
private void setEnter() {

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) return;

    EditorInfo info = getCurrentInputEditorInfo();

    if (info != null) {

        int action = info.imeOptions &
                EditorInfo.IME_MASK_ACTION;

        if (action != EditorInfo.IME_ACTION_NONE &&
                action != EditorInfo.IME_ACTION_UNSPECIFIED) {

            connection.performEditorAction(action);

        } else {

            connection.commitText("\n", 1);
        }

    } else {

        connection.commitText("\n", 1);
    }

    updatePredictionBar();
}



    
