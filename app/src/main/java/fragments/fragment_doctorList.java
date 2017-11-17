package fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.LinkedHashSet;
import java.util.Set;

import InterfaceService.DoctorRetrieve;
import InterfaceService.FragmentApiDocCallback;
import InterfaceService.FragmentDoctorListRetrieve;
import Sqlite.DatabaseHandler;
import adapter.DoctorAdapter;
import adapter.DoctorListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Doctors;
import model.GetDoctorsToHospital;
import model.SpecsAdapter;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.SharedPref;

/**
 * Created by IPC on 11/16/2017.
 */

public class fragment_doctorList extends Fragment implements FragmentApiDocCallback {

    @BindView(R.id.ed_searchDoctor)
    EditText ed_searchDoctor;
    @BindView(R.id.rv_doctor)
    RecyclerView rv_doctor;
    @BindView(R.id.tv_doc_not_found)
    TextView tv_doc_not_found;

    @BindView(R.id.cvDoctorDetails)
    CardView cvDoctorDetails;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_spec)
    TextView tv_spec;
    @BindView(R.id.tv_room)
    TextView tv_room;
    @BindView(R.id.tv_sched)
    TextView tv_sched;

    @BindView(R.id.btn_sort)
    LinearLayout btn_sort;


    AlertDialogCustom alertDialogCustom = new AlertDialogCustom() ;
    ProgressDialog pd;
    Context context;
    FragmentApiDocCallback callback;
    DoctorListAdapter doctorAdapter;
    DatabaseHandler databaseHandler;
    Loader loader;
    FragmentDoctorListRetrieve implement;
    LinearLayoutManager llm;
    ArrayList<GetDoctorsToHospital> array = new ArrayList<>();
    ArrayList<SpecsAdapter> selectedSpec = new ArrayList<>();
    private String search_string = "";
    private String sort_by = "";
    private String room_number = "";
    private String DERMATOLOGY = "DERMATOLOGY";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        callback = this;
        databaseHandler = new DatabaseHandler(context);
        loader = new Loader(context);
        implement = new FragmentDoctorListRetrieve(context, callback, databaseHandler);

        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_doctor.setLayoutManager(llm);

        String hosp_code = SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context);
        if(hosp_code != null){
            implement.dropDoctors();
            implement.getDoctors(hosp_code);
        }else {
            tv_doc_not_found.setVisibility(View.VISIBLE);
            tv_doc_not_found.setText("Please Select Hospital first");
        }

        doctorAdapter = new DoctorListAdapter(context, array,callback);
        rv_doctor.setAdapter(doctorAdapter);



        ed_searchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getSearchDoctor(String.valueOf(s), selectedSpec, sort_by, room_number);
                search_string = String.valueOf(s);
            }
        });
        init();
        return view;
    }

    private void getSearchDoctor(String editable, ArrayList<SpecsAdapter> selectedSpec, String sort_by, String room_number) {

        array.clear();
        array.addAll(databaseHandler.retrieveDoctor(String.valueOf(editable), selectedSpec, implement.testSort(sort_by), room_number));

        // get only the unique value from the set
        Timber.d("original size with duplicate %s", array.size());
        Set<GetDoctorsToHospital> uniqueSet = new LinkedHashSet<>(array);
        array.clear();
        array.addAll(uniqueSet);
        Timber.d("new set without duplicate %s", array.size());

        doctorAdapter.notifyDataSetChanged();

        if (array.size() == 0) {
            tv_doc_not_found.setVisibility(View.VISIBLE);
            cvDoctorDetails.setVisibility(View.GONE);
            rv_doctor.setVisibility(View.GONE);
        } else {
            doctorAdapter.notifyDataSetChanged();
            rv_doctor.setVisibility(View.VISIBLE);
            tv_doc_not_found.setVisibility(View.GONE);
        }
    }

    private void init() {
        pd = new ProgressDialog(getContext(), R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Requesting...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
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
    public void onDoctorSelect(ArrayList<GetDoctorsToHospital> array, int adapterPosition) {

    }

    @Override
    public void internetConnected(String hospCode) {
        loader.startLad();
        loader.setMessage("Getting Doctor List...");
        implement.getDoctorList(hospCode);
    }

    @Override
    public void noInternetConnection() {
        alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);
    }

    @Override
    public void onError(String message) {
        Log.e("DOCTOR", message);
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onSuccess(Doctors doctors) {
        loader.stopLoad();

        Timber.d("doctor list : %s", doctors.toString());
        if (doctors.getGetDoctorsToHospital() != null && doctors.getGetDoctorsToHospital().size() > 0) {
            alertDialogCustom.showMe(
                    context,
                    alertDialogCustom.HOLD_ON_title,
                    getString(R.string.success_load_doctors),
                    1);
        }
        getSearchDoctor("", selectedSpec, sort_by, "");
    }
}
