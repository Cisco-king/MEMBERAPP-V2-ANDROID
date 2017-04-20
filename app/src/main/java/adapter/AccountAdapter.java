package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.medicard.member.MemberAccountActivity;
import com.medicard.member.R;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Accounts;
import model.Cities;
import model.Dependents;
import model.GetUSER;
import model.MemberInfo;
import model.VerifyMember;
import model.VerifyMemberData;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.ImageConverters;
import utilities.RemarksFilter;
import utilities.SharedPref;

/**
 * Created by Jpcab on 9/29/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int TYPE_HEADER = 0;
    private int TYPE_ITEM = 1;
    ArrayList<Dependents> arrayAccounts = new ArrayList();
    ArrayList<MemberInfo> header = new ArrayList();
    Context context;
    ProgressDialog progressDialog;
    AlertDialogCustom alertDialogCustom;

    public AccountAdapter(ArrayList<MemberInfo> header, ArrayList<Dependents> arrayAccounts, Context context, ProgressDialog progressDialog, AlertDialogCustom alertDialogCustom) {
        this.arrayAccounts = arrayAccounts;
        this.context = context;
        this.header = header;
        this.progressDialog = progressDialog;
        this.alertDialogCustom = alertDialogCustom;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View header = LayoutInflater.from(context).inflate(R.layout.fragment_member_info_header, parent, false);
            return new headerHolder(header);
        } else if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.row_accounts, parent, false);
            return new itemHolder(itemView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof headerHolder) {
            final headerHolder headerHolder = (AccountAdapter.headerHolder) holder;
            //header.get(0).getMEM_FNAME() + " " + header.get(0).getMEM_LNAME()
            headerHolder.tv_name.setText(header.get(0).getMEM_NAME());
            headerHolder.tv_company.setText(header.get(0).getACCOUNT_NAME());
            headerHolder.tv_id.setText(header.get(0).getPRIN_CODE());

            headerHolder.progressBar.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(AppInterface.PHOTOLINK + header.get(0).getPRIN_CODE())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .placeholder(R.drawable.imageplaceholder)
                    .error(R.drawable.imageplaceholder)
                    .into(headerHolder.iv_user, new Callback() {
                        @Override
                        public void onSuccess() {

                            headerHolder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            headerHolder.progressBar.setVisibility(View.GONE);
                        }
                    });


        } else if (holder instanceof itemHolder) {

            itemHolder itemHolder = (AccountAdapter.itemHolder) holder;
            itemHolder.tv_company.setText(header.get(0).getACCOUNT_NAME());
            itemHolder.tv_id.setText(arrayAccounts.get(position - 1).getMemCode());
            itemHolder.tv_name.setText(arrayAccounts.get(position - 1).getMemFname() + " " + arrayAccounts.get(position - 1).getMemLname());

        }
    }

    @Override
    public int getItemCount() {
        return arrayAccounts.size() + 1;
    }


    public class itemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_name, tv_id, tv_company;

        public CardView cv_account;

        public itemHolder(View item) {
            super(item);
            tv_name = (TextView) item.findViewById(R.id.tv_name);
            tv_id = (TextView) item.findViewById(R.id.tv_idNumber);
            cv_account = (CardView) item.findViewById(R.id.cv_account);

            tv_company = (TextView) item.findViewById(R.id.tv_company);

            cv_account.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {


                case R.id.cv_account:

                    getUserData(arrayAccounts.get(getAdapterPosition() - 1).getMemCode());

                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;

    }

    public class headerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        CircleImageView iv_user;
        public TextView tv_name, tv_id, tv_company;

        CardView cv_account;
        ProgressBar progressBar;

        public headerHolder(View itemView) {
            super(itemView);

            iv_user = (CircleImageView) itemView.findViewById(R.id.iv_user);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_id = (TextView) itemView.findViewById(R.id.tv_idNumber);
            tv_company = (TextView) itemView.findViewById(R.id.tv_company);

            cv_account = (CardView) itemView.findViewById(R.id.cv_account);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            cv_account.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.cv_account:

                    RemarksFilter filter = new RemarksFilter();
                    Intent intent = new Intent(v.getContext(), MemberAccountActivity.class);
                    intent.putExtra("USER", "PRINCIPAL");
                    intent.putExtra("BIRTHDAY", header.get(0).getBDAY());
                    intent.putExtra("AGE", header.get(0).getAGE());
                    intent.putExtra("CIVIL", header.get(0).getCIVIL_STATUS());
                    intent.putExtra("GENDER", header.get(0).getMEM_SEX());
                    intent.putExtra("COMPANY", header.get(0).getACCOUNT_NAME());
                    intent.putExtra("STATUS", header.get(0).getMem_OStat_Code());
                    intent.putExtra("MEMTYPE", header.get(0).getMEM_TYPE());
                    intent.putExtra("MEMCODE", header.get(0).getPRIN_CODE());
                    intent.putExtra("EFFECTIVE", header.get(0).getEFF_DATE());
                    intent.putExtra("VALIDITY", header.get(0).getVAL_DATE());
                    intent.putExtra("DD", header.get(0).getDD_Reg_Limits());
                    intent.putExtra("LNAME", header.get(0).getMEM_LNAME());
                    intent.putExtra("FNAME", header.get(0).getMEM_FNAME());
                    intent.putExtra("PLAN", header.get(0).getPlan_Desc());
                    intent.putExtra("BIRTHDAY", header.get(0).getBDAY());
                    intent.putExtra("REMARK", filter.filterRemarks(header.get(0).getID_REM(),
                            header.get(0).getID_REM2(),
                            header.get(0).getID_REM3(),
                            header.get(0).getID_REM4(),
                            header.get(0).getID_REM5(),
                            header.get(0).getID_REM6(),
                            header.get(0).getID_REM7()));


                    v.getContext().startActivity(intent);
                    //  ((Activity)context).finish();
                    break;


            }

        }
    }

    public void getUserData(String id) {

        progressDialog.show();
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDependentInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<VerifyMemberData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {

                            Log.e("ACCOUNTADAPTER", e.getMessage());


                            alertDialogCustom.showMe(context, "Hold On", ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            progressDialog.dismiss();


                        } catch (Exception error) {


                            alertDialogCustom.showMe(context, "Hold On", ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            progressDialog.dismiss();


                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(VerifyMemberData memberInfo) {
                        RemarksFilter filter = new RemarksFilter();
                        Intent intent = new Intent(context, MemberAccountActivity.class);
                        intent.putExtra("USER", "DEPENDENT");
                        intent.putExtra("BIRTHDAY", memberInfo.getMemberInfo().getBDAY());
                        intent.putExtra("AGE", memberInfo.getMemberInfo().getAGE() + "");
                        intent.putExtra("CIVIL", memberInfo.getMemberInfo().getCIVIL_STATUS() + "");
                        intent.putExtra("GENDER", String.valueOf(memberInfo.getMemberInfo().getMEM_SEX()));
                        intent.putExtra("COMPANY", memberInfo.getMemberInfo().getACCOUNT_NAME() + "");
                        intent.putExtra("STATUS", memberInfo.getMemberInfo().getMem_OStat_Code() + "");
                        intent.putExtra("MEMTYPE", memberInfo.getMemberInfo().getMEM_TYPE() + "");
                        intent.putExtra("MEMCODE", memberInfo.getMemberInfo().getPRIN_CODE() + "");
                        intent.putExtra("EFFECTIVE", memberInfo.getMemberInfo().getEFF_DATE() + "");
                        intent.putExtra("VALIDITY", memberInfo.getMemberInfo().getVAL_DATE() + "");
                        intent.putExtra("DD", memberInfo.getMemberInfo().getDD_Reg_Limits() + "");
                        intent.putExtra("LNAME", memberInfo.getMemberInfo().getMEM_LNAME() + "");
                        intent.putExtra("FNAME", memberInfo.getMemberInfo().getMEM_FNAME() + "");
                        intent.putExtra("PLAN", memberInfo.getMemberInfo().getPlan_Desc() + "");
                        intent.putExtra("BIRTHDAY", memberInfo.getMemberInfo().getBDAY() + "");
                        intent.putExtra("REMARK", filter.filterRemarks(memberInfo.getMemberInfo().getID_REM(),
                                memberInfo.getMemberInfo().getID_REM2(),
                                memberInfo.getMemberInfo().getID_REM3(),
                                memberInfo.getMemberInfo().getID_REM4(),
                                memberInfo.getMemberInfo().getID_REM5(),
                                memberInfo.getMemberInfo().getID_REM6(),
                                memberInfo.getMemberInfo().getID_REM7()));


                        context.startActivity(intent);
                        //  ((Activity)context).finish();

                        progressDialog.dismiss();

                    }
                });
    }

}
