package cn.edu.scujcc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**
 * 通过频道相关业务逻辑
 * 提供模拟的频道数据
 * @author 
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	/*
	 * 获取所有频道数据
	 * @return 频道List*/
	public List<Channel> getAllChannels(){
//		List<String> qs = new ArrayList<>();
//		qs.add("720P");
//		qs.add("1080P");
		return repo.findAll();
	}
	
	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	/*
	 * 获取所有频道
	 * @return
	 * 删除指定的频道
	 * @param id
	 * @return
	 * */
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		
		return result;
	}
	
	/*
	 * 更新一个频道
	 * @param c 待更新的频道，用于更新已存在的统一频道
	 * @return 更新后的频道
	 * */
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if(saved != null) {
			if(c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if(c.getQuality() != null) {
				saved.setQuality(c.getQuality());
			}
			if(c.getUrl() != null) {
				saved.setUrl(c.getUrl());
			}
			if(c.getComments() != null) {
				saved.getComments().addAll(c.getComments());
			}else {
				saved.setComments(c.getComments());
			}
		}
		
		return repo.save(saved);
	}
	
	public List<Channel> searchByQuality(String quality){
		return repo.findByQuality(quality);
	}
	/*
	 * @param c
	 * @return
	 * */
	public List<Channel> getLatestCommentsChannel(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(),
				now.getMonthValue(),now.getDayOfMonth(),0,0);
		return repo.findByCommentsDtAfter(today);
	}
	public Channel createChannel(Channel c) {
		return repo.save(c);
		
	}
}
