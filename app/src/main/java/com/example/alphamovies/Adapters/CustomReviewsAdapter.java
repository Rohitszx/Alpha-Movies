package com.example.alphamovies.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alphamovies.Models.Reviews;
import com.example.alphamovies.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomReviewsAdapter extends RecyclerView.Adapter<CustomReviewsAdapter.MovieReviewViewHolder> {

    private Context context;
    private List<Reviews> reviewList;

    public CustomReviewsAdapter(Context context, List<Reviews> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_reviews_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieReviewViewHolder holder, final int position) {
        final Reviews review = reviewList.get(position);
        holder.bindReview(reviewList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current state of the item
                boolean expanded = review.isExpanded();
                // Change the state
                review.setExpanded(!expanded);
                // Notify the adapter that item has changed
                CustomReviewsAdapter.this.notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView author;
        @BindView(R.id.tv_content)
        TextView content;
        @BindView(R.id.sub_item)
        LinearLayout subItem;

        MovieReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindReview(Reviews review) {
            boolean expanded = review.isExpanded();
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            author.setText(review.getAuthor());
            content.setText(review.getContent());
        }
    }
}
