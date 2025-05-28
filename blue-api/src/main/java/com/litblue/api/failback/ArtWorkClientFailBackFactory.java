package com.litblue.api.failback;

import com.litblue.api.client.GetArtWorkClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author MECHREVO
 *
 */
@Slf4j
@Component
public class ArtWorkClientFailBackFactory implements FallbackFactory<GetArtWorkClient> {
    @Override
    public GetArtWorkClient create(Throwable cause) {
        return new GetArtWorkClient() {
            @Override
            public String getTestContent() {
                log.info("用户信息查询失败", cause);
                //设置正常结果值
                return "我出现异常了，但是我处理了";
            }
        };
    }
}

