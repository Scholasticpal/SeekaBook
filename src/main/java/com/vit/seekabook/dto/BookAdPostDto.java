package com.vit.seekabook.dto;

import com.vit.seekabook.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAdPostDto {
    private String sellerEmail;
    private String bookTitle;
    private String price;
    private String bookDescription;
    private String bookReview;
}
