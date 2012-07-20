package com.uptimesoftware.business.tag;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.uptimesoftware.business.tag.DescendantTagsByVisibleTagsCalculator;
import com.uptimesoftware.business.tag.TagIdTreeNode;

public class DescendantViewsByVisibleViewsCalculatorTest {

	private Set<Long> allViewIds;
	private Set<Long> visibleViewIds;
	private Set<TagIdTreeNode> viewIdTree;
	private Set<Long> calculatedDescendantViewIds;

	@Before
	public void setUp() throws Exception {
		allViewIds = Sets.newHashSet();
		visibleViewIds = Sets.newHashSet();
		viewIdTree = Sets.newHashSet();

		addVisibleView(1L, null);
		addHiddenView(2L, null);
	}

	@Test
	public void testNoChildren() {
		calculateDescendants();
		assertDescendantCount(0);
	}

	@Test
	public void testOneChild() {
		addVisibleView(3L, 1L);

		calculateDescendants();
		assertDescendantCount(1);
		assertDescendantsContain(3L);
		assertDescendantsMissing(1L);
		assertDescendantsMissing(2L);
	}

	@Test
	public void testOneGrandchild() {
		addVisibleView(3L, 1L);
		addVisibleView(4L, 3L);

		calculateDescendants();
		assertDescendantCount(2);
		assertDescendantsContain(3L);
		assertDescendantsContain(4L);
		assertDescendantsMissing(1L);
		assertDescendantsMissing(2L);
	}

	@Test
	public void testChildrenOfHidden() {
		addVisibleView(3L, 2L);
		addVisibleView(4L, 3L);

		calculateDescendants();
		assertDescendantCount(1);
		assertDescendantsContain(4L);
		assertDescendantsMissing(3L);
		assertDescendantsMissing(1L);
		assertDescendantsMissing(2L);
	}

	@Test
	public void testBiggerTreeWithHiddenViewUnderVisibleView() {
		addVisibleView(3L, 1L);
		addHiddenView(4L, 1L);
		addVisibleView(5L, 3L);
		addVisibleView(6L, 3L);
		addVisibleView(7L, 4L);
		addVisibleView(8L, 4L);

		calculateDescendants();
		assertDescendantCount(6);
		assertDescendantsContain(3L);
		assertDescendantsContain(4L);
		assertDescendantsContain(5L);
		assertDescendantsContain(6L);
		assertDescendantsContain(7L);
		assertDescendantsContain(8L);
		assertDescendantsMissing(1L);
		assertDescendantsMissing(2L);
	}

	private void addVisibleView(Long id, Long parentId) {
		allViewIds.add(id);
		visibleViewIds.add(id);
		setParent(id, parentId);
	}

	private void addHiddenView(Long id, Long parentId) {
		allViewIds.add(id);
		setParent(id, parentId);
	}

	private void setParent(Long child, Long parent) {
		final ViewIdTreeNodeImpl relationship = new ViewIdTreeNodeImpl(child, parent);
		viewIdTree.remove(relationship);
		viewIdTree.add(relationship);
	}

	private void calculateDescendants() {
		DescendantTagsByVisibleTagsCalculator calc = new DescendantTagsByVisibleTagsCalculator(viewIdTree);
		calculatedDescendantViewIds = calc.getDescendantTagIds(visibleViewIds);
	}

	private void assertDescendantCount(int expectedCount) {
		assertTrue(expectedCount == calculatedDescendantViewIds.size());
	}

	private void assertDescendantsMissing(Long expected) {
		assertFalse(calculatedDescendantViewIds.contains(expected));
	}

	private void assertDescendantsContain(Long expected) {
		assertTrue(calculatedDescendantViewIds.contains(expected));
	}

}
