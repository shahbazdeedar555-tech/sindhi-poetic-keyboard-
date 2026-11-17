

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

        page1.setOrientation(LinearLayout.VERTICAL);

        String[][] rows = {
                {"آ", "ا", "ب", "ٻ", "پ", "ت", "ٿ"},
                {"ٽ", "ث", "ج", "ڄ", "چ", "ڇ", "ح"},
                {"خ", "د", "ڌ", "ڏ", "ڊ", "ڍ", "ذ"},
                {"ر", "ڙ", "ز", "س", "ش", "ص", "ض"}
        };

        for (String[] letters : rows) {

            LinearLayout row = createRow(page1);

            for (String letter : letters) {

                Button button = createButton(
                        letter, row, white, black);

                button.setOnClickListener(v ->
                        commit(letter));
            }
        }

        LinearLayout bottom = createRow(page1);

        createButton("؟", bottom, green, white)
                .setOnClickListener(v -> commit("؟"));

        createButton("،", bottom, green, white)
                .setOnClickListener(v -> commit("،"));

        createButton("صفحو 2", bottom, green, white)
                .setOnClickListener(v -> showPage2());

        createButton("Space", bottom, green, white)
                .setOnClickListener(v -> commit(" "));

        createButton("⌫", bottom, green, white)
                .setOnClickListener(v -> deleteOne());
    }

    private void setupPage2() {

        page2.setOrientation(LinearLayout.VERTICAL);

        String[][] rows = {
                {"ط", "ظ", "ع", "غ", "ف", "ڦ", "ق"},
                {"ڪ", "ک", "گ", "ڳ", "ڱ", "ل", "م"},
                {"ن", "ڻ", "و", "ه", "ء", "ي", "ئ"},
                {"ڀ", "ڃ", "ڄ", "ڊ", "ڙ", "ھ", "ؤ"}
        };

        for (String[] letters : rows) {

            LinearLayout row = createRow(page2);

            for (String letter : letters) {

                Button button = createButton(
                        letter, row, white, black);

                button.setOnClickListener(v ->
                        commit(letter));
            }
        }

        LinearLayout bottom = createRow(page2);

        createButton("صفحو 1", bottom, green, white)
                .setOnClickListener(v -> showPage1());

        createButton("Space", bottom, green, white)
                .setOnClickListener(v -> commit(" "));

        createButton("⌫", bottom, green, white)
                .setOnClickListener(v -> deleteOne());

        createButton("Enter", bottom, green, white)
                .setOnClickListener(v -> commit("\n"));
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

    
