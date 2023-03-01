package pers.ztcly.andesiteapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ztcly.andesiteapi.entity.ApiServiceReturnEntity;
import pers.ztcly.andesiteapi.util.ApiServiceErrorCode;
import pers.ztcly.andesitedb.modules.mall.entity.ProductionsEntity;
import pers.ztcly.andesitedb.modules.mall.entity.UsercartEntity;
import pers.ztcly.andesitedb.modules.mall.service.UsercartService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ztcly
 * @date 2023-03-01 19:51
 * @description
 **/
@Service
public class CartService {
    private final Log logger = LogFactory.getLog(CartService.class);

    @Autowired
    private UsercartService usercartService;

    @Autowired
    private ProductService productService;

    public ApiServiceReturnEntity<List<ProductionsEntity>> getCartByUserId(Integer userid){
        ApiServiceReturnEntity<List<ProductionsEntity>> result = new ApiServiceReturnEntity<>(null,null);
        if(userid==null){
            logger.error(ApiServiceErrorCode.BadArgument.getMsg()+" "+userid);
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        QueryWrapper<UsercartEntity> queryWrapper = new QueryWrapper<UsercartEntity>().eq("user_id",userid);
        List<UsercartEntity> usercartEntities = usercartService.list(queryWrapper);
        if(usercartEntities==null||usercartEntities.isEmpty()){
            logger.debug(ApiServiceErrorCode.CartIsEmpty);
            result.setApiServiceErrorCode(ApiServiceErrorCode.CartIsEmpty);
            return result;
        }
        List<ProductionsEntity> productionsEntities = new ArrayList<>();
        for(UsercartEntity usercartEntity:usercartEntities){
            ApiServiceReturnEntity<ProductionsEntity> queryResult= productService.getProductById(usercartEntity.getPrdtId());
            if(queryResult.getApiServiceErrorCode()!=ApiServiceErrorCode.OK){
                logger.error(ApiServiceErrorCode.ProductNotExist.getMsg()+" ID:"+usercartEntity.getPrdtId());
                continue;
            }
            productionsEntities.add(queryResult.getReturnEntity());
        }
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        result.setReturnEntity(productionsEntities);
        logger.debug(ApiServiceErrorCode.OK.getMsg()+" ID:"+userid);
        return result;
    }
}
