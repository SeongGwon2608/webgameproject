package com.naver.insa8029.domain;

import lombok.Data;

@Data
public class UserInfo {
	private String nickname;
	private String userpw;
	private String email;
	private int money;
	private int userlevel;
	private int userexp;
	private boolean userdel;
	private String profileimage;
	private int heal;
	private int shop;
}
