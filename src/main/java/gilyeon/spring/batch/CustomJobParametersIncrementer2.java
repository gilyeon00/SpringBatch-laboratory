package gilyeon.spring.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJobParametersIncrementer2 implements JobParametersIncrementer {

    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
    @Override
    public JobParameters getNext(JobParameters parameters) {
        String id = dateFormat.format(new Date());
        String name = "jobjobB";
        return new JobParametersBuilder()
                .addString("keyB", id)
                .addString("nameB", name)
                .toJobParameters();

    }
}
