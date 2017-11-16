package fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;

import butterknife.ButterKnife;
import utilities.AlertDialogCustom;

/**
 * Created by IPC on 11/16/2017.
 */

public class fragment_doctorList extends Fragment {


    AlertDialogCustom alertDialogCustom = new AlertDialogCustom() ;
    ProgressDialog pd;

    public fragment_doctorList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
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
}
