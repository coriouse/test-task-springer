package org.springer.model;

/**
 * Model for Document
 * @author ogarkov_sa
 * @since 08.01.2014
 */
public class Document {
	
	private Long id;
	private String title; 
	private String author; 
	private String watermark;
	private String type;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getWatermark() {
		return watermark;
	}
	
	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", author=" + author
				+ ", watermark=" + watermark + ", type=" + type + "]";
	}

}
