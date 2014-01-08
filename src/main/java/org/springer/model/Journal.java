package org.springer.model;

/**
 * Model for Journal
 * @author ogarkov_sa
 * @since 08.01.2014
 */
public class Journal extends Document {
	
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Journal [content=" + content + ", getId()=" + getId()
				+ ", getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor()
				+ ", getWatermark()=" + getWatermark() + ", getType()="
				+ getType() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	
	
	

}
