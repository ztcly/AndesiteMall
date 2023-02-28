package pers.ztcly.andesiteapi.entity;

import pers.ztcly.andesitedb.modules.mall.entity.PrdtbasicEntity;
import pers.ztcly.andesitedb.modules.mall.entity.PrdtspecificationsEntity;
import pers.ztcly.andesitedb.modules.mall.entity.ProductionsEntity;

/**
 * @author ztcly
 * @date 2023-02-28 17:41
 * @description
 **/
public class ProductReturnEntity {
    private ProductionsEntity productionsEntity;
    private PrdtbasicEntity prdtbasicEntity;
    private PrdtspecificationsEntity prdtspecificationsEntity;

    public ProductReturnEntity(ProductionsEntity productionsEntity, PrdtbasicEntity prdtbasicEntity, PrdtspecificationsEntity prdtspecificationsEntity) {
        this.productionsEntity = productionsEntity;
        this.prdtbasicEntity = prdtbasicEntity;
        this.prdtspecificationsEntity = prdtspecificationsEntity;
    }

    public ProductionsEntity getProductionsEntity() {
        return productionsEntity;
    }

    public void setProductionsEntity(ProductionsEntity productionsEntity) {
        this.productionsEntity = productionsEntity;
    }

    public PrdtbasicEntity getPrdtbasicEntity() {
        return prdtbasicEntity;
    }

    public void setPrdtbasicEntity(PrdtbasicEntity prdtbasicEntity) {
        this.prdtbasicEntity = prdtbasicEntity;
    }

    public PrdtspecificationsEntity getPrdtspecificationsEntity() {
        return prdtspecificationsEntity;
    }

    public void setPrdtspecificationsEntity(PrdtspecificationsEntity prdtspecificationsEntity) {
        this.prdtspecificationsEntity = prdtspecificationsEntity;
    }
}
