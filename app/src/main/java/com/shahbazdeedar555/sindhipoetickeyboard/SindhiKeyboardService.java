
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {

        TextView test = new TextView(this);

        test.setText("سنڌي ڪي بورڊ TEST");
        test.setTextSize(28);
        test.setTextColor(Color.WHITE);
        test.setBackgroundColor(Color.RED);
        test.setGravity(Gravity.CENTER);
        test.setPadding(10, 10, 10, 10);

        return test;
    }
}
