package com.syscho.user.album.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syscho.user.album.vo.AlbumVO;

@RestController
@RequestMapping("/album")
public class UserAlbumController {
	static List<AlbumVO> list = new ArrayList<>();

	static {

		list.add(new AlbumVO("admin", "nature", "1212", "www.google.con/nature/fir.jpeg"));
		list.add(new AlbumVO("admin2", "WaterFalls", "2212", "www.google.con/nature/water.jpeg"));
		list.add(new AlbumVO("admin2", "nature", "1212", "www.google.con/nature/fir.jpeg"));
		list.add(new AlbumVO("admin", "nature", "1212", "www.google.con/nature/fir.jpeg"));

	}

	@GetMapping(path = "/{userId}")
	public List<AlbumVO> getUserAlbums(@PathVariable("userId") String userId) {

		return list.stream().filter(vo -> vo.getUserId().equalsIgnoreCase(userId)).collect(Collectors.toList());
	}

	@GetMapping
	public List<AlbumVO> getAllAblum() {

		return list;
	}

	public static <S, T> List<T> copyList(List<S> source, Class<T> type) {

		List<T> target = new ArrayList<>();
		Assert.notNull(source, "Source cannot be null");
		Assert.notNull(target, "Target cannot be null");
		Assert.notNull(type, "Target Class type is required");

		source.forEach(sourceClass -> {
			T targetObj = BeanUtils.instantiateClass(type);
			BeanUtils.copyProperties(sourceClass, targetObj);
			target.add(targetObj);
		});

		return target;
	}

}
