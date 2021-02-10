package net.thefuturetoday.stockopedia.securityanalytics.etl;

import net.thefuturetoday.stockopedia.securityanalytics.model.FactDto;
import org.springframework.batch.item.ItemProcessor;

public class FactProcessor implements ItemProcessor<FactDto, FactDto> {

    @Override
    public FactDto process(FactDto factDto) throws Exception {
        return factDto;
    }
}
