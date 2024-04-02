package com.example.lrb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 *
 * @date 2022-03-21 21:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String content;
    private String replyContent;
    private LocalDateTime createTime;
    private LocalDateTime replyTime;
    private Integer userId;


    @Transient
    private String name;

}
