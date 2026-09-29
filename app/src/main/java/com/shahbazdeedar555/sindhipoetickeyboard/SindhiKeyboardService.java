

package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.inputmethod.InputConnection;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {

        LinearLayout keyboard = new LinearLayout(this);
        keyboard.setOrientation(LinearLayout.VERTICAL);
        keyboard.setPadding(4, 4, 4, 4);

        addRow(keyboard, "ا", "ب", "پ", "ت", "ٽ");
        addRow(keyboard, "ث", "ج", "ڄ", "چ", "ڇ");
        addRow(keyboard, "ح", "خ", "د", "ڌ", "ڏ");
        addRow(keyboard, "ر", "ڙ", "ز", "س", "ش");
        addRow(keyboard, "ک", "گ", "ڳ", "ل", "م");
        addRow(keyboard, "ن", "ڻ", "و", "ه", "ء");
        addRow(keyboard, "ي", "ئ", "ڪ", "ڱ", "ڦ");

        Button space = new Button(this);
        space.setText("خالي جاءِ");
        space.setTextSize(18);

        space.setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.commitText(" ", 1);
            }
        });

        keyboard.addView(space);

        return keyboard;
    }

    private void addRow(LinearLayout keyboard, String... letters) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        for (String letter : letters) {

            Button key = new Button(this);
            key.setText(letter);
            key.setTextSize(22);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0,
                            65,
                            1
                    );

            key.setLayoutParams(params);

            key.setOnClickListener(v -> {

                Button clicked = (Button) v;
                InputConnection ic = getCurrentInputConnection();

                if (ic != null) {
                    ic.commitText(
                            clicked.getText().toString(),
                            1
                    );
                }
            });

            row.addView(key);
        }

        keyboard.addView(row);
    }
}
