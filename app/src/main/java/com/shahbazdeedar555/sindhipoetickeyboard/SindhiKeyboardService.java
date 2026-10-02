
package com.shahbazdeedar555.sindhipoetickeyboard;
import android.inputmethodservice.InputMethodService; import android.view.View;
public class SindhiKeyboardService extends InputMethodService {
private View keyboard;

@Override
public View onCreateInputView() {

    // Load the complete keyboard design
    keyboard = getLayoutInflater().inflate(
            R.layout.keyboard_view,
            null
    );

    // Return the keyboard directly
    return keyboard;
}
}
