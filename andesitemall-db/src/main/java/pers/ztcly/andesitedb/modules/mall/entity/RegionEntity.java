package pers.ztcly.andesitedb.modules.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 行政区域表
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("tbl_region")
@ApiModel(value = "RegionEntity对象", description = "行政区域表")
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty("行政区域名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("行政区域编码")
    @TableField("code")
    private Integer code;


}
