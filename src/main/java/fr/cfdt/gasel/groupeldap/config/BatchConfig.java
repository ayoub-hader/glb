package fr.cfdt.gasel.groupeldap.config;

import fr.cfdt.gasel.groupeldap.batch.UpdateLdapBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public UpdateLdapBatch updateLdapBatch() {
        return new UpdateLdapBatch();
    }

    @Bean
    public Step updateLdap(){
        return steps.get("updateLdap")
                .tasklet(updateLdapBatch())
                .build();
    }

    @Bean
    public Job updateLdapJob(){
        return jobs.get("updateLdapJob")
                .incrementer(new RunIdIncrementer())
                .start(updateLdap())
                .build();
    }
}
