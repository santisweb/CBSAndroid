package com.cristalbusinessservices.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristalbusinessservices.Model.Meeting;
import com.cristalbusinessservices.databinding.LineMeetingBinding;

import java.util.List;

public class AdapterMeeting extends RecyclerView.Adapter<AdapterMeeting.ViewHolder> {
    Context context;
    List<Meeting> list;

    public AdapterMeeting(Context context, List<Meeting> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LineMeetingBinding binding = LineMeetingBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting met = list.get(position);
        holder.bind.tvTitle.setText("Meeting: "+met.getTitle());
        holder.bind.tvMess.setText(met.getMess());
        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(met.getUrl()));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LineMeetingBinding bind;
        public ViewHolder(@NonNull LineMeetingBinding lineMeetingBinding) {
            super(lineMeetingBinding.getRoot());
            this.bind = lineMeetingBinding;
        }
    }
}
