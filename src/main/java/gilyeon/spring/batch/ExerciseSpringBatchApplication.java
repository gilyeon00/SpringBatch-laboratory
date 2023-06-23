package gilyeon.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing  // "이 애플리케이션은 배치 프로세싱을 하겠다" 의미
public class ExerciseSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseSpringBatchApplication.class, args);
    }

}
