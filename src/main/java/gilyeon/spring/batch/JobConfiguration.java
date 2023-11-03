package gilyeon.spring.batch;

import gilyeon.spring.batch.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
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
    public Job flowStepJob() {
        return jobBuilderFactory.get("flowStepJob")
                .start(flowStep())
                .next(step3())
                .incrementer(new RunIdIncrementer())
                .build();
    }


    @Bean
    public Step flowStep() {
        return stepBuilderFactory.get("flowStep")
                .flow(myflow())
                .build();
    }

    @Bean
    public Flow myflow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myflow");
        flowBuilder
                .start(step2())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println(">>>> step2 executed");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("ste32")
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println(">>>> step3 executed");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }


}
