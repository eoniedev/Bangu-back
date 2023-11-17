package com.ssafy.member.model.service;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
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
    public List<MemberDto> listMember(Map<String, String> map) throws Exception {
        return memberMapper.listMember(map);
    }

    @Override
    public MemberDto getMember(String userId) throws Exception {
        return memberMapper.getMember(userId);
    }

    @Override
    public void updateMember(MemberDto memberDto) throws Exception {
        memberMapper.updateMember(memberDto);
    }

    @Override
    public void deleteMember(String userId) throws Exception {
        memberMapper.deleteMember(userId);
    }

    @Override
    public MemberDto login(MemberDto memberDto) throws Exception {
        return memberMapper.login(memberDto);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", refreshToken);
        memberMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userId) throws Exception {
        return memberMapper.getRefreshToken(userId);
    }

    @Override
    public void deleRefreshToken(String userId) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

}
