package org.webapp.example.school.domain_model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Mark on 1/22/2017.
 */
public class Student {

    // Note, these Jackson JSON Properties are optional. Without them, they use the java field name.
    @JsonProperty("name")
    private String mName;

    @JsonProperty("DOB")
    // without JsonFormat, it would return as the default serialization for Date, which would be ms since epoch.
    // that's probably a preferable format, but this is stricly for demonstrative purposes with Jackson
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date mBirthDate;

    @JsonProperty("SSN")
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
