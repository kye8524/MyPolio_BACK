package mypolio.mypolio.repository;


import mypolio.mypolio.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findBySeq(int userSeq);
}
