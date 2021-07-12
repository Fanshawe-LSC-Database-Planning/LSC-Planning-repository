
package com.fanshawe.nfttracker.api.request;

public class SectionRequest {
	Long sectionId;
	Long sectionName;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getSectionName() {
		return sectionName;
	}

	public void setSectionName(Long sectionName) {
		this.sectionName = sectionName;
	}

	public String toString() {
		return "Section [sectionId=" + sectionId + ", sectionName=" + sectionName + "]";
	}
}
