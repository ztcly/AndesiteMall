package pers.ztcly.andesitedb.modules.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import pers.ztcly.andesitedb.exception.DuplicateUserIdException;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;
import pers.ztcly.andesitedb.modules.mall.entity.UserinfoEntity;
import pers.ztcly.andesitedb.modules.mall.entity.mapper.UserinfoMapper;
import pers.ztcly.andesitedb.modules.mall.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ztcly.andesitedb.util.DataBaseErrorCode;

import java.util.List;

/**
 * <p>
 * 用户账户其它信息 服务实现类
 * </p>
 *
 * @author ztcly
 * @since 2023-02-23
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, UserinfoEntity> implements UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;


    @Override
    public UserinfoEntity findById(Integer id) throws DuplicateUserIdException {

        List<UserinfoEntity> queryResult = userinfoMapper.selectById(id);
        if (queryResult.size() == 0) { //用户信息不存在
            return null;
        } else if (queryResult.size() != 1) { //ID为自增 不应当重复
            throw new DuplicateUserIdException("数据库中找到了" + queryResult.size() + "个ID为" + id.toString() + "的用户");
        }else if(queryResult.get(0).getIsDeleted()==1){
            return null;
        }
        else {
            return queryResult.get(0);
        }
    }


}
