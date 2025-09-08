package io.lvdaxianer.xinference.embedding.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.ai.embedding.EmbeddingOptions;

/**
 * xinference embedding options
 * @author lvdaxianer
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class XinferenceEmbeddingOptions implements EmbeddingOptions
{

    @JsonProperty("model")
    private String model;
    @JsonProperty("dimensions")
    private Integer dimensions = 0;

    @Override
    public String getModel()
    {
        return this.model;
    }

    @Override
    public Integer getDimensions()
    {
        return this.dimensions;
    }
}
