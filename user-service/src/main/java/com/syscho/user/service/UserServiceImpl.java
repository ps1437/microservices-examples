package com.syscho.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.syscho.user.dao.UserRepository;
import com.syscho.user.entity.UserDO;
import com.syscho.user.utils.CommonUtils;
import com.syscho.user.vo.AlbumVO;
import com.syscho.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private AlbumServiceClient albumServiceClient;

	private UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean createUser(UserVO userVO) {
		UserDO userDO = new UserDO();
		userDO.setEncryptedPwd(userVO.getPassword());
		BeanUtils.copyProperties(userVO, userDO);
		repository.save(userDO);

		return true;
	}

	@Override
	public UserVO updateUser(UserVO userVO) {

		Optional<UserDO> user = repository.findByUserName(userVO.getUserName());

		UserDO userDO = user.get();
		BeanUtils.copyProperties(userVO, userDO);
		repository.save(userDO);
		return userVO;
	}

	@Override
	public List<UserVO> getUsers() {
		List<UserDO> userList = repository.findAll();
		return CommonUtils.copyList(userList, UserVO.class);
	}

	@Override
	public UserVO getUserByName(String userName) {
		Optional<UserDO> user = repository.findByUserName(userName);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User Not Found");
		}
		UserDO userDO = user.get();
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(userDO, userVO);
		userVO.setAlbums(getUserAlbums(userName));
		return userVO;
	}

	@Override
	public List<AlbumVO> getUserAlbums(String userName) {

		List<AlbumVO> userAlbums = albumServiceClient.getUserAlbums(userName);

		return userAlbums;
	}

}
