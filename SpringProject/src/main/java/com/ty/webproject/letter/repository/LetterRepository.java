package com.ty.webproject.letter.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ty.webproject.letter.dto.LetterDTO;

@Mapper
public interface LetterRepository {

	public List<LetterDTO> getPopup(@Param("to_id") String to_id, @Param("limit") int limit);
	public void letterDeletet(@Param("lto_id") String lto_id,@Param("lfrom_id") String lfrom_id,@Param("ltime") int ltime);
	public void letterWrite(@Param("to_idl") String to_idl,@Param("from_idl") String from_idl, @Param("titlel") String titlel, @Param("contentl") String contentl,@Param("letter_datel") long letter_datel);
}
