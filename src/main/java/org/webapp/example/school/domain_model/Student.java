package org.webapp.example.school.domain_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Mark on 1/22/2017.
 */
public class Student {

    // Note, tehse Jackson JSON Properties are optional
    @JsonProperty("name")
    private String mName;

    @JsonProperty("DOB")
    private Date mBirthDate;
    private String mSocialSecurityNumber;

    public Student() {
    }

    public Student(String mName, Date mBirthDate, String mSocialSecurityNumber) {
        this.mName = mName;
        this.mBirthDate = mBirthDate;
        this.mSocialSecurityNumber = mSocialSecurityNumber;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Date getmBirthDate() {
        return mBirthDate;
    }

    public void setmBirthDate(Date mBirthDate) {
        this.mBirthDate = mBirthDate;
    }

    public String getmSocialSecurityNumber() {
        return mSocialSecurityNumber;
    }

    public void setmSocialSecurityNumber(String mSocialSecurityNumber) {
        this.mSocialSecurityNumber = mSocialSecurityNumber;
    }
}
