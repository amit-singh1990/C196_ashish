package com.term_tracker.C196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.term_tracker.C196.Entity.CourseEntity;
import com.term_tracker.scheduler.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<CourseEntity> mCourses;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView1;
        private final TextView courseItemView2;

        private CourseViewHolder(View itemView) {
            super(itemView);
                courseItemView1 = itemView.findViewById(R.id.courseTextView1);
                courseItemView2 = itemView.findViewById(R.id.courseTextView2);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        final CourseEntity current = mCourses.get(position);
                        Intent intent = new Intent(context, CourseDetailsActivity.class);
                        intent.putExtra("courseId", current.getCourseId());
                        intent.putExtra("courseTitle", current.getCourseTitle());
                        intent.putExtra("startDate", current.getStartDate());
                        intent.putExtra("endDate", current.getEndDate());
                        intent.putExtra("courseStatus", current.getCourseStatus());
                        intent.putExtra("instructorName", current.getInstructorName());
                        intent.putExtra("instructorPhone", current.getInstructorPhone());
                        intent.putExtra("instructorEmail", current.getInstructorEmail());
                        intent.putExtra("courseNotes", current.getCourseNotes());
                        intent.putExtra("termId", current.getTermId());
                        intent.putExtra("farrock", "soph ooooouse");
                        context.startActivity(intent);
                    }
                });
        }
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item,parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if(mCourses != null) {
            CourseEntity current = mCourses.get(position);
            holder.courseItemView1.setText(current.getCourseTitle());
            holder.courseItemView2.setText(current.getEndDate());
        }
            else {
            CourseEntity current = mCourses.get(position);
            holder.courseItemView1.setText("No Word");
            holder.courseItemView2.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if(mCourses != null) {
            return mCourses.size();
        }
        else return 0;
    }

    public void setCourses(List<CourseEntity> words) {
        mCourses = words;
        notifyDataSetChanged();
    }
}
