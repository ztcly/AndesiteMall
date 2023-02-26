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
import lombok.ToString;

/**
 * <p>
 * 用户账户其它信息
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Getter
@Setter
@ToString
@TableName("tbl_userinfo")
@ApiModel(value = "UserinfoEntity对象", description = "用户账户其它信息")
public class UserinfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户信息ID 外键来源user表")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("用户生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty("上次登录时间")
    @TableField("lastLoginTime")
    private Date lastLoginTime;

    @ApiModelProperty("上次登录IP")
    @TableField("lastLoginIP")
    private String lastLoginIP;

    @ApiModelProperty("性别")
    @TableField("gender")
    private String gender;

    @TableField("wxid")
    private String wxid;

    @ApiModelProperty("逻辑删除")
    @TableField("isDeleted")
    private Integer isDeleted;

    @ApiModelProperty("添加时间")
    @TableField("addTime")
    private Date addTime;

    @ApiModelProperty("更新时间")
    @TableField("updateTime")
    private Date updateTime;

}
