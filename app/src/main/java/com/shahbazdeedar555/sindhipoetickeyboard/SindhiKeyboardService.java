package com.shahbazdeedar555.sindhipoetickeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;

public class SindhiKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {
        return getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );
    }
}
