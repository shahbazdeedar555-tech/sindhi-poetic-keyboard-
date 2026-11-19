
private void createSuggestionBar() {

    suggestionBar = new LinearLayout(this);
    suggestionBar.setOrientation(LinearLayout.HORIZONTAL);
    suggestionBar.setGravity(Gravity.CENTER);
    suggestionBar.setLayoutDirection(
            View.LAYOUT_DIRECTION_LTR);

    suggestionBar.setBackgroundColor(
            Color.rgb(245, 241, 232));

    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dp(38));

    root.addView(suggestionBar, 1, params);
    suggestionBar.setVisibility(View.GONE);
}

private void updatePredictionBar() {

    if (suggestionBar == null) return;

    suggestionBar.removeAllViews();

    boolean englishMode =
            englishPage != null &&
            englishPage.getVisibility() == View.VISIBLE;

    if (!englishMode) {
        suggestionBar.setVisibility(View.GONE);
        return;
    }

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) return;

    CharSequence before =
            connection.getTextBeforeCursor(100, 0);

    if (before == null) return;

    ArrayList<String> predictions =
            new ArrayList<>(
                    SmartEnglishPrediction.getSuggestions(
                            before.toString()));

    int count = Math.min(3, predictions.size());

    for (int i = 0; i < count; i++) {

        final String prediction = predictions.get(i);

        Button button = new Button(this);
        button.setText(prediction.trim());
        button.setTextSize(14);
        button.setTextColor(green);
        button.setAllCaps(false);
        button.setPadding(dp(3), 0, dp(3), 0);
        button.setBackgroundColor(
                Color.rgb(245, 241, 232));

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(0,
                        dp(36), 1);

        suggestionBar.addView(button, buttonParams);

        button.setOnClickListener(v ->
                applyPrediction(prediction));
    }

    suggestionBar.setVisibility(
            count > 0 ? View.VISIBLE : View.GONE);
}

private void applyPrediction(String prediction) {

    InputConnection connection =
            getCurrentInputConnection();

    if (connection == null) return;

    CharSequence before =
            connection.getTextBeforeCursor(100, 0);

    if (before == null) return;

    String text = before.toString();

    if (prediction.startsWith(" ")) {

        connection.commitText(
                prediction.trim() + " ", 1);

    } else {

        if (!text.isEmpty() &&
                !Character.isWhitespace(
                        text.charAt(text.length() - 1))) {

            int start = text.length();

            while (start > 0 &&
                    !Character.isWhitespace(
                            text.charAt(start - 1))) {
                start--;
            }

            connection.deleteSurroundingText(
                    text.length() - start, 0);
        }

        connection.commitText(prediction + " ", 1);
    }

    updatePredictionBar();
}
