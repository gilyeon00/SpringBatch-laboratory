package gilyeon.spring.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job () {
        return jobBuilderFactory.get("my-job")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        // StepContribution 이 JobParameters 를 참조하는 방법
                        JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
                        jobParameters.getDate("date");
                        jobParameters.getDouble("age");
                        jobParameters.getString("name");
                        jobParameters.getLong("seq");


                        // ChunkContext 이 JobParameters 를 참조하는 방법
                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
                        jobParameters1.get("date");
                        jobParameters1.get("age");

                        System.out.println("------step1 has executed------");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step step2 () {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("------step2 has executed------");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }


}
