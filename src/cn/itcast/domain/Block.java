package cn.itcast.domain;

import java.util.Date;

public class Block {
	private Integer id;
	private String userId;
	private String information1;
	private String information2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInformation1() {
		return information1;
	}

	public void setInformation1(String information1) {
		this.information1 = information1;
	}

	public String getInformation2() {
		return information2;
	}

	public void setInformation2(String information2) {
		this.information2 = information2;
	}
}
