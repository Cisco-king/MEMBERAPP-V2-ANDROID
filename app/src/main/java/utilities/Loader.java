package utilities;

import android.app.ProgressDialog;
import android.content.Context;
import com.medicard.member.R;

/**
 * Created by mpx-pawpaw on 1/20/17.
 */

public class Loader {


    ProgressDialog pd;

    Context context;

    public Loader(Context context) {
        this.context = context;
    }

    public void setMessage(String message) {
        pd.setMessage(message);
    }

    public void startLad() {
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Logging in...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pd.show();
    }

    public void checkLoad(){
        if((pd != null))
            pd.dismiss();
    }

    public void stopLoad() {
        pd.dismiss();
    }
}
