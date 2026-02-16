package co.com.ancas.configurations;

import co.com.ancas.domain.ports.TaskRepositoryPort;
import co.com.ancas.use_cases.adapters.TaskPortAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public TaskPortAdapter taskAdapter(TaskRepositoryPort taskRepositoryPort) {
        return new TaskPortAdapter(taskRepositoryPort);
    }
}
