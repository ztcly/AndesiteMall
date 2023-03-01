package pers.ztcly.andesiteapi.util;

public enum ApiServiceErrorCode {
    ///用户服务//////////
    OK(0,"操作成功"),
    BadArgument(11,"参数错误"),
    UserNotExist(12,"用户不存在"),
    DatabaseError(13,"数据库错误"),
    PasswordError(14,"账号或密码错误"),
    UserAlreadyExist(15,"用户已存在"),
    ///产品服务//////////
    ProductBasicNotExist(31,"产品基础信息不存在"),
    ProductSepcNotExist(32,"产品规格规格信息不存在"),
    ProductNotExist(33,"产品信息不存在"),
    CartIsEmpty(34,"购物车为空"),



    ;

    private final String msg;
    private final int code;

    ApiServiceErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
