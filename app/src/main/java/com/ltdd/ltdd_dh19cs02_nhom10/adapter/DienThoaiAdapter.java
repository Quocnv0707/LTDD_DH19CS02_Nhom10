package com.ltdd.ltdd_dh19cs02_nhom10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ltdd.ltdd_dh19cs02_nhom10.R;
import com.ltdd.ltdd_dh19cs02_nhom10.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SanPhamMoi> array;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public DienThoaiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai, parent, false);
            return new MyViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            SanPhamMoi sanPhamMoi = array.get(position);
            myViewHolder.mobileName.setText(sanPhamMoi.getProductName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.price.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getPrice())) + " VNĐ");
            myViewHolder.moTa.setText(sanPhamMoi.getMoTa());
            Glide.with(context).load(sanPhamMoi.getImage()).into(myViewHolder.image);
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mobileName, price, moTa;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mobileName = itemView.findViewById(R.id.itemDt_Name);
            price = itemView.findViewById(R.id.itemDt_Name);
            moTa = itemView.findViewById(R.id.itemDt_mota);
            image = itemView.findViewById(R.id.itemDt_image);

        }
    }
}
