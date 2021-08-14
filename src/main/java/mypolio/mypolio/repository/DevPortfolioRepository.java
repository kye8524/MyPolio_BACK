package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DevPortfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevPortfolioRepository extends CrudRepository<DevPortfolio, Integer> {
    Optional<DevPortfolio> findBySeq(int devPortfolio_seq);
}
