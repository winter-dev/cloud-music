package com.cloud.music.order.feigin;

import com.cloud.music.common.utils.R;
import com.cloud.music.core.constant.CloudMusicConstants;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.order.feigin.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(contextId = "remoteFileService", value = CloudMusicConstants.USER_MODULE_NAME, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 根据userId查询用户数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/user/getById/{id}", method = RequestMethod.GET)
    R<CloudUser> getUserById(@PathVariable("id") String id);
}
