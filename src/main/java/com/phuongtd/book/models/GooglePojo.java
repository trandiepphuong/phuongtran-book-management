package com.phuongtd.book.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GooglePojo {
    private String email;
    //private String name;
    private String givenName;
    private String familyName;
    //private String link;
    private String picture;
    private String accessToken;
}