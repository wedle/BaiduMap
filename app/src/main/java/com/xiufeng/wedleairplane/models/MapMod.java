package com.xiufeng.wedleairplane.models;

import java.io.Serializable;

/**
 * Created by HuangXiuFeng on 2017/2/24.
 */

public class MapMod implements Serializable {
    public String address;
    public  double lats;
    public  double lngs;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLats() {
        return lats;
    }

    public void setLats(double lats) {
        this.lats = lats;
    }

    public double getLngs() {
        return lngs;
    }

    public void setLngs(double lngs) {
        this.lngs = lngs;
    }

    public MapMod(String address, double lats, double lngs) {
        this.address = address;
        this.lats = lats;
        this.lngs = lngs;
    }
}
