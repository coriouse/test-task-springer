package org.springer.model;

/**
 * Model for Book
 * @author ogarkov_sa
 * @since 08.01.2014
 */
public class Book extends Document {
	
	private String topic;
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Book [topic=" + topic + ", content=" + content + ", getId()="
				+ getId() + ", getTitle()=" + getTitle() + ", getAuthor()="
				+ getAuthor() + ", getWatermark()=" + getWatermark()
				+ ", getType()=" + getType() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}





	
	
	

	
	
	
	

}
