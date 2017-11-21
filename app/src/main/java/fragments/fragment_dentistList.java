package fragments;

import android.content.Context;
import android.content.Intent;
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

import InterfaceService.FragmentApiDentistCallback;
import InterfaceService.FragmentDentistListRetrive;
import Sqlite.DatabaseHandler;
import adapter.DentistListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.CitiesAdapter;
import model.DentistList;
import model.ProvincesAdapter;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.SetUnfilledField;
import utilities.SharedPref;
import v2.SortDentistFragment;
import v2.SortHospitalActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by IPC on 11/17/2017.
 */

public class fragment_dentistList extends Fragment implements FragmentApiDentistCallback, View.OnClickListener {

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

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.btn_sort)
    LinearLayout btn_sort;

    int DOCTOR_CALL = 100;

    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    Context context;
    FragmentApiDentistCallback callback;
    DentistListAdapter dentistListAdapter;
    DatabaseHandler databaseHandler;
    Loader loader;
    FragmentDentistListRetrive implement;
    LinearLayoutManager llm;
    ArrayList<DentistList> dentistListArrayList = new ArrayList<>();
    //    ArrayList<SpecsAdapter> selectedSpec = new ArrayList<>();
    private String search_string = "";
    private String sort_by = "";
    private String room_number = "";
    private String DERMATOLOGY = "DERMATOLOGY";

    private String isMedicardOnly = "false";
    String sortBy = "";
    ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    private int SORT_CALL = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dentist_list, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        callback = this;
        databaseHandler = new DatabaseHandler(context);
        loader = new Loader(context);
        implement = new FragmentDentistListRetrive(context, callback, databaseHandler);

        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_doctor.setLayoutManager(llm);


        implement.dropDentist();
        implement.getDentistConnection();


        dentistListAdapter = new DentistListAdapter(context, dentistListArrayList, callback,databaseHandler);
        rv_doctor.setAdapter(dentistListAdapter);
        tv_title.setText(context.getString(R.string.select_dentist));

        ed_searchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getSearchDoctor(String.valueOf(s));
                search_string = String.valueOf(s);
            }
        });
        btn_sort.setOnClickListener(this);
        return view;
    }

//    private void getSearchDoctor(String editable, ArrayList<SpecsAdapter> selectedSpec, String sort_by, String room_number) {
//
//        array.clear();
//        array.addAll(databaseHandler.retrieveDoctor(String.valueOf(editable), selectedSpec, implement.testSort(sort_by), room_number));
//
//        // get only the unique value from the set
//        Timber.d("original size with duplicate %s", array.size());
//        Set<GetDoctorsToHospital> uniqueSet = new LinkedHashSet<>(array);
//        array.clear();
//        array.addAll(uniqueSet);
//        Timber.d("new set without duplicate %s", array.size());
//
//        doctorAdapter.notifyDataSetChanged();
//
//        if (array.size() == 0) {
//            tv_doc_not_found.setVisibility(View.VISIBLE);
//            cvDoctorDetails.setVisibility(View.GONE);
//            rv_doctor.setVisibility(View.GONE);
//        } else {
//            doctorAdapter.notifyDataSetChanged();
//            rv_doctor.setVisibility(View.VISIBLE);
//            tv_doc_not_found.setVisibility(View.GONE);
//        }
//    }




    @OnClick({R.id.btn_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sort:
                Intent intent = new Intent(getActivity(), SortDentistFragment.class);
                intent.putExtra(Constant.MEDICARD_ONLY, isMedicardOnly);
                intent.putExtra(Constant.SORT_BY, sortBy);
                intent.putParcelableArrayListExtra(Constant.SELECTED_CITY, selectedCity);
                intent.putParcelableArrayListExtra(Constant.SELECTED_PROVINCE, selectedProvince);
                intent.putExtra(Constant.SEARCH_STRING, search_string);
                startActivityForResult(intent, SORT_CALL);
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
    public void onDentistSelect(ArrayList<DentistList> array, int position) {
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, array.get(position).getDocLname() +", "+ array.get(position).getDocLname() +" "+ array.get(position).getDocFname() , context);
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, array.get(position).getDoctorCode(), context);
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, array.get(position).getSpecDesc(), context);
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_SCHED, array.get(position).getSchedule(), context);
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_U, "", context);
//        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, array.get(position).getRoom(), context);

//        try {
//            cvDoctorDetails.setVisibility(View.VISIBLE);
//            tv_name.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, context));
//            tv_spec.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, context));
//            tv_sched.setText("Schedule: " + SetUnfilledField.setData(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_SCHED, context)));
//            tv_room.setText("Room: " + SetUnfilledField.setData(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, context)));
//        } catch (Exception e) {
//            cvDoctorDetails.setVisibility(View.GONE);
//        }
    }

    @Override
    public void internetConnected() {
        loader.checkLoad();
        loader.startLad();
        loader.setMessage("Getting Dentist List...");
        implement.getDentistList();
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
    public void onSuccess(ArrayList<DentistList> DentistLists) {
        implement.updateDentistList(DentistLists);
    }

    @Override
    public void onSuccessDataInsert(ArrayList<DentistList> DentistLists) {

        if (DentistLists != null && DentistLists.size() > 0) {
            alertDialogCustom.showMe(
                    context,
                    alertDialogCustom.HOLD_ON_title,
                    getString(R.string.success_load_dentist),
                    1);
        }
        getSearchDoctor("");
        loader.stopLoad();
    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SORT_CALL && resultCode == RESULT_OK) {
            isMedicardOnly = data.getStringExtra(Constant.MEDICARD_ONLY);
            sortBy = data.getStringExtra(Constant.SORT_BY);
            selectedCity = data.getParcelableArrayListExtra(Constant.SELECTED_CITY);
            selectedProvince = data.getParcelableArrayListExtra(Constant.SELECTED_PROVINCE);
            search_string = data.getStringExtra(Constant.SEARCH_STRING);

//            Log.d("sort_by", sortBy);
//            for (int x = 0; x < selectedCity.size(); x++) {
//                Log.d("selected_city", selectedCity.get(x).getCityName());
//           }
            ed_searchDoctor.setText(search_string);
            getSearchDoctor(search_string);
        }
    }


    private void getSearchDoctor(String editable) {

//        array.clear();
//        array.addAll(databaseHandler.retrieveDentist(String.valueOf(editable)));
//
//        dentistListAdapter.notifyDataSetChanged();
//
//        if (array.size() == 0) {
//            tv_doc_not_found.setVisibility(View.VISIBLE);
//            cvDoctorDetails.setVisibility(View.GONE);
//            rv_doctor.setVisibility(View.GONE);
//        } else {
//            dentistListAdapter.notifyDataSetChanged();
//            rv_doctor.setVisibility(View.VISIBLE);
//            tv_doc_not_found.setVisibility(View.GONE);
//        }
        System.out.println("isMedicardOnly " + isMedicardOnly);
        System.out.println("selectedProvince " + selectedProvince);
        System.out.println("sortBy " + sortBy);
        System.out.println("selectedCity " + selectedCity);
        System.out.println("dentistListAdapter " + dentistListAdapter);
        System.out.println("s " + editable);
        implement.updateDentistList(isMedicardOnly, selectedProvince, sortBy, selectedCity, dentistListArrayList, editable);
        dentistListAdapter.notifyDataSetChanged();
        implement.updateListUI(dentistListArrayList, rv_doctor, tv_doc_not_found);
    }

    @Override
    public void onPause() {
        super.onPause();
        loader.checkLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loader.checkLoad();
    }
}