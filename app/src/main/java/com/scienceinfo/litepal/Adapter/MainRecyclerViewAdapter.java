package com.scienceinfo.litepal.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scienceinfo.litepal.Bean.Curriculum;
import com.scienceinfo.litepal.R;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>  {
    private final List<Curriculum> list;
    ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void OnItemClick(View v, int position);
    }

    public MainRecyclerViewAdapter(List<Curriculum> list) {
        this.list=list;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        MainRecyclerViewAdapter.ViewHolder viewHolder = new MainRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mText.setText("课程名称:"+list.get(position).getC_name());
        holder.mtvId.setText("课程ID:"+list.get(position).getBaseObjId());
        holder.mtvInfo.setText("课程信息:"+list.get(position).getC_info());


        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mText;
        TextView mtvId;
        TextView mtvInfo;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tvname);
            mtvId = itemView.findViewById(R.id.tvid);
            mtvInfo = itemView.findViewById(R.id.tvinfo);
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
