import RoleType.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Member") // JPA가 관리하는 객체
/**
 * 제약 조건 이름 설정
 */
@Table(name = "Member")
/**
 * @SequenceGenerator(
 *                  name = [시퀀스 이름]
 *                  squenceName = [매핑할 데이터베이스 시퀀스 이름]
 *                  initialvalue= 1 , allocationSize = 1
 * )
 */
@SequenceGenerator(
        name = "member_seq_gen"
        ,sequenceName = "member_seq"
)
public class Member {

    /**
     * @Id | 직접할당
     * @GeneratedValue | 자동 생성
     * IDENTITY | 데이터베이스에 위임 , MYSQL
     * SEQUENCE | 데이터베이스 시퀀스 오브젝트 사용 ,Oracle | SequenceGenerator 필요
     * TABLE | 키 생성용 테이블 사용, 모든 DB에서 사용 | @TableGenearator 필요
     * AUTO | 방언에 따라 자동지정 | 확인 필요
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "member_seq_gen")
    private Long id;    // 타입은 숫자형

    /**
     * name | 컬럼 이름
     * unique | 유니크제약조건(비추천)
     * length | 길이
     * conlumnDefinition | 컬럼 정보를 직접 설정
     * percicion, scale | 큰 수자 사용
     */
    @Column(name = "name" )
    private String userName;
    private int age;

    /**
     * EnumType.ORDIANL | enum 순서를 저장 ( 비추천)
     * EnumType.STRING | enum 이름을 저장
     */
    @Enumerated(EnumType.STRING)
    private RoleType reoleType;

    /**
     * LocalDate , LocalDateTime 사용시 생략 가능
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * 문자는 CLOb
     * 나머지는 Blob
     */
    @Lob
    private String description;

    @Transient // 생성 제외
    private String temp;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.userName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }
}
