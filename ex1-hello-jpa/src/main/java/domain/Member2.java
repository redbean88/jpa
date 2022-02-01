package domain;

import javax.persistence.*;

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
public class Member2 {

    /**
     * @Id | 직접할당
     * @GeneratedValue | 자동 생성
     * IDENTITY | 데이터베이스에 위임 , MYSQL
     * SEQUENCE | 데이터베이스 시퀀스 오브젝트 사용 ,Oracle | SequenceGenerator 필요
     * TABLE | 키 생성용 테이블 사용, 모든 DB에서 사용 | @TableGenearator 필요
     * AUTO | 방언에 따라 자동지정 | 확인 필요
     *
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;    // 타입은 숫자형

    /**
     * name | 컬럼 이름
     * unique | 유니크제약조건(비추천)
     * length | 길이
     * conlumnDefinition | 컬럼 정보를 직접 설정
     * percicion, scale | 큰 수자 사용
     */
    @Column(name = "NAME" )
    private String userName;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
