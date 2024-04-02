package com.example.lrb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @date 2022-03-20 21:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "node_comment")
public class NodeComment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer parentId;
    private Integer nodeId;
    private Integer fromId;
    private Integer toId;
    private String content;
    private LocalDateTime createTime;

    @Transient
    private String fromName;
    @Transient
    private String fromAvatar;
    @Transient
    private String toName;
    @Transient
    private String toAvatar;
    @Transient
    private List<NodeComment> childrenComment;
}
