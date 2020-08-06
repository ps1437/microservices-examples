package com.syscho.user.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumVO {

	private String userId;
	private String albumId;
	private String imageId;
	private String imageUrl;

}