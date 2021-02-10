package net.thefuturetoday.stockopedia.securityanalytics.data;

import net.thefuturetoday.stockopedia.securityanalytics.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
