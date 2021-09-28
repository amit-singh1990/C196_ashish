package com.term_tracker.C196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.scheduler.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<AssessmentEntity> mAssessment;

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItem1;
        private final TextView assessmentItem2;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItem1 = itemView.findViewById(R.id.assignmentTextView1);
            assessmentItem2 = itemView.findViewById(R.id.assignmentTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final AssessmentEntity currentAssessment = mAssessment.get(position);
                    Intent intent = new Intent(context, AssessmentDetailsActivity.class);
                    intent.putExtra("assessmentId", currentAssessment.getAssessmentId());
                    intent.putExtra("assessmentPerformanceTitle", currentAssessment.getPerformanceTitle());
                    intent.putExtra("assessmentPerformanceDate", currentAssessment.getPerformanceDate());
                    intent.putExtra("assessmentObjectiveTitle", currentAssessment.getObjectiveTitle());
                    intent.putExtra("assessmentObjectiveDate", currentAssessment.getObjectiveDate());
                    intent.putExtra("courseId", currentAssessment.getCourseId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assignment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if(mAssessment != null) {
            AssessmentEntity currentAssessment = mAssessment.get(position);
            holder.assessmentItem1.setText(currentAssessment.getPerformanceTitle());
            holder.assessmentItem2.setText(currentAssessment.getObjectiveTitle());
        }
    }

    public void setAssessments(List<AssessmentEntity> words) {
        mAssessment = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessment != null) {
            return mAssessment.size();
        } else {
            return 0;
        }
    }
}
