package com.cloud.music.user.controller;

import com.cloud.music.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class HealthController {

    @GetMapping("/health")
    public R<String> health() {
        return R.ok("alive");
    }
}
