package pers.ztcly.andesitedb.modules.mall.entity.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> selectByName(@Param("name") String name);

    List<UserEntity> selectById(@Param("id") Integer id);
}
