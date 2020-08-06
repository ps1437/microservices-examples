package com.syscho.user.vo;

import java.util.List;

import lombok.Data;

@Data
public class UserVO {

	private String userName;
	private String emailId;
	private String password;
	private String dob;
	private String gender;

	private List<AlbumVO> albums;
}
