package ck.janko.controller.carModel;

import java.util.List;

//经销商围栏列表
public class GPSFenceList{
    String  Lng_w84;
    String  Lat_w84;
    int  Radius;
    int  Fence_Type; // 0圆形 其他是多边形
    List<ComFenceXY> ComFenceXYList;

    public String getLng_w84() {
        return Lng_w84;
    }

    public void setLng_w84(String lng_w84) {
        Lng_w84 = lng_w84;
    }

    public String getLat_w84() {
        return Lat_w84;
    }

    public void setLat_w84(String lat_w84) {
        Lat_w84 = lat_w84;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    public int getFence_Type() {
        return Fence_Type;
    }

    public void setFence_Type(int fence_Type) {
        Fence_Type = fence_Type;
    }

    public List<ComFenceXY> getComFenceXYList() {
        return ComFenceXYList;
    }

    public void setComFenceXYList(List<ComFenceXY> comFenceXYList) {
        ComFenceXYList = comFenceXYList;
    }
}