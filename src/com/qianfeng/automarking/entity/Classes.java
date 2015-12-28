package com.qianfeng.automarking.entity;

/***
 * ∞‡º∂–≈œ¢
 * 
 * @author HuangGuiZhao
 *
 */
public class Classes {
	private int id;
	private String className;
	private String userName;
	private String introduce;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Classes() {
	}

	public Classes(String className, String userName, String introduce) {
		super();
		this.className = className;
		this.userName = userName;
		this.introduce = introduce;
	}
	
	public Classes(String className, String introduce) {
		super();
		this.className = className;
		this.introduce = introduce;
	}
}
