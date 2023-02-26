package pers.ztcly.andesitedb.modules.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("tbl_prdtoforder")
@ApiModel(value = "PrdtoforderEntity对象", description = "")
public class PrdtoforderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("order_id")
    private Integer orderId;

    @TableField("prdt_id")
    private Integer prdtId;

    @TableField("displayprice")
    private BigDecimal displayprice;

    @TableField("actualprice")
    private BigDecimal actualprice;

    @TableField("isDeleted")
    private Integer isDeleted;

    @TableField("addTime")
    private Date addTime;

    @TableField("updateTime")
    private Date updateTime;


}
