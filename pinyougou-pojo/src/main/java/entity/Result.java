package entity;

/**
 * 结果返回封装
 * @author mofei
 * @date 2018/9/5 23:06
 */
public class Result {
    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
