package io.lvdaxianer.xinference.embedding.model.client;

import io.lvdaxianer.xinference.embedding.model.entity.EmbeddingEntity;
import io.lvdaxianer.xinference.embedding.model.exception.RequestFailException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * xinference embedding client 向量转换 客户端
 * @author lvdaxianer
 */
@AllArgsConstructor
@Slf4j
public class XinferenceEmbeddingClient implements Function<List<String>, EmbeddingResponse>
{
    private String baseUrl;
    private String model;
    private RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public EmbeddingResponse apply(List<String> strings)
    {

        // -- 准备请求体数据
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("input", strings);

        // -- 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        // -- 创建HttpEntity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        EmbeddingEntity embeddingEntity = null;
        try {
            // -- restTemplate 请求数据
            embeddingEntity = restTemplate.postForObject(baseUrl + "/v1/embeddings", requestEntity, EmbeddingEntity.class);
        } catch (Exception e) {
            log.error("xinference embedding client error", e);
            throw new RequestFailException(e.getMessage());
        }

        assert embeddingEntity != null;
        List<Embedding> list = embeddingEntity.getData().stream().map(l -> new Embedding(l.getEmbedding(), l.getIndex())).toList();
        return new EmbeddingResponse(list);
    }
}
