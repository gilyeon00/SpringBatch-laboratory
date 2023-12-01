package gilyeon.spring.batch;

import gilyeon.spring.batch.entity.Customer;
import gilyeon.spring.batch.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Basic;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;


    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .start(step1())
                .next(step2())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        return null;
                    }
                })
                .build();
    }


    @Bean
    public Step step2 () {
        return stepBuilderFactory.get("step2")
                .<Member, Member>chunk(3)
                .reader(jpaCursorItemReader())
                .writer()
                .build();
    }

    @Bean
    public ItemReader<Member> jpaCursorItemReader() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", "A%");

        return new JpaCursorItemReaderBuilder<Member>()
                .name("jpaItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select m from Member m where m.firstName like :firstName")
                .parameterValues(parameters)
                .build();
    }

    @Bean
    public ItemWriter<Member> itemWriter(List<Member> memberList) {
        for(Member member : memberList){
            System.out.println(member.toString());
        }
        return null;
    }


}
