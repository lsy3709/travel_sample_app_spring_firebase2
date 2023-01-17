package com.and4.travel_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
   
    private String password;
    private String username;
    private String nickname;
}