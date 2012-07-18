package com.uptimesoftware.business.users;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class VisibilityCalculatorTest {

	@Test
	public void nullUserIdThrows() {
		VisibilityCalculator calc = new VisibilityCalculator(null);
		try {
			calc.getEntityIds(null);
		} catch (Exception e) {
			return;
		}
		fail("getEntityIds(null) should throw");
	}

	@Test
	public void canGetEntityIds() {
		VisibilityCalculator calc = new VisibilityCalculator(new UserGroupEntityVisibility() {

			@Override
			public Set<Long> getEntityIds(Long userId) {
				if (userId == 1L) {
					return ImmutableSet.of(100L, 200L, 300L);
				}
				if (userId == 2L) {
					return ImmutableSet.of(100L, 200L);
				}
				return ImmutableSet.of();
			}
		});
		assertArrayEquals(new Long[] { 100L, 200L, 300L }, calc.getEntityIds(1L).toArray());
		assertArrayEquals(new Long[] { 100L, 200L }, calc.getEntityIds(2L).toArray());
		assertArrayEquals(new Long[] {}, calc.getEntityIds(3L).toArray());
	}

}
