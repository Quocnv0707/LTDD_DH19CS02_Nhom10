package com.ltdd.ltdd_dh19cs02_nhom10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ltdd.ltdd_dh19cs02_nhom10.R;
import com.ltdd.ltdd_dh19cs02_nhom10.model.LoaiSP;

import java.util.List;

public class LoaiSPAdapter extends BaseAdapter {
    List<LoaiSP> array;
    Context context;

    public LoaiSPAdapter(List<LoaiSP> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView textTenSP;
        ImageView img;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.textTenSP = view.findViewById(R.id.item_tenSP);
            viewHolder.img = view.findViewById((R.id.item_image));
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textTenSP.setText(array.get(i).getProductName());
        Glide.with(context).load(array.get(i).getImage()).into(viewHolder.img);
        return view;
    }
}
