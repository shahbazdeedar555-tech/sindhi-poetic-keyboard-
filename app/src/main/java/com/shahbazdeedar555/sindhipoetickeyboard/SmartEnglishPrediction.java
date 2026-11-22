
package com.shahbazdeedar555.sindhipoetickeyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class SmartEnglishPrediction {

    private SmartEnglishPrediction() {
        // Utility class
    }

    public static List<String> getSuggestions(String text) {

        List<String> suggestions = new ArrayList<>();

        if (text == null || text.trim().isEmpty()) {
            return suggestions;
        }

        boolean hasTrailingSpace =
                Character.isWhitespace(
                        text.charAt(text.length() - 1));

        String cleaned = text.trim();
        String[] words = cleaned.split("\\s+");

        String lastWord = words[words.length - 1];

        String previousWord = "";

        if (words.length > 1) {
            previousWord = words[words.length - 2];
        }

        String current =
                lastWord.toLowerCase(Locale.ROOT);

        String previous =
                previousWord.toLowerCase(Locale.ROOT);

        // --------------------------------
        // SUBJECT PREDICTIONS
        // --------------------------------

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

                    // --------------------------------
                    // CONTRACTION PREDICTIONS
                    // --------------------------------

                    addContractionSuggestions(
                            suggestions, current);

                    // --------------------------------
                    // CONTEXTUAL HELPING VERBS
                    // --------------------------------

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
                        addWordCompletions(
                                suggestions, current);
                    }

                    break;
            }
        }

        // --------------------------------
        // NEXT WORD PREDICTIONS
        // --------------------------------

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

                // Contraction next-word predictions

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

    // --------------------------------
    // CONTRACTION COMPLETIONS
    // --------------------------------

    private static void addContractionSuggestions(
            List<String> suggestions,
            String prefix) {

        String[] contractions = {

                "I'm",
                "I've",
                "I'll",
                "I'd",

                "you're",
                "you've",
                "you'll",
                "you'd",

                "we're",
                "we've",
                "we'll",
                "we'd",

                "they're",
                "they've",
                "they'll",
                "they'd",

                "he's",
                "he'll",
                "he'd",

                "she's",
                "she'll",
                "she'd",

                "it's",
                "it'll",

                "isn't",
                "aren't",
                "wasn't",
                "weren't",

                "haven't",
                "hasn't",
                "hadn't",

                "don't",
                "doesn't",
                "didn't",

                "can't",
                "couldn't",
                "won't",
                "wouldn't",
                "shouldn't",
                "mustn't",

                "that's",
                "there's",
                "what's",
                "who's",
                "where's",
                "when's",
                "how's",

                "whoever's",
                "whichever's"
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

    // --------------------------------
    // ADD SUGGESTIONS
    // --------------------------------

    private static void add(
            List<String> list,
            String... values) {

        for (String value : values) {

            if (!list.contains(value)
                    && list.size() < 3) {

                list.add(value);
            }
        }
    }

    // --------------------------------
    // BASIC WORD COMPLETIONS
    // --------------------------------

    private static void addWordCompletions(
            List<String> suggestions,
            String prefix) {

        String[] commonWords = {

                "hello",
                "help",
                "happy",
                "have",
                "here",
                "home",
                "good",
                "great",
                "going",
                "get",
                "give",
                "know",
                "like",
                "love",
                "learn",
                "look",
                "make",
                "morning",
                "name",
                "nice",
                "please",
                "people",
                "school",
                "student",
                "teacher",
                "thank",
                "thanks",
                "this",
                "that",
                "there",
                "what",
                "when",
                "where",
                "which",
                "who",
                "why",
                "yes",
                "your"
        };

        if (prefix.isEmpty()) return;

        for (String word : commonWords) {

            if (word.startsWith(prefix)
                    && !word.equals(prefix)) {

                add(suggestions, word);
            }

            if (suggestions.size() >= 3) {
    break;
}

}
}

public static List<String> getSuggestions(
        String text,
        PersonalDictionary dictionary) {

    List<String> results = new ArrayList<>();

    // Old Smart Predictions first
    results.addAll(getSuggestions(text));

    // New Personal Dictionary predictions later
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
                boolean exists = false;

                for (String item : results) {
                    if (item.equalsIgnoreCase(word)) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    results.add(word);
                }
            }
        }
    }

    return new ArrayList<>(
            results.subList(0, Math.min(3, results.size())));
}
    }
}
