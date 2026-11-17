

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
