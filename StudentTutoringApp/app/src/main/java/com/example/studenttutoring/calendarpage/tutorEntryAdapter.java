package com.example.studenttutoring.calendarpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studenttutoring.R;

import java.util.ArrayList;

public class tutorEntryAdapter extends RecyclerView.Adapter<tutorEntryAdapter.tutorViewHolder>{
    private ArrayList<tutorEntry> tutorList;


    public tutorEntryAdapter(ArrayList<tutorEntry> tutorList) { this.tutorList=tutorList;}

    @NonNull
    @Override
    public tutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tutor_slot,
                parent,
                false);
        return new tutorViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull tutorViewHolder holder, int position) {
        holder.setTutor(tutorList.get(position));
    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }


    static class tutorViewHolder extends RecyclerView.ViewHolder{

        TextView tutorName, tutorStart, tutorEnd, tutorEmail, tutorNum;
        public tutorViewHolder(@NonNull View itemView) {
            super(itemView);
            tutorName = itemView.findViewById(R.id.tutor_name);
            tutorStart = itemView.findViewById(R.id.tutor_start_time);
            tutorEnd = itemView.findViewById(R.id.tutor_end_time);
            tutorEmail = itemView.findViewById(R.id.tutor_email);
            tutorNum = itemView.findViewById(R.id.tutor_contact_num);
        }

        void setTutor(tutorEntry tutor){
            tutorName.setText(tutor.getName());
            tutorStart.setText(tutor.getStart_time());
            tutorEnd.setText(tutor.getEnd_time());
            tutorEmail.setText(tutor.getEmail());
            tutorNum.setText(tutor.getContact_num());
        }
    }
}
