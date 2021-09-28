package com.term_tracker.C196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.term_tracker.C196.Entity.TermEntity;
import com.term_tracker.scheduler.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;

        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.termTextView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final TermEntity current = mTerms.get(position);
                    Intent intent = new Intent(context, CoursesActivity.class);
                    intent.putExtra("termId", current.getTermId());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStart", current.getTermStart());
                    intent.putExtra("termEnd", current.getTermEnd());
                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<TermEntity> mTerms;


        public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if(mTerms != null) {
            final TermEntity current = mTerms.get(position);
            holder.termItemView.setText(current.getTermTitle());
        } else {
            holder.termItemView.setText("NO WORD");
        }
    }

    @Override
    public int getItemCount() {
        if(mTerms != null) {
            return mTerms.size();
        } else {
            return 0;
        }
    }

    public void setTerms(List<TermEntity> words) {
        mTerms = words;
        notifyDataSetChanged();
    }

}
