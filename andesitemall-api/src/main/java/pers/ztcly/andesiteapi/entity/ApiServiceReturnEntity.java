package pers.ztcly.andesiteapi.entity;

import pers.ztcly.andesiteapi.util.ApiServiceErrorCode;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;

/**
 * @author ztcly
 * @date 2023-02-25 16:58
 * @description
 **/
public class ApiServiceReturnEntity<T> {
    private T returnEntity;
    private ApiServiceErrorCode apiServiceErrorCode;

    public ApiServiceReturnEntity(T returnEntity, ApiServiceErrorCode apiServiceErrorCode) {
        this.returnEntity = returnEntity;
        this.apiServiceErrorCode = apiServiceErrorCode;
    }

    public T getReturnEntity() {
        return returnEntity;
    }

    public void setReturnEntity(T returnEntity) {
        this.returnEntity = returnEntity;
    }

    public ApiServiceErrorCode getApiServiceErrorCode() {
        return apiServiceErrorCode;
    }

    public void setApiServiceErrorCode(ApiServiceErrorCode apiServiceErrorCode) {
        this.apiServiceErrorCode = apiServiceErrorCode;
    }
}
