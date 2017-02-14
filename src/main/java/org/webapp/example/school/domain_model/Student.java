package org.webapp.example.school.domain_model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Student class. {@link JsonProperty} are used on the getters because the default Jackson object mapper
 * pulls the values from fields. If I put the {@link JsonProperty} on the fields, then I'd see the properties
 * twice in the JSON (different names). I'd rather stay with the default, so the properties are named on the getters.
 */
public class Student {

    // Note, these Jackson JSON Properties are optional. Without them, they use the java field name.
    private String mName;
    private Date mBirthDate;
    private String mSocialSecurityNumber;


    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public Student() {
    }

    public Student(String mName, Date mBirthDate, String mSocialSecurityNumber) {
        this.mName = mName;
        this.mBirthDate = (Date) mBirthDate.clone();
        this.mSocialSecurityNumber = mSocialSecurityNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    // without JsonFormat, it would return as the default serialization for Date, which would be ms since epoch.
    // that's probably a preferable format, but this is stricly for demonstrative purposes with Jackson
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("DOB")
    public Date getBirthDate() {
        if (null != mBirthDate) {
            return (Date) mBirthDate.clone();
        }
        return null;
    }

    public void setBirthDate(Date birthDate) {
        if (null != birthDate) {
            this.mBirthDate = (Date) birthDate.clone();
        }
    }

    @JsonProperty("SSN")
    public String getSocialSecurityNumber() {
        return mSocialSecurityNumber;
    }

    public void setSocialSecurityNumber(String mSocialSecurityNumber) {
        this.mSocialSecurityNumber = mSocialSecurityNumber;
    }
}
