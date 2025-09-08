package io.lvdaxianer.xinference.embedding.model.entity;

import lombok.Data;

import java.util.List;

/**
 * embedding 解析结果
 * @author lvdaxianer
 */
@Data
public class EmbeddingEntity
{
    public List<EmbeddingData> data;

    @Data
    public static class EmbeddingData {
        private float[] embedding;
        private Integer index;
    }
}
