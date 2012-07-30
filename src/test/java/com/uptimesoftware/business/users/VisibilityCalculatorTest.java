package com.uptimesoftware.business.users;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.uptimesoftware.business.tag.TagIdData;
import com.uptimesoftware.business.tag.TagIdTreeNode;
import com.uptimesoftware.business.tag.TagIdTreeNodeImpl;

public class VisibilityCalculatorTest {

	@Test
	public void nullUserIdThrows() {
		VisibilityCalculator calc = new VisibilityCalculator(null, null);
		try {
			calc.getElementIds(null);
		} catch (Exception e) {
			return;
		}
		fail("getelementIds(null) should throw");
	}

	@Test
	public void noGroupsOrtags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, NoTags);
		assertArrayEquals(new Long[] {}, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
		assertArrayEquals(new Long[] {}, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void groups() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, NoTags);
		assertArrayEquals(new Long[] { element(1), element(2), element(3) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(1), element(2) }, elements(calc, user(2)));
		assertArrayEquals(new Long[] {}, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void tags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, SimpleTags);
		assertArrayEquals(new Long[] {}, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void groupsAndtags() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, SimpleTags);
		assertArrayEquals(new Long[] { element(1), element(2), element(3) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(1), element(2) }, elements(calc, user(2)));
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void complextags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, ComplexTags);
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
	}

	@Test
	public void simpleGroupsAndComplextags() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, ComplexTags);
		assertArrayEquals(new Long[] { element(1), element(2), element(3), element(4) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(1), element(2) }, elements(calc, user(2)));
	}

	private static final Long[] elements(VisibilityCalculator calc, Long userId) {
		return toArray(calc.getElementIds(user(userId)));
	}

	private static final Long[] toArray(Set<Long> set) {
		final Long[] array = set.toArray(new Long[0]);
		Arrays.sort(array);
		return array;
	}

	private static final long user(long id) {
		return id;
	}

	private static final long tag(long id) {
		return id * 10L;
	}

	private static final long element(long id) {
		return id * 100L;
	}

	private static final UserGroupElementVisibilityData NoGroups = new UserGroupElementVisibilityData() {

		@Override
		public Set<Long> getElementIds(Long userId) {
			return ImmutableSet.of();
		}
	};

	private static final UserGroupElementVisibilityData SimpleGroups = new UserGroupElementVisibilityData() {

		@Override
		public Set<Long> getElementIds(Long userId) {
			if (userId == user(1)) {
				return ImmutableSet.of(element(1), element(2), element(3));
			}
			if (userId == user(2)) {
				return ImmutableSet.of(element(1), element(2));
			}
			return ImmutableSet.of();
		}
	};

	private static final TagIdData NoTags = new TagIdData() {

		@Override
		public Set<Long> findVisibleTagIds(Long userId) {
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of();
		}

		@Override
		public Multimap<Long, Long> findAllElementIdsByTagIds() {
			return ImmutableMultimap.of();
		}
	};

	private static final TagIdData SimpleTags = new TagIdData() {

		@Override
		public Set<Long> findVisibleTagIds(Long userId) {
			if (userId == user(3)) {
				return ImmutableSet.of(tag(1));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new TagIdTreeNodeImpl(tag(1), null));
		}

		@Override
		public Multimap<Long, Long> findAllElementIdsByTagIds() {
			return ImmutableMultimap.of(tag(1), element(4));
		}
	};

	private static final TagIdData ComplexTags = new TagIdData() {

		@Override
		public Set<Long> findVisibleTagIds(Long userId) {
			if (userId == user(1)) {
				return ImmutableSet.of(tag(2));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new TagIdTreeNodeImpl(tag(1), null), new TagIdTreeNodeImpl(tag(2), tag(1)),
					new TagIdTreeNodeImpl(tag(3), tag(2)));
		}

		@Override
		public Multimap<Long, Long> findAllElementIdsByTagIds() {
			return ImmutableMultimap.of(tag(3), element(4));
		}
	};

}
