package com.vit.seekabook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAd {
    private long adID;
    private User seller;
    private String bookTitle;
    private String price;
    private String bookDescription;
    private String bookReview;
    private boolean approvedByAdmin;
}
