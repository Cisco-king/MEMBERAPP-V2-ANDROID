package views;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import timber.log.Timber;
import utilities.ValidatorUtils;

/**
 * Created by casjohnpaul on 5/16/2017.
 */

public class NoSpecialCharacterChecker implements TextWatcher {


    private final EditText editText;

    public NoSpecialCharacterChecker(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence str, int start, int before, int count) {
        if (str.toString().length() <= 0 || ValidatorUtils.noSpecialCharacter(str.toString())) {
            Timber.d("typing ... %s", str.toString());
            editText.setError("please enter only a-zA-z and a (.) dot character");
        } else {
            editText.setError(null);
            Timber.d("no error while typing...");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
