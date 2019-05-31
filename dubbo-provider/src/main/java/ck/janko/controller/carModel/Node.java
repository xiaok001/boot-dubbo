package ck.janko.controller.carModel;

import java.util.List;

/**
 * 传值给分析围栏超出方法的判断
 */
public class Node {
    List<GPSFenceList> gpsFenceList;
    String vin;
    TotalMessage totalMessage;

    public List<GPSFenceList> getGpsFenceList() {
        return gpsFenceList;
    }

    public void setGpsFenceList(List<GPSFenceList> gpsFenceList) {
        this.gpsFenceList = gpsFenceList;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public TotalMessage getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(TotalMessage totalMessage) {
        this.totalMessage = totalMessage;
    }
}
