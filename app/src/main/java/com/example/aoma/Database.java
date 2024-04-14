package com.example.aoma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.FitWindowsViewGroup;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text,email text,password text)";
        db.execSQL(qry1);

        String qry2 = "create table cart(username text,product text,price float, otype text)";
        db.execSQL(qry2);

        String qry3 = "create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)";
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String [2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result = 1;
        }
        return result;
    }
    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if(c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "RM" + price);
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time", time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"RM"+c.getString(2)+"RM"+c.getString(3)+"RM"+c.getString(4)+"RM"+c.getString(5)+"RM"+c.getString(6)+"RM"+c.getString(7)+"RM"+c.getString(8));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?", str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    // below is the method for deleting our ordered and booked stuff.
    public void cancelBook(String username) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete("orderplace", "username=?", new String[]{username});
        db.close();
    }

    public ArrayList<String> getOrderDataForAllUsers() {
        ArrayList<String> allOrderDetails = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Select all order details from the orderplace table
        Cursor cursor = db.rawQuery("SELECT * FROM orderplace", null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            do {
                // Retrieve order details for each row
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String contact = cursor.getString(cursor.getColumnIndex("contactno"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                // Create a string representing the order details
                String orderDetail = username + "RM" + fullname + "RM" + time + "RM" + contact + "RM" + date;

                // Add the order detail string to the list
                allOrderDetails.add(orderDetail);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        // Return the list of order details for all users
        return allOrderDetails;
    }

    // Method to delete a user from the database
    public void deleteUser(String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("orderplace", "date=?", new String[]{date});
        db.close();
    }
}
