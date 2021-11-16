package com.example.appmucsic.Service;
public class APIService      {

    private static String base_url = "http://192.168.37.1/api/";
    public static String getmp3 = "http://192.168.37.1/music/storage/app/mp3/";
    public static String getalbum = "http://192.168.37.1/music/storage/app/album/";
    public static String getbaihat = "http://192.168.37.1/music/storage/app/baihat/";
    public static String getplaylist = "http://192.168.37.1/music/storage/app/playlist/";
    public static String getchude = "http://192.168.37.1/music/storage/app/chude/";
    public static String gettheloai = "http://192.168.37.1/music/storage/app/theloai/";
    public static String getquangcao = "http://192.168.37.1/music/storage/app/quangcao/";

    public  static  Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }

}