package co.com.ancas.config;

import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.use_cases.adapters.TaskAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public TaskAdapter taskAdapter(TaskRepositoryPort taskRepositoryPort) {
        return new TaskAdapter(taskRepositoryPort);
    }
}
