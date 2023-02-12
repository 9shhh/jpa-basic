package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 애플리케이션 로딩 시점에 딱 1개만 생성되어야 함.

        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위 마다 생성 되어야함. 웹 기준, 고객의 요청이 올떄 마다 사용되고 close() 됨.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            // 회원 조회
            Member findMember = em.find(Member.class, member.getId());

            // 팀 조회(회원과 연관관계가 없음)
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);

            // ******* 위 코드는 객체지향답지 않은 방식. ******* //

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // 애플리케이션이 종료되면 닫아줘야함.
        emf.close();
    }
}
