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
 * 用户搜索历史
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("tbl_userhistory")
@ApiModel(value = "UserhistoryEntity对象", description = "用户搜索历史")
public class UserhistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("搜索历史内容")
    @TableField("history")
    private String history;

    @ApiModelProperty("逻辑删除")
    @TableField("isDeleted")
    private Integer isDeleted;

    @ApiModelProperty("添加时间（决定顺序）")
    @TableField("addTime")
    private Date addTime;

    @ApiModelProperty("更新时间")
    @TableField("updateTime")
    private Date updateTime;


}
