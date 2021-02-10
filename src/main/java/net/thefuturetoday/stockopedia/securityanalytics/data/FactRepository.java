package net.thefuturetoday.stockopedia.securityanalytics.data;

import net.thefuturetoday.stockopedia.securityanalytics.model.Fact;
import net.thefuturetoday.stockopedia.securityanalytics.model.FactIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactRepository extends JpaRepository<Fact, FactIdentity> {

    @Query("select f from Fact f inner join Security as s on f.id.security = s inner join Attribute as a on f.id.attribute = a where s.symbol =:securitySymbol and a.name = :attributeName")
    Optional<Fact> getFact(String securitySymbol, String attributeName);
}
