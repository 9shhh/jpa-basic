package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 애플리케이션 로딩 시점에 딱 1개만 생성되어야 함.

        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위 마다 생성 되어야함. 웹 기준, 고객의 요청이 올떄 마다 사용되고 close() 됨.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 회원 등록
            Member member = new Member();
            member.setId(13L);
            member.setName("HelloA1");
            em.persist(member);

            em.flush();

            // 회원 수정
//            Member findMember = em.find(Member.class, 10L);
//            findMember.setName("HelloJPA");

            // 회원 조회 JPQL -> 엔티티 객체를 대상으로 하는 객체지향 쿼리
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

            System.out.println("========================");
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

// flush() 호출 출력 결과 -> 커밋 전에 insert 쿼리가 나감. commit 되고 실제 db에 반영됨.
//Hibernate:
//        /* insert hellojpa.Member
//         */ insert
//        into
//        Member
//        (name, id)
//        values
//        (?, ?)
//========================
