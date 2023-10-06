package gilyeon.spring.batch;

import gilyeon.spring.batch.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job parentJob() {
        return jobBuilderFactory.get("parentJob")
                .start(jobStep(null))
                .next(step2())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    // ExecutionContext 에 있는 값을 key 를 통해 가져올 수 있음
    private DefaultJobParametersExtractor myJobParametersExtractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"name"});
        return extractor;
    }
    @Bean
    public Step jobStep (JobLauncher jobLauncher) {
        return stepBuilderFactory.get("my-jobStep")
                .job(childJob())
                .launcher(jobLauncher)
//                .parametersExtractor(new JobParametersExtractor() {
//                    @Override
//                    public JobParameters getJobParameters(Job job, StepExecution stepExecution) {
//                        return null;
//                    }
//                })
                .parametersExtractor(myJobParametersExtractor())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("name", "user1");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    @Bean
    public Job childJob() {
        return jobBuilderFactory.get("childJob")
                .start(step1())
                .incrementer(new CustomJobParametersIncrementer())
                .build();
    }

    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .tasklet(((contribution, chunkContext) -> RepeatStatus.FINISHED))
                .build();
    }

    @Bean
    public Step step2 () {
        return stepBuilderFactory.get("step2")
                .tasklet(((contribution, chunkContext) -> RepeatStatus.FINISHED))
                .build();
    }


}
