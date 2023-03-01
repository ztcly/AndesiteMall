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
 * 
 * </p>
 *
 * @author ztcly
 * @since 2023-03-01
 */
@Getter
@Setter
@TableName("tbl_usercart")
@ApiModel(value = "UsercartEntity对象", description = "")
public class UsercartEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("prdt_id")
    private Integer prdtId;

    @TableField("num")
    private Integer num;

    @TableField("isDeleted")
    private Integer isDeleted;

    @TableField("addTime")
    private Date addTime;

    @TableField("updateTime")
    private Date updateTime;


}
