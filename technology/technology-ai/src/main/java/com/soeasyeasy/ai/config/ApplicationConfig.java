package com.soeasyeasy.ai.config;

import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/25 18:38
 * @Description:
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public DocumentTransformer documentTransformer() {
        return new TokenTextSplitter();
    }

    //@Bean
    //public DocumentTransformer documentTransformer() {
    //    return TextSplitter.builder()
    //            .withChunkSize(1000)      // 每个 chunk 的字符数
    //            .withOverlap(200)         // 重叠字符数
    //            .withSeparator("\n")      // 分割符
    //            .build();
    //}
//}
}