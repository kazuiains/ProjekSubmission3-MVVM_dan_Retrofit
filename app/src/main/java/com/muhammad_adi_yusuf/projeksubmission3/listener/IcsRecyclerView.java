package com.muhammad_adi_yusuf.projeksubmission3.listener;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammad_adi_yusuf.projeksubmission3.R;

public class IcsRecyclerView {
    private final RecyclerView reCyclerview;
    private OnItemClickListener onItemclick;
    private OnItemLongClickListener onItemlongclick;
    private View.OnClickListener onClicklist = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onItemclick != null) {
                RecyclerView.ViewHolder holder = reCyclerview.getChildViewHolder(v);
                onItemclick.onItemClicked(reCyclerview, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener onLongclicklist = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (onItemlongclick != null) {
                RecyclerView.ViewHolder holder = reCyclerview.getChildViewHolder(v);
                return onItemlongclick.onItemLongClicked(reCyclerview, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener attchListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if (onItemclick != null) {
                view.setOnClickListener(onClicklist);
            }
            if (onItemlongclick != null) {
                view.setOnLongClickListener(onLongclicklist);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {
        }
    };

    private IcsRecyclerView(RecyclerView recyclerView) {
        reCyclerview = recyclerView;
        reCyclerview.setTag(R.id.ics_recycler_view, this);
        reCyclerview.addOnChildAttachStateChangeListener(attchListener);
    }

    public static IcsRecyclerView addTo(RecyclerView view) {
        IcsRecyclerView support = (IcsRecyclerView) view.getTag(R.id.ics_recycler_view);
        if (support == null) {
            support = new IcsRecyclerView(view);
        }
        return support;
    }

    public static IcsRecyclerView removeFrom(RecyclerView view) {
        IcsRecyclerView support = (IcsRecyclerView) view.getTag(R.id.ics_recycler_view);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemclick = listener;
    }


    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemlongclick = listener;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(attchListener);
        view.setTag(R.id.ics_recycler_view, null);
    }

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
