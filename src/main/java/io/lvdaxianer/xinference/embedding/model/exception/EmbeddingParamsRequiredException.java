package io.lvdaxianer.xinference.embedding.model.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 缺少参数异常
 *
 * @author lvdaxianer
 */
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingParamsRequiredException extends Exception
{
    private String message;
}
