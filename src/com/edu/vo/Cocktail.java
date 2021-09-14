package com.edu.vo;

/** 식품 정보 */
public class Cocktail {
	protected int cid;
	protected String cname;
	protected String base;
	protected int degree;
	protected String description;
	protected String method;
	protected String material;
	protected String imageURL;
	
	
	//CID, CNAME, BASE, DEGREE, DESCRIPTION, METHOD, MATERIAL, IMAGEURL
	public Cocktail() {
	}
	public Cocktail(int cid, String cname, String base, int degree, String description, String method, String material,
			String imageURL) {
		this.cid = cid;
		this.cname = cname;
		this.base = base;
		this.degree = degree;
		this.description = description;
		this.method = method;
		this.material = material;
		this.imageURL = imageURL;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Override
	public String toString() {
		return "Cocktail [cid=" + cid + ", cname=" + cname + ", base=" + base + ", degree=" + degree + ", description="
				+ description + ", method=" + method + ", material=" + material + ", imageURL=" + imageURL + "]";
	}
	
}
