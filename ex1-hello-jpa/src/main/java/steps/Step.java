package steps;

import javax.persistence.EntityManager;

public interface Step {
    void logic(EntityManager em);
}
