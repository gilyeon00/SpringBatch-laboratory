package gilyeon.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // Spring 이 이 파일이 설정파일임을 인지할 수 있도록
@Slf4j             // log 찍기 위해
public class HelloConfiguration {

    /*
        Job 은 배치 처리의 최소 실행 단위
        Job 을 만들기 위해 Spring 은 JobBuilderFactory 제공
        이 클래스는 Spring Batch 설정에 의해서, 이미 Bean 으로 설정되어 있기때문에 생성자 주입으로 받을 수 있음
     */
    private final JobBuilderFactory jobBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory){
        this.jobBuilderFactory = jobBuilderFactory;
    }

    /*
        job 이름 : helloJob (<- spring batch 를 실행하게 하는 key)
        RunIdIncrementer : 항상 job 이 실행할 때마다 파라미터 id를 자동으로 생성주는 클래스
        start() : job 실행 시, 최초로 실행할 step 설정할 메서드
     */
    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .start()
                .build();
    }


}
