package com.ssafy.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.notice.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	void writeArticle(NoticeDto noticeDto) throws SQLException;

	void registerFile(NoticeDto noticeDto) throws Exception;

	List<NoticeDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	NoticeDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyArticle(NoticeDto noticeDto) throws SQLException;

	void deleteArticle(int articleNo) throws SQLException;

}