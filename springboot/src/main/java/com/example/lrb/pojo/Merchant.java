package com.example.lrb.pojo;

import com.example.lrb.enums.RoleEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

/**
 * @date 2022-05-11 11:44
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private String name;
    private String password;
    private String idcard;
    private String address;
    private String storeName;
    private String products;
    private String mobile;
}
