package com.model;



public class Products {
    private int productid;
    private String productname;
    private int productprice;
    private String productdescription;
    private int categoryid;
    private String productimage;
    
    
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Products(int productid, String productname, int productprice, String productdescription, int categoryid,
			String productimage) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productprice = productprice;
		this.productdescription = productdescription;
		this.categoryid = categoryid;
		this.productimage = productimage;
	}
	public int getProductid() {
		return productid;
	}
	public String getProductname() {
		return productname;
	}
	public int getProductprice() {
		return productprice;
	}
	public String getProductdescription() {
		return productdescription;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public String getProductimage() {
		return productimage;
	}
	
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}
	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productname=" + productname + ", productprice=" + productprice
				+ ", productdescription=" + productdescription + ", categoryid=" + categoryid + ", productimage="
				+ productimage + "]";
	}

  
}
