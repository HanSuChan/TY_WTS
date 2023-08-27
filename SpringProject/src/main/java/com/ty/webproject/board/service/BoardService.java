package com.ty.webproject.board.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.board.controller.BoardController;
import com.ty.webproject.board.dto.BoardDTO;
import com.ty.webproject.board.dto.ReplyDTO;
import com.ty.webproject.board.repository.BoardRepository;



@Service
public class BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	
    @Autowired 
    private BoardRepository boardRepository;
    

    
    public void deleteBoard(int deleteBoardIdx) {
    	
    	boardRepository.deleteBoard(deleteBoardIdx);
    }
    
    public void updateBoard(HashMap<String, String> map) {
    	logger.info("updateBoard=" + map );
    	boardRepository.updateBoard(map);
    	
    }
    
    
    public boolean addReply(ReplyDTO reply) {
    	
    	return boardRepository.addReply(reply);
    	
    }
    
    public void deleteReply(int replyDeleteIdx) {
		logger.info("deleteReply" + "replyDeleteIdx= " + replyDeleteIdx);    	
    	boardRepository.deleteReply(replyDeleteIdx);
    }
    
    public List<ReplyDTO> getReply(int boardIdx) {
    	return boardRepository.getReply(boardIdx);
    }
    
    public BoardDTO getBoardOne(int idx) {
    	return boardRepository.getBoardOne(idx);
    }
    
	
    public List<BoardDTO> getBoard(){
        return boardRepository.getBoard();
    }
    
    public boolean addBoard(BoardDTO boardDTO) {
    	return boardRepository.addBoard(boardDTO);
    }
    
    public String getBoardContents(int boardIdx) {
        BoardDTO board = boardRepository.getBoardOne(boardIdx);
        if (board != null) {
            return board.getContents();
        }
        return null; // 게시물이 없을 경우 null 반환 또는 예외 처리
    }
	

}
