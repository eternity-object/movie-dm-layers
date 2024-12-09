package org.eternity.domainmodel.persistence;

import jakarta.persistence.EntityManager;
import org.eternity.domainmodel.generic.Money;
import org.eternity.domainmodel.movie.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
public class JpaInheritanceTest {
	@Autowired
	private EntityManager em;

	@Test
	public void hierarchy_load() {
		em.persist(new Movie("aa",11, Money.wons(10000),
				new AmountDiscountPolicy(Money.wons(1000), Set.of())));
		em.flush();
		em.clear();

		Movie m = em.createQuery("select m from Movie m", Movie.class).getSingleResult();
		m.getDiscountPolicy().getConditions().size();
		em.flush();
	}
}
