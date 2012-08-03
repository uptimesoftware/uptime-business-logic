package com.uptimesoftware.business.users;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.uptimesoftware.business.visibility.PermissionChecker;
import com.uptimesoftware.business.visibility.TagFinder;
import com.uptimesoftware.business.visibility.TagIdTreeNode;
import com.uptimesoftware.business.visibility.TagIdTreeNodeImpl;
import com.uptimesoftware.business.visibility.UserGroupFinder;
import com.uptimesoftware.business.visibility.VisibilityCalculator;

public class VisibilityCalculatorTest {

	@Test
	public void nullUserIdThrows() {
		VisibilityCalculator calc = new VisibilityCalculator(null, null, null);
		try {
			calc.getElementIds(null);
		} catch (Exception e) {
			return;
		}
		fail("getelementIds(null) should throw");
	}

	@Test
	public void noGroupsOrTags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, NoGroups, NoTags);
		assertArrayEquals(new Long[] {}, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
		assertArrayEquals(new Long[] {}, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void groups() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, SimpleGroups, NoTags);
		assertArrayEquals(new Long[] { element(1), element(2), element(3) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(1), element(2) }, elements(calc, user(2)));
		assertArrayEquals(new Long[] {}, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void tags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, NoGroups, SimpleTags);
		assertArrayEquals(new Long[] {}, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void groupsAndTags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, SimpleGroups, SimpleTags);
		assertArrayEquals(new Long[] { element(1), element(2), element(3) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(1), element(2) }, elements(calc, user(2)));
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(3)));
		assertArrayEquals(new Long[] {}, elements(calc, user(4)));
	}

	@Test
	public void complexTags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, NoGroups, ComplexTags);
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] {}, elements(calc, user(2)));
	}

	@Test
	public void complexTagsWithAdmin() {
		VisibilityCalculator calc = new VisibilityCalculator(User2Admin, NoGroups, ComplexTags);
		assertArrayEquals(new Long[] { element(4) }, elements(calc, user(1)));
		assertArrayEquals(new Long[] { element(4), element(5) }, elements(calc, user(2)));
	}

	@Test
	public void simpleGroupsAndComplexTags() {
		VisibilityCalculator calc = new VisibilityCalculator(NoAdmins, SimpleGroups, ComplexTags);
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

	private static final long userGroup(long id) {
		return id * 1000L;
	}

	private static final PermissionChecker NoAdmins = new PermissionChecker() {

		@Override
		public boolean hasAdministratorPermission(Long userId) {
			return false;
		}

	};

	private static final PermissionChecker User2Admin = new PermissionChecker() {

		@Override
		public boolean hasAdministratorPermission(Long userId) {
			return userId == user(2);
		}

	};

	private static final UserGroupFinder NoGroups = new UserGroupFinder() {

		@Override
		public Set<Long> findUserGroupIds(Long userId) {
			return ImmutableSet.of();
		}

		@Override
		public Set<Long> findElementIds(Set<Long> userGroupIds) {
			return ImmutableSet.of();
		}
	};

	private static final UserGroupFinder SimpleGroups = new UserGroupFinder() {

		@Override
		public Set<Long> findUserGroupIds(Long userId) {
			if (userId == user(1)) {
				return ImmutableSet.of(userGroup(1), userGroup(2));
			}
			if (userId == user(2)) {
				return ImmutableSet.of(userGroup(1));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<Long> findElementIds(Set<Long> userGroupIds) {
			Set<Long> elementIds = Sets.newHashSet();
			if (userGroupIds.contains(userGroup(1))) {
				elementIds.add(element(1));
				elementIds.add(element(2));
			}
			if (userGroupIds.contains(userGroup(2))) {
				elementIds.add(element(3));
			}
			return ImmutableSet.copyOf(elementIds);
		}
	};

	private static final TagFinder NoTags = new TagFinder() {

		@Override
		public Set<Long> findTagIds(Long userId) {
			return ImmutableSet.of();
		}

		@Override
		public Set<Long> findAllTagIds() {
			return ImmutableSet.of();
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of();
		}

		@Override
		public Set<Long> findElementIds(Set<Long> tagIds) {
			return ImmutableSet.of();
		}
	};

	private static final TagFinder SimpleTags = new TagFinder() {

		@Override
		public Set<Long> findTagIds(Long userId) {
			if (userId == user(3)) {
				return ImmutableSet.of(tag(1));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<Long> findAllTagIds() {
			return ImmutableSet.of(tag(1));
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new TagIdTreeNodeImpl(tag(1), null));
		}

		@Override
		public Set<Long> findElementIds(Set<Long> tagIds) {
			Set<Long> elementIds = Sets.newHashSet();
			if (tagIds.contains(tag(1))) {
				elementIds.add(element(4));
			}
			return ImmutableSet.copyOf(elementIds);
		}
	};

	private static final TagFinder ComplexTags = new TagFinder() {

		@Override
		public Set<Long> findTagIds(Long userId) {
			if (userId == user(1)) {
				return ImmutableSet.of(tag(2));
			}
			return ImmutableSet.of();
		}

		@Override
		public Set<Long> findAllTagIds() {
			return ImmutableSet.of(tag(1), tag(2), tag(3), tag(4));
		}

		@Override
		public Set<TagIdTreeNode> findAllTagIdTreeNodes() {
			return ImmutableSet.<TagIdTreeNode> of(new TagIdTreeNodeImpl(tag(1), null), new TagIdTreeNodeImpl(tag(2), tag(1)),
					new TagIdTreeNodeImpl(tag(3), tag(2)));
		}

		@Override
		public Set<Long> findElementIds(Set<Long> tagIds) {
			Set<Long> elementIds = Sets.newHashSet();
			if (tagIds.contains(tag(3))) {
				elementIds.add(element(4));
			}
			if (tagIds.contains(tag(4))) {
				elementIds.add(element(5));
			}
			return ImmutableSet.copyOf(elementIds);
		}
	};

}
