package org.exoplatform.model;

public class Picture {
	private String name;
	private String path;
	private String title;
	
	public Picture(String name, String path, String title) {
		super();
		this.name = name;
		this.path = path;
		this.title = title;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
