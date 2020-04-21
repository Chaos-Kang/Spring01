package cn.edu.scujcc.dao;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import cn.edu.scujcc.model.Channel;


public interface ChannelRepository extends MongoRepository<Channel,String> {
	public List<Channel> findByTitleAndQuality(String t,String q);
	public Page<Channel> findByTitleContaining(String t,Pageable page);
	
	//找出评论时间在指定日期之后的所有频道
	
	public List<Channel> findByQuality(String quality);
	public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);
}
