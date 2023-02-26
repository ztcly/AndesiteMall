package pers.ztcly.andesitedb.modules.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.ztcly.andesitedb.exception.DuplicateUserIdException;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;
import pers.ztcly.andesitedb.modules.mall.entity.mapper.UserMapper;
import pers.ztcly.andesitedb.modules.mall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity findById(Integer id) throws DuplicateUserIdException {
        List<UserEntity> queryResult = userMapper.selectById(id);
        if(queryResult.size()==0){ //用户不存在
            return null;
        }
        else if(queryResult.size()!=1){ //ID为自增 不应当重复
            throw new DuplicateUserIdException("数据库中找到了"+queryResult.size()+"个ID为"+id.toString()+"的用户");
        }
        else if(queryResult.get(0).getIsDeleted()==1){//被逻辑删除
            return null;
        }
        else{
            return queryResult.get(0);
        }
    }

    @Override
    public UserEntity findByName(String name) throws DuplicateUserIdException {
        List<UserEntity> queryResult = userMapper.selectByName(name);
        if(queryResult.size()==0){ //用户不存在
            return null;
        }
        else if(queryResult.size()!=1){ //登入名也不应当重复
            throw new DuplicateUserIdException("数据库中找到了"+queryResult.size()+"个名称为"+name+"的用户");
        }
        else{
            return queryResult.get(0);
        }

    }
}
