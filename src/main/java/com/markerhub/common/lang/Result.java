package com.markerhub.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public static Result getResult(String code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        return Result.getResult("200","operate success",data);
    }

    public static Result fail(String code,String msg){
        return Result.getResult(code,msg,null);
    }
}
