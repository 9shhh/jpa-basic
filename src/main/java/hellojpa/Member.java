package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME"
//            insertable = false, // insert 시 해당 컬럼 미 반영
//            updatable = false // update 시 해당 컬럼 미 반영  => insert, update 둘다 false 이므로 읽기 전용 컬럼인 셈.
    )
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

    private Integer age;

    @Enumerated(EnumType.STRING)
    // EnumType.ORDINAL 사용에 주의. => 숫자 값으로 저장되는데 중간에 enum 값이 추가되면,
    // 기존 데이터의 의미가 변경될 수 있음. ex) 0: 관리자, 1: 유저 -> 변경 후) 0: 관리자, 1: VIP, 2: 일반 유저. 기존 일반 유저가 갑자기 VIP 가 되버림.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
