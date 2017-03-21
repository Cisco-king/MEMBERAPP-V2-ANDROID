package utilities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import com.medicard.member.R;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.gson.Gson;

import model.RequestResult;
import model.SendLoa;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import v2.DetailsActivity;

/**
 * Created by mpx-pawpaw on 12/29/16.
 */

public class DialogConfirmation {

}


