package org.webapp.example.school.domain;

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

    /**
     * Constructor.
     */
    public Student() {
    }

    /**
     * Constructor.
     * @param name name
     * @param birthDate birth date
     * @param socialSecurityNumber SSN
     */
    public Student(String name, Date birthDate, String socialSecurityNumber) {
        this.mName = name;
        this.mBirthDate = (Date) birthDate.clone();
        this.mSocialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Gets name.
     * @return name
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets name.
     * @param name name
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Gets the date.
     * Without JsonFormat, it would return as the default serialization for Date, which would be ms since epoch.
     * that's probably a preferable format, but this is stricly for demonstrative purposes with Jackson
     * @return the date
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("DOB")
    public Date getBirthDate() {
        if (null != mBirthDate) {
            return (Date) mBirthDate.clone();
        }
        return null;
    }

    /**
     * Sets birth date.
     * @param birthDate the birth date
     */
    public void setBirthDate(Date birthDate) {
        if (null != birthDate) {
            this.mBirthDate = (Date) birthDate.clone();
        }
    }

    /**
     * Gets the SSN.
     * @return the ssn.
     */
    @JsonProperty("SSN")
    public String getSocialSecurityNumber() {
        return mSocialSecurityNumber;
    }

    /**
     * Sets the SSN.
     * @param socialSecurityNumber the ssn
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.mSocialSecurityNumber = socialSecurityNumber;
    }
}
