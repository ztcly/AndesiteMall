package pers.ztcly.andesitedb.exception;

/**
 * @author ztcly
 * @date 2023-02-24 16:14
 * @description
 **/
public class DuplicateUserIdException extends Exception{
    public DuplicateUserIdException(){
        super();
    }

    public DuplicateUserIdException(String s){
        super(s);
    }
}
