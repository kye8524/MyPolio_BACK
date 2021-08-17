package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DesignPortfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignPortfolioRepository extends CrudRepository<DesignPortfolio, Integer> {
    List<DesignPortfolio> findAllBySeq(int designPortfolio_seq);
}
