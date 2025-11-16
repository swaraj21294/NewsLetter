package org.example.web.controller;
import org.slf4j.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
@Component
@ConditionalOnProperty(name="monitor.mem.enabled", havingValue="true", matchIfMissing=false)
public class MemLogger {
  private final Logger log = LoggerFactory.getLogger(MemLogger.class);
  @Scheduled(fixedDelay = 10000)
  public void log() {
    Runtime r = Runtime.getRuntime();
    long used = (r.totalMemory() - r.freeMemory()) / 1024 / 1024;
    long total = r.totalMemory() / 1024 / 1024;
    long max = r.maxMemory() / 1024 / 1024;
    log.info("JVM mem used={}MB total={}MB max={}MB threads={}", used, total, max, Thread.activeCount());
  }
}
