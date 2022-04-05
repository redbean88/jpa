package study.datajap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajap.domain.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
