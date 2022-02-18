package com.wasim919.translator.actuators;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CustomHealthCheck extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder bldr) throws Exception {
        boolean running = false;
        int currentMinute = LocalDateTime.now().getMinute();
        if (currentMinute % 2 == 0) {
            bldr.up();
        } else {
            bldr.down();
        }
    }
}
