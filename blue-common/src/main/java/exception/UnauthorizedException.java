package exception;

/**
 * 客户端必须对自身进行身份验证才能获得请求的响应
 */
public class UnauthorizedException extends CommonException {

    public UnauthorizedException(String message) {
        super(message, 401);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, 401);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, 401);
    }
}
