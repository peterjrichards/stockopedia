package net.thefuturetoday.stockopedia.securityanalytics.etl;

import net.thefuturetoday.stockopedia.securityanalytics.model.AttributeDto;
import org.springframework.batch.item.ItemProcessor;

public class AttributeProcessor implements ItemProcessor<AttributeDto, AttributeDto> {
    @Override
    public AttributeDto process(AttributeDto attributeDto) throws Exception {
        return attributeDto;
    }
}
