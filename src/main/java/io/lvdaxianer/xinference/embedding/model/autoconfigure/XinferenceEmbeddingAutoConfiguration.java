package io.lvdaxianer.xinference.embedding.model.autoconfigure;

import io.lvdaxianer.xinference.embedding.model.XinferenceEmbeddingModel;
import io.lvdaxianer.xinference.embedding.model.exception.EmbeddingParamsRequiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 自动装配
 * @author lvdaxianer
 */
@ConditionalOnProperty(
        name = {"spring.ai.xinference.embedding.base-url"}
)
@EnableConfigurationProperties({XinferenceEmbeddingProperties.class})
@ImportAutoConfiguration(
        classes = {SpringAiRetryAutoConfiguration.class, RestClientAutoConfiguration.class}
)
@Slf4j
@Configuration
public class XinferenceEmbeddingAutoConfiguration
{

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @ConditionalOnMissingBean
    @Bean
    public XinferenceEmbeddingModel xinferenceEmbeddingModel(XinferenceEmbeddingProperties properties, RestTemplate restTemplate) throws EmbeddingParamsRequiredException
    {
        log.info("XinferenceEmbeddingModel init");

        String baseUrl = properties.getBaseUrl();
        if (ObjectUtils.isEmpty(baseUrl)) {
            throw new EmbeddingParamsRequiredException("Required parameters[base-url]");
        }

        if (ObjectUtils.isEmpty(properties.getOptions())) {
            throw new EmbeddingParamsRequiredException("Required parameters[options]");
        } else {
            assert properties.getOptions() != null;
        }

        String model = properties.getOptions().getModel();

        if (ObjectUtils.isEmpty(model)) {
            throw new EmbeddingParamsRequiredException("Required parameters[model]");
        }

        log.info("XinferenceEmbeddingModel init success");
        log.info("XinferenceEmbeddingModel init params: baseUrl={}, model={}", baseUrl, model);

        return new XinferenceEmbeddingModel(properties, properties.getOptions(), restTemplate);
    }
}
