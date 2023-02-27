package pers.ztcly.andesiteapi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ztcly.andesiteapi.entity.CustomerReturnEntity;
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

    public CustomerReturnEntity userCheck(String name){
        CustomerReturnEntity result = new CustomerReturnEntity(null,null);
        if(name==null){//参数错误
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.BadArgument);
            return result;
        }
        UserEntity customer;
        try {//出现重复登入名
            customer = userService.findByName(name);
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.DatabaseError);
            return result;
        }
        if (customer == null) {//寻找的用户不存在
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserNotExist);
            return result;
        }
        result.setUserEntity(customer);
        result.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserAlreadyExist);
        return result;
    }

    public CustomerReturnEntity userCheck(UserEntity user){
        CustomerReturnEntity result = new CustomerReturnEntity(null,null);
        if(user==null||user.getName()==null||user.getPassword()==null){
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.BadArgument);
            return result;
        }
        UserEntity customer;
        try {//出现重复登入名
            customer = userService.findById(user.getId());
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.DatabaseError);
            return result;
        }
        if (customer == null) {//寻找的用户不存在
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserNotExist);
            return result;
        }
        result.setUserEntity(customer);
        result.setCustomerServiceErrorCode(CustomerServiceErrorCode.UserAlreadyExist);
        return result;
    }

    public CustomerReturnEntity loginCheck(String name, String password, String loginip) {

        CustomerReturnEntity userCheckResult = userCheck(name);
        if(userCheckResult.getCustomerServiceErrorCode()!=CustomerServiceErrorCode.UserAlreadyExist){
            return userCheckResult;
        }
        if(!password.equals(MD5Util.getMD5(userCheckResult.getUserEntity().getPassword()))){
            userCheckResult.setCustomerServiceErrorCode(CustomerServiceErrorCode.PasswordError);
            return userCheckResult;
        }

        //登录信息失败了就把失败原因传出去
        userCheckResult.setCustomerServiceErrorCode(loginInfoUpdate(userCheckResult.getUserEntity(),loginip));

        return userCheckResult;

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

    public CustomerReturnEntity register(UserEntity user){
        CustomerReturnEntity userCheckResult = userCheck(user);
        if(userCheckResult.getCustomerServiceErrorCode()!=CustomerServiceErrorCode.UserNotExist){
            return userCheckResult;
        }
        if(!userService.save(user)){
            logger.error("[CustomerService]Register_DatabaseError: Add"+ user);
        }
        userCheckResult.setCustomerServiceErrorCode(CustomerServiceErrorCode.OK);
        String decryptPassword = user.getPassword();
        user.setPassword(MD5Util.getMD5(decryptPassword));
        userCheckResult.setUserEntity(user);
        return userCheckResult;

    }

    public CustomerReturnEntity getCustomer(String username){
        CustomerReturnEntity result = userCheck(username);
        if(result.getCustomerServiceErrorCode()==CustomerServiceErrorCode.UserAlreadyExist){
            result.setCustomerServiceErrorCode(CustomerServiceErrorCode.OK);
        }
        return result;
    }

    public CustomerServiceErrorCode updateCustomer(UserEntity user){
        CustomerReturnEntity result = userCheck(user);
        if(result.getCustomerServiceErrorCode()!=CustomerServiceErrorCode.UserAlreadyExist){
            return result.getCustomerServiceErrorCode();
        }
        if(!userService.updateById(user)){
            logger.error("[CustomerService]updateCustomer_DatabaseError: Update"+ result);
            return CustomerServiceErrorCode.DatabaseError;
        }
        return CustomerServiceErrorCode.OK;
    }
}







