package com.hs.fruits.platform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hs.fruits.common.utils.FileUtils;
import com.hs.fruits.platform.dao.FruitsDAO;

@Service("fruitsService")
public class FruitsServiceImpl implements FruitsService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Resource(name = "fruitsDAO")
	private FruitsDAO fruitsDAO;

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map)
			throws Exception {
		return fruitsDAO.selectBoardList(map);
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		fruitsDAO.insertBoard(map);

        List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for(int i=0, size=list.size(); i<size; i++){
        	fruitsDAO.insertFile(list.get(i));
        }


	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map)
			throws Exception {
		fruitsDAO.updateHitCnt(map);
	    Map<String, Object> resultMap = new HashMap<String,Object>();
	    Map<String, Object> tempMap = fruitsDAO.selectBoardDetail(map);
	    resultMap.put("map", tempMap);
		
	    List<Map<String,Object>> list = fruitsDAO.selectFileList(map);
	    resultMap.put("list", list);

		return resultMap;

	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		fruitsDAO.updateBoard(map);
		
		fruitsDAO.deleteFileList(map);
	    List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
	    Map<String,Object> tempMap = null;
	    for(int i=0, size=list.size(); i<size; i++){
	        tempMap = list.get(i);
	        if(tempMap.get("IS_NEW").equals("Y")){
	        	fruitsDAO.insertFile(tempMap);
	        }
	        else{
	        	fruitsDAO.updateFile(tempMap);
	        }
	    }
		
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		fruitsDAO.deleteBoard(map);
	}

}
