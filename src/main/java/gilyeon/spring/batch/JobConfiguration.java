package gilyeon.spring.batch;

import gilyeon.spring.batch.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job parentJob() {
        return jobBuilderFactory.get("parentJob")
                .start(step1())
//                .next(step2())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(3)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }
    private ItemReader<Customer> itemReader() {
        return new CustomItemReader(Arrays.asList(new Customer("user1"),
                new Customer("user2"),
                new Customer("user3")));
    }

    private ItemProcessor<? super Customer, ? extends Customer> itemProcessor() {
        return new CustomItemProcessor();
    }

    private ItemWriter<? super Customer> itemWriter() {
        return new CustomItemWriter();
    }




//    @Bean
//    public Step step2 () {
//        return stepBuilderFactory.get("step2")
//                .<String, String>chunk(5)
//                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4")))
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String inputItem) throws Exception {
//                        Thread.sleep(300);
//                        System.out.println("item = " + inputItem);
//                        return "my " + inputItem;
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(List<? extends String> outputItems) throws Exception {
//                        Thread.sleep(300);
//                        System.out.println("items = " + outputItems);
//                    }
//                })
//                .build();
//    }


}
