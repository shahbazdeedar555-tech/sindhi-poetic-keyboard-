package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService; import android.os.Handler; import android.os.Looper; import android.graphics.Color; import android.graphics.drawable.GradientDrawable; import android.view.Gravity; import android.view.KeyEvent; import android.view.MotionEvent; import android.view.View; import android.view.ViewGroup; import android.view.inputmethod.InputConnection; import android.view.inputmethod.EditorInfo; import android.widget.Button; import android.widget.LinearLayout; import android.widget.ScrollView;    import java.util.Locale;
import java.util.ArrayList;
public class SindhiKeyboardService extends InputMethodService
         {
    private View keyboard, page1, page2;
    private LinearLayout root, englishPage, englishSymbolsPage;
    
    private LinearLayout urduPage, urduPage2;

    private LinearLayout emojiPage;         
    private LinearLayout suggestionBar;
         
    private String currentCheckedWord = "";
    private Button languageButton;
    private PersonalDictionary personalDictionary;          

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

        languageButton.setOnClickListener(
                v -> openLanguageMenu());
    }

    private void openLanguageMenu() {
        if (languageButton == null) return;

        android.widget.PopupMenu menu =
                new android.widget.PopupMenu(this, languageButton);

        menu.getMenu().add("سنڌي");
        menu.getMenu().add("English");
        menu.getMenu().add("اردو");
         menu.getMenu().add("😀 Emoji");    

        menu.setOnMenuItemClickListener(item -> {
            String selected = item.getTitle().toString();

            if (selected.equals("سنڌي")) {
                showPage1();
            } else if (selected.equals("English")) {
                showEnglishPage();
            } else if (selected.equals("اردو")) {
    showUrduPage();
} else if (selected.equals("😀 Emoji")) {
    showEmojiPage();
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

        setKey(R.id.key_dot, ".");
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
            

            // English spelling suggestion bar
suggestionBar = new LinearLayout(this);
suggestionBar.setOrientation(LinearLayout.HORIZONTAL);
suggestionBar.setGravity(Gravity.CENTER);
suggestionBar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
suggestionBar.setBackgroundColor(green);

englishPage.addView(
        suggestionBar,
        new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(36)
        )
);
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

  

private void addEnglishLetter(
        LinearLayout row, String letter) {

    Button button = makeButton(letter);

    row.addView(button,
            new LinearLayout.LayoutParams(0, dp(48), 1));

    button.setOnClickListener(v -> {
        String output;

        if (englishShift || shouldCapitalizeNextLetter()) {
    output = letter.toUpperCase(Locale.ENGLISH);
} else {
    output = letter.toLowerCase(Locale.ENGLISH);
}

        commit(output);

        if (englishShift) {
            englishShift = false;
            refreshEnglishLabels();
        }
    });
}




private boolean shouldCapitalizeNextLetter() {
    InputConnection ic = getCurrentInputConnection();
    if (ic == null) return false;

    CharSequence before = ic.getTextBeforeCursor(150, 0);

    if (before == null || before.length() == 0) {
        return true;
    }

    String text = before.toString();
    int i = text.length() - 1;

    while (i >= 0 && Character.isWhitespace(text.charAt(i))) {
        i--;
    }

    // Beginning of a sentence or empty text
    if (i < 0) return true;

    char last = text.charAt(i);

    return last == '.' || last == '?' || last == '!';
}


private final String[] englishWords = {
    "a", "about", "after", "again", "all", "also", "am",
    "an", "and", "any", "are", "as", "ask", "at",
    "back", "be", "because", "been", "before", "best",
    "better", "book", "boy", "but", "by", "can",
    "come", "could", "day", "did", "do", "does",
    "down", "each", "easy", "eat", "English", "even",
    "every", "example", "family", "feel", "find", "first",
    "for", "friend", "from", "get", "give", "go", "good",
    "great", "had", "has", "have", "he", "hello", "help",
    "her", "here", "him", "his", "how", "I", "if",
    "in", "into", "is", "it", "its", "just", "know",
    "learn", "like", "little", "look", "love", "make",
    "many", "may", "me", "more", "most", "much", "my",
    "name", "need", "never", "new", "next", "no", "not",
    "now", "of", "off", "old", "on", "one", "only",
    "or", "other", "our", "out", "people", "please",
    "read", "right", "said", "same", "school", "see",
    "she", "should", "so", "some", "student", "study",
    "take", "teacher", "tell", "than", "thank", "that",
    "the", "their", "them", "then", "there", "these",
    "they", "thing", "think", "this", "time", "to",
    "today", "tomorrow", "too", "try", "up", "us",
    "use", "very", "want", "was", "way", "we", "well",
    "were", "what", "when", "where", "which", "who",
    "will", "with", "word", "work", "would", "write",
    "yes", "you", "your",

"can't", "don't", "doesn't", "isn't", "wasn't",
    "weren't", "won't", "wouldn't", "couldn't",
    "shouldn't", "haven't", "hasn't", "hadn't",
    "I'm", "you're", "we're", "they're", "it's",
    "that's", "there's", "I've", "you've", "we've",
    "they've", "I'll", "you'll", "we'll", "they'll",

    "ice", "cream", "ink", "insect", "thinking"
};



         
                 

private void updateEnglishSuggestions() {
    if (suggestionBar == null || englishPage == null) return;

    suggestionBar.removeAllViews();

    if (englishPage.getVisibility() != View.VISIBLE) return;

    InputConnection ic = getCurrentInputConnection();
    if (ic == null) return;

    CharSequence before = ic.getTextBeforeCursor(150, 0);
    if (before == null || before.length() == 0) return;

    String text = before.toString();
    boolean hasTrailingSpace =
            Character.isWhitespace(text.charAt(text.length() - 1));

    String trimmed = text.trim();
    if (trimmed.isEmpty()) return;

    String[] parts = trimmed.split("\\s+");
    String typedWord = hasTrailingSpace ? "" : parts[parts.length - 1];

    StringBuilder previous = new StringBuilder();
    int previousEnd = hasTrailingSpace ? parts.length : parts.length - 1;

    for (int i = 0; i < previousEnd; i++) {
        if (previous.length() > 0) previous.append(" ");
        previous.append(parts[i]);
    }

    String priorText = previous.toString().toLowerCase(Locale.ENGLISH);
    String lowerWord = typedWord.toLowerCase(Locale.ENGLISH);

    ArrayList<String> results = new ArrayList<>();
    boolean nextWordMode = false;

    // 1. Correct common missing apostrophes.
    String correction = correctContraction(lowerWord);

    if (correction != null && !hasTrailingSpace) {
        results.add(correction);
    }

    // 2. Recognize a likely completed question.
    else if (isLikelyQuestion(trimmed)
            && !trimmed.endsWith("?")
            && !trimmed.endsWith(".")
            && !trimmed.endsWith("!")) {
        results.add("?");
    }

    // 3. Predict phrases after "hello my".
    else if (priorText.equals("hello") && lowerWord.equals("my")
            || hasTrailingSpace && priorText.equals("hello my")) {
        results.add("dear");
        results.add("friend");
        results.add("students");
        nextWordMode = true;
    }

    // 4. Predict after "I".
    else if (lowerWord.equals("i") && !hasTrailingSpace) {
        results.add("am");
        results.add("can");
        results.add("will");
        results.add("have");
        nextWordMode = true;
    }

    // 5. Predict after "I am".
    else if (priorText.equals("i") && lowerWord.equals("am")
            || hasTrailingSpace && priorText.equals("i am")) {

        addWords(results,
                "happy", "delighted", "tired",
                "fine", "ready", "going",
                "learning", "working", "playing",
                "reading", "writing", "studying",
                "eating", "sleeping", "speaking");

        nextWordMode = true;
    }

             

    // Predict possessive words from the subject.
    else if (
            (priorText.equals("i") && lowerWord.equals("have"))
            || (hasTrailingSpace && priorText.equals("i have"))
            || (priorText.equals("you") && lowerWord.equals("have"))
            || (hasTrailingSpace && priorText.equals("you have"))
            || (priorText.equals("she") && lowerWord.equals("has"))
            || (hasTrailingSpace && priorText.equals("she has"))
            || (priorText.equals("he") && lowerWord.equals("has"))
            || (hasTrailingSpace && priorText.equals("he has"))
            || (priorText.equals("they") && lowerWord.equals("have"))
            || (hasTrailingSpace && priorText.equals("they have"))
            || (priorText.equals("we") && lowerWord.equals("have"))
            || (hasTrailingSpace && priorText.equals("we have"))
            || (priorText.equals("it") && lowerWord.equals("has"))
            || (hasTrailingSpace && priorText.equals("it has"))
    ) {
        String subject = "";

        if (priorText.equals("i") || priorText.equals("you")
                || priorText.equals("she") || priorText.equals("he")
                || priorText.equals("they") || priorText.equals("we")
                || priorText.equals("it")) {
            subject = priorText;
        } else if (hasTrailingSpace) {
            String[] contextWords = priorText.split("\\s+");
            subject = contextWords[contextWords.length - 1];
        }

        switch (subject) {
            case "i":
                addWords(results, "my");
                break;
            case "you":
                addWords(results, "your");
                break;
            case "she":
                addWords(results, "her");
                break;
            case "he":
                addWords(results, "his");
                break;
            case "they":
                addWords(results, "their");
                break;
            case "we":
                addWords(results, "our");
                break;
            case "it":
                addWords(results, "its");
                break;
        }

        nextWordMode = true;
    }

    // 6. Predict after "I have".
    else if (priorText.equals("i") && lowerWord.equals("have")
            || hasTrailingSpace && priorText.equals("i have")) {

        addWords(results,
                "written", "eaten", "done",
                "seen", "gone", "finished",
                "learned", "worked", "been");

        nextWordMode = true;
    }

    // 7. Predict after "I have to".
    else if (priorText.equals("i have") && lowerWord.equals("to")
            || hasTrailingSpace && priorText.equals("i have to")) {

        addWords(results,
                "go", "write", "eat",
                "study", "work", "learn",
                "read", "leave", "help");

        nextWordMode = true;
    }

    // 8. Predict after "I have been".
    else if (priorText.equals("i have") && lowerWord.equals("been")
            || hasTrailingSpace
            && priorText.equals("i have been")) {

        addWords(results,
                "working", "learning", "reading",
                "writing", "waiting", "playing",
                "studying", "speaking");

        nextWordMode = true;
    }

    // 9. Predict after "I am going".
    else if (priorText.equals("i am") && lowerWord.equals("going")
            || hasTrailingSpace && priorText.equals("i am going")) {

        addWords(results, "to", "home", "there", "outside");
        nextWordMode = true;
    }

    // 10. Predict after "I am going to".
    else if (priorText.equals("i am going") && lowerWord.equals("to")
            || hasTrailingSpace
            && priorText.equals("i am going to")) {

        addWords(results,
                "school", "work", "learn", "study", "eat");

        nextWordMode = true;
    }

    // 8. Common subject and sentence patterns.
    else if (hasTrailingSpace || lowerWord.isEmpty()
            || lowerWord.equals("you") || lowerWord.equals("he")
            || lowerWord.equals("she") || lowerWord.equals("it")
            || lowerWord.equals("we") || lowerWord.equals("they")
            || lowerWord.equals("boy") || lowerWord.equals("boys")) {

        String last = hasTrailingSpace
                ? parts[parts.length - 1].toLowerCase(Locale.ENGLISH)
                : lowerWord;

        String[] choices;

        if (last.equals("i")) {
            choices = new String[]{"am", "can", "will"};
        } else if (last.equals("you")) {
            choices = new String[]{"are", "can", "have"};
        } else if (last.equals("he") || last.equals("she")
                || last.equals("it")) {
            choices = new String[]{"is", "has", "does"};
        } else if (last.equals("we") || last.equals("they")
                || last.equals("boys")) {
            choices = new String[]{"are", "have", "can"};
        } else if (last.equals("am")) {
            choices = new String[]{"happy", "fine", "going"};
        } else if (last.equals("is")) {
            choices = new String[]{"good", "here", "going"};
        } else if (last.equals("are")) {
            choices = new String[]{"ready", "here", "going"};
        } else if (last.equals("the")) {
            choices = new String[]{"boy", "boys", "school"};
        } else if (last.equals("a")) {
            choices = new String[]{"boy", "book", "good"};
        } else {
            choices = new String[]{"the", "to", "and"};
        }

        addWords(results, choices);
        nextWordMode = true;
    }


    // Complete words using learned and built-in vocabulary.
    if (results.isEmpty() && !lowerWord.isEmpty()) {

        // Suggest correct spelling and capitalization for known words.
        boolean sentenceStart = priorText.isEmpty()
                || priorText.endsWith(".")
                || priorText.endsWith("?")
                || priorText.endsWith("!");

        for (String word : englishWords) {
    if (word != null
            && word.toLowerCase(Locale.ENGLISH)
                    .equals(lowerWord)
            && !typedWord.equals(word)) {

        String expectedWord = word;
        results.add(expectedWord);
        break;
    }
}

        // First: show words learned by this user.
        if (results.isEmpty() && personalDictionary != null) {
            try {
                for (String word : personalDictionary.getSuggestions(
                        lowerWord,
                        PersonalDictionary.ENGLISH,
                        3)) {

                    if (!results.contains(word)) {
                        results.add(word);
                    }

                    if (results.size() >= 3) {
                        break;
                    }
                }
            } catch (Exception ignored) {
                // Continue with built-in words if needed.
            }
        }

        // Then: fill remaining spaces with built-in words.
        for (String word : englishWords) {
            if (results.size() >= 3) {
                break;
            }

            if (word != null
                    && word.toLowerCase(Locale.ENGLISH)
                            .startsWith(lowerWord)
                    && !word.equalsIgnoreCase(lowerWord)
                    && !results.contains(word)) {

                results.add(word);
            }
        }
    }

                
    int count = Math.min(3, results.size());

    for (int i = 0; i < count; i++) {
        final String suggestion = results.get(i);
        final boolean appendNext = nextWordMode;
        final String wordToReplace = typedWord;
        final boolean trailingSpace = hasTrailingSpace;

        Button button = makeButton(suggestion);
        button.setTextSize(14);

        suggestionBar.addView(button,
                new LinearLayout.LayoutParams(0, dp(36), 1));

        
        button.setOnClickListener(v -> {
            InputConnection currentIc = getCurrentInputConnection();
            if (currentIc == null) return;

            // Punctuation must follow the sentence.
            if (suggestion.equals("?")) {
                if (trailingSpace) {
                    currentIc.deleteSurroundingText(1, 0);
                }
                currentIc.commitText("?", 1);

            } else if (appendNext) {
                String insertion = trailingSpace
                        ? suggestion + " "
                        : " " + suggestion + " ";

                currentIc.commitText(insertion, 1);

            } else {
                if (!wordToReplace.isEmpty()) {
                    currentIc.deleteSurroundingText(
                            wordToReplace.length(), 0);
                }

                currentIc.commitText(suggestion + " ", 1);
            }

            updateEnglishSuggestions();
        });
    }
}

private void addWords(ArrayList<String> target, String... words) {
    for (String word : words) {
        if (!target.contains(word)) target.add(word);
    }
}

private String correctContraction(String word) {
    switch (word) {
        case "lets": return "let's";
        case "dont": return "don't";
        case "doesnt": return "doesn't";
        case "didnt": return "didn't";
        case "cant": return "can't";
        case "wont": return "won't";
        case "isnt": return "isn't";
        case "wasnt": return "wasn't";
        case "werent": return "weren't";
        case "im": return "I'm";
        case "youre": return "you're";
        case "theyre": return "they're";
        case "were": return null;
        case "ive": return "I've";
        case "youve": return "you've";
        case "ill": return "I'll";
        case "youll": return "you'll";
        default: return null;
    }
}

private boolean isLikelyQuestion(String sentence) {
    String lower = sentence.toLowerCase(Locale.ENGLISH).trim();
    String[] words = lower.split("\\s+");

    if (words.length < 2) return false;

    String first = words[0];

    String[] questionStarters = {
        "what", "where", "when", "why", "who",
        "whom", "whose", "which", "how"
    };

    for (String starter : questionStarters) {
        if (first.equals(starter)) return true;
    }

    String[] auxiliaryStarters = {
        "am", "is", "are", "was", "were",
        "do", "does", "did", "can", "could",
        "will", "would", "shall", "should",
        "have", "has", "had"
    };

    for (String starter : auxiliaryStarters) {
        if (first.equals(starter)) return true;
    }

    return false;
}


private int editDistance(String a, String b) {

    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
    for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

    for (int i = 1; i <= a.length(); i++) {
        for (int j = 1; j <= b.length(); j++) {
            int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;

            dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
            );
        }
    }

    return dp[a.length()][b.length()];
}

private void refreshEnglishLabels() { if (englishPage != null) { refreshEnglishView(englishPage); } }
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

         

    // EMOJI AND GREETINGS PAGE

    private void createEmojiPage() {
        emojiPage = new LinearLayout(this);
        emojiPage.setOrientation(LinearLayout.VERTICAL);
        emojiPage.setBackgroundColor(green);
        emojiPage.setVisibility(View.GONE);
        emojiPage.setPadding(dp(3), dp(3), dp(3), dp(3));

        root.addView(emojiPage,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);

        emojiPage.addView(scrollView,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(250)));

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(content);

        addEmojiRow(content, "❤️", "😍", "🥰", "😊", "😂", "🙏");
        addEmojiRow(content, "🌹", "🌷", "💐", "🌺", "🌸", "🌻");
        addEmojiRow(content, "👍", "👏", "🤲", "💚", "💖", "✨");
        addEmojiRow(content, "😀", "😁", "😎", "😭", "😢", "😇");
        addEmojiRow(content, "🎉", "🎂", "🌙", "☀️", "⭐", "🔥");
        addEmojiRow(content, "🤲", "❤️", "🌹", "😊", "🙏", "💚");

        addGreetingRow(content,
                "السلام عليڪم", "وعليڪم السلام");

        addGreetingRow(content,
                "يا علي مدد", "يا علي مدد!");

        addGreetingRow(content,
                "Good Morning", "Good Evening");

        addGreetingRow(content,
                "Good Night", "Thank You");

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);

        addSpecialButton(bottom, "🌐 ٻولي",
                this::openLanguageMenu);

        addSpecialButton(bottom, "ABC",
                this::showEnglishPage);

        emojiPage.addView(bottom);
    }

    private void addEmojiRow(
            LinearLayout parent, String... emojis) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        for (String emoji : emojis) {
            Button button = makeButton(emoji);
            button.setTextSize(23);

            row.addView(button,
                    new LinearLayout.LayoutParams(
                            0, dp(44), 1));

            button.setOnClickListener(v -> commit(emoji));
        }

        parent.addView(row);
    }

    private void addGreetingRow(
            LinearLayout parent, String... phrases) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        for (String phrase : phrases) {
            Button button = makeButton(phrase);
            button.setTextSize(13);

            row.addView(button,
                    new LinearLayout.LayoutParams(
                            0, dp(44), 1));

            button.setOnClickListener(v -> {
                commit(phrase);
                commit(" ");
            });
        }

        parent.addView(row);
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

        // Each control appears only once.
        addSpecialButton(bottom, "⇧", this::showUrduPage2);
        addSpecialButton(bottom, "🌐", this::openLanguageMenu);
        addSpecialButton(bottom, "؟", () -> commit("؟"));

        // English full stop on Urdu Page 1.
        addSpecialButton(bottom, ".", () -> commit("."));

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

    
private void commit(String text) {
    InputConnection ic = getCurrentInputConnection();
    if (ic == null || text == null) return;

    

    // Learn the completed word before inserting a separator.
    if (personalDictionary != null && text.length() == 1) {
        char ch = text.charAt(0);

        boolean boundary = Character.isWhitespace(ch)
                || ".,!?;:،؟؛۔".indexOf(ch) >= 0;

        if (boundary) {
            CharSequence before = ic.getTextBeforeCursor(100, 0);

            if (before != null) {
                String trimmed = before.toString().trim();

                if (!trimmed.isEmpty()) {
                    String[] words = trimmed.split("\\s+");
                    String lastWord = words[words.length - 1];

                    if (!lastWord.isEmpty()) {
                        try {
                            personalDictionary.learnWord(
                                    lastWord,
                                    PersonalDictionary.ENGLISH
                            );
                        } catch (Exception ignored) {
                            // Keep keyboard typing working if learning fails.
                        }
                    }
                }
            }
        }
    }


  
// Capitalize standalone English pronoun "i" before a space.
if (text.equals(" ")) {
    CharSequence before = ic.getTextBeforeCursor(20, 0);

    if (before != null && before.length() > 0) {
        String previous = before.toString();

        // Find the word immediately before the cursor.
        String trimmed = previous.replaceFirst("\\s+$", "");
        int wordStart = trimmed.lastIndexOf(' ') + 1;
        String lastWord = trimmed.substring(wordStart);

        if (lastWord.equals("i")) {
            ic.deleteSurroundingText(lastWord.length(), 0);
            ic.commitText("I ", 1);
            updateEnglishSuggestions();
            return;
        }
    }
}


ic.commitText(text, 1);
updateEnglishSuggestions();}
private void deleteOne() {
    InputConnection ic = getCurrentInputConnection();

    if (ic != null) {
        ic.deleteSurroundingText(1, 0);
        updateEnglishSuggestions();
    }
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

    private void showEmojiPage() {
    hideAllPages();
    emojiPage.setVisibility(View.VISIBLE);
}      
    // SHOW PAGES

    private void hideAllPages() {
        page1.setVisibility(View.GONE);
        page2.setVisibility(View.GONE);
        englishPage.setVisibility(View.GONE);
        englishSymbolsPage.setVisibility(View.GONE);
        urduPage.setVisibility(View.GONE);
        urduPage2.setVisibility(View.GONE);
         emojiPage.setVisibility(View.GONE);
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
    updateEnglishSuggestions();
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
