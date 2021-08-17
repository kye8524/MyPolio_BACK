package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DevExperience;
import mypolio.mypolio.entity.DevPortfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevExperienceRepository extends CrudRepository<DevExperience, Integer> {
    Optional<DevExperience> findAllByDevPortfolio(DevPortfolio devPortfolio);
}
