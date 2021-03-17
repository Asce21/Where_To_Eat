package com.example.wte;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class Restaurant implements Parcelable {
    //Constructors

    public Restaurant(int restaurantID, String name, String days, String hours, String addressOne, String addressTwo, String city, String state, String zip,
                      String phone, String website, String breakfast, String lunch, String dinner) {
        this.rID = restaurantID;
        this.name = name;
        this.days = days;
        this.hours = hours;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.website = website;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }//End of constructor

    protected Restaurant(Parcel in) {
        rID = in.readInt();
        name = in.readString();
        days = in.readString();
        hours = in.readString();
        addressOne = in.readString();
        addressTwo = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        phone = in.readString();
        website = in.readString();
        breakfast = in.readString();
        lunch = in.readString();
        dinner = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }//End of method createFromParcel

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }//End of method newArray
    };//End of Creator


    //Getters
    public int getrID() {return rID;}
    public String getName() {return name;}
    public String getDays() {return days;}
    public String getHours() {return hours;}
    public String getAddressOne() {return addressOne;}
    public String getAddressTwo() {return addressTwo;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZip() {return zip;}
    public String getPhone() {return phone;}
    public String getWebsite() {return website;}
    public String getBreakfast() {return breakfast;}
    public String getLunch() {return lunch;}
    public String getDinner() {return dinner;}

    //Setters
    public void setrID(int id) {this.rID = id;}
    public void setName(String name) {this.name = name;}
    public void setDays(String days) {this.days = days;}
    public void setHours(String hours) {this.hours = hours;}
    public void setAddressOne(String addressOne) {this.addressOne = addressOne;}
    public void setAddressTwo(String addressTwo) {this.addressTwo = addressTwo;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZip(String zip) {this.zip = zip;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setWebsite(String website) {this.website = website;}
    public void setBreakfast(String breakfast) {this.breakfast = breakfast;}
    public void setLunch(String lunch) {this.lunch = lunch;}
    public void setDinner(String dinner) {this.dinner = dinner;}

    //Variables
    private String name, days, hours, addressOne, addressTwo, city, state, zip, phone, website, breakfast, lunch, dinner;
    int rID;

    @Override
    public int describeContents() {
        return 0;
    }//End of overloaded method describeContents

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rID);
        dest.writeString(name);
        dest.writeString(days);
        dest.writeString(hours);
        dest.writeString(addressOne);
        dest.writeString(addressTwo);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(breakfast);
        dest.writeString(lunch);
        dest.writeString(dinner);
    }//End of overloaded method writeToParcel


}//End of class Restaurant
