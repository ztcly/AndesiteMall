package pers.ztcly.andesitedb.modules.mall.entity.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import pers.ztcly.andesitedb.modules.mall.entity.UserinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户账户其它信息 Mapper 接口
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Mapper
public interface UserinfoMapper extends BaseMapper<UserinfoEntity> {
    List<UserinfoEntity> selectById(@Param("id") Integer id);
}
