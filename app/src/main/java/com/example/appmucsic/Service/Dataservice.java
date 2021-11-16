package com.example.appmucsic.Service;

import com.example.appmucsic.Model.Album;
import com.example.appmucsic.Model.Baihat;
import com.example.appmucsic.Model.ChuDe;
import com.example.appmucsic.Model.Playlist;
import com.example.appmucsic.Model.Quangcao;
import com.example.appmucsic.Model.TheLoai;
import com.example.appmucsic.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
  Call<List<Quangcao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();
    @GET("chudevathelaoitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();
  @GET("albumhot.php")
  Call<List<Album>> GetAlbumHot();
@GET("baihatduocthich.php")
Call<List<Baihat>> GetBaiHatHot();

@FormUrlEncoded
@POST("danhsachbaihat.php")
  Call<List<Baihat>> GetDanhsachbaihattheoquangcao (@Field("idquangcao") String idquangcao);

@FormUrlEncoded
  @POST("danhsachbaihat.php")
  Call<List<Baihat>> GetDanhsachbaihattheoplaylist (@Field("idplaylist") String idplaylist);


@GET("danhsachcacplaylist.php")
  Call<List<Playlist>> GetDanhsachcacplaylist();
@FormUrlEncoded
  @POST("danhsachbaihat.php")
  Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);
@GET("tatcachude.php")
  Call<List<ChuDe>> GetAllChuDe();

  @FormUrlEncoded
  @POST("theloaitheochude.php")
  Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

  @GET("tatcaalbum.php")
  Call<List<Album>> GetAllalbum();
  @FormUrlEncoded
  @POST("danhsachbaihat.php")
  Call<List<Baihat>> GetDanhsachbaihatheoalbum(@Field("idalbum") String idalbum);

  @FormUrlEncoded
  @POST("updateluotthich.php")
  Call<String> UpdateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);

  @FormUrlEncoded
  @POST("searchbaihat.php")
  Call<List<Baihat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);
}
