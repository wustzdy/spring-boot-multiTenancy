package com.wust.spring.boot.multi.tenant.bean.bean;

import com.wust.spring.boot.multi.tenant.bean.api.Clock;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class GrandClock implements Clock {
    @Override
    public Date now() {
        return Date.from(Instant.now());
    }
}
