package com.ishaque.backend;

import java.lang.management.ManagementFactory;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

    @GetMapping("/api/monitor")
    public Map<String, Object> getMetrics() {

        Runtime runtime = Runtime.getRuntime();

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        long maxMemory = runtime.maxMemory();

        long uptime = ManagementFactory
                .getRuntimeMXBean()
                .getUptime();

        Map<String, Object> metrics = new LinkedHashMap<>();

        metrics.put("application", "devops-monitor");
        metrics.put("status", "UP");
        metrics.put("uptimeSeconds", uptime / 1000);
        metrics.put("jvmMemoryUsedMB", usedMemory / (1024.0 * 1024));
        metrics.put("jvmMemoryMaxMB", maxMemory / (1024.0 * 1024));

        return metrics;
    }
}