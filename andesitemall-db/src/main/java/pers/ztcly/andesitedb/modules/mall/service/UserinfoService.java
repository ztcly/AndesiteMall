package pers.ztcly.andesitedb.modules.mall.service;

import pers.ztcly.andesitedb.exception.DuplicateUserIdException;
import pers.ztcly.andesitedb.modules.mall.entity.UserinfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.ztcly.andesitedb.util.DataBaseErrorCode;

/**
 * <p>
 * 用户账户其它信息 服务类
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
public interface UserinfoService extends IService<UserinfoEntity> {
    UserinfoEntity findById(Integer id) throws DuplicateUserIdException;
}
