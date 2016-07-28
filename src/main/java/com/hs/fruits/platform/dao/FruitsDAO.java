package com.hs.fruits.platform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hs.fruits.common.dao.AbstractDAO;

@Repository("fruitsDAO")
public class FruitsDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("fruits.selectBoardList",
				map);
	}

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("fruits.insertBoard", map);
	}

	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("fruits.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("fruits.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		update("fruits.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		update("fruits.deleteBoard", map);

	}

	public void insertFile(Map<String, Object> map) throws Exception {
		insert("fruits.insertFile", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map)
			throws Exception {
		return (List<Map<String, Object>>) selectList("fruits.selectFileList",
				map);
	}

	public void deleteFileList(Map<String, Object> map) throws Exception {
		update("fruits.deleteFileList", map);
	}

	public void updateFile(Map<String, Object> map) throws Exception {
		update("fruits.updateFile", map);
	}
}