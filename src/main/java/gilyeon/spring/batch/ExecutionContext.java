package gilyeon.spring.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContext implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("========ExecutionContext===========");

        org.springframework.batch.item.ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        org.springframework.batch.item.ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();


        String jobName = contribution.getStepExecution().getJobExecution().getJobInstance().getJobName();
        String stepName = contribution.getStepExecution().getStepName();

        if(jobExecutionContext.get("jobName")==null){
            jobExecutionContext.put("jobName",jobName);
        }
        if(stepExecutionContext.get("stepName") == null){
            stepExecutionContext.put("stepName", stepName);
        }
        return null;
    }
}
