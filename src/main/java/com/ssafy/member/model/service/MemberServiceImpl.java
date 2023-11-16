package com.ssafy.member.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		return memberMapper.loginMember(map);
	}

	@Override
	public List<MemberDto> listMember(Map<String, String> map) {
		return memberMapper.listMember(map);
	}

	@Override
	public MemberDto getMember(String userId) {
		return memberMapper.getMember(userId);
	}

	@Override
	public void updateMember(MemberDto memberDto) {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) {
		memberMapper.deleteMember(userId);
	}

}
