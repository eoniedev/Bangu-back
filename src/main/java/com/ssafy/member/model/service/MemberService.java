package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.dto.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(Map<String, String> map) throws Exception;
	List<MemberDto> listMember(Map<String, String> object);
	MemberDto getMember(String userId);
	void updateMember(MemberDto memberDto);
	void deleteMember(String userId);
	
}
