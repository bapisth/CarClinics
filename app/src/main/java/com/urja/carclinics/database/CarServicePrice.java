package com.urja.carclinics.database;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CAR_SERVICE_PRICE".
 */
public class CarServicePrice {

    private Long id;
    private String serviceCode;
    private String serviceDesc;
    private String priceSmall;
    private String priceMedium;
    private String priceLarge;
    private boolean isChecked;

    public CarServicePrice() {
    }

    public CarServicePrice(Long id) {
        this.id = id;
    }

    public CarServicePrice(Long id, String serviceCode, String serviceDesc, String priceSmall, String priceMedium, String priceLarge) {
        this.id = id;
        this.serviceCode = serviceCode;
        this.serviceDesc = serviceDesc;
        this.priceSmall = priceSmall;
        this.priceMedium = priceMedium;
        this.priceLarge = priceLarge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(String priceSmall) {
        this.priceSmall = priceSmall;
    }

    public String getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(String priceMedium) {
        this.priceMedium = priceMedium;
    }

    public String getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(String priceLarge) {
        this.priceLarge = priceLarge;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}