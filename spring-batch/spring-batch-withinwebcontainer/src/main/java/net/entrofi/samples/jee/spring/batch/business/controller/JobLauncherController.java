package net.entrofi.samples.jee.spring.batch.business.controller;


import net.entrofi.samples.jee.spring.batch.business.batch.TagMappingItem;
import net.entrofi.samples.jee.spring.batch.business.model.ProductTag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {



    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ItemReader reader;

    @Autowired
    private ItemWriter writer;

    @Autowired
    private ItemProcessor processor;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private ThreadPoolTaskScheduler scheduler;


    @GetMapping(value = "/handle")
    public String handle() throws Exception{
        ((FlatFileItemReader) reader).setResource(new ClassPathResource("tags.txt"));

        Runnable job = () -> {
            try {
                jobLauncher.run(importTags(), new JobParameters());
            } catch (JobExecutionAlreadyRunningException e) {
                e.printStackTrace();
            } catch (JobRestartException e) {
                e.printStackTrace();
            } catch (JobInstanceAlreadyCompleteException e) {
                e.printStackTrace();
            } catch (JobParametersInvalidException e) {
                e.printStackTrace();
            }

        };
        scheduler.submit(job);
        return "success";
    }


    private Job importTags() {
        Step importTagStep = importTagStep(stepBuilderFactory, reader, writer, processor);
        return jobBuilderFactory.get("importTags")
                .incrementer(new RunIdIncrementer())
                .flow(importTagStep)
                .end()
                .build();
    }


    private Step importTagStep(StepBuilderFactory stepBuilderFactory, ItemReader reader,
                              ItemWriter writer, ItemProcessor processor) {
        return stepBuilderFactory.get("importTagStep")
                .<TagMappingItem, ProductTag>chunk(2000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
