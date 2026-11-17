

package com.shahbaz555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
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

    private LinearLayout customPhraseContainer;

    private SharedPreferences phrasePreferences;

    private boolean englishShift = false;
    private boolean urduSecondPage = false;

    private final Handler handler =
            new Handler(Looper.getMainLooper());

    private final int GREEN = Color.rgb(23, 63, 53);
    private final int BLACK = Color.BLACK;
    private final int WHITE = Color.WHITE;

    private final ArrayList<String> defaultLiteraryPhrases =
            new ArrayList<>();

    // =================================================
    // CREATE KEYBOARD
    // =================================================

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view, null);

        root = keyboard.findViewById(R.id.keyboard_root);
        page1 = keyboard.findViewById(R.id.keyboard_page1);
        page2 = keyboard.findViewById(R.id.keyboard_page2);

        phrasePreferences = getSharedPreferences(
                "literary_phrases", MODE_PRIVATE);

        setupPage1();
        setupPage2();

        setupNumbers();

        createEnglishPage();
        createEnglishSymbolsPage();

        createUrduPage();
        createUrduPage2();

        createEmojiPage();

        attachExtraPages();

        showSindhiPage1();

        return keyboard;
    }

    // =================================================
    // SINDHI PAGE 1
    // =================================================

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

    // =================================================
    // SINDHI PAGE 2
    // =================================================

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

    private void setupNumbers() {
        // Reserved for future number page.
    }

    // =================================================
    // KEY ROW
    // =================================================

    private void addKeyRow(
            LinearLayout parent,
            String[] keys) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        row.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(48)));

        for (String key : keys) {

            Button button = createKeyButton(key);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0, dp(43), 1f);

            params.setMargins(
                    dp(2), dp(2), dp(2), dp(2));

            row.addView(button, params);
        }

        parent.addView(row);
    }

    // =================================================
    // KEY BUTTON
    // =================================================

    private Button createKeyButton(String text) {

        Button button = new Button(this);

        button.setText(text);
        button.setTextSize(21);
        button.setTextColor(BLACK);
        button.setAllCaps(false);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setPadding(0, 0, 0, 0);

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(WHITE);
        background.setCornerRadius(dp(6));
        background.setStroke(dp(1), Color.LTGRAY);

        button.setBackground(background);

        button.setOnClickListener(v -> commitText(text));

        return button;
    }

    // =================================================
    // BOTTOM ROW
    // =================================================

    private void addBottomRow(LinearLayout parent) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        row.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(50)));

        addBottomButton(row, "?123",
                v -> showSindhiPage2());

        addBottomButton(row, "🌐",
                v -> showLanguageMenu());

        addBottomButton(row, "،",
                v -> commitText("،"));

        addBottomButton(row, "Space",
                v -> commitText(" "));

        addBottomButton(row, "Enter",
                v -> pressEnter());

        addBottomButton(row, "⌫",
                v -> deleteOne());

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
                        0, dp(44), 1f);

        params.setMargins(
                dp(2), dp(2), dp(2), dp(2));

        row.addView(button, params);
    }

    // =================================================
    // INPUT HELPERS
    // =================================================

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

            connection.sendKeyEvent(
                    new KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_ENTER));

            connection.sendKeyEvent(
                    new KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_ENTER));
        }
    }

    // =================================================
    // DP
    // =================================================

    private int dp(float value) {

        return (int) (
                value *
                getResources().getDisplayMetrics().density);
    }

    // =================================================
    // LANGUAGE MENU
    // =================================================

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
                            showSindhiPage1();
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

    // =================================================
    // ENGLISH KEYBOARD
    // =================================================

    private void createEnglishPage() {

        englishPage = new LinearLayout(this);

        englishPage.setOrientation(
                LinearLayout.VERTICAL);

        rebuildEnglishPage();
    }

    private void rebuildEnglishPage() {

        englishPage.removeAllViews();

        addEnglishRow("qwertyuiop");
        addEnglishRow("asdfghjkl");
        addEnglishRow("zxcvbnm");

        addEnglishBottomRow();
    }

    private void addEnglishRow(String letters) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        row.setGravity(Gravity.CENTER);

        for (char letter : letters.toCharArray()) {

            String key = String.valueOf(letter);

            String displayKey = englishShift
                    ? key.toUpperCase(Locale.ROOT)
                    : key;

            Button button = createKeyButton(displayKey);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0, dp(43), 1f);

            params.setMargins(
                    dp(2), dp(2), dp(2), dp(2));

            row.addView(button, params);
        }

        englishPage.addView(row);
    }

    private void addEnglishBottomRow() {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        row.setGravity(Gravity.CENTER);

        addBottomButton(row, "⇧", v -> {

            englishShift = !englishShift;

            rebuildEnglishPage();

            showEnglishPage();
        });

        addBottomButton(row, "?123",
                v -> showEnglishSymbolsPage());

        addBottomButton(row, "🌐",
                v -> showLanguageMenu());

        addBottomButton(row, "Space",
                v -> commitText(" "));

        addBottomButton(row, "⌫",
                v -> deleteOne());

        addBottomButton(row, "↵",
                v -> pressEnter());

        englishPage.addView(row);
    }

    // =================================================
    // ENGLISH SYMBOLS
    // =================================================

    private void createEnglishSymbolsPage() {

        englishSymbolsPage = new LinearLayout(this);

        englishSymbolsPage.setOrientation(
                LinearLayout.VERTICAL);

        addKeyRow(englishSymbolsPage,
                new String[]{"1","2","3","4","5","6","7","8","9","0"});

        addKeyRow(englishSymbolsPage,
                new String[]{"@","#","$","%","&","-","+","(",")"});

        addKeyRow(englishSymbolsPage,
                new String[]{"*","\"","'",":",";","!","?","/"});

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        addBottomButton(row, "ABC",
                v -> showEnglishPage());

        addBottomButton(row, "🌐",
                v -> showLanguageMenu());

        addBottomButton(row, "Space",
                v -> commitText(" "));

        addBottomButton(row, "⌫",
                v -> deleteOne());

        addBottomButton(row, "↵",
                v -> pressEnter());

        englishSymbolsPage.addView(row);
    }

    // =================================================
    // URDU PAGE 1
    // =================================================

    private void createUrduPage() {

        urduPage = new LinearLayout(this);

        urduPage.setOrientation(
                LinearLayout.VERTICAL);

        addKeyRow(urduPage,
                new String[]{"ق","و","ع","ر","ت","ے","ء","ی","ہ","پ"});

        addKeyRow(urduPage,
                new String[]{"ا","س","د","ف","گ","ھ","ج","ک","ل"});

        addKeyRow(urduPage,
                new String[]{"ز","ش","چ","ط","ب","ن","م"});

        addUrduBottomRow(urduPage);
    }

    // =================================================
    // URDU PAGE 2
    // =================================================

    private void createUrduPage2() {

        urduPage2 = new LinearLayout(this);

        urduPage2.setOrientation(
                LinearLayout.VERTICAL);

        addKeyRow(urduPage2,
                new String[]{"آ","أ","إ","ؤ","ئ","ء","ة","ۃ"});

        addKeyRow(urduPage2,
                new String[]{"،","؛","؟","!","۔","(",")"});

        addKeyRow(urduPage2,
                new String[]{"۰","۱","۲","۳","۴","۵","۶","۷","۸","۹"});

        addUrduBottomRow(urduPage2);
    }

    private void addUrduBottomRow(LinearLayout parent) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        addBottomButton(row,
                parent == urduPage ? "صفحہ 2" : "صفحہ 1",
                v -> {

                    if (parent == urduPage) {
                        showUrduPage2();
                    } else {
                        showUrduPage();
                    }
                });

        addBottomButton(row, "🌐",
                v -> showLanguageMenu());

        addBottomButton(row, "Space",
                v -> commitText(" "));

        addBottomButton(row, "⌫",
                v -> deleteOne());

        addBottomButton(row, "↵",
                v -> pressEnter());

        parent.addView(row);
    }

    // =================================================
    // EMOJI PAGE
    // =================================================

    private void createEmojiPage() {

        emojiPage = new LinearLayout(this);

        emojiPage.setOrientation(
                LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);

        LinearLayout content = new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL);

        content.setPadding(
                dp(5), dp(5), dp(5), dp(5));

        scrollView.addView(content);

        addPhraseHeading(content, "😊 Emojis");

        addEmojiRow(content,
                new String[]{"😀","😃","😄","😁","😆","😊","🙂","😉"});

        addEmojiRow(content,
                new String[]{"😍","🥰","😘","😎","🤗","🤔","😢","😭"});

        addEmojiRow(content,
                new String[]{"😂","🤣","😇","🙏","👏","👍","❤️","🌹"});

        addPhraseHeading(content, "🌹 ادبي داد");

        customPhraseContainer = new LinearLayout(this);

        customPhraseContainer.setOrientation(
                LinearLayout.VERTICAL);

        content.addView(customPhraseContainer);

        addPhraseHeading(content, "سلام جا جملا");

        addGreetingRow(content);

        emojiPage.addView(scrollView);

        loadDefaultLiteraryPhrases();

        loadCustomPhrases();
    }

    // =================================================
    // EMOJI ROW
    // =================================================

    private void addEmojiRow(
            LinearLayout parent,
            String[] emojis) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        for (String emoji : emojis) {

            Button button = createKeyButton(emoji);

            button.setTextSize(23);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0, dp(45), 1f);

            params.setMargins(
                    dp(2), dp(2), dp(2), dp(2));

            row.addView(button, params);
        }

        parent.addView(row);
    }

    // =================================================
    // PHRASE HEADING
    // =================================================

    private void addPhraseHeading(
            LinearLayout parent,
            String heading) {

        TextView title = new TextView(this);

        title.setText(heading);
        title.setTextSize(19);
        title.setTextColor(GREEN);

        title.setTypeface(
                null, Typeface.BOLD);

        title.setGravity(Gravity.CENTER);

        title.setPadding(
                dp(5), dp(10), dp(5), dp(10));

        parent.addView(title);
    }

    // =================================================
    // GREETING PHRASES
    // =================================================

    private void addGreetingRow(LinearLayout parent) {

        addPhraseButton(parent, "اسلام عليڪم");

        addPhraseButton(parent, "وعليڪم السلام");

        addPhraseButton(parent, "يا علي مدد");
    }

    // =================================================
    // DEFAULT LITERARY PHRASES
    // =================================================

    private void loadDefaultLiteraryPhrases() {

        defaultLiteraryPhrases.clear();

        defaultLiteraryPhrases.add(
                "وااااهه! وااااه! ڇا ته غزل آهي!");

        defaultLiteraryPhrases.add(
                "تمام خوبصورت شاعري!");

        defaultLiteraryPhrases.add(
                "دل کي ڇهندڙ لفظ!");

        defaultLiteraryPhrases.add(
                "لاجواب! بي مثال!");

        defaultLiteraryPhrases.add(
                "واه! ڇا ته خيال آهي!");

        defaultLiteraryPhrases.add(
                "لفظن ۾ احساسن جو سمنڊ!");

        defaultLiteraryPhrases.add(
                "تمام بهترين تخليق!");
    }

    // =================================================
    // PHRASE BUTTON
    // =================================================

    private void addPhraseButton(
            LinearLayout parent,
            String phrase) {

        Button button = new Button(this);

        button.setText(phrase);
        button.setTextSize(16);
        button.setTextColor(WHITE);
        button.setAllCaps(false);
        button.setBackgroundColor(GREEN);

        button.setOnClickListener(
                v -> commitText(phrase + " "));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(
                dp(3), dp(3), dp(3), dp(3));

        parent.addView(button, params);
    }

    // =================================================
    // ADD NEW PHRASE BUTTON
    // =================================================

    private void createAddPhraseButton() {

        Button addButton = new Button(this);

        addButton.setText(
                "➕ نئون ادبي جملو شامل ڪريو");

        addButton.setTextColor(WHITE);

        addButton.setAllCaps(false);

        addButton.setBackgroundColor(GREEN);

        addButton.setOnClickListener(
                v -> showAddPhraseDialog());

        customPhraseContainer.addView(addButton);
    }

    // =================================================
    // ADD PHRASE DIALOG
    // =================================================

    private void showAddPhraseDialog() {

        EditText input = new EditText(this);

        input.setHint("پنهنجو ادبي جملو لکو");

        input.setTextSize(18);

        new AlertDialog.Builder(this)

                .setTitle("نئون ادبي جملو")

                .setView(input)

                .setPositiveButton("Save", (dialog, which) -> {

                    String phrase = input.getText()
                            .toString().trim();

                    if (!phrase.isEmpty()) {

                        saveCustomPhrase(phrase);

                        loadCustomPhrases();
                    }
                })

                .setNegativeButton("Cancel", null)

                .show();
    }

    // =================================================
    // SAVE CUSTOM PHRASE
    // =================================================

    private void saveCustomPhrase(String phrase) {

        try {

            JSONArray phrases = new JSONArray(
                    phrasePreferences.getString(
                            "custom_phrases", "[]"));

            phrases.put(phrase);

            phrasePreferences.edit()

                    .putString(
                            "custom_phrases",
                            phrases.toString())

                    .apply();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =================================================
    // LOAD CUSTOM PHRASES
    // =================================================

    private void loadCustomPhrases() {

        if (customPhraseContainer == null) {
            return;
        }

        customPhraseContainer.removeAllViews();

        // Default phrases

        for (String phrase : defaultLiteraryPhrases) {

            addPhraseButton(
                    customPhraseContainer, phrase);
        }

        // User-added phrases

        try {

            JSONArray phrases = new JSONArray(
                    phrasePreferences.getString(
                            "custom_phrases", "[]"));

            for (int i = 0; i < phrases.length(); i++) {

                String phrase = phrases.getString(i);

                addCustomPhraseButton(
                        customPhraseContainer, phrase);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        createAddPhraseButton();
    }

    // =================================================
    // CUSTOM PHRASE BUTTON
    // =================================================

    private void addCustomPhraseButton(
            LinearLayout parent,
            String phrase) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        row.setGravity(
                Gravity.CENTER_VERTICAL);

        Button phraseButton = new Button(this);

        phraseButton.setText(phrase);

        phraseButton.setTextSize(15);

        phraseButton.setTextColor(WHITE);

        phraseButton.setAllCaps(false);

        phraseButton.setBackgroundColor(GREEN);

        phraseButton.setOnClickListener(
                v -> commitText(phrase + " "));

        Button deleteButton = new Button(this);

        deleteButton.setText("✖");

        deleteButton.setTextColor(WHITE);

        deleteButton.setBackgroundColor(Color.RED);

        deleteButton.setOnClickListener(
                v -> confirmDeletePhrase(phrase));

        row.addView(
                phraseButton,
                new LinearLayout.LayoutParams(
                        0, dp(48), 1f));

        row.addView(
                deleteButton,
                new LinearLayout.LayoutParams(
                        dp(55), dp(48)));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(
                dp(3), dp(3), dp(3), dp(3));

        parent.addView(row, params);
    }

    // =================================================
    // CONFIRM DELETE
    // =================================================

    private void confirmDeletePhrase(String phrase) {

        new AlertDialog.Builder(this)

                .setTitle("جملو ختم ڪريو؟")

                .setMessage(phrase)

                .setPositiveButton("Delete", (dialog, which) -> {

                    deleteCustomPhrase(phrase);

                    loadCustomPhrases();
                })

                .setNegativeButton("Cancel", null)

                .show();
    }

    // =================================================
    // DELETE CUSTOM PHRASE
    // =================================================

    private void deleteCustomPhrase(String phrase) {

        try {

            JSONArray oldPhrases = new JSONArray(
                    phrasePreferences.getString(
                            "custom_phrases", "[]"));

            JSONArray newPhrases = new JSONArray();

            boolean deleted = false;

            for (int i = 0; i < oldPhrases.length(); i++) {

                String item = oldPhrases.getString(i);

                if (!deleted && item.equals(phrase)) {

                    deleted = true;

                } else {

                    newPhrases.put(item);
                }
            }

            phrasePreferences.edit()

                    .putString(
                            "custom_phrases",
                            newPhrases.toString())

                    .apply();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =================================================
    // ATTACH EXTRA PAGES
    // =================================================

    private void attachExtraPages() {

        if (root == null) {
            return;
        }

        if (englishPage.getParent() == null) {
            root.addView(englishPage);
        }

        if (englishSymbolsPage.getParent() == null) {
            root.addView(englishSymbolsPage);
        }

        if (urduPage.getParent() == null) {
            root.addView(urduPage);
        }

        if (urduPage2.getParent() == null) {
            root.addView(urduPage2);
        }

        if (emojiPage.getParent() == null) {
            root.addView(emojiPage);
        }

        hideExtraPages();
    }

    // =================================================
    // HIDE EXTRA PAGES
    // =================================================

    private void hideExtraPages() {

        if (englishPage != null) {
            englishPage.setVisibility(View.GONE);
        }

        if (englishSymbolsPage != null) {
            englishSymbolsPage.setVisibility(View.GONE);
        }

        if (urduPage != null) {
            urduPage.setVisibility(View.GONE);
        }

        if (urduPage2 != null) {
            urduPage2.setVisibility(View.GONE);
        }

        if (emojiPage != null) {
            emojiPage.setVisibility(View.GONE);
        }
    }

    // =================================================
    // SHOW ONLY SELECTED PAGE
    // =================================================

    private void showOnly(View selectedPage) {

        page1.setVisibility(View.GONE);

        page2.setVisibility(View.GONE);

        hideExtraPages();

        selectedPage.setVisibility(View.VISIBLE);
    }

    // =================================================
    // SHOW SINDHI PAGE 1
    // =================================================

    private void showSindhiPage1() {

        hideExtraPages();

        page1.setVisibility(View.VISIBLE);

        page2.setVisibility(View.GONE);
    }

    // =================================================
    // SHOW SINDHI PAGE 2
    // =================================================

    private void showSindhiPage2() {

        hideExtraPages();

        page1.setVisibility(View.GONE);

        page2.setVisibility(View.VISIBLE);
    }

    // =================================================
    // SHOW ENGLISH PAGE
    // =================================================

    private void showEnglishPage() {

        showOnly(englishPage);
    }

    // =================================================
    // SHOW ENGLISH SYMBOLS
    // =================================================

    private void showEnglishSymbolsPage() {

        showOnly(englishSymbolsPage);
    }

    // =================================================
    // SHOW URDU PAGE
    // =================================================

    private void showUrduPage() {

        urduSecondPage = false;

        showOnly(urduPage);
    }

    // =================================================
    // SHOW URDU PAGE 2
    // =================================================

    private void showUrduPage2() {

        urduSecondPage = true;

        showOnly(urduPage2);
    }

    // =================================================
    // SHOW EMOJI PAGE
    // =================================================

    private void showEmojiPage() {

        showOnly(emojiPage);
    }

    // =================================================
    // INPUT START
    // =================================================

    @Override
    public void onStartInput(EditorInfo attribute,
                             boolean restarting) {

        super.onStartInput(attribute, restarting);

        englishShift = false;

        urduSecondPage = false;
    }

    // =================================================
    // INPUT VIEW START
    // =================================================

    @Override
    public void onStartInputView(EditorInfo info,
                                 boolean restarting) {

        super.onStartInputView(info, restarting);

        attachExtraPages();

        showSindhiPage1();
    }

    // =================================================
    // CLEANUP
    // =================================================

    @Override
    public void onDestroy() {

        handler.removeCallbacksAndMessages(null);

        super.onDestroy();
    }
}
