package pers.ztcly.andesiteapi.util;

public enum CustomerServiceErrorCode {
    ///登录检查//////////
    OK(0,"操作成功"),
    BadArgument(1,"参数错误"),
    UserNotExist(2,"用户不存在"),
    DatabaseError(3,"数据库错误"),
    PasswordError(4,"账号或密码错误"),
    UserAlreadyExist(5,"用户已存在")
    ///登录信息更新//////////

    ;

    private String msg;
    private int code;

    private CustomerServiceErrorCode(int code, String msg){
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
