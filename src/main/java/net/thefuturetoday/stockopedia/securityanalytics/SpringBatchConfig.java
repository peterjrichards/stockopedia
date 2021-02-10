package net.thefuturetoday.stockopedia.securityanalytics;

import net.thefuturetoday.stockopedia.securityanalytics.etl.AttributeProcessor;
import net.thefuturetoday.stockopedia.securityanalytics.etl.FactProcessor;
import net.thefuturetoday.stockopedia.securityanalytics.etl.JobListener;
import net.thefuturetoday.stockopedia.securityanalytics.etl.SecurityProcessor;
import net.thefuturetoday.stockopedia.securityanalytics.etl.model.AttributeDto;
import net.thefuturetoday.stockopedia.securityanalytics.etl.model.FactDto;
import net.thefuturetoday.stockopedia.securityanalytics.etl.model.SecurityDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public FlatFileItemReader<SecurityDto> securityReader() {
        FlatFileItemReader<SecurityDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("securities.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<SecurityDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "symbol");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<SecurityDto>() {{
                setTargetType(SecurityDto.class);
            }});
        }});
        return reader;
    }

    @Bean
    public FlatFileItemReader<AttributeDto> attributeReader() {
        FlatFileItemReader<AttributeDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("attributes.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<AttributeDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "name");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<AttributeDto>() {{
                setTargetType(AttributeDto.class);
            }});
        }});
        return reader;
    }

    @Bean
    public FlatFileItemReader<FactDto> factReader() {
        FlatFileItemReader<FactDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("facts.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<FactDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("security_id", "attribute_id", "value");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<FactDto>() {{
                setTargetType(FactDto.class);
            }});
        }});
        return reader;
    }

    @Bean
    public SecurityProcessor securityProcessor() {
        return new SecurityProcessor();
    }

    @Bean
    public AttributeProcessor attributeProcessor() { return new AttributeProcessor(); }

    @Bean
    public FactProcessor factProcessor() { return new FactProcessor(); }

    @Bean
    public JdbcBatchItemWriter<SecurityDto> securityWriter() {
        JdbcBatchItemWriter<SecurityDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO security_analytics.securities (id, symbol) VALUES (:id, :symbol)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<AttributeDto> attributeWriter() {
        JdbcBatchItemWriter<AttributeDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO security_analytics.attributes (id, name) VALUES (:id, :name)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<FactDto> factWriter() {
        JdbcBatchItemWriter<FactDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO security_analytics.facts (security_id, attribute_id, value) VALUES (:securityId, :attributeId, :value)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step loadSecurities() {
        return stepBuilderFactory.get("loadSecurities")
                .<SecurityDto, SecurityDto> chunk(10)
                .reader(securityReader())
                .processor(securityProcessor())
                .writer(securityWriter())
                .build();
    }

    @Bean
    public Step loadAttributes() {
        return stepBuilderFactory.get("loadAttributes")
                .<AttributeDto, AttributeDto> chunk(10)
                .reader(attributeReader())
                .processor(attributeProcessor())
                .writer(attributeWriter())
                .build();
    }


    @Bean
    public Step loadFacts() {
        return stepBuilderFactory.get("loadFacts")
                .<FactDto, FactDto> chunk(10)
                .reader(factReader())
                .processor(factProcessor())
                .writer(factWriter())
                .build();
    }

    @Bean
    public Job importDataJob(JobListener listener){
        return jobBuilderFactory.get("importDataJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(loadSecurities())
                .next(loadAttributes())
                .next(loadFacts())
                .end()
                .build();
    }
}
