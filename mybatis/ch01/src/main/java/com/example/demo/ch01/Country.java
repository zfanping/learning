package com.example.demo.ch01;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by frank on 2018-09-29.
 */
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    private String countryname;
    private String countrycode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryname='" + countryname + '\'' +
                ", countrycode='" + countrycode + '\'' +
                '}';
    }
}
