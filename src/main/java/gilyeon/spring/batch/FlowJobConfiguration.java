//package gilyeon.spring.batch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class FlowJobConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job flowJob(){
//        return jobBuilderFactory.get("myFlowJob")
//                .start(myFlow1())
//                    .on("*")
//                    .to(step5())
//                .from(myFlow1())
//                    .on("COMPLETED")
//                    .to(myFlow3())
//                .next(step3())
//                .end()
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
//
//    @Bean
//    public Flow myFlow1(){
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myFlow1");
//        flowBuilder.start(step1())
//                .next(step2())
//                .end();
//
//        return flowBuilder.build();
//    }
//
//    @Bean
//    public Flow myFlow2(){
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myFlow2");
//        flowBuilder.start(myFlow1())
//                .next(step5())
//                .end();
//
//        return flowBuilder.build();
//    }
//
//    @Bean
//    public Flow myFlow3(){
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myFlow3");
//        flowBuilder.start(step3())
//                .next(step4())
//                .end();
//
//        return flowBuilder.build();
//    }
//
//
//
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("==============step1 executed=============");
////                        contribution.setExitStatus(ExitStatus.FAILED);
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2(){
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("==============step2 executed=============");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3(){
//        return stepBuilderFactory.get("step3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("==============step3 executed=============");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step4(){
//        return stepBuilderFactory.get("step4")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("==============step4 executed=============");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//
//    @Bean
//    public Step step5(){
//        return stepBuilderFactory.get("step5")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("==============step5 executed=============");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//
//}
