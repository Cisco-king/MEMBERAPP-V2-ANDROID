package fragments;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import InterfaceService.ApiHospCallback;
import InterfaceService.ExclusionRetrieve;
import InterfaceService.FragmentApiHospCallback;
import InterfaceService.HospitalListRetrieve;
import Sqlite.DatabaseHandler;
import adapter.HospitalAdapter;
import adapter.HospitalListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.HospitalList;
import model.ProvincesAdapter;
import services.OnClicklistener;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.NetworkTest;
import utilities.SharedPref;

/**
 * Created by IPC on 11/16/2017.
 */


public class fragment_hospitalList extends Fragment implements View.OnClickListener, FragmentApiHospCallback {


    @BindView(R.id.ed_searchHosp)
    EditText ed_searchHosp;
    @BindView(R.id.rv_hospital)
    RecyclerView rv_hospital;
    @BindView(R.id.tv_hosp_not_found)
    TextView tv_hosp_not_found;

    @BindView(R.id.cvHospitalDetails)
    CardView cvHospitalDetails;
    @BindView(R.id.tvHospitalName)
    TextView tvHospitalName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvContactNo)
    TextView tvContactNo;
    @BindView(R.id.tvContactPerson)
    TextView tvContactPerson;

    @BindView(R.id.btn_sort)
    LinearLayout btn_sort;

    Context context;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    ProgressDialog pd;
    HospitalListRetrieve implement;
    DatabaseHandler databaseHandler;

    HospitalListAdapter hospitalAdapter;
    ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    ArrayList<HospitalList> arraylistHospital = new ArrayList<>();
    private String isMedicardOnly = "false";
    String sortBy = "";

    LinearLayoutManager llm;


    FragmentApiHospCallback fragmentApiHospCallback;
    private String search_string = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hospital_list, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        fragmentApiHospCallback = this;
        databaseHandler = new DatabaseHandler(context);
        implement = new HospitalListRetrieve(context, databaseHandler);

        sortBy = context.getString(R.string.medicard_first);
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_hospital.setLayoutManager(llm);

        if (NetworkTest.isOnline(context)) {
            ExclusionRetrieve.getHospInPatient(context, databaseHandler, SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context), fragmentApiHospCallback);
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

        init();
        return view;


    }

    private void init() {
        pd = new ProgressDialog(getContext(), R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Loading Hospital...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        btn_sort.setOnClickListener(this);
        hospitalAdapter = new HospitalListAdapter(context, arraylistHospital, fragmentApiHospCallback);
        rv_hospital.setAdapter(hospitalAdapter);
        retrieveHosp("");
        ed_searchHosp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                retrieveHosp(String.valueOf(s));
                search_string = String.valueOf(s);
            }
        });

    }

    private void retrieveHosp(final String s) {
        System.out.println("isMedicardOnly " + isMedicardOnly);
        System.out.println("selectedProvince " + selectedProvince);
        System.out.println("sortBy " + sortBy);
        System.out.println("selectedCity " + selectedCity);
        System.out.println("hospitalAdapter " + hospitalAdapter);
        System.out.println("s " + s);
        implement.updateHospitalList(isMedicardOnly, selectedProvince, sortBy, selectedCity, arraylistHospital, s);
        hospitalAdapter.notifyDataSetChanged();
        implement.updateListUI(arraylistHospital, rv_hospital, tv_hosp_not_found);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sort:

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
    public void onError(String message) {

    }

    @Override
    public void onSuccess() {
        retrieveHosp("");
    }

    @Override
    public void onHospitalSelect(ArrayList<HospitalList> array, int position) {
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, arraylistHospital.get(position).getHospitalName(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, arraylistHospital.get(position).getHospitalCode(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, arraylistHospital.get(position).getStreetAddress(), context);
        SharedPref.setAppPreference(context, SharedPref.KEY_HOSPITAL_FULL_ADDRESS, arraylistHospital.get(position).getFullAddress());
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, arraylistHospital.get(position).getPhoneNo(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, arraylistHospital.get(position).getContactPerson(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, "", context);


        Log.d(SharedPref.HOSPITAL_NAME, arraylistHospital.get(position).getHospitalName());
        Log.d(SharedPref.HOSPITAL_CODE, arraylistHospital.get(position).getHospitalCode());
        Log.d(SharedPref.HOSPITAL_ADD, arraylistHospital.get(position).getStreetAddress());

        try {
            cvHospitalDetails.setVisibility(View.VISIBLE);
            tvHospitalName.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, context));
            tvAddress.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.KEY_HOSPITAL_FULL_ADDRESS, context));
            if (arraylistHospital.get(position).getPhoneNo().isEmpty())
                tvContactNo.setText("NO CONTACT NUMBER");
            else
                tvContactNo.setText("Tel. No: " + SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, context));
            if (null == arraylistHospital.get(position).getContactPerson() || (arraylistHospital.get(position).getContactPerson().isEmpty() && arraylistHospital.get(position).getContactPerson().length() == 0)) {
                tvContactPerson.setVisibility(View.GONE);
            } else {
                tvContactPerson.setText("Contact Person: " +SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, context));
            }
        } catch (Exception e) {
            cvHospitalDetails.setVisibility(View.GONE);
        }
    }
}