# xinference-embedding-model-spring-ai-starter

英文 | [中文](https://github.com/lvdaxianer/xinference-embedding-model-spring-ai-starter/blob/master/README.zh.md)          

> Spring AI xinference embedding model

## add dependency 
```xml
<dependency>
    <groupId>io.github.lvdaxianer</groupId>  
    <artifactId>xinference-embedding-model-spring-ai-starter</artifactId>
    <version>xxxx</version>
</dependency>
```

## config 
```yaml
spring:
  ai:
    xinference:
      embedding:
        base-url: http://xx:9997
        options:
          model: Qwen3-Embedding-4B
```

##  version
| current version | springboot | spring-ai | Is it available? |
| :--- | :--: | ---: | ---: |
| 1.0.0 |  3.4.5  | 1.0.1 | × |
| 1.0.1 |  3.4.5  | 1.0.1 | √ |  

## usage
```java
@RestController
@RequestMapping("/embedding")
@RequiredArgsConstructor
public class EmbeddingController
{
    private final EmbeddingModel embeddingModel;

    @GetMapping("/chat")
    public float[] chat() {
        List<String> list = List.of("我是lihh", "我是chendd");

        EmbeddingRequest embeddingRequest = new EmbeddingRequest(list, XinferenceEmbeddingOptions.builder().build());
        EmbeddingResponse embeddingResponse = embeddingModel.call(embeddingRequest);
        return embeddingResponse.getResult().getOutput();
    }
}
```
