package com.soeasyeasy.auth.rpc;

import com.soeasyeasy.auth.entity.ApiReq;
import com.soeasyeasy.common.constants.GlobalResCodeConstants;
import com.soeasyeasy.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * feign
 *
 * @author hc
 * @date 2025/04/18
 */
@FeignClient(name = "see-gateway", url = "${url.see-gateway:127.0.0.1:9000}", fallbackFactory = GatewayFeign.GatewayFeignFallbackFactory.class)
public interface GatewayFeign {

    /**
     * 推送api数据
     *
     * @return {@link Result }<{@link ? }>
     */
    @PostMapping("/auth/api/batchSave")
    Result<?> batchSave(List<ApiReq> apiReqList);

    @Slf4j
    @Component
    class GatewayFeignFallbackFactory implements FallbackFactory<GatewayFeign> {
        /**
         * Returns an instance of the fallback appropriate for the given cause.
         *
         * @param cause cause of an exception.
         * @return fallback
         */
        @Override
        public GatewayFeign create(Throwable cause) {
            return apiReqList -> {
                log.error("推送api信息失败：{}", apiReqList, cause);
                return Result.error(GlobalResCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "推送api信息失败");
            };
        }
    }

}
