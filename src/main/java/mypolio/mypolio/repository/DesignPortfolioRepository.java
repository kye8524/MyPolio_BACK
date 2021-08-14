package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DesignPortfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignPortfolioRepository extends CrudRepository<DesignPortfolio, Integer> {

}
