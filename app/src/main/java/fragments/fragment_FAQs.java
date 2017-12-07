package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;

import java.util.ArrayList;

import InterfaceService.FAQsCallback;
import InterfaceService.FAQsRetrieve;
import Sqlite.DatabaseHandler;
import adapter.FAQsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.FAQsModel;
import model.MaceFAQs;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.NetworkTest;

/**
 * Created by IPC on 11/22/2017.
 */


public class fragment_FAQs extends Fragment implements FAQsCallback {

    @BindView(R.id.rv_faqs)
    RecyclerView rv_faqs;

    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    Context context;
    FAQsRetrieve implement;
    FAQsCallback callback;
    DatabaseHandler db;
    FAQsAdapter adapter;
    ProgressDialog pd;
    ArrayList<MaceFAQs> maceFAQ = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        callback = this;
        db = new DatabaseHandler(context);
        implement = new FAQsRetrieve(context, callback, db);
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        if (NetworkTest.isOnline(getContext())) {
            pd.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    implement.getMaceFAQs();
                }
            }, 2000);
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);

        return view;
    }

    @Override
    public void onSuccess(FAQsModel body) {

        maceFAQ.clear();
        maceFAQ.addAll(body.getMaceFaqs());
        pd.dismiss();
        adapter = new FAQsAdapter(rv_faqs,context, maceFAQ);
        rv_faqs.setLayoutManager(new LinearLayoutManager(context));
        rv_faqs.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }


}
