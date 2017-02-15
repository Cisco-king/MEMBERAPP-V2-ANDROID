package v2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.medicard.com.medicard.R;
import android.widget.Button;

import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import InterfaceService.LoaRequestRerieve;
import adapter.LoaRequestAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.LoaRequest;

public class fragment_loaRequest extends Fragment implements LOARequestCallback {

    @BindView(R.id.rv_loa_request)
    RecyclerView rv_loa_request;
    @BindView(R.id.btn_sort)
    FancyButton btn_sort;
    @BindView(R.id.btn_filter)
    FancyButton btn_filter;

    LinearLayoutManager llm;
    LoaRequestAdapter adapter;
    ArrayList<LoaRequest> arrayList = new ArrayList<>();

    LoaRequestRerieve implement;

    private Context context;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public fragment_loaRequest() {
        // Required empty public constructor
    }

    public static fragment_loaRequest newInstance(String param1, String param2) {
        fragment_loaRequest fragment = new fragment_loaRequest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        context = getActivity();
        implement = new LoaRequestRerieve(context);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_loa_request.setLayoutManager(llm);


//        implement.changeButtonColorDeselect(btn_filter);
//        implement.changeButtonColorSelected(btn_sort);

        addData();
        adapter = new LoaRequestAdapter(context, arrayList);
        rv_loa_request.setAdapter(adapter);
    }

    private void addData() {


        for (int x = 0; x < 10; x++) {


            LoaRequest loa = new LoaRequest("John, The " + x, "Oct 1,200" + x, "PENDING");
            arrayList.add(loa);

        }


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
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
