package net.thefuturetoday.stockopedia.securityanalytics.data;

import net.thefuturetoday.stockopedia.securityanalytics.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {
}
