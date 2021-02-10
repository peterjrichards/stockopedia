package net.thefuturetoday.stockopedia.securityanalytics.etl;

import net.thefuturetoday.stockopedia.securityanalytics.etl.model.SecurityDto;
import org.springframework.batch.item.ItemProcessor;

public class SecurityProcessor implements ItemProcessor<SecurityDto, SecurityDto> {
    @Override
    public SecurityDto process(SecurityDto securityDto) throws Exception {
        return securityDto;
    }
}
