package com.uptimesoftware.business.visibility;

import com.google.common.base.Objects;
import com.uptimesoftware.business.visibility.TagIdTreeNode;

public final class TagIdTreeNodeImpl implements TagIdTreeNode {

	private final Long id;
	private final Long parentId;

	public TagIdTreeNodeImpl(Long id, Long parentId) {
		this.id = id;
		this.parentId = parentId;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long getParentId() {
		return parentId;
	}

	@Override
	public int hashCode() {
		return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TagIdTreeNodeImpl other = (TagIdTreeNodeImpl) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("parentId", parentId).toString();
	}

}