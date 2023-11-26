package com.bugs.controller.result;

public class Result {
    // 返回的值
    private Object data;
    // 编码，是否成功
    private Integer code;
    // 描述信息
    private String msg;

    public static Result createResult(Integer code, Object data, String msg) {
        return new Result(code, data, msg);
    }
    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
