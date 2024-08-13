package com.example.my_coffee_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Coffee implements Parcelable {
    private String name;
    private int price;
    private int quantity;

    public Coffee(String name, int price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    protected Coffee(Parcel in) {
        name = in.readString();
        price = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {
        @Override
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        @Override
        public Coffee[] newArray(int size) {
            return new Coffee[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(quantity);
    }
}
