package eki.ekilex.data;

import eki.common.constant.ReferenceOwner;
import eki.common.constant.ReferenceType;
import eki.common.data.AbstractDataObject;

public class SourceLink extends AbstractDataObject {

	private static final long serialVersionUID = 1L;

	private ReferenceOwner owner;

	private Long ownerId;

	private Long id;

	private ReferenceType type;

	private String name;

	private String value;

	private Long sourceId;

	public ReferenceOwner getOwner() {
		return owner;
	}

	public void setOwner(ReferenceOwner owner) {
		this.owner = owner;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReferenceType getType() {
		return type;
	}

	public void setType(ReferenceType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

}
