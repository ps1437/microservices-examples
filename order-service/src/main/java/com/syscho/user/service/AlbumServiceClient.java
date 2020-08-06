package com.syscho.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.syscho.user.vo.AlbumVO;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "user-album", fallbackFactory = AlbumServiceFallback.class)
public interface AlbumServiceClient {

	@GetMapping(path = "/album/{userId}")
	public List<AlbumVO> getUserAlbums(@PathVariable("userId") String userId);

}

class AlbumServiceFallback implements FallbackFactory<AlbumService> {

	@Override
	public AlbumService create(Throwable expection) {
		return new AlbumService(expection);
	}

}

class AlbumService implements AlbumServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	Throwable throwable;

	public AlbumService(Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public List<AlbumVO> getUserAlbums(String userId) {
		System.out.println("AlbumService.getUserAlbums()" + throwable);
		logger.error(throwable.getLocalizedMessage(), throwable);
		return new ArrayList<AlbumVO>();

	}

}