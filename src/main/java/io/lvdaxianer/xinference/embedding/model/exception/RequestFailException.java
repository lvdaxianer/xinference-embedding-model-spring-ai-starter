package io.lvdaxianer.xinference.embedding.model.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 请求失败抛出的异常
 *
 * @author lvdaxianer
 */
@AllArgsConstructor
@NoArgsConstructor
public class RequestFailException extends Exception
{
    private String message;
}
