package org.example.ratelimit.simple;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TokenBucket {
    private final long capacity;        // Maximum number of tokens the bucket can hold
    private final double fillRate;      // Rate at which tokens are added to the bucket (tokens per second)
    private double tokens;              // Current number of tokens in the bucket
    private Instant lastRefillTimestamp; // Last time we refilled the bucket

    public TokenBucket(long capacity, double fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;  // Start with a full bucket
        this.lastRefillTimestamp = Instant.now().minusSeconds(10);
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();  // First, add any new tokens based on elapsed time

        if (this.tokens < tokens) {
            return false;  // Not enough tokens, deny the request
        }

        this.tokens -= tokens;  // Consume the tokens
        return true;  // Allow the request
    }

    private void refill() {
        Instant now = Instant.now();
        long nowInSeconds = now.getEpochSecond();
        long lastRefillTime = lastRefillTimestamp.getEpochSecond();
        double refillToken = (nowInSeconds - lastRefillTime) * fillRate;
        System.out.println(refillToken);
        // Calculate how many tokens to add based on the time elapsed
        double tokensToAdd = (now.toEpochMilli() - lastRefillTimestamp.toEpochMilli()) * fillRate / 1000.0;
        System.out.println(tokensToAdd);
        this.tokens = Math.min(capacity, this.tokens + tokensToAdd);  // Add tokens, but don't exceed capacity
        this.lastRefillTimestamp = now;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(3, 1);
        List<Thread> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int time = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000 * time);
                        bucket.allowRequest(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            list.add(t);
        }
        for (Thread t : list) {
            t.start();
        }
        for (Thread t : list) {
            t.join();
        }
    }
}
