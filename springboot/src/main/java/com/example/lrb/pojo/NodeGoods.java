package com.example.lrb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date 2022-03-20 21:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "node_goods")
public class NodeGoods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer nodeId;
    private Integer goodsId;
}
