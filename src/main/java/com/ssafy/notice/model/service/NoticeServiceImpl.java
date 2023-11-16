package com.ssafy.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.notice.model.dto.NoticeDto;
import org.springframework.stereotype.Service;

import com.ssafy.notice.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper mapper;
	
	
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void writeArticle(NoticeDto noticeDto) throws Exception {
		mapper.writeArticle(noticeDto);
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return mapper.listArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = mapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int articleNo) throws Exception {
		return mapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		mapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(NoticeDto noticeDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		mapper.modifyArticle(noticeDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		mapper.deleteArticle(articleNo);
	}

}
