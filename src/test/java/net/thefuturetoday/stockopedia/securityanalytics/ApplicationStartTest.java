package net.thefuturetoday.stockopedia.securityanalytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationStartTest {
    @Test
    public void applicationStarts() {
        SecurityAnalyticsApplication.main(new String[] {});
    }
}

