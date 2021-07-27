package org.xpp.client2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "client1", fallback = Client1Feign.Client1FeignFallback.class)
public interface Client1Feign {
    /**
     * 获取信息
     * @return
     */
    @GetMapping("getClient1ValueFromConfig")
    @ResponseBody
    String getClient1ValueFromConfig();

    @Component
    class Client1FeignFallback implements Client1Feign {

        @Override
        public String getClient1ValueFromConfig() {
            return "未正确获取client1服务的信息";
        }
    }
}
