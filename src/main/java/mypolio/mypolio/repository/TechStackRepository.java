package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DevPortfolio;
import mypolio.mypolio.entity.TechStack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechStackRepository extends CrudRepository<TechStack, Integer> {
    List<TechStack> findAllByDevPortfolio(DevPortfolio devPortfolio);
}
