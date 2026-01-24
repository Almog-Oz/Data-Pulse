package org.ditto.datapulse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DataPulseConsoleApplication implements CommandLineRunner {
    private final DataPulseExecutor executor;

    public static void main(String[] args) {
        SpringApplication.run(DataPulseConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.executor.run();
    }
}
