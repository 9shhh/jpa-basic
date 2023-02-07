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
            // 회원 등록
            // Member member = new Member();
            // member.setId(13L);
            // member.setUsername("HelloA1");
            // em.persist(member);

            // 회원 수정
            Member findMember = em.find(Member.class, 10L);
            findMember.setUsername("HelloJPA111");

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
