package com.cristalbusinessservices.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cristalbusinessservices.Model.Appointments.ResultAppointments;
import com.cristalbusinessservices.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMyAppointments extends RecyclerView.Adapter<AdapterMyAppointments.RecyclerViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private List<ResultAppointments> arrayList;
    private Context context;

    public AdapterMyAppointments(Context context, List<ResultAppointments> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            holder.tv_name.setText(arrayList.get(position).getShortDescription());
            String pattern_date0 = "yyyy-MM-dd'T'HH:mm:ss";
            String strDateFormat0 = "EEEE, MMMM dd',' yyyy ";
            String strDateFormat2 = "hh:mm a";
            String reformattedStr0 = "";
            try {
                reformattedStr0 = new SimpleDateFormat(strDateFormat0).format(new SimpleDateFormat(pattern_date0).parse(arrayList.get(position).getStartDate()));
                holder.tv_time.setText(reformattedStr0 +"\nTime: "+new SimpleDateFormat(strDateFormat2).format(new SimpleDateFormat(pattern_date0).parse(arrayList.get(position).getStartDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.line_my_appointments, viewGroup, false);
        return new RecyclerViewHolder(mainGroup);

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;
        @BindView(R.id.tv_time)
        AppCompatTextView tv_time;

        RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}

