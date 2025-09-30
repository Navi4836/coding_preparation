package org.example.circuit.breaker;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

enum CircuitState {CLOSED, OPEN, HALF_OPEN}

// Value Object: RequestResult
class RequestResult {
    private final long timestamp;
    private final int statusCode;

    public RequestResult(long timestamp, int statusCode) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
    }

    public boolean isFailure() {
        return statusCode >= 500; // e.g., HTTP 5xx considered failures
    }

    public long getTimestamp() {
        return timestamp;
    }
}

// Entity: CircuitBreaker
class CircuitBreaker {
    private final String serviceName;
    private final int failureThreshold;       // e.g., 3 failures
    private final long windowSizeMillis;      // e.g., 10 sec
    private final long cooldownMillis;        // e.g., 30 sec

    private final Deque<Long> failures = new ArrayDeque<>();
    private CircuitState state = CircuitState.CLOSED;
    private long lastStateChange = 0;

    public CircuitBreaker(String serviceName, int failureThreshold,
                          long windowSizeMillis, long cooldownMillis) {
        this.serviceName = serviceName;
        this.failureThreshold = failureThreshold;
        this.windowSizeMillis = windowSizeMillis;
        this.cooldownMillis = cooldownMillis;
    }

    public boolean allowRequest(long now) {
        if (state == CircuitState.OPEN) {
            if (now - lastStateChange > cooldownMillis) {
                state = CircuitState.HALF_OPEN;
                return true; // try a single test request
            }
            return false; // still blocking
        }
        return true;
    }

    public void recordResult(RequestResult result) {
        long now = result.getTimestamp();

        if (!result.isFailure()) {
            if (state == CircuitState.HALF_OPEN || state == CircuitState.OPEN) {
                state = CircuitState.CLOSED;
                failures.clear();
            }
            return;
        }

        // record failure
        failures.addLast(now);
        cleanupOldFailures(now);

        if (failures.size() >= failureThreshold && state == CircuitState.CLOSED) {
            tripCircuit(now);
        } else if (state == CircuitState.HALF_OPEN) {
            tripCircuit(now); // failed test request
        }
    }

    private void tripCircuit(long now) {
        state = CircuitState.OPEN;
        lastStateChange = now;
        System.out.println("Circuit OPEN for service: " + serviceName);
    }

    private void cleanupOldFailures(long now) {
        while (!failures.isEmpty() && now - failures.peekFirst() > windowSizeMillis) {
            failures.removeFirst();
        }
    }

    public CircuitState getState() {
        return state;
    }
}

// Manager: orchestrates multiple services
class CircuitBreakerManager {
    private final Map<String, CircuitBreaker> breakers = new HashMap<>();

    public void register(String service, CircuitBreaker breaker) {
        breakers.put(service, breaker);
    }

    public boolean allowRequest(String service, long now) {
        return breakers.get(service).allowRequest(now);
    }

    public void recordResult(String service, RequestResult result) {
        breakers.get(service).recordResult(result);
    }
}

