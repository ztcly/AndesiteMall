package pers.ztcly.andesiteapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.ztcly.andesiteapi.entity.ApiServiceReturnEntity;
import pers.ztcly.andesiteapi.entity.ProductReturnEntity;
import pers.ztcly.andesiteapi.util.ApiServiceErrorCode;
import pers.ztcly.andesitedb.modules.mall.entity.PrdtbasicEntity;
import pers.ztcly.andesitedb.modules.mall.entity.PrdtspecificationsEntity;
import pers.ztcly.andesitedb.modules.mall.entity.ProductionsEntity;
import pers.ztcly.andesitedb.modules.mall.service.PrdtbasicService;
import pers.ztcly.andesitedb.modules.mall.service.PrdtspecificationsService;
import pers.ztcly.andesitedb.modules.mall.service.ProductionsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ztcly
 * @date 2023-02-28 15:48
 * @description
 **/
public class ProductService {
    private final Log logger = LogFactory.getLog(ProductService.class);

    @Autowired
    private ProductionsService productionsService;

    @Autowired
    private PrdtbasicService prdtbasicService;

    @Autowired
    private PrdtspecificationsService prdtspecificationsService;


    public ApiServiceReturnEntity<PrdtbasicEntity> getBasicProductById(String id){
        ApiServiceReturnEntity<PrdtbasicEntity> result = new ApiServiceReturnEntity<>(null,null);
        if(id==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }

        PrdtbasicEntity queryResult = prdtbasicService.getById(id);
        if(queryResult==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.ProductBasicNotExist);
            return result;
        }

        result.setReturnEntity(queryResult);
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        return result;
    }
    public ApiServiceReturnEntity<List<PrdtbasicEntity>> getBasicProductByShop(Integer shopid){
        ApiServiceReturnEntity<List<PrdtbasicEntity>> result = new ApiServiceReturnEntity<>(null,null);
        if(shopid==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return  result;
        }
        QueryWrapper<PrdtbasicEntity> queryWrapper = new QueryWrapper<PrdtbasicEntity>()
                                                        .eq("shop_id",shopid)
                                                        .ne("isDeleted",1);
        List<PrdtbasicEntity> basicQueryResult = prdtbasicService.list();
        if(basicQueryResult==null||basicQueryResult.isEmpty()){
            result.setApiServiceErrorCode(ApiServiceErrorCode.ProductBasicNotExist);
            return result;
        }
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        result.setReturnEntity(basicQueryResult);
        return result;
    }
    public ApiServiceReturnEntity<PrdtspecificationsEntity> getPrdtSpecificationsByPrdtId(Integer prdtid){
        ApiServiceReturnEntity<PrdtspecificationsEntity> result = new ApiServiceReturnEntity<>(null,null);
        if(prdtid==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        QueryWrapper<PrdtspecificationsEntity> queryWrapper = new QueryWrapper<PrdtspecificationsEntity>()
                .eq("prdt_id",prdtid)
                .ne("isDeleted",1);
        PrdtspecificationsEntity queryResult = prdtspecificationsService.getOne(queryWrapper);
        if(queryResult==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.ProductSepcNotExist);
            return result;
        }
        result.setReturnEntity(queryResult);
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        return result;
    }
    public ApiServiceReturnEntity<List<ProductionsEntity>> getProductionByBscId(Integer bscid){
        ApiServiceReturnEntity<List<ProductionsEntity>> result = new ApiServiceReturnEntity<>(null,null);
        if(bscid==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        QueryWrapper<ProductionsEntity> queryWrapper = new QueryWrapper<ProductionsEntity>()
                .eq("bscprdt_id",bscid)
                .ne("isDeleted",1);
        List<ProductionsEntity> queryResult = productionsService.list(queryWrapper);
        if(queryResult==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.ProductSepcNotExist);
            return result;
        }
        result.setReturnEntity(queryResult);
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        return result;
    }

    public ApiServiceReturnEntity<List<ProductReturnEntity>> getProductByShop(Integer shopid){
        List<ProductReturnEntity> productReturnEntity = new ArrayList<>();
        ApiServiceReturnEntity<List<ProductReturnEntity>> result = new ApiServiceReturnEntity<>(null,null);
        if(shopid==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        ApiServiceReturnEntity<List<PrdtbasicEntity>> basicQueryResult = getBasicProductByShop(shopid);
        if(basicQueryResult.getApiServiceErrorCode()!=ApiServiceErrorCode.OK){
            result.setApiServiceErrorCode(basicQueryResult.getApiServiceErrorCode());
            return result;
        }
        for(PrdtbasicEntity prdtbasicEntity:basicQueryResult.getReturnEntity()){
            ApiServiceReturnEntity<List<ProductionsEntity>> prdtsQueryResult = getProductionByBscId(prdtbasicEntity.getId());
            List<ProductionsEntity> productionsEntities = prdtsQueryResult.getReturnEntity();
            if(productionsEntities==null||productionsEntities.isEmpty()){
                continue;
            }
            for(ProductionsEntity productionsEntity:productionsEntities){
                ApiServiceReturnEntity<PrdtspecificationsEntity> specQueryResult = getPrdtSpecificationsByPrdtId(productionsEntity.getId());
                if(specQueryResult.getApiServiceErrorCode()!=ApiServiceErrorCode.OK){
                    continue;
                }
                productReturnEntity.add(new ProductReturnEntity(productionsEntity,prdtbasicEntity, specQueryResult.getReturnEntity()));
            }
        }
        result.setReturnEntity(productReturnEntity);
        result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        return result;
    }
}
