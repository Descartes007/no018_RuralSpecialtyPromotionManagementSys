package com.example.lrb.pojo;

import com.example.lrb.enums.NodeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @date 2022-03-20 21:54
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "goods")
public class Goods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String content;
    private BigDecimal price;
    private String images;
    private Integer userId;
    private Integer inventory;
    private LocalDateTime createTime;

    public String getShowImg() {
        if (StringUtils.isNotBlank(images)) {
            return images.split(";")[0];
        } else {
            return "";
        }
    }

    @Transient
    private User user;
    @Transient
    private Merchant merchant;
    @Transient
    private Long likes;
    @Transient
    private Boolean isLike;
    @Transient
    private List<NodeComment> commentList;
}
