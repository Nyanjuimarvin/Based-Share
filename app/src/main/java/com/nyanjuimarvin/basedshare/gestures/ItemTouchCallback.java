package com.nyanjuimarvin.basedshare.gestures;

import androidx.recyclerview.widget.RecyclerView;

//Interface for drag & drop
public interface ItemTouchCallback {
    void onMoveItem(int oldPosition, int newPosition);
    void onItemSwipe(RecyclerView.ViewHolder viewHolder, int position);
}