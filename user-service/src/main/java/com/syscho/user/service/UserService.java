package com.syscho.user.service;

import java.util.List;

import com.syscho.user.vo.AlbumVO;
import com.syscho.user.vo.UserVO;

public interface UserService extends AlbumServiceClient {

	boolean createUser(UserVO userVO);

	UserVO updateUser(UserVO userVO);

	List<UserVO> getUsers();

	UserVO getUserByName(String userName);

	List<AlbumVO> getUserAlbums(String userId);

}
