package com.ltdd.ltdd_dh19cs02_nhom10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ltdd.ltdd_dh19cs02_nhom10.R;
import com.ltdd.ltdd_dh19cs02_nhom10.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public SanPhamMoiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public SanPhamMoiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_moi, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamMoiAdapter.MyViewHolder holder, int position) {
        SanPhamMoi sanPhamMoi = array.get(position);
        holder.txtName.setText(sanPhamMoi.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        holder.txtPrice.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getPrice())) + " VNĐ");
        //holder.txtPrice.setText("Giá: " + sanPhamMoi.getPrice());
        Glide.with(context).load(sanPhamMoi.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtPrice, txtName;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = itemView.findViewById(R.id.itemsp_price);
            txtName = itemView.findViewById(R.id.itemsp_name);
            image = itemView.findViewById(R.id.itemsp_image);
        }
    }
}
