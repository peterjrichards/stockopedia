package net.thefuturetoday.stockopedia.securityanalytics.etl;

import net.thefuturetoday.stockopedia.securityanalytics.etl.model.AttributeDto;
import net.thefuturetoday.stockopedia.securityanalytics.etl.model.FactDto;
import net.thefuturetoday.stockopedia.securityanalytics.etl.model.SecurityDto;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Securities...");
            List<SecurityDto> securities = jdbcTemplate.query("SELECT id,symbol FROM security_analytics.securities",
                    (rs, rowNum)-> new SecurityDto(rs.getInt(1), rs.getString(2)));
            securities.forEach(System.out::println);

            System.out.println("Attributes...");
            List<AttributeDto> attributes = jdbcTemplate.query("SELECT id,name FROM security_analytics.attributes",
                    (rs, rowNum)-> new AttributeDto(rs.getInt(1), rs.getString(2)));
            attributes.forEach(System.out::println);

            System.out.println("Facts...");
            List<FactDto> facts = jdbcTemplate.query("SELECT security_id,attribute_id,value FROM security_analytics.facts",
                    (rs, rowNum)-> new FactDto(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3)));
            facts.forEach(System.out::println);
        }
    }
}
