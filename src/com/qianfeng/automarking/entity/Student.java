package com.qianfeng.automarking.entity;

public class Student {
	private int id;
	private String name;
	private int age;
	private String phone;
	private int errors;
	private int rights;
	private String className;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Student() {
		super();
	}

	public Student(String name, int age, String phone,String className) {
		super();
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.className = className;
	}

	public Student(String name, String className) {
		this.setName(name);
		this.setClassName(className);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
