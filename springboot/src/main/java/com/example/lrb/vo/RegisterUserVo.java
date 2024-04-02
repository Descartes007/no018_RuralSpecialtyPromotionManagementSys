package com.example.lrb.vo;

import com.example.lrb.enums.RoleEnums;
import lombok.Data;

/**
 * @date 2022-05-11 11:36
 */
@Data
public class RegisterUserVo {
    private RoleEnums registerType;
    private String name;
    private String password;
    private String location;
    private String sex;
    private String age;
    private String mobile;
    private String hometown;
    private String idcard;
    private String address;
    private String storeName;
    private String confirmPass;
    private String products;
}
