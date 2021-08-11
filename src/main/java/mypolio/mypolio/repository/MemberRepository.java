package mypolio.mypolio.repository;


import mypolio.mypolio.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    public Member findByEmail(String email);
}
