package com.ty.webproject.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ty.webproject.board.dto.BoardDTO;
import com.ty.webproject.board.dto.ReplyDTO;
import com.ty.webproject.board.service.BoardService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/updateBoard/page", method = RequestMethod.GET)
	public String updateBoardPage(@RequestParam("updateBoardIdx") String updateBoardIdx, Model model) {
		int boardIdx = Integer.parseInt(updateBoardIdx);
		// 게시물 서비스를 이용하여 컨텐츠 얻기
		String boardContents = boardService.getBoardContents(boardIdx);
		logger.info("updateBoard" + "boardContents= " + boardContents);
		
		model.addAttribute("updateBoardIdx", updateBoardIdx);
		model.addAttribute("boardContents", boardContents);
		logger.info("updateBoardPage" + "updateBoardIdx= " + updateBoardIdx);
		

		

		return "board/boardUpdate";
	}

	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public String updateBoard(@RequestParam HashMap<String, String> paramMap) {
		boardService.updateBoard(paramMap);
		logger.info("updateBoard" + "paramMap= " + paramMap);

		String updateBoardIdx = paramMap.get("idx");		
	

		return "redirect:/view?idx=" + updateBoardIdx;
	}

	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(@RequestParam("idx") int replyDeleteIdx, @RequestParam("replyIdx") int replyIdx) {
		logger.info("deleteReply" + "replyDeleteIdx= " + replyDeleteIdx);

		boardService.deleteReply(replyIdx);
		return "redirect:view?idx=" + replyDeleteIdx;
	}

	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam("deleteBoardIdx") int deleteBoardIdx) {
		boardService.deleteBoard(deleteBoardIdx);

		return "board/board";
	}

	@RequestMapping(value = "/replyList", method = RequestMethod.GET)
	@ResponseBody
	public List<ReplyDTO> replyList(@RequestParam("idx") int boardIdx) {
		return boardService.getReply(boardIdx);
	}

	@RequestMapping(value = "/writeReply", method = RequestMethod.POST)
	public String writeReply(@RequestParam("idx") int idx, @RequestParam("replyIdx") int replyIdx,
			@RequestParam("contents") String contents) {
		boardService.addReply(new ReplyDTO(0, idx, replyIdx, contents));
		return "redirect:view?idx=" + idx;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view() {
		return "board/view";
	}

	@RequestMapping(value = "/boardView", method = RequestMethod.GET)
	@ResponseBody
	public BoardDTO boardList(@RequestParam("idx") int idx) {
		return boardService.getBoardOne(idx);
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "/writeAction", method = RequestMethod.POST)
	public String writeAction(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("contents") String contents)
			throws IllegalStateException, IOException {
		String PATH = request.getSession().getServletContext().getRealPath("/") + "resources/";
		if (!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(PATH + file.getOriginalFilename()));
		}

		boardService.addBoard(new BoardDTO(0, title, contents, file.getOriginalFilename()));

		return "board/board";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "board/index";
	}

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board() {
		return "/board/board";
	}

	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	@ResponseBody
	public List<BoardDTO> boardList() {
		return boardService.getBoard();
	}

}
