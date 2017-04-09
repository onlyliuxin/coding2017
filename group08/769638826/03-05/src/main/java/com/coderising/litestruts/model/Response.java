package com.coderising.litestruts.model;

/**
 * Created by huitailang on 17/3/5.
 * @author zhangkun
 * @date 2017年03月05日17:46:49
 * 响应结果
 */
public class Response {
    private String code;
    private String viewPath;

    public Response(String code, String viewPath) {
        this.code = code;
        this.viewPath = viewPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (!code.equals(response.code)) return false;
        return viewPath.equals(response.viewPath);

    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + viewPath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", viewPath='" + viewPath + '\'' +
                '}';
    }
}
