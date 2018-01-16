package adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.GetDoctorsToHospital;
import utilities.SetUnfilledField;
import utilities.SharedPref;

/**
 * Created by IPC on 1/16/2018.
 */

public class MockUpDocAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_VIEW = 0;
    public final int TYPE_LOAD = 1;

    static Context context;
    ArrayList<GetDoctorsToHospital> arrayListDoc;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public MockUpDocAdapter(Context context, ArrayList<GetDoctorsToHospital> arrayListDoc) {
        this.context = context;
        this.arrayListDoc = arrayListDoc;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_VIEW) {
            return new DoctorHolder(inflater.inflate(R.layout.row_doctor_mock, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_VIEW) {
            ((DoctorHolder) holder).bindData(arrayListDoc.get(position), position);
        }
        //No else part needed as loadFirst holder doesn't bind any data
    }

    @Override
    public int getItemViewType(int position) {
        if (null != arrayListDoc.get(position).getType()
                && !arrayListDoc.get(position).getType().equals("load")) {
            return TYPE_LOAD;
        } else {
            return TYPE_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return arrayListDoc.size();
    }

    /* VIEW HOLDERS */

    static class DoctorHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_spec)
        TextView tv_spec;
        @BindView(R.id.tv_sched)
        TextView tv_sched;
        @BindView(R.id.tv_room)
        TextView tv_room;
        @BindView(R.id.cv_item)
        CardView cv_item;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public DoctorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(GetDoctorsToHospital doc, int position) {

            if (null == doc.getDocLname() && null == doc.getDocFname()){
                progressBar.isEnabled();
                cv_item.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }else {
                cv_item.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tv_name.setText((++position) + " " + doc.getDocLname() + ", " + doc.getDocFname());
                tv_spec.setText(SetUnfilledField.setData(null == doc.getSpecDesc() ? "" : doc.getSpecDesc()));
                tv_sched.setText("Schedule: " + SetUnfilledField.setData(null == doc.getSchedule() ? "" : doc.getSchedule()));
                tv_room.setText("Room: " + SetUnfilledField.setData(null == doc.getRoom() ? "" : doc.getRoom()));
            }

        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void setIsLoading(boolean isLoading1) {
        isLoading = isLoading1;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
