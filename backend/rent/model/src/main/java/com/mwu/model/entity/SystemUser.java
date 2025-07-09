package com.mwu.model.entity;

import com.mwu.model.enums.BaseStatus;
import com.mwu.model.enums.SystemUserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Schema(description = "员工信息")
@Entity
@Table(name = "system_user")
@Data
public class SystemUser  extends BaseEntity {

    @Schema(description = "主键")
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Schema(description = "用户名")
    @Column(name = "username")
    private String username;

    @Schema(description = "密码")
    @Column(name = "password")
    private String password;

    @Schema(description = "姓名")
    @Column(name = "name")
    private String name;

    @Schema(description = "用户类型")
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private SystemUserType type;

    @Schema(description = "手机号码")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "头像地址")
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Schema(description = "备注信息")
    @Column(name = "additional_info")
    private String additionalInfo;

    @Schema(description = "岗位id")
    @Column(name = "post_id")
    private Long postId;

    @Schema(description = "账号状态")
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private BaseStatus status;

}
