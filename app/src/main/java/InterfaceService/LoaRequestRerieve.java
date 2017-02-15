package InterfaceService;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import model.LoaRequest;

/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestRerieve {

    Context c ;
    public LoaRequestRerieve(Context c){
        this.c = c ;
    }

    public   void changeButtonColorSelected(Button btn ){

        btn.setBackgroundColor(ContextCompat.getColor(c , R.color.colorPrimaryLight));
        btn.setTextColor(ContextCompat.getColor(c , R.color.white));
    }

    public   void changeButtonColorDeselect(Button btn ){
        btn.setBackgroundColor(ContextCompat.getColor(c , R.color.white));
        btn.setTextColor(ContextCompat.getColor(c , R.color.colorPrimaryLight));
    }

}
