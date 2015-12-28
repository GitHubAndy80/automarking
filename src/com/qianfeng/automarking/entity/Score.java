package com.qianfeng.automarking.entity;

/**
 * 學生成績信息
 * 
 * @author HuangGuiZhao
 *
 */
public class Score {
	private int id;
	private String name;
	private int score;
	private int classId;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

}
