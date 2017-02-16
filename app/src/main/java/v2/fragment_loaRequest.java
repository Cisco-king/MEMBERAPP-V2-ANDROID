package v2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.medicard.com.medicard.R;

import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import InterfaceService.LoaRequestRetrieve;
import Sqlite.DatabaseHandler;
import adapter.LoaRequestAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Loa;
import model.LoaList;
import utilities.AlertDialogCustom;
import utilities.SharedPref;


public class fragment_loaRequest extends Fragment implements LOARequestCallback {


    /**
     * ADD LOADING
     */

    @BindView(R.id.rv_loa_request)
    RecyclerView rv_loa_request;
    @BindView(R.id.btn_sort)
    FancyButton btn_sort;
    @BindView(R.id.btn_filter)
    FancyButton btn_filter;

    LinearLayoutManager llm;
    LoaRequestAdapter adapter;
    ArrayList<LoaList> arrayList = new ArrayList<>();

    LoaRequestRetrieve implement;
    LOARequestCallback callback;

    AlertDialogCustom alertDialogCustom;
    DatabaseHandler databaseHandler;
    private Context context;

    public fragment_loaRequest() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loarequest, container, false);
        ButterKnife.bind(this, view);

        init();


        return view;
    }

    private void init() {
        callback = this;
        context = getActivity();
        databaseHandler = new DatabaseHandler(context);
        implement = new LoaRequestRetrieve(context, callback);
        alertDialogCustom = new AlertDialogCustom();
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rv_loa_request.setLayoutManager(llm);
        adapter = new LoaRequestAdapter(context, arrayList, databaseHandler, callback);
        rv_loa_request.setAdapter(adapter);

        databaseHandler.dropLoa();
        arrayList.addAll(databaseHandler.retrieveLoa());
        //  implement.testDataDownLoadRequirement(arrayList , databaseHandler);
        implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));

//        implement.changeButtonColorDeselect(btn_filter);
//        implement.changeButtonColorSelected(btn_sort);


    }


    @OnClick({R.id.btn_sort, R.id.btn_filter})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_sort:
//                implement.changeButtonColorDeselect(btn_filter);
//                implement.changeButtonColorSelected(btn_sort);
                break;

            case R.id.btn_filter:
//                implement.changeButtonColorDeselect(btn_sort);
//                implement.changeButtonColorSelected(btn_filter);
                break;


        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onErrorLoaListener(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
    }

    @Override
    public void onSuccessLoaListener(Loa loa) {
        Log.d("LOA_SUCCESS", loa.toString());

        implement.getData(loa, databaseHandler);
    }

    @Override
    public void onDbLoaSuccessListener() {
        arrayList.clear();
        arrayList.addAll(databaseHandler.retrieveLoa());
        adapter.notifyDataSetChanged();
    }
}
