
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.widget.Toast;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(
                this,
                "SERVICE CREATED",
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public View onCreateInputView() {

        Toast.makeText(
                this,
                "KEYBOARD VIEW STARTED",
                Toast.LENGTH_LONG
        ).show();

        return getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );
    }
}
