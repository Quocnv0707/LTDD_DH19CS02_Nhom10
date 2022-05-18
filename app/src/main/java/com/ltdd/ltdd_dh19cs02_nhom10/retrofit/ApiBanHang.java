package com.ltdd.ltdd_dh19cs02_nhom10.retrofit;

import com.ltdd.ltdd_dh19cs02_nhom10.model.LoaiSPModel;
import com.ltdd.ltdd_dh19cs02_nhom10.model.SanPhamMoiModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("getCategory.php")
    Observable<LoaiSPModel> getCategory();

    @GET("getProducts.php")
    Observable<SanPhamMoiModel> getSPMoi();

    @POST("chiTiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
            @Field("page") int page,
            @Field("category") int category
    );
}
