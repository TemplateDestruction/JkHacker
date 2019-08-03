package com.breakout.myapplication.model;


import java.io.Serializable;

public class CalcModel implements Serializable {
    float current_rem;
    float manage_options;
    float maintenance;
    float summa;

    public CalcModel(float current_rem, float manage_options, float maintenance, float summa) {
        this.current_rem = current_rem;
        this.manage_options = manage_options;
        this.maintenance = maintenance;
        this.summa = summa;
    }


    public float getCurrent_rem() {
        return current_rem;
    }

    public void setCurrent_rem(float current_rem) {
        this.current_rem = current_rem;
    }

    public float getManage_options() {
        return manage_options;
    }

    public void setManage_options(float manage_options) {
        this.manage_options = manage_options;
    }

    public float getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(float maintenance) {
        this.maintenance = maintenance;
    }

    public float getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }
}
