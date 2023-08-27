package com.ty.webproject.board.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ty.webproject.board.dto.BoardDTO;
import com.ty.webproject.board.dto.ReplyDTO;

@Mapper
public interface BoardRepository {
	
    public boolean addBoard(BoardDTO boardDTO);
    public List<BoardDTO> getBoard();
    public BoardDTO getBoardOne(int idx);
    public void deleteBoard(int deleteBoardIdx);
    public void updateBoard(HashMap<String, String> Map);
    
    public String getBoardContents(int boardIdx);    
    public boolean addReply(ReplyDTO replyDTO);
    public List<ReplyDTO> getReply(int boardIdx);
    public void deleteReply(int replyDeleteIdx);
    

}
