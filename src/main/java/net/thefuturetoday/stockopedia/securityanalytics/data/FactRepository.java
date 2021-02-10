package net.thefuturetoday.stockopedia.securityanalytics.data;

import net.thefuturetoday.stockopedia.securityanalytics.model.Fact;
import net.thefuturetoday.stockopedia.securityanalytics.model.FactIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepository extends JpaRepository<Fact, FactIdentity> {
}
