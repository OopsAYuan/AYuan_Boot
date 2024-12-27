package com.ayuan.framework.common.pojo;

import com.ayuan.framework.common.exception.ErrorCode;
import com.ayuan.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class CommonResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 状态消息
     */
    private String msg;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = GlobalErrorCodeConstants.SUCCESS.getMsg();
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }
    public static <T> CommonResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }
    public static <T> CommonResult<T> error(CommonResult<T> result) {
        return error(result.getCode(), result.getMsg());
    }

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess(code);
    }
    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore
    public boolean isErroe() {
        return !isSuccess(code);

    }

}
