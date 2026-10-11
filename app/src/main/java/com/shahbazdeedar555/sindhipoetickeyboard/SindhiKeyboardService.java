
package com.shahbazdeedar555.sindhipoetickeyboard;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextServicesManager;
import android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SindhiKeyboardService extends InputMethodService {

    private SpellCheckerSession spellCheckerSession;

    private final Handler suggestionHandler =
            new Handler(Looper.getMainLooper());

    private final Map<String, String[]> nextWordPredictions =
            new HashMap<>();

    private boolean spellCheckRequestPending = false;



    private void initializeSpellChecker() {
        try {
            TextServicesManager manager =
                    (TextServicesManager) getSystemService(
                            TEXT_SERVICES_MANAGER_SERVICE);

            if (manager == null) return;

            spellCheckerSession = manager.newSpellCheckerSession(
                    (Bundle) null,
                    Locale.ENGLISH,
                    new SpellCheckerSessionListener() {
                        @Override
                        public void onGetSuggestions(
                                SuggestionsInfo[] results) {

                            suggestionHandler.post(() -> {
                                spellCheckRequestPending = false;

                                if (results == null ||
                                        results.length == 0) {
                                    return;
                                }

                                showSpellingAlternatives(results);
                            });
                        }

                        @Override
                        public void onGetSentenceSuggestions(
                                android.view.textservice
                                        .SentenceSuggestionsInfo[] results) {
                            // Word-based suggestions are used here.
                        }
                    },
                    true
            );


        } catch (Exception e) {
            spellCheckerSession = null;
        }
    }

    private void showSpellingAlternatives(
            SuggestionsInfo[] results) {

        if (results == null || results.length == 0) {
            return;
        }

        SuggestionsInfo info = results[0];

        if (info == null) {
            return;
        }

        int count = info.getSuggestionsCount();

        if (count <= 0) {
            return;
        }

        Set<String> alternatives = new HashSet<>();

        for (int i = 0; i < count; i++) {
            String suggestion = info.getSuggestionAt(i);

            if (suggestion != null &&
                    !suggestion.trim().isEmpty()) {
                alternatives.add(suggestion);
            }
        }

        // Suggestion bar connection will be added later.
    }
}
