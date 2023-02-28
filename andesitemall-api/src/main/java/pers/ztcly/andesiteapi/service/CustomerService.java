package pers.ztcly.andesiteapi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ztcly.andesiteapi.entity.ApiServiceReturnEntity;
import pers.ztcly.andesiteapi.util.ApiServiceErrorCode;
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

    public ApiServiceReturnEntity<UserEntity> userCheck(String name){
        ApiServiceReturnEntity<UserEntity> result = new ApiServiceReturnEntity<>(null, null);
        if(name==null){//参数错误
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        UserEntity customer;
        try {//出现重复登入名
            customer = userService.findByName(name);
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            result.setApiServiceErrorCode(ApiServiceErrorCode.DatabaseError);
            return result;
        }
        if (customer == null) {//寻找的用户不存在
            result.setApiServiceErrorCode(ApiServiceErrorCode.UserNotExist);
            return result;
        }
        if(customer.getIsDeleted()==1){
            result.setApiServiceErrorCode(ApiServiceErrorCode.UserNotExist);
            return result;
        }
        result.setReturnEntity(customer);
        result.setApiServiceErrorCode(ApiServiceErrorCode.UserAlreadyExist);
        return result;
    }

    public ApiServiceReturnEntity<UserEntity> userCheck(UserEntity user){
        ApiServiceReturnEntity<UserEntity> result = new ApiServiceReturnEntity<>(null,null);
        if(user==null||user.getName()==null||user.getPassword()==null){
            result.setApiServiceErrorCode(ApiServiceErrorCode.BadArgument);
            return result;
        }
        UserEntity customer;
        try {//出现重复登入名
            customer = userService.findById(user.getId());
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            result.setApiServiceErrorCode(ApiServiceErrorCode.DatabaseError);
            return result;
        }
        if (customer == null) {//寻找的用户不存在
            result.setApiServiceErrorCode(ApiServiceErrorCode.UserNotExist);
            return result;
        }
        if(customer.getIsDeleted()==1){
            result.setApiServiceErrorCode(ApiServiceErrorCode.UserNotExist);
            return result;
        }
        result.setReturnEntity(customer);
        result.setApiServiceErrorCode(ApiServiceErrorCode.UserAlreadyExist);
        return result;
    }

    public ApiServiceReturnEntity<UserEntity> loginCheck(String name, String password, String loginip) {

        ApiServiceReturnEntity<UserEntity> userCheckResult = userCheck(name);
        if(userCheckResult.getApiServiceErrorCode()!= ApiServiceErrorCode.UserAlreadyExist){
            return userCheckResult;
        }
        if(!password.equals(MD5Util.getMD5(userCheckResult.getReturnEntity().getPassword()))){
            userCheckResult.setApiServiceErrorCode(ApiServiceErrorCode.PasswordError);
            return userCheckResult;
        }

        //登录信息失败了就把失败原因传出去
        userCheckResult.setApiServiceErrorCode(loginInfoUpdate(userCheckResult.getReturnEntity(),loginip));

        return userCheckResult;

    }

    private ApiServiceErrorCode loginInfoUpdate(UserEntity user, String loginip){
        UserinfoEntity result;
        try {
            result = userinfoService.findById(user.getId());
        } catch (DuplicateUserIdException e) {
            logger.error(e.getMessage());
            return ApiServiceErrorCode.DatabaseError;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        result.setLastLoginTime(date);

        result.setLastLoginIP(loginip);

        if(!userinfoService.updateById(result)){
            logger.error("[CustomerService]LoginInfoUpdate_DatabaseError: Update"+ result);
            return ApiServiceErrorCode.DatabaseError;
        }

        return ApiServiceErrorCode.OK;
    }

    public ApiServiceReturnEntity<UserEntity> register(UserEntity user){
        ApiServiceReturnEntity<UserEntity> userCheckResult = userCheck(user);
        if(userCheckResult.getApiServiceErrorCode()!= ApiServiceErrorCode.UserNotExist){
            return userCheckResult;
        }
        if(!userService.save(user)){
            logger.error("[CustomerService]Register_DatabaseError: Add"+ user);
        }
        userCheckResult.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        String decryptPassword = user.getPassword();
        user.setPassword(MD5Util.getMD5(decryptPassword));
        userCheckResult.setReturnEntity(user);
        return userCheckResult;

    }

    public ApiServiceReturnEntity<UserEntity> getCustomer(String username){
        ApiServiceReturnEntity<UserEntity> result = userCheck(username);
        if(result.getApiServiceErrorCode()== ApiServiceErrorCode.UserAlreadyExist){
            result.setApiServiceErrorCode(ApiServiceErrorCode.OK);
        }
        return result;
    }

    public ApiServiceErrorCode updateCustomer(UserEntity user){
        ApiServiceReturnEntity<UserEntity> result = userCheck(user);
        if(result.getApiServiceErrorCode()!= ApiServiceErrorCode.UserAlreadyExist){
            return result.getApiServiceErrorCode();
        }
        if(!userService.updateById(user)){
            logger.error("[CustomerService]updateCustomer_DatabaseError: Update"+ result);
            return ApiServiceErrorCode.DatabaseError;
        }
        return ApiServiceErrorCode.OK;
    }
}







