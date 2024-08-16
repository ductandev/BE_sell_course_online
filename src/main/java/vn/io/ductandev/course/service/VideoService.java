package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.model.VideoDTO;

public interface VideoService {

	List<VideoDTO> getListVideo();
	
	boolean addVideo(VideoDTO videoDTO);
	
	
}
