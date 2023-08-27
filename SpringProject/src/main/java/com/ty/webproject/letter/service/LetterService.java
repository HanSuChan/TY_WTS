package com.ty.webproject.letter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.letter.dto.LetterDTO;
import com.ty.webproject.letter.repository.LetterRepository;

@Service
public class LetterService implements LetterRepository{
	
	@Autowired
	private LetterRepository letterRepository;
	@Override
	public List<LetterDTO> getPopup(String to_id, int limit){
		System.out.println(to_id);
		System.out.println("@@@@+"+letterRepository.getPopup(to_id, limit));
		return letterRepository.getPopup(to_id, limit);
	}
	@Override
	public void letterDeletet(String lto_id, String lfrom_id, int ltime) {
		letterRepository.letterDeletet(lto_id,lfrom_id,ltime);
	}
	@Override
	public void letterWrite(String to_idl,String from_idl, String titlel, String contentl, long letter_datel) {
		letterRepository.letterWrite(to_idl,from_idl,titlel,contentl,letter_datel);
	}
}
