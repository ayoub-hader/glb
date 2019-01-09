package fr.cfdt.gasel.groupeldap.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateLdapBatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job updateLdapJob;

//    @Scheduled(cron = "${cron.expression}")
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(updateLdapJob, new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters());
    }
}
