package modules.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * <p>
 *      App Activity Configuration to all app may be initialize here
 * </p>
 *
 * @author John Paul Cas
 * @since 4/11/2017
 */
public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btnBack) FancyButton btnBack;

    @BindView(R.id.tvToolbarTitle) TextView tvToolbarTitle;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyView();
    }

    /**
     * <p>
     *     Initialize all Object/component(s) here, <br />
     *     All view is must initialize using {@link BindView}
     * </p>
     */
    protected void initViews() {
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
    }

    /**
     * <p>
     *     Detach activity View or Components here
     * </p>
     */
    protected void destroyView() {
        unbinder.unbind();
    }

    /**
     * <p>
     *     Set customable toolbar title
     * </p>
     *
     * @param title
     * The Toolbar Title
     */
    protected void setToolbarCustomableTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    /**
     * Handle Back navigation event
     */
    @OnClick(R.id.btnBack)
    public void backNavigation() {
        finish();
    }


    /**
     * <p>
     *     Set the layout resource ID if extend the base activity,
     *     This will be the equivalent to {@link android.app.Activity#setContentView(int)}
     * </p>
     *
     * @return
     * The Layout Resource ID
     */
    protected abstract int getLayoutResource();

}
