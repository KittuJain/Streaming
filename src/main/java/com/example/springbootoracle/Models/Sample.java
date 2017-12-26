package com.example.springbootoracle.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String col1;
    public String col2;
    public String col3;
    public String col4;
    public String col5;
    public String col6;
    public String col7;
    public String col8;
    public String col9;
    public String col10;
    public String col11;
    public String col12;
    public String col13;
    public String col14;

    public Sample(String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8,
                  String col9, String col10, String col11, String col12, String col13, String col14) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.col5 = col5;
        this.col6 = col6;
        this.col7 = col7;
        this.col8 = col8;
        this.col9 = col9;
        this.col10 = col10;
        this.col11 = col11;
        this.col12 = col12;
        this.col13 = col13;
        this.col14 = col14;
    }

    public Sample() {

    }

}

