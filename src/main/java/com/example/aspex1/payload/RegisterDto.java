package com.example.aspex1.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String email;

    private String password;

    private String phone;

    private String name;

}
