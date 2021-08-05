package mypolio.mypolio.repository;

import mypolio.mypolio.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Member findByemail(String email);
}
