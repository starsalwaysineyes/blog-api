package com.jt.common.result;

import com.jt.common.constant.ResultCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private Object data;

    public Result(){

    }

    public Result(Integer code,String msg){
        this.code = code ;
        this.msg = msg;
    }

    //TODO
//    public void setResultCode(){
//        this.code = code;
//    }

    public void setResultMsg(String msg){
        this.msg = msg;
    }

    public static Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }


    public void setCode(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public void setMsg(String msg){
        this.msg = msg;

    }

    public String getMsg(){
        return msg;
    }

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
        return data;
    }

    public static Result error(ResultCode resultCode){
        Result result = new Result();

        result.setResultCode(resultCode);

        return result;

    }

    public static Result error(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public Map<String,Object> simple(){

        Map<String,Object> simple = new HashMap<String,Object>();

        this.data = simple;

        return simple;
    }



}
