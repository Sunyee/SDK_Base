package com.wan91.simo.lib.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wan91.simo.lib.bean.FunctionBean;
import com.wan91.simo.lib.listener.OnMultiClickListener;
import com.wan91.simo.lib.utils.LayoutUtils;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.IconViewHolder> {
    private List<FunctionBean> items;

    public ItemAdapter(List<FunctionBean> list) {
        items = list;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())//实例化LayoutInflater
                .inflate(LayoutUtils.getLayout(parent.getContext(), "wan91_item_mch_fun"), parent, false);
        IconViewHolder myHolder = new IconViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(IconViewHolder holder, int position) {
        final FunctionBean functionBean = items.get(position);
        holder.icon.setBackgroundResource(LayoutUtils.getMipmap(holder.itemView.getContext(),functionBean.icon));
        holder.tvName.setText(functionBean.name);
        holder.layoutFun.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        private View view;
        public ImageView icon;
        public TextView tvName;
        public View layoutFun;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            layoutFun = itemView.findViewById(LayoutUtils.getIdByName(itemView.getContext(), "id", "layout_fun"));
            icon = itemView.findViewById(LayoutUtils.getIdByName(itemView.getContext(), "id", "img_icon"));
            tvName = itemView.findViewById(LayoutUtils.getIdByName(itemView.getContext(), "id", "tv_name"));
            itemView.setTag(this);
        }

        public View getView() {
            return view;
        }
    }
}