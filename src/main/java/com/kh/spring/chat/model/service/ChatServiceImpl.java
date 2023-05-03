package com.kh.spring.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.chat.model.dao.ChatDAO;
import com.kh.spring.chat.model.vo.ChatRoom;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDAO dao;
	
	@Override
	public List<ChatRoom> selectChatRoomList(){
		return dao.selectChatRoomList();
	}
}
