package exception;

/**
 * 客户端没有访问内容的权限
 */
public class ForbiddenException extends CommonException {

    public ForbiddenException(String message) {
        super(message, 403);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, 403);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, 403);
    }
}
