// 设置json数据格式

package com.roam.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   // 无参构造
@AllArgsConstructor  // 全参构造
public class Result<T> {
    private Integer code;   //状态码
    private String message;  //状态说明
    private T data; // 泛型T  什么数据类型都行

    public static <T> Result<T> success(){
        return new Result<>(200,"success",null);
    }

//   重载
//    给前端返回数据
    public static <T> Result<T> success(T data){
        return new Result<>(200,"success",data);
    }
//    只返回状态码和message
public static <T> Result<T> success(Integer code,String message){
    return new Result<>(code,message,null);
}

//    给前端返回数据和指定message
    public static <T> Result<T> success(T data, String message){
        return new Result<>(200,message,data);
    }
//    给前端不返回数据只返回message
    public static <T> Result<T> success(String message){
        return new Result<>(200,message,null);
    }
//    失败的几个
public static<T>  Result<T> fail(){
    return new Result<>(201,"fail",null);
}

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(201,message,null);
    }
}
