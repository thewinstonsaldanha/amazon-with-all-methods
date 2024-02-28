package com.model;



public class Category {
  private int categoryid;
  private String categoryname;
public Category(int categoryid, String categoryname) {
	super();
	this.categoryid = categoryid;
	this.categoryname = categoryname;
}
public int getCategoryid() {
	return categoryid;
}
public String getCategoryname() {
	return categoryname;
}


public void setCategoryid(int categoryid) {
	this.categoryid = categoryid;
}
public void setCategoryname(String categoryname) {
	this.categoryname = categoryname;
}
@Override
public String toString() {
	return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
}
}
