package com.common.service.handler;

import com.example.common.exception.AbstractException;
import com.example.common.exception.MessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 操作异常
 *
 * @author lvdongfeng
 * @date 2019/7/22 14:38
 * @desc
 */
@ResponseStatus(HttpStatus.OK)
public class OperationException extends AbstractException {

    private static final long serialVersionUID = 3510775765637913007L;

    public OperationException(){}

    public OperationException(MessageEnum msg) {
        super(msg);
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

    /**
     * 带format的异常输出
     * @param format format
     * @param arguments 参数
     */
    protected OperationException(String format, Object... arguments){
        super(String.format(format, arguments));
    }


}
