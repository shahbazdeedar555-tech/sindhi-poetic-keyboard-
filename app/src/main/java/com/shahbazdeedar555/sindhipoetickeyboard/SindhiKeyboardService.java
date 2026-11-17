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

    private View keyboard;
    private LinearLayout page1, page2, root;
    private LinearLayout englishPage, englishSymbolsPage;
    private LinearLayout urduPage, urduPage2, emojiPage;
    private LinearLayout suggestionBar;
    private LinearLayout customPhraseContainer;
    private LinearLayout customPhraseSection;

    private TextView currentCheckedWord;
    private Button languageButton;

    private PersonalDictionary personalDictionary;
    private SharedPreferences phrasePreferences;

    private boolean shiftOn = false;
    private boolean englishShift = false;
    private boolean urduSecondPage = false;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private Runnable deleteRunnable;
    private Runnable languageDeleteRunnable;

    private boolean deleteHolding = false;
    private boolean languageDeleteHolding = false;

    private final int GREEN = Color.rgb(23, 63, 53);
    private final int BLACK = Color.BLACK;
    private final int WHITE = Color.WHITE;

    private final ArrayList<String> defaultLiteraryPhrases =
            new ArrayList<>();

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

    // ---------------- PAGE 1 ----------------

    private void setupPage1() {

        page1.removeAllViews();

        addKeyRow(page1,
                new String[]{"آ", "ا", "ب", "ٻ", "ڀ", "ت", "ٿ", "ٽ", "ٺ"});

        addKeyRow(page1,
                new String[]{"ث", "پ", "ج", "ڄ", "جھ", "چ", "ڇ", "ح", "خ"});

        addKeyRow(page1,
                new String[]{"د", "ڌ", "ڏ", "ڊ", "ڍ", "ذ", "ر", "ڙ", "ز"});

        addKeyRow(page1,
                new String[]{"س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف"});

        addKeyRow(page1,
                new String[]{"ق", "ڪ", "ک", "گ", "ڳ", "ل", "م", "ن", "ڻ"});

        addKeyRow(page1,
                new String[]{"و", "ه", "ء", "ي", "ئ", "ئ", "ۡ"});

        addBottomRow(page1);
    }

    // ---------------- PAGE 2 ----------------

    private void setupPage2() {

        page2.removeAllViews();

        addKeyRow(page2,
                new String[]{"َ", "ِ", "ُ", "ْ", "ّ", "ً", "ٍ", "ٌ"});

        addKeyRow(page2,
                new String[]{"آ", "أ", "إ", "ؤ", "ئ", "ء", "ۂ"});

        addKeyRow(page2,
                new String[]{"،", "؛", "؟", "!", ".", ":", "…"});

        addKeyRow(page2,
                new String[]{"(", ")", "[", "]", "{", "}", "ـ"});

        addKeyRow(page2,
                new String[]{"@", "#", "$", "%", "&", "*", "+"});

        addBottomRow(page2);
    }

    // ---------------- NUMBER ROW ----------------

    private void setupNumbers() {

        // Number keys are shown through the bottom controls.
        // The number page can be expanded later if needed.
    }

    // ---------------- KEY ROW ----------------

    private void addKeyRow(
            LinearLayout parent,
            String[] keys) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);
        row.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(48)));

        for (String key : keys) {

            Button button = createKeyButton(key);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0,
                            dp(43),
                            1f);

            params.setMargins(dp(2), dp(2), dp(2), dp(2));

            row.addView(button, params);
        }

        parent.addView(row);
    }

    // ---------------- KEY BUTTON ----------------

    private Button createKeyButton(String text) {

        Button button = new Button(this);

        button.setText(text);
        button.setTextSize(21);
        button.setTextColor(BLACK);
        button.setAllCaps(false);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setPadding(0, 0, 0, 0);

        GradientDrawable background = new GradientDrawable();
        background.setColor(WHITE);
        background.setCornerRadius(dp(6));
        background.setStroke(dp(1), Color.LTGRAY);

        button.setBackground(background);

        button.setOnClickListener(v -> {

            InputConnection connection =
                    getCurrentInputConnection();

            if (connection != null) {
                connection.commitText(text, 1);
            }
        });

        return button;
    }

    // ---------------- BOTTOM ROW ----------------

    private void addBottomRow(LinearLayout parent) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        row.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(50)));

        addBottomButton(row, "?123", v -> showPage2());
        addBottomButton(row, "🌐", v -> showLanguageMenu());
        addBottomButton(row, "،", v -> commitText("،"));
        addBottomButton(row, "Space", v -> commitText(" "));
        addBottomButton(row, "Enter", v -> pressEnter());
        addBottomButton(row, "⌫", v -> deleteOne());

        parent.addView(row);
    }

    private void addBottomButton(
            LinearLayout row,
            String text,
            View.OnClickListener listener) {

        Button button = new Button(this);

        button.setText(text);
        button.setTextSize(14);
        button.setTextColor(WHITE);
        button.setAllCaps(false);
        button.setBackgroundColor(GREEN);

        button.setOnClickListener(listener);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        dp(44),
                        1f);

        params.setMargins(dp(2), dp(2), dp(2), dp(2));

        row.addView(button, params);
    }

    // ---------------- INPUT HELPERS ----------------

    private void commitText(String text) {

        InputConnection connection =
                getCurrentInputConnection();

        if (connection != null) {
            connection.commitText(text, 1);
        }
    }

    private void deleteOne() {

        InputConnection connection =
                getCurrentInputConnection();

        if (connection != null) {
            connection.deleteSurroundingText(1, 0);
        }
    }

    private void pressEnter() {

        InputConnection connection =
                getCurrentInputConnection();

        if (connection != null) {
            connection.sendKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER));

            connection.sendKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_ENTER));
        }
    }

    // ---------------- PAGE SWITCHING ----------------

    private void showPage1() {

        page1.setVisibility(View.VISIBLE);
        page2.setVisibility(View.GONE);
    }

    private void showPage2() {

        page1.setVisibility(View.GONE);
        page2.setVisibility(View.VISIBLE);
    }

    private int dp(float value) {

        return (int) (value *
                getResources().getDisplayMetrics().density);
    }

    // ---------------- LANGUAGE BUTTON ----------------

    private void createLanguageButton() {

        languageButton = new Button(this);
        languageButton.setText("🌐");
        languageButton.setAllCaps(false);
        languageButton.setTextSize(18);
        languageButton.setBackgroundColor(GREEN);
        languageButton.setTextColor(WHITE);
    }

    private void showLanguageMenu() {

        String[] languages = {
                "سنڌي",
                "English",
                "اردو",
                "Emoji"
        };

        new AlertDialog.Builder(this)
                .setTitle("Select Keyboard")
                .setItems(languages, (dialog, which) -> {

                    switch (which) {

                        case 0:
                            showPage1();
                            break;

                        case 1:
                            showEnglishPage();
                            break;

                        case 2:
                            showUrduPage();
                            break;

                        case 3:
                            showEmojiPage();
                            break;
                    }
                })
                .show();
    }
