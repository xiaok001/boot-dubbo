package ck.janko.controller.carModel;


//围栏统计使用字段
public class OutOfFenceInfo{

    public Boolean getFlagONOFF() {
        return FlagONOFF;
    }

    public void setFlagONOFF(Boolean flagONOFF) {
        FlagONOFF = flagONOFF;
    }

    public int getOutTimes() {
        return OutTimes;
    }

    public void setOutTimes(int outTimes) {
        OutTimes = outTimes;
    }

    Boolean FlagONOFF;      //true表示当前已超，false表示当前未超
    int OutTimes;         //超出围栏次数
}
