# xinference-embedding-model-spring-ai-starter

中文 | [英文](https://github.com/lvdaxianer/xinference-embedding-model-spring-ai-starter/blob/master/README.md)  

> Spring AI xinference embedding 模型

## 添加依赖
```xml
<dependency>
    <groupId>io.github.lvdaxianer</groupId>    
    <artifactId>xinference-embedding-model-spring-ai-starter</artifactId>  
    <version>xxxx</version>
</dependency>
```

## 配置 
```yaml
spring:
  ai:
    xinference:
      embedding:
        base-url: http://xx:9997
        options:
          model: Qwen3-Embedding-4B
```

##  版本
| current version | springboot | spring-ai | Is it available? |  
| :--- | :--: | ---: | ---: |
| 1.0.0 |  3.4.5  | 1.0.1 | × |
| 1.0.1 |  3.4.5  | 1.0.1 | √ |  

## 如何使用
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
