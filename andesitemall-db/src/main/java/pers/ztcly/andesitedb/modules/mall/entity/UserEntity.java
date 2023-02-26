package pers.ztcly.andesitedb.modules.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("tbl_user")
@ApiModel(value = "UserEntity对象", description = "用户表")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户登录名")
    @TableField("name")
    private String name;

    @ApiModelProperty("用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("用户头像")
    @TableField("avater")
    private String avater;

    @TableField("phoneNumber")
    private String phoneNumber;

    @ApiModelProperty("用户电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("用户权限等级")
    @TableField("permissionLevel")
    private String permissionLevel;

    @ApiModelProperty("是否被删除（逻辑删除） 0为未删除，1为已删除")
    @TableField("isDeleted")
    private Integer isDeleted;

    @ApiModelProperty("添加记录时间戳")
    @TableField("addTime")
    private Date addTime;

    @ApiModelProperty("更新记录时间戳")
    @TableField("updateTime")
    private Date updateTime;


}
