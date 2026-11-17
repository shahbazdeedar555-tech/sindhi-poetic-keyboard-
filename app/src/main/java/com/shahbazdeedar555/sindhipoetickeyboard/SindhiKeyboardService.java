

package com.shahbaz555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
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
    private LinearLayout customPhraseSection;

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

        phrasePreferences = getSharedPreferences(
                "literary_phrases", MODE_PRIVATE);

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
        createEmojiPage();

        showPage1();

        return keyboard;
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density);
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
            String text, LinearLayout parent,
            int backgroundColor, int textColor) {

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
    }

    private void showPage2() {

        page1.setVisibility(View.GONE);
        page2.setVisibility(View.VISIBLE);

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
    }

    private void hideSindhiPages() {

        page1.setVisibility(View.GONE);
        page2.setVisibility(View.GONE);
    }

    private void showEnglishPage() {

        hideSindhiPages();

        englishPage.setVisibility(View.VISIBLE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.GONE);
        emojiPage.setVisibility(View.GONE);
    }

    private void showEnglishSymbolsPage() {

        hideSindhiPages();

        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.VISIBLE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.GONE);
        emojiPage.setVisibility(View.GONE);
    }

    private void showUrduPage() {

        hideSindhiPages();

        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.VISIBLE);
        urduPage2.setVisibility(View.GONE);
        emojiPage.setVisibility(View.GONE);
    }

    private void showUrduPage2() {

        hideSindhiPages();

        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.VISIBLE);
        emojiPage.setVisibility(View.GONE);
    }

    private void showEmojiPage() {

        hideSindhiPages();

        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.GONE);
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

    // ROW 7 : CONTROLS
    keyboard.findViewById(R.id.key_shift_bottom)
            .setOnClickListener(v -> {
                shiftOn = !shiftOn;
            });

    keyboard.findViewById(R.id.key_delete_bottom)
            .setOnClickListener(v -> deleteOne());

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
    keyboard.findViewById(R.id.key_shift_bottom2)
            .setOnClickListener(v -> {
                shiftOn = !shiftOn;
            });

    keyboard.findViewById(R.id.key_delete_bottom2)
            .setOnClickListener(v -> deleteOne());

    keyboard.findViewById(R.id.key_space_bottom2)
            .setOnClickListener(v -> setSpace());

    keyboard.findViewById(R.id.key_enter_bottom2)
            .setOnClickListener(v -> setEnter());
}

    

    
private void setupNumbers() {

    LinearLayout row = createRow(page1);

    String[] numbers = {
            "۱", "۲", "۳", "۴", "۵",
            "۶", "۷", "۸", "۹", "۰"
    };

    for (String number : numbers) {

        Button button = createButton(
                number, row, green, white);

        button.setOnClickListener(v ->
                commit(number));
    }
}
    
    

    private void createLanguageButton() {

        languageButton = new Button(this);
        languageButton.setText("سنڌي ▼");
        languageButton.setTextColor(white);
        languageButton.setTextSize(16);
        languageButton.setAllCaps(false);
        languageButton.setBackground(
                makeBackground(green, 8));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(42));

        params.setMargins(dp(3), dp(3), dp(3), dp(3));

        root.addView(languageButton, 0, params);

        languageButton.setOnClickListener(v -> {

            String[] languages = {
                    "سنڌي",
                    "English",
                    "اردو",
                    "Emoji"
            };

            new AlertDialog.Builder(this)
                    .setTitle("Select Language")
                    .setItems(languages, (dialog, which) -> {

                        switch (which) {

                            case 0:
                                languageButton.setText("سنڌي ▼");
                                showPage1();
                                break;

                            case 1:
                                languageButton.setText("English ▼");
                                showEnglishPage();
                                break;

                            case 2:
                                languageButton.setText("اردو ▼");
                                showUrduPage();
                                break;

                            case 3:
                                languageButton.setText("Emoji ▼");
                                showEmojiPage();
                                break;
                        }
                    })
                    .show();
        });
    }

    
