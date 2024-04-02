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
@Table(name = "node")
public class Node {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String content;
    private String images;
    private String address;
    private String authorizedFile;
    private String authorizedUser;
    private LocalDateTime authorizedTime;
    private String videos;
    private Integer userId;
    private Integer pageview;
    private LocalDateTime createTime;
    private NodeEnums status;
    private NodeEnums auditStatus;


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
    private String city; // 选中的城市
    @Transient
    private BigDecimal likes;
    @Transient
    private Long likeCount;
    @Transient
    private Boolean isLike;
    @Transient
    private List<NodeComment> commentList;
    @Transient
    private List<Goods> goodsList;

    public BigDecimal getLikes() {
        if (likes != null) {
            return likes.setScale(1, BigDecimal.ROUND_HALF_UP);
        } else {
            return BigDecimal.ZERO.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }
}
