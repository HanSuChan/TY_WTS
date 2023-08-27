package com.ty.webproject.letter.Controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.webproject.letter.dto.LetterDTO;
import com.ty.webproject.letter.service.LetterService;

@Controller
public class LetterController {

	@Autowired
	private LetterService letterService;
	
	@ResponseBody
	@RequestMapping(value="home/popup", method = RequestMethod.GET)
	public List<LetterDTO> popups(@RequestParam String to_id ) {
		System.out.println("fillpop");
		System.out.println(to_id);
		List<LetterDTO> cut = new ArrayList<LetterDTO>();
		List<LetterDTO> fill = letterService.getPopup("test", 4);
		int check=0;
		for(int i=0;i<fill.size();i++) {
			if(fill.get(i).getToCheck().equals("0")) {
				cut.add(fill.get(i));
				check++;
				if(check==6) break;
			}
		}
		return cut;
	}
	@RequestMapping("/LetterBox")
	public String a() {
		return "letter/LetterBox";
	}
	@ResponseBody
	@RequestMapping(value="/letterDelete", method = RequestMethod.POST)
	public void delete(@RequestParam String lto_id, @RequestParam String lfrom_id, @RequestParam int ltime) {
		System.out.println(lto_id);
		System.out.println(lfrom_id);
		System.out.println(ltime);
		letterService.letterDeletet(lto_id,lfrom_id,ltime);
	}
	@ResponseBody
	@RequestMapping(value="writeLetter", method = RequestMethod.POST)
	public void Write(@RequestParam String to_idl,@RequestParam String from_idl, @RequestParam String contentl, @RequestParam String titlel) {
		System.out.println("@@@@");
		Instant instant = Instant.now();
        long letter_datel = instant.getEpochSecond();
		letterService.letterWrite(to_idl,from_idl,titlel,contentl,letter_datel);
	}
}
