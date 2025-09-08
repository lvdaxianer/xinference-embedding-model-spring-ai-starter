package io.lvdaxianer.xinference.embedding.model;

import io.lvdaxianer.xinference.embedding.model.autoconfigure.XinferenceEmbeddingProperties;
import io.lvdaxianer.xinference.embedding.model.client.XinferenceEmbeddingClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.AbstractEmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

/**
 * XinferenceEmbeddingModel xinference embedding 模型配置
 * @author lvdaxianer
 */
public class XinferenceEmbeddingModel extends AbstractEmbeddingModel
{

    private final XinferenceEmbeddingProperties properties;
    private final XinferenceEmbeddingOptions options;
    private final MetadataMode metadataMode;
    private final RetryTemplate retryTemplate;
    private final RestTemplate restTemplate;

    public XinferenceEmbeddingModel(XinferenceEmbeddingProperties properties, XinferenceEmbeddingOptions options, MetadataMode metadataMode, RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.properties = properties;
        this.options = options;
        this.restTemplate = restTemplate;
        this.metadataMode = metadataMode;
        this.retryTemplate = retryTemplate;
    }

    public XinferenceEmbeddingModel(XinferenceEmbeddingProperties properties, XinferenceEmbeddingOptions options, RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this(properties, options, MetadataMode.EMBED, restTemplate, retryTemplate);
    }

    public XinferenceEmbeddingModel(XinferenceEmbeddingProperties properties, XinferenceEmbeddingOptions options, RestTemplate restTemplate) {
        this(properties, options, restTemplate, RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }

    /**
     * 直接调用 call 方法
     *
     * @author lvdaxianer
     * @param request the request object to be sent to the AI model
     * @return embedding 结果
     */
    @Override
    public EmbeddingResponse call(@NotNull EmbeddingRequest request)
    {
        Assert.notNull(request, "Request must not be null");

        String model = ObjectUtils.isEmpty(request.getOptions()) || ObjectUtils.isEmpty(request.getOptions().getModel())
                ? this.options.getModel()
                : request.getOptions().getModel();

        return this.retryTemplate.execute(context -> {
            XinferenceEmbeddingClient client = new XinferenceEmbeddingClient(this.properties.getBaseUrl(), model, this.restTemplate);
            return client.apply(request.getInstructions());
        });
    }

    /**
     * 获取 embedding 后的值
     *
     * @author lvdaxianer
     * @param document the document to be embedded
     * @return embedding 值
     */
    @Override
    public float[] embed(@NotNull Document document)
    {
        Assert.notNull(document, "Document must not be null");
        return this.embed(document.getFormattedContent(this.metadataMode));
    }
}
