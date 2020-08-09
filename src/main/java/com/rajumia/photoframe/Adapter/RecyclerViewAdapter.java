package com.rajumia.photoframe.Adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ortiz.touchview.TouchImageView;
import com.rajumia.photoframe.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> {

    int[] images;
    RecyclerView recyclerView;

    public class MyView extends RecyclerView.ViewHolder {
        TouchImageView imageView;
        public MyView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.displayPic);
        }
    }

    public RecyclerViewAdapter(int[] horizontalList,RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.images = horizontalList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);

        //new improvement
        int width = recyclerView.getWidth();
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.width = width;
        itemView.setLayoutParams(params);

        TouchImageView var = itemView.findViewById(R.id.displayPic);
        var.setOnTouchListener((v, event) ->
        {
            //can scroll horizontally checks if there's still a part of the image that can be scrolled until you reach the edge
            // do not allow recycler view to intercept touch event when is in zoomed state
            if (/*event.getPointerCount() >= 2 || (var.canScrollHorizontally(1) && var.canScrollHorizontally(-1)) || */(var.isZoomed()) )
            {
                //multi-touch event
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:

                    case MotionEvent.ACTION_MOVE:
                        // Disallow RecyclerView to intercept touch events.
                        var.getParent().requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow RecyclerView to intercept touch events.
                        var.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;

                    default:
                        return true;
                }
            }
            return true;
        });

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, int position) {
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
