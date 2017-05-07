package modules.base.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import constants.ParcelableObject;
import model.Member;

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
