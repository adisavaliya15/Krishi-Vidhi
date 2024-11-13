package com.example.finale;

public class Mandi_Records {
    String timestamp,state,district,market,commodity,variety,arrival_date,min_price,max_price,modal_price;

    public Mandi_Records(String timestamp, String state, String district, String market, String commodity, String variety, String arrival_date, String min_price, String max_price, String modal_price) {
        //this.timestamp = timestamp;
        this.state = state;
        this.district = district;
        this.market = market;
        this.commodity = commodity;
        this.variety = variety;
        this.arrival_date = arrival_date;
        this.min_price = min_price;
        this.max_price = max_price;
        this.modal_price = modal_price;
    }

  /*  public String getTimestamp() {
        return  timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }*/

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMarket() {
        return "માર્કેટ નુ નામ: " +market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCommodity() {
        return "પાક: "+ commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getVariety() {
        return "પ્રકાર: "+ variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getArrival_date() {
        return "અપડેટ તારીખ: "+ arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getMin_price() {
        return "ન્યુનતમ કિઁમત: "+ min_price+"/100kg";
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return "મહતમ કિઁમત: "+ max_price+"/100kg";
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getModal_price() {
        return modal_price+"/100kg";
    }

    public void setModal_price(String modal_price) {
        this.modal_price = modal_price;
    }
}
