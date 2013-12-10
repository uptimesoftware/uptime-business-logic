package com.uptimesoftware.business.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.RoundingMode;

import org.junit.Test;

import com.google.common.math.DoubleMath;
import com.uptimesoftware.business.elementgroup.ElementGroups;

public class ElementGroupsTest {

	@Test
	public void testMyInfrastructureGroupId() {
		assertEquals(1L, ElementGroups.MY_INFRASTRUCTURE_ID.longValue());
	}

	@Test
	public void testMyInfrastructureGroupIdEquality() {
		assertEquals(ElementGroups.MY_INFRASTRUCTURE_ID.longValue(),
				DoubleMath.roundToLong(ElementGroups.MY_INFRASTRUCTURE_ID_DOUBLE, RoundingMode.UNNECESSARY));
	}

	@Test
	public void testMyInfrastructureGroupName() {
		assertEquals("My Infrastructure", ElementGroups.MY_INFRASTRUCTURE_NAME);
	}

	@Test
	public void testMyInfrastructureGroupIdIsValid() {
		assertTrue(ElementGroups.isValidId(Long.MAX_VALUE));
		assertTrue(ElementGroups.isValidId(1L));
		assertFalse(ElementGroups.isValidId(0L));
		assertFalse(ElementGroups.isValidId(-1L));
		assertFalse(ElementGroups.isValidId(Long.MIN_VALUE));
	}

}
