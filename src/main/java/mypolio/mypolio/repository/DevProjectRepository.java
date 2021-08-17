package mypolio.mypolio.repository;

import mypolio.mypolio.entity.DevPortfolio;
import mypolio.mypolio.entity.DevProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DevProjectRepository extends CrudRepository<DevProject, Integer> {
    List<DevProject> findAllByDevPortfolio(DevPortfolio devPortfolio);

}
