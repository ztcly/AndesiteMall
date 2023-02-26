package pers.ztcly.andesitedb.util;

public enum DataBaseErrorCode {


    ///登录检查//////////

    ///登录信息更新//////////

    ;

    private String msg;
    private int code;

    private DataBaseErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
