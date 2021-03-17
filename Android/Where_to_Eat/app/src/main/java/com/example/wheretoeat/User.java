package com.example.wheretoeat;

import java.io.Serializable;

public class User implements Cloneable {


    //Constructors
    User(String initName, String initPass, String initEmail, String initPhone)  {
        username = initName;
        password = initPass;
        emailAddress = initEmail;
        phoneNumber = initPhone;
    }//End of overloaded constructor

    //Getters
    public String getUsername()    {return username;}
    public String getPassword()    {return password;}
    public String getEmailAddress() {return emailAddress;}
    public String getPhoneNumber()  {return phoneNumber;}

    @Override
    public User clone() throws CloneNotSupportedException {
        User temp;
        try {
            temp = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            temp = new User(null, null, null, null);
        }//End of catch block
        return temp;
    }//End of method clone

    //Setters
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    //Member variables
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;
}//End of class User
