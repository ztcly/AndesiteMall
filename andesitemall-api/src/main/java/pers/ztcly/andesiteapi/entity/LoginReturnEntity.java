package pers.ztcly.andesiteapi.entity;

import pers.ztcly.andesiteapi.util.CustomerServiceErrorCode;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;

/**
 * @author ztcly
 * @date 2023-02-25 16:58
 * @description
 **/
public class LoginReturnEntity {
    private UserEntity userEntity;
    private CustomerServiceErrorCode customerServiceErrorCode;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CustomerServiceErrorCode getCustomerServiceErrorCode() {
        return customerServiceErrorCode;
    }

    public void setCustomerServiceErrorCode(CustomerServiceErrorCode customerServiceErrorCode) {
        this.customerServiceErrorCode = customerServiceErrorCode;
    }

    public LoginReturnEntity(UserEntity userEntity, CustomerServiceErrorCode customerServiceErrorCode) {
        this.userEntity = userEntity;
        this.customerServiceErrorCode = customerServiceErrorCode;
    }
}
