package pers.ztcly.andesitedb.modules.mall.service;

import org.springframework.stereotype.Service;
import pers.ztcly.andesitedb.exception.DuplicateUserIdException;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 * @author ztcly
 * @since 2023-02-23
 */


@Service
public interface UserService extends IService<UserEntity> {
    UserEntity findById(Integer id) throws DuplicateUserIdException;
    UserEntity findByName(String name) throws DuplicateUserIdException;

}
