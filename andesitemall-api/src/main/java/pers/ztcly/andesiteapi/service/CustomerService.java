package pers.ztcly.andesiteapi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ztcly.andesiteapi.entity.LoginReturnEntity;
import pers.ztcly.andesiteapi.util.CustomerServiceErrorCode;
import pers.ztcly.andesiteapi.util.MD5Util;
import pers.ztcly.andesitedb.exception.DuplicateUserIdException;
import pers.ztcly.andesitedb.modules.mall.entity.UserEntity;
import pers.ztcly.andesitedb.modules.mall.entity.UserinfoEntity;
import pers.ztcly.andesitedb.modules.mall.service.UserService;
import pers.ztcly.andesitedb.modules.mall.service.UserinfoService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author ztcly
 * @date 2023-02-24 15:21
 * @description
 **/
@Service
public class CustomerService {
    private final Log logger = LogFactory.getLog(CustomerService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserinfoService userinfoService;



    public LoginReturnEntity loginCheck(String name, String password,String loginip) {

        LoginReturnEntity loginReturnEntity = new LoginReturnEntity(null,null);
        if (name == null || password == null) {
            loginReturnEntity.setCustomerServiceErrorCode(CustomerServiceErrorCode.BadArgument);
            return loginReturnEntity;
        }

        UserEntity customer;
        try {
            customer = userService.findByName(name);
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            loginReturnEntity.setCustomerServiceErrorCode(CustomerServiceErrorCode.DatabaseError);
            return loginReturnEntity;
        }

        if (customer == null) {
            loginReturnEntity.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserNotExist);
            return loginReturnEntity;
        }
        if(!password.equals(MD5Util.getMD5(customer.getPassword()))){
            loginReturnEntity.setCustomerServiceErrorCode(CustomerServiceErrorCode.PasswordError);
            return loginReturnEntity;
        }

        CustomerServiceErrorCode infoUpdateRV =  loginInfoUpdate(customer,loginip);
        if(infoUpdateRV!= CustomerServiceErrorCode.OK){
            loginReturnEntity.setCustomerServiceErrorCode(infoUpdateRV);
        }
        else{
            loginReturnEntity.setCustomerServiceErrorCode(CustomerServiceErrorCode.OK);
            loginReturnEntity.setUserEntity(customer);
        }
        return loginReturnEntity;

    }

    private CustomerServiceErrorCode loginInfoUpdate(UserEntity user, String loginip){
        UserinfoEntity result;
        try {
            result = userinfoService.findById(user.getId());
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            return CustomerServiceErrorCode.DatabaseError;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        result.setLastLoginTime(date);

        result.setLastLoginIP(loginip);

        if(!userinfoService.updateById(result)){
            logger.error("[CustomerService]LoginInfoUpdate_DatabaseError: Update"+ result);
            return CustomerServiceErrorCode.DatabaseError;
        }

        return CustomerServiceErrorCode.OK;
    }

    public LoginReturnEntity register(UserEntity user){
        LoginReturnEntity result = new LoginReturnEntity(null,null);
        if(user.getName()==null||user.getPassword()==null){
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.BadArgument);
            return result;
        }

        UserEntity queryResult;
        try {
            queryResult = userService.findByName(user.getName());
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.DatabaseError);
            return result;
        }
        if(queryResult!=null) {
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserAlreadyExist);
            return result;
        }

        if(!userService.save(user)){
            logger.error("[CustomerService]Register_DatabaseError: Add"+ user);
        }
        result.setCustomerServiceErrorCode(CustomerServiceErrorCode.OK);
        result.setUserEntity(user);
        return result;



    }
}







