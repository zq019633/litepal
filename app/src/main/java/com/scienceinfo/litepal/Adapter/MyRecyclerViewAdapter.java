package com.scienceinfo.litepal.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scienceinfo.litepal.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>  {
    private final ArrayList<String> list;
    private final ArrayList<Integer> img;
    ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void OnItemClick(View v, int position);
    }

    public MyRecyclerViewAdapter(ArrayList<String> list, ArrayList<Integer> img) {
        this.list=list;
        this.img=img;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_me, parent, false);
        MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mText.setText(list.get(position));
        holder.mImg.setImageResource(img.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mText;
        ImageView mImg;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv);
            mImg = itemView.findViewById(R.id.control);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.OnItemClick(v, (Integer) itemView.getTag());
            }
        }
    }
}
