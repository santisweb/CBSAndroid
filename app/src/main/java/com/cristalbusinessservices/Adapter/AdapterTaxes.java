package com.cristalbusinessservices.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cristalbusinessservices.Model.My_Taxes.ResultMyTaxes;
import com.cristalbusinessservices.MyTaxes;
import com.cristalbusinessservices.R;
import com.cristalbusinessservices.UploadDocument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterTaxes extends RecyclerView.Adapter<AdapterTaxes.RecyclerViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private List<ResultMyTaxes> arrayList;
    private Context context;

    public AdapterTaxes(Context context, List<ResultMyTaxes> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.cv_upload_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyTaxes)context).startActivityForResult(new Intent(context, UploadDocument.class).putExtra("posiTax", position), 12);
            }
        });

        holder.bt_make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyTaxes)context).getPaid("/v1/invoices/tax/" + arrayList.get(position).getId() + "/unpaid?contactId=" + arrayList.get(position).getContactId());
            }
        });

        holder.cv_complete_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size() - 1 >= position && arrayList.get(position)!=null && arrayList.get(position).getCompleteFormLink()!=null && arrayList.get(position).getCompleteFormLink().length()>0) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayList.get(position).getCompleteFormLink()));
                    ((MyTaxes) context).startActivity(browserIntent);
                }

            }
        });

        holder.tv_name.setText(arrayList.get(position).getTitle());
        String pattern_date0 = "yyyy-MM-dd'T'HH:mm:ss";
        String strDateFormat0 = "MM-dd-yyyy";
        String reformattedStr0 = "";
        try {
            reformattedStr0 = new SimpleDateFormat(strDateFormat0).format(new SimpleDateFormat(pattern_date0).parse(arrayList.get(position).getCreatedDate()));
            holder.tv_submit.setText(context.getString(R.string.submitted_on)+" "+reformattedStr0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tv_status.setText(arrayList.get(position).getStatus());
        if (arrayList.get(position).getStatus().equals("Complete")){
            holder.tv_status.setTextColor(Color.parseColor("#31ac00"));
            holder.img_status.setColorFilter(Color.parseColor("#31ac00"));
        }else {
            holder.tv_status.setTextColor(Color.parseColor("#ff7315"));
            holder.img_status.setColorFilter(Color.parseColor("#ff7315"));
        }
    }

    //
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.line_my_taxes, viewGroup, false);
        return new RecyclerViewHolder(mainGroup);

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cv_complete_client)
        CardView cv_complete_client;
        @BindView(R.id.cv_upload_document)
        CardView cv_upload_document;
        @BindView(R.id.bt_make_payment)
        CardView bt_make_payment;
        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;
        @BindView(R.id.tv_submit)
        AppCompatTextView tv_submit;
        @BindView(R.id.tv_status)
        AppCompatTextView tv_status;
        @BindView(R.id.img_status)
        AppCompatImageView img_status;
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

