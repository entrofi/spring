package net.entrofi.samples.jee.spring.batch.business.config;


import net.entrofi.samples.jee.spring.batch.business.batch.TagMappingItem;
import net.entrofi.samples.jee.spring.batch.business.batch.TagMappingItemProcessor;
import net.entrofi.samples.jee.spring.batch.business.model.ProductTag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class BatchConfigurer {


    @Autowired
    private TagMappingItemProcessor processor;


    @Bean
    public ItemWriter itemWriter(@Autowired EntityManagerFactory entityManagerFactory) {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public ItemReader reader() {
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new ClassPathResource("tags.txt"));
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames(new String[] {"tagName", "productId", "productName"});
                }
                });

                setFieldSetMapper(new BeanWrapperFieldSetMapper<TagMappingItem>() {{
                    setTargetType(TagMappingItem.class);
                }});
            }
        });
        return reader;
    }

    @Bean
    public Job importTags(JobBuilderFactory jobBuilderFactory, Step importTagStep) {
        return jobBuilderFactory.get("importTags")
                .incrementer(new RunIdIncrementer())
                .flow(importTagStep)
                .end()
                .build();
    }

    @Bean
    public Step importTagStep(StepBuilderFactory stepBuilderFactory, ItemReader reader,
                              ItemWriter writer, ItemProcessor processor) {
        return stepBuilderFactory.get("importTagStep")
                .<TagMappingItem, ProductTag>chunk(1000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
