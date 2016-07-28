package com.hs.fruits.platform.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hs.fruits.common.common.CommandMap;
import com.hs.fruits.platform.service.FruitsService;

@Controller
public class FruitsController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "fruitsService")
	private FruitsService fruitsService;

	/**
	 * List Page
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openBoardList.hs")
	public ModelAndView openSampleList(Map<String, Object> commandMap)
			throws Exception {

		ModelAndView mv = new ModelAndView("/fruits/boardList");

		List<Map<String, Object>> list = fruitsService
				.selectBoardList(commandMap);
		mv.addObject("list", list);

		return mv;
	}

	/**
	 * Write Page
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openBoardWrite.hs")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/fruits/boardWrite");

		return mv;
	}

	/**
	 * Insert Page
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertBoard.hs")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/openBoardList.hs");

		fruitsService.insertBoard(commandMap.getMap(), request);

		return mv;
	}

	/**
	 * Detail Page
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openBoardDetail.hs")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/fruits/boardDetail");

		Map<String, Object> map = fruitsService.selectBoardDetail(commandMap
				.getMap());
		mv.addObject("map", map.get("map")); // detail info
		mv.addObject("list", map.get("list")); // file info
		
		return mv;
	}

	/**
	 * Update Page
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openBoardUpdate.hs")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/fruits/boardUpdate");

	    Map<String,Object> map = fruitsService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map.get("map"));
	    mv.addObject("list", map.get("list"));

		return mv;
	}

	@RequestMapping(value = "/updateBoard.hs")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/openBoardDetail.hs");

		fruitsService.updateBoard(commandMap.getMap(), request);

		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}

	@RequestMapping(value="/deleteBoard.hs")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/openBoardList.hs");
	     
	    fruitsService.deleteBoard(commandMap.getMap());
	     
	    return mv;
	}

	
	/**
	 * request.getParameter -> map test url :
	 * http://localhost:8080/commandMap.hs?test=1111&test2=2222
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commandMap.hs")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("");

		if (commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap()
					.entrySet().iterator();
			Entry<String, Object> entry = null;

			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : "
						+ entry.getValue());
			}
		}
		return mv;
	}

}