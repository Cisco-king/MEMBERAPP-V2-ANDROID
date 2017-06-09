package modules.base.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

import constants.ParcelableObject;
import model.Attachment;
import model.HospitalList;
import model.Member;
import services.model.HospitalsToDoctor;
import utilities.SharedPref;

/**
 * Created by John Paul Cas on 4/12/2017.
 */

public abstract class TestTrackableActivity extends BaseActivity {

    private SharedPreferences testSharePref;
    private Member member;


    @Override
    protected void initViews() {
        super.initViews();

        initParcelableMember();
    }

    protected void initParcelableMember() {
        try {
            member = getIntent().getParcelableExtra(ParcelableObject.MEMBER);
        } catch (Exception e) {
            // do some logging here...
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ParcelableObject.MEMBER, member);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        member = savedInstanceState.getParcelable(ParcelableObject.MEMBER);
    }

    /**
     *
     * @return
     * The {@link Member}
     */
    protected Member getMember() {
        return this.member;
    }

}
