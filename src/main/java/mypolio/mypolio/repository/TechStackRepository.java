package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DevPortfolio;
import mypolio.mypolio.entity.DevTechStack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechStackRepository extends CrudRepository<DevTechStack, Integer> {
    List<DevTechStack> findAllByDevPortfolio(DevPortfolio devPortfolio);
}
