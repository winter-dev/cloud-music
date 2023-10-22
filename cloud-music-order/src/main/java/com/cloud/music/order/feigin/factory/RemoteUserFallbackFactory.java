package com.cloud.music.order.feigin.factory;

import com.cloud.music.common.utils.R;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.order.feigin.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable cause) {
        log.error("RemoteUserFallbackFactory error message is {}", cause.getMessage());
        return new RemoteUserService() {
            @Override
            public R<CloudUser> getUserById(String id) {
                return R.fail("获取用户失败:" + cause.getMessage());
            }
        };
    }
}
