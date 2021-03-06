package com.ltdd.ltdd_dh19cs02_nhom10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.ltdd.ltdd_dh19cs02_nhom10.R;
import com.ltdd.ltdd_dh19cs02_nhom10.adapter.LoaiSPAdapter;
import com.ltdd.ltdd_dh19cs02_nhom10.adapter.SanPhamMoiAdapter;
import com.ltdd.ltdd_dh19cs02_nhom10.model.LoaiSP;
import com.ltdd.ltdd_dh19cs02_nhom10.model.SanPhamMoi;
import com.ltdd.ltdd_dh19cs02_nhom10.retrofit.ApiBanHang;
import com.ltdd.ltdd_dh19cs02_nhom10.retrofit.RetrofitClient;
import com.ltdd.ltdd_dh19cs02_nhom10.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ListView listView;
    RecyclerView recyclerView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    LoaiSPAdapter loaiSPAdapter;
    List<LoaiSP> categories;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> mangSPMoi;
    SanPhamMoiAdapter sanPhamMoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        actionBar();
        actionViewFlipper();
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create((ApiBanHang.class));

        if (isConnected(this)){
            actionViewFlipper();
            getCategory();
            getSPMoi();
            getEventClick();
        }else
            Toast.makeText(getApplicationContext(), "Kh??ng c?? Internet", Toast.LENGTH_LONG).show();
    }

    private void getSPMoi() {
        compositeDisposable.add(apiBanHang.getSPMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SanPhamMoiModel ->{
                    if (SanPhamMoiModel.isSuccess()){
                        mangSPMoi = SanPhamMoiModel.getResult();
                        sanPhamMoiAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSPMoi);
                        recyclerView.setAdapter(sanPhamMoiAdapter);
                    }
                }));
    }

    private void actionViewFlipper() {
        List<String> arrAdv = new ArrayList<>();
        arrAdv.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        arrAdv.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        arrAdv.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");

        for (int i = 0; i < arrAdv.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrAdv.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setInAnimation(slide_out);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void anhXa(){
        toolbar = findViewById(R.id.toolbarManHinhChinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        listView = findViewById(R.id.listViewManHinhChinh);
        navigationView = findViewById(R.id.navigationView);
        recyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        //Khoi tao list
        categories = new ArrayList<>();
        mangSPMoi = new ArrayList<>();
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isConnected() || mobile != null && mobile.isConnected()){
            return true;
        }
        else return false;
    }

    public void getCategory() {
        compositeDisposable.add(apiBanHang.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LoaiSPModel ->{
                            if (LoaiSPModel.isSuccess()){
                                categories = LoaiSPModel.getResult();
                                //Khoi tao adapter
                                loaiSPAdapter = new LoaiSPAdapter(categories, getApplicationContext());
                                listView.setAdapter(loaiSPAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "kh??ng k???t n???i ???????c v???i server" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }));
    }

    private void getEventClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent trangChu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangChu);
                        break;
                    case 1:
                        Intent dienThoai = new Intent(getApplicationContext(), DienThoaiActivity.class);
                        startActivity(dienThoai);
                        dienThoai.putExtra("category", 1);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(), LaptopActivity.class);
                        startActivity(laptop);
                        break;
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
}