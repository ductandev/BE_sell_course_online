package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.VideoDTO;

public interface VideoService {

	List<VideoDTO> getListVideo();
	
	boolean addVideo(VideoDTO videoDTO);
	
	
}
