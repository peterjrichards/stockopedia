package net.thefuturetoday.stockopedia.securityanalytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationStartTest {
    @Test
    public void applicationStarts() {
        SecurityAnalyticsApplication.main(new String[] {});
    }
}

