package ck.janko.controller.carModel;

import com.sun.corba.se.impl.oa.toa.TOA;

//GPS坐标
public class TotalMessage {
    public TotalMessage(){

    }
    public TotalMessage(String lng,String lat){
        this.Lat_w84=lat;
        this.Lng_w84=lng;
    }
    String Lat_w84;
    String Lng_w84;

    public String getLat_w84() {
        return Lat_w84;
    }

    public void setLat_w84(String lat_w84) {
        Lat_w84 = lat_w84;
    }

    public String getLng_w84() {
        return Lng_w84;
    }

    public void setLng_w84(String lng_w84) {
        Lng_w84 = lng_w84;
    }
}
