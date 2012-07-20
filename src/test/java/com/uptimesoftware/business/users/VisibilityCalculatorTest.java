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
import com.uptimesoftware.business.tag.ViewIdTreeNodeImpl;

public class VisibilityCalculatorTest {

	@Test
	public void nullUserIdThrows() {
		VisibilityCalculator calc = new VisibilityCalculator(null, null);
		try {
			calc.getElementIds(null);
		} catch (Exception e) {
			return;
		}
		fail("getEntityIds(null) should throw");
	}

	@Test
	public void noGroupsOrViews() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, NoViews);
		assertArrayEquals(new Long[] {}, entities(calc, user(1)));
		assertArrayEquals(new Long[] {}, entities(calc, user(2)));
		assertArrayEquals(new Long[] {}, entities(calc, user(3)));
		assertArrayEquals(new Long[] {}, entities(calc, user(4)));
	}

	@Test
	public void groups() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, NoViews);
		assertArrayEquals(new Long[] { entity(1), entity(2), entity(3) }, entities(calc, user(1)));
		assertArrayEquals(new Long[] { entity(1), entity(2) }, entities(calc, user(2)));
		assertArrayEquals(new Long[] {}, entities(calc, user(3)));
		assertArrayEquals(new Long[] {}, entities(calc, user(4)));
	}

	@Test
	public void views() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, SimpleViews);
		assertArrayEquals(new Long[] {}, entities(calc, user(1)));
		assertArrayEquals(new Long[] {}, entities(calc, user(2)));
		assertArrayEquals(new Long[] { entity(4) }, entities(calc, user(3)));
		assertArrayEquals(new Long[] {}, entities(calc, user(4)));
	}

	@Test
	public void groupsAndViews() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, SimpleViews);
		assertArrayEquals(new Long[] { entity(1), entity(2), entity(3) }, entities(calc, user(1)));
		assertArrayEquals(new Long[] { entity(1), entity(2) }, entities(calc, user(2)));
		assertArrayEquals(new Long[] { entity(4) }, entities(calc, user(3)));
		assertArrayEquals(new Long[] {}, entities(calc, user(4)));
	}

	@Test
	public void complexViews() {
		VisibilityCalculator calc = new VisibilityCalculator(NoGroups, ComplexViews);
		assertArrayEquals(new Long[] { entity(4) }, entities(calc, user(1)));
		assertArrayEquals(new Long[] {}, entities(calc, user(2)));
	}

	@Test
	public void simpleGroupsAndComplexViews() {
		VisibilityCalculator calc = new VisibilityCalculator(SimpleGroups, ComplexViews);
		assertArrayEquals(new Long[] { entity(1), entity(2), entity(3), entity(4) }, entities(calc, user(1)));
		assertArrayEquals(new Long[] { entity(1), entity(2) }, entities(calc, user(2)));
	}

	private static final Long[] entities(VisibilityCalculator calc, Long userId) {
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

	private static final long view(long id) {
		return id * 10L;
	}

	private static final long entity(long id) {
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
				return ImmutableSet.of(entity(1), entity(2), entity(3));
			}
			if (userId == user(2)) {
				return ImmutableSet.of(entity(1), entity(2));
			}
			return ImmutableSet.of();
		}
	};

	private static final TagIdData NoViews = new TagIdData() {

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

	private static final TagIdData SimpleViews = new TagIdData() {

		@Override
		public Set<Long> findVisibleTagIds(Long userId) {
			if (userId == user(3)) {
				return ImmutableSet.of(view(1));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new ViewIdTreeNodeImpl(view(1), null));
		}

		@Override
		public Multimap<Long, Long> findAllElementIdsByTagIds() {
			return ImmutableMultimap.of(view(1), entity(4));
		}
	};

	private static final TagIdData ComplexViews = new TagIdData() {

		@Override
		public Set<Long> findVisibleTagIds(Long userId) {
			if (userId == user(1)) {
				return ImmutableSet.of(view(2));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new ViewIdTreeNodeImpl(view(1), null), new ViewIdTreeNodeImpl(view(2),
					view(1)), new ViewIdTreeNodeImpl(view(3), view(2)));
		}

		@Override
		public Multimap<Long, Long> findAllElementIdsByTagIds() {
			return ImmutableMultimap.of(view(3), entity(4));
		}
	};

}
