package io.lvdaxianer.xinference.embedding.model.autoconfigure;

import io.lvdaxianer.xinference.embedding.model.XinferenceEmbeddingOptions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.ai.document.MetadataMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * XinferenceEmbeddingProperties
 * @author lvdaxianer
 */
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("spring.ai.xinference.embedding")
@Component
@Data
public class XinferenceEmbeddingProperties extends XinferenceEmbeddingParentProperties
{
    public static final String CONFIG_PREFIX = "spring.ai.xinference.embedding";

    private MetadataMode metadataMode;
    @NestedConfigurationProperty
    private XinferenceEmbeddingOptions options;
}
