package com.mwu.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {



    @Schema(description = "创建时间")
    @Column(name = "create_time")
    @JsonIgnore    // 序列化时被忽略
    @CreationTimestamp
    private Date createTime;

    @Schema(description = "更新时间")
    @Column(name = "update_time")
    @JsonIgnore    // 序列化时被忽略
    @UpdateTimestamp
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @Column(name="is_deleted")
    @JsonIgnore    // 序列化时被忽略
    private Byte isDeleted;
}
