
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

        String lastWord =
                words[words.length - 1];

        String previousWord = "";

        if (words.length > 1) {
            previousWord =
                    words[words.length - 2];
        }

        String current =
                lastWord.toLowerCase(Locale.ROOT);

        String previous =
                previousWord.toLowerCase(Locale.ROOT);

        // --------------------------------
        // SUBJECT PREDICTIONS
        // --------------------------------

        if (!hasTrailingSpace) {

            if (current.equals("i")) {
                add(suggestions,
                        "I am",
                        "I have",
                        "I can");
            }

            else if (current.equals("you")) {
                add(suggestions,
                        "you are",
                        "you have",
                        "you can");
            }

            else if (current.equals("we")) {
                add(suggestions,
                        "we are",
                        "we have",
                        "we can");
            }

            else if (current.equals("they")) {
                add(suggestions,
                        "they are",
                        "they have",
                        "they can");
            }

            else if (current.equals("he")) {
                add(suggestions,
                        "he is",
                        "he has",
                        "he can");
            }

            else if (current.equals("she")) {
                add(suggestions,
                        "she is",
                        "she has",
                        "she can");
            }

            else if (current.equals("it")) {
                add(suggestions,
                        "it is",
                        "it has",
                        "it can");
            }

            // Contextual helping verbs
            else if (previous.equals("i")
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

            // General word completion
            else {
                addWordCompletions(
                        suggestions, current);
            }
        }

        // --------------------------------
        // NEXT WORD PREDICTIONS
        // --------------------------------

        else {

            switch (current) {

                case "i":
                    add(suggestions,
                            "am", "have", "can");
                    break;

                case "you":
                case "we":
                case "they":
                    add(suggestions,
                            "are", "have", "can");
                    break;

                case "he":
                case "she":
                case "it":
                    add(suggestions,
                            "is", "has", "can");
                    break;

                case "am":
                    add(suggestions,
                            "happy", "fine", "ready");
                    break;

                case "is":
                    add(suggestions,
                            "good", "beautiful", "ready");
                    break;

                case "are":
                    add(suggestions,
                            "you", "we", "they");
                    break;

                case "have":
                    add(suggestions,
                            "a", "to", "been");
                    break;

                case "has":
                    add(suggestions,
                            "a", "been", "to");
                    break;

                case "can":
                    add(suggestions,
                            "do", "go", "help");
                    break;

                case "will":
                    add(suggestions,
                            "be", "go", "do");
                    break;

                case "do":
                    add(suggestions,
                            "you", "not", "it");
                    break;

                case "the":
                    add(suggestions,
                            "book", "boy", "girl");
                    break;

                case "my":
                    add(suggestions,
                            "name", "book", "friend");
                    break;

                case "good":
                    add(suggestions,
                            "morning", "boy", "girl");
                    break;

                default:
                    break;
            }
        }

        return suggestions;
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

                suggestions.add(word);

                if (suggestions.size() >= 3) {
                    break;
                }
            }
        }
    }
}
