
package com.shahbazdeedar555.sindhipoetickeyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class SmartEnglishPrediction {

    private SmartEnglishPrediction() {
    }

    // =====================================
    // MAIN PREDICTION SYSTEM
    // =====================================

    public static List<String> getSuggestions(String text) {

        List<String> suggestions = new ArrayList<>();

        if (text == null || text.trim().isEmpty()) {
            return suggestions;
        }

        boolean hasTrailingSpace =
                Character.isWhitespace(text.charAt(text.length() - 1));

        String cleaned = text.trim();
        String[] words = cleaned.split("\\s+");

        String current =
                words[words.length - 1].toLowerCase(Locale.ROOT);

        String previous = "";

        if (words.length > 1) {
            previous = words[words.length - 2]
                    .toLowerCase(Locale.ROOT);
        }

        // SUBJECT PREDICTIONS

        if (!hasTrailingSpace) {

            switch (current) {

                case "i":
                    add(suggestions, "I am", "I have", "I can");
                    break;

                case "you":
                    add(suggestions, "you are", "you have", "you can");
                    break;

                case "we":
                    add(suggestions, "we are", "we have", "we can");
                    break;

                case "they":
                    add(suggestions, "they are", "they have", "they can");
                    break;

                case "he":
                    add(suggestions, "he is", "he has", "he can");
                    break;

                case "she":
                    add(suggestions, "she is", "she has", "she can");
                    break;

                case "it":
                    add(suggestions, "it is", "it has", "it can");
                    break;

                default:

                    addContractionSuggestions(suggestions, current);

                    if (suggestions.isEmpty()) {

                        if (previous.equals("i")
                                && current.startsWith("a")) {
                            add(suggestions, "am");
                        }

                        else if ((previous.equals("you")
                                || previous.equals("we")
                                || previous.equals("they"))
                                && current.startsWith("a")) {
                            add(suggestions, "are");
                        }

                        else if ((previous.equals("he")
                                || previous.equals("she")
                                || previous.equals("it"))
                                && current.startsWith("i")) {
                            add(suggestions, "is");
                        }
                    }

                    if (suggestions.isEmpty()) {
                        addWordCompletions(suggestions, current);
                    }

                    break;
            }
        }

        // NEXT WORD PREDICTIONS

        else {

            switch (current) {

                case "i":
                    add(suggestions, "am", "have", "can");
                    break;

                case "you":
                case "we":
                case "they":
                    add(suggestions, "are", "have", "can");
                    break;

                case "he":
                case "she":
                case "it":
                    add(suggestions, "is", "has", "can");
                    break;

                case "am":
                    add(suggestions, "happy", "fine", "ready");
                    break;

                case "is":
                    add(suggestions, "good", "beautiful", "ready");
                    break;

                case "are":
                    add(suggestions, "you", "we", "they");
                    break;

                case "have":
                    add(suggestions, "a", "to", "been");
                    break;

                case "has":
                    add(suggestions, "a", "been", "to");
                    break;

                case "can":
                    add(suggestions, "do", "go", "help");
                    break;

                case "will":
                    add(suggestions, "be", "go", "do");
                    break;

                case "do":
                    add(suggestions, "you", "not", "it");
                    break;

                case "the":
                    add(suggestions, "book", "boy", "girl");
                    break;

                case "my":
                    add(suggestions, "name", "book", "friend");
                    break;

                case "good":
                    add(suggestions, "morning", "boy", "girl");
                    break;

                case "i'm":
                    add(suggestions, "happy", "fine", "ready");
                    break;

                case "you're":
                    add(suggestions, "right", "good", "welcome");
                    break;

                case "we're":
                case "they're":
                    add(suggestions, "ready", "happy", "going");
                    break;

                case "he's":
                case "she's":
                case "it's":
                    add(suggestions, "good", "nice", "beautiful");
                    break;

                case "isn't":
                    add(suggestions, "it", "that", "he");
                    break;

                case "aren't":
                    add(suggestions, "you", "they", "we");
                    break;

                case "don't":
                    add(suggestions, "know", "worry", "go");
                    break;

                case "doesn't":
                    add(suggestions, "know", "work", "matter");
                    break;

                case "didn't":
                    add(suggestions, "know", "go", "see");
                    break;

                case "can't":
                    add(suggestions, "do", "go", "help");
                    break;

                case "won't":
                    add(suggestions, "go", "come", "do");
                    break;

                case "i've":
                case "we've":
                case "they've":
                    add(suggestions, "been", "got", "seen");
                    break;

                case "i'll":
                case "you'll":
                case "we'll":
                case "they'll":
                    add(suggestions, "be", "go", "help");
                    break;

                default:
                    break;
            }
        }

        return suggestions;
    }

    // =====================================
    // CONTRACTION PREDICTIONS
    // =====================================

    private static void addContractionSuggestions(
            List<String> suggestions,
            String prefix) {

        String[] contractions = {

                "I'm", "I've", "I'll", "I'd",

                "you're", "you've", "you'll", "you'd",

                "we're", "we've", "we'll", "we'd",

                "they're", "they've", "they'll", "they'd",

                "he's", "he'll", "he'd",

                "she's", "she'll", "she'd",

                "it's", "it'll",

                "isn't", "aren't", "wasn't", "weren't",

                "haven't", "hasn't", "hadn't",

                "don't", "doesn't", "didn't",

                "can't", "couldn't", "won't",
                "wouldn't", "shouldn't", "mustn't",

                "that's", "there's", "what's",
                "who's", "where's", "when's", "how's",

                "whoever's", "whichever's"
        };

        for (String contraction : contractions) {

            if (contraction.toLowerCase(Locale.ROOT)
                    .startsWith(prefix)
                    && !contraction.equalsIgnoreCase(prefix)) {

                add(suggestions, contraction);
            }

            if (suggestions.size() >= 3) {
                return;
            }
        }
    }

    // =====================================
    // ADD SUGGESTIONS
    // =====================================

    private static void add(
            List<String> list,
            String... values) {

        for (String value : values) {

            if (!containsIgnoreCase(list, value)
                    && list.size() < 3) {

                list.add(value);
            }
        }
    }

    private static boolean containsIgnoreCase(
            List<String> list,
            String value) {

        for (String item : list) {
            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    // =====================================
    // ENGLISH WORD DATABASE
    // =====================================

    private static final String[] COMMON_WORDS = {

        "about", "above", "after", "again", "against",
        "all", "always", "also", "am", "among",
        "an", "and", "another", "any", "are",
        "around", "as", "ask", "at", "away",

        "back", "bad", "beautiful", "beauty",
        "because", "become", "been", "before",
        "begin", "best", "better", "between",
        "big", "book", "boy", "bring", "brother",
        "but", "buy", "by",

        "call", "came", "can", "cannot", "care",
        "carry", "change", "child", "children",
        "class", "clean", "come", "common",
        "could", "country", "correct",

        "day", "did", "different", "do", "does",
        "doing", "done", "down", "during",

        "each", "early", "easy", "eat", "education",
        "end", "enough", "every", "example",
        "excellent", "experience",

        "face", "family", "far", "fast", "father",
        "feel", "few", "find", "fine", "first",
        "follow", "for", "friend", "friends",
        "from", "full",

        "game", "gave", "get", "girl", "give",
        "go", "going", "good", "great", "green",
        "group", "grow",

        "happy", "hard", "has", "have", "he",
        "head", "hear", "help", "her", "here",
        "high", "him", "his", "home", "hope",
        "house", "how",

        "i", "if", "important", "in", "into",
        "is", "it", "its",

        "just", "keep", "kind", "know",

        "large", "last", "learn", "leave", "left",
        "let", "life", "like", "little", "live",
        "long", "look", "love",

        "made", "make", "man", "many", "may",
        "me", "mean", "meet", "might", "more",
        "morning", "most", "mother", "much",
        "must", "my",

        "name", "near", "need", "never", "new",
        "next", "nice", "night", "not", "now",

        "of", "off", "often", "old", "on",
        "once", "one", "only", "open", "or",
        "other", "our", "out", "over",

        "people", "place", "play", "please",
        "point", "put",

        "question", "quick", "quite",

        "read", "really", "right", "run",

        "said", "same", "school", "see", "seem",
        "sentence", "she", "should", "show",
        "small", "so", "some", "something",
        "sometimes", "speak", "start", "still",
        "student", "study", "such",

        "take", "talk", "teacher", "tell", "than",
        "thank", "thanks", "that", "the", "their",
        "them", "then", "there", "these", "they",
        "thing", "think", "this", "those", "three",
        "through", "time", "to", "today", "together",
        "too", "try",

        "under", "understand", "until", "up", "us",
        "use",

        "very", "visit",

        "want", "was", "watch", "way", "we",
        "well", "were", "what", "when", "where",
        "which", "while", "who", "why", "will",
        "with", "without", "word", "work", "world",
        "write",

        "yes", "yet", "you", "young", "your"
    };

    // =====================================
    // WORD COMPLETION + SPELLING CORRECTION
    // =====================================

    private static void addWordCompletions(
            List<String> suggestions,
            String prefix) {

        if (prefix.isEmpty()) {
            return;
        }

        // FIRST: NORMAL WORD COMPLETION

        for (String word : COMMON_WORDS) {

            if (word.startsWith(prefix)
                    && !word.equalsIgnoreCase(prefix)) {

                add(suggestions, word);
            }

            if (suggestions.size() >= 3) {
                return;
            }
        }

        // SECOND: SPELLING CORRECTION

        if (suggestions.isEmpty() && prefix.length() >= 3) {

            addSpellingCorrections(suggestions, prefix);
        }
    }

    // =====================================
    // SPELLING CORRECTION ENGINE
    // =====================================

    private static void addSpellingCorrections(
            List<String> suggestions,
            String typedWord) {

        List<String> candidates = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        for (String word : COMMON_WORDS) {

            if (word.equalsIgnoreCase(typedWord)) {
                continue;
            }

            // Ignore words with very different lengths

            if (Math.abs(word.length() - typedWord.length()) > 2) {
                continue;
            }

            int distance = editDistance(typedWord, word);

            // Accept only close spelling matches

            if (distance <= 2) {

                int position = 0;

                while (position < distances.size()
                        && distances.get(position) <= distance) {
                    position++;
                }

                candidates.add(position, word);
                distances.add(position, distance);

                if (candidates.size() > 3) {
                    candidates.remove(3);
                    distances.remove(3);
                }
            }
        }

        for (String candidate : candidates) {
            add(suggestions, candidate);
        }
    }

    // =====================================
    // LEVENSHTEIN EDIT DISTANCE
    // =====================================

    private static int editDistance(
            String first,
            String second) {

        int m = first.length();
        int n = second.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int cost = first.charAt(i - 1)
                        == second.charAt(j - 1) ? 0 : 1;

                int deletion = dp[i - 1][j] + 1;
                int insertion = dp[i][j - 1] + 1;
                int substitution = dp[i - 1][j - 1] + cost;

                dp[i][j] = Math.min(
                        Math.min(deletion, insertion),
                        substitution);
            }
        }

        return dp[m][n];
    }

    // =====================================
    // PERSONAL DICTIONARY INTEGRATION
    // =====================================

    public static List<String> getSuggestions(
            String text,
            PersonalDictionary dictionary) {

        List<String> results = new ArrayList<>();

        results.addAll(getSuggestions(text));

        if (text != null && !text.isEmpty()
                && !Character.isWhitespace(
                        text.charAt(text.length() - 1))
                && dictionary != null) {

            String trimmed = text.trim();
            int lastSpace = trimmed.lastIndexOf(' ');

            String prefix = trimmed.substring(lastSpace + 1);

            if (!prefix.isEmpty()) {

                List<String> learned =
                        dictionary.getSuggestions(
                                prefix,
                                PersonalDictionary.ENGLISH,
                                3);

                for (String word : learned) {

                    if (!containsIgnoreCase(results, word)
                            && results.size() < 3) {

                        results.add(word);
                    }
                }
            }
        }

        return results;
    }
}
