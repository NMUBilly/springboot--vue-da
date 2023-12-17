package com.B1lly.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bId;
    private float price;
    private String bName;
    private String author;
    //借出情况
    private Integer availability;
}
