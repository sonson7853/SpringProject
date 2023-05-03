package com.kh.spring.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.chat.model.vo.ChatRoom;

@Repository
public class ChatDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<ChatRoom> selectChatRoomList(){
		
		return sqlSession.selectList("chattingMapper.selectChatRoomList");
	}
	
}
