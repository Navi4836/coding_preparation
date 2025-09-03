package org.example.ratelimit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RateLimiter {
    public RateLimiter() {
        // Schedule token replenishment every second
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //scheduler.scheduleAtFixedRate(this::replenishTokens, 1, 1, TimeUnit.SECONDS);
    }
}
