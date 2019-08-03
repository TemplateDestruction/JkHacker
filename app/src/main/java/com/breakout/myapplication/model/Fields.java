
package com.breakout.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("area_total")
    @Expose
    private String areaTotal;
    @SerializedName("formalname_area")
    @Expose
    private String formalnameArea;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("rayon")
    @Expose
    private String rayon;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("floor_count")
    @Expose
    private Double floorCount;
    @SerializedName("elevators_count")
    @Expose
    private Double elevatorsCount;
    @SerializedName("management_organisation_id")
    @Expose
    private Integer managementOrganisationId;

    public String getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(String areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getFormalnameArea() {
        return formalnameArea;
    }

    public void setFormalnameArea(String formalnameArea) {
        this.formalnameArea = formalnameArea;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Double floorCount) {
        this.floorCount = floorCount;
    }

    public Double getElevatorsCount() {
        return elevatorsCount;
    }

    public void setElevatorsCount(Double elevatorsCount) {
        this.elevatorsCount = elevatorsCount;
    }

    public Integer getManagementOrganisationId() {
        return managementOrganisationId;
    }

    public void setManagementOrganisationId(Integer managementOrganisationId) {
        this.managementOrganisationId = managementOrganisationId;
    }

}
