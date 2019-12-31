package com.online.school.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JsonData {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<>();

    private JsonData(){}

    //操作成功，调用这个方法，返回成功的数据
    public static JsonData success(){
        JsonData r = new JsonData();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    //操作失败，调用这个方法，返回失败的数据
    public static JsonData error(){
        JsonData r = new JsonData();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    //使用链式编程
    public JsonData isSuccess(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public JsonData message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonData code(Integer code){
        this.setCode(code);
        return this;
    }

    public JsonData data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public JsonData data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
