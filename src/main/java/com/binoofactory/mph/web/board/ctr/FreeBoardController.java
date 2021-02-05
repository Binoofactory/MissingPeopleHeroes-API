package com.binoofactory.mph.web.board.ctr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binoofactory.mph.cmmn.data.ApiDataRstCd;
import com.binoofactory.mph.utils.ApiResponseUtil;
import com.binoofactory.mph.utils.Array2ListUtil;
import com.binoofactory.mph.utils.DateUtil;
import com.binoofactory.mph.utils.PageUtil;
import com.binoofactory.mph.web.board.svc.BaordService;
import com.binoofactory.mph.web.board.vo.BoardVO;
import com.binoofactory.mph.web.vo.Board;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "자유 게시판", tags="게시판")
@RequestMapping("/api/t1/board/")
public class FreeBoardController {
	
	@Autowired
	private BaordService freeboardService;

	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private ApiResponseUtil apiResponseUtil;
	@Autowired
	private PageUtil pageUtil;
	
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(FreeBoardController.class);

	@ApiOperation(value = "list/{boardName}/{boardNo}", notes = "게시판 글 조회")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.GET, value = "list/{boardName}")
	public Map<String, Object> list(
			@ApiParam(value = "페이지번호", required = false, example = "1") 		@RequestParam(required=false) 	Integer 	pageNo,
			@ApiParam(value = "페이지 크기", required = false, example = "10") 	@RequestParam(required=false) 	Integer 	pageSize,
			@ApiParam(value = "게시판명", required = true, example = "test") 		@PathVariable(required=true) 	String 		boardName,
			@ApiParam(value = "식별번호", required = false, example = "1") 		@PathVariable(required=false) 	Integer 	boardNo
		) throws Exception {

		PageUtil.PageVO pageVO = pageUtil.getDefaultPage(pageNo, pageSize);
		List<BoardVO> boards = new ArrayList<BoardVO>();
		if(boardNo == null)
		{
			boards = freeboardService.findList("notice", boardName, "Y", pageVO.limit, pageVO.offset);			
			return apiResponseUtil.makeResult(
					Array2ListUtil.array2List(
							boards.stream()
								.map(board -> new Board(board)).toArray())
					, ApiDataRstCd.SEARCH_SUCCESS);
		}
		else
		{
			BoardVO searchVO = new BoardVO();
			searchVO.setBno(boardNo);
			BoardVO boardVO = freeboardService.find(searchVO); 
			return apiResponseUtil.makeResult(new Board(boardVO), ApiDataRstCd.SEARCH_SUCCESS);
		}
	}

	@ApiOperation(value = "list/{boardName}", notes = "게시판 글 추가")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.POST, value = "list/{boardName}")
	public Map<String, Object> add(
			@ApiParam(value = "게시판명", required = true, example = "test") 		@PathVariable(required=true) 	String 		boardName,
			@RequestBody(required = true) Board board
			)	throws Exception 
	{
		freeboardService.save(
				BoardVO
					.builder()
					.boardGrpType(boardName)
					.boardType(board.boardType)
					.title(board.title)
					.contents(board.contents)
					.isUsed(board.isUsed)
					.regDt(dateUtil.getDatetimeDetail())
					.edtDt(dateUtil.getDatetimeDetail())
					.build());

		return apiResponseUtil.makeResult(ApiDataRstCd.ADD_CMMN_DATA);
	}
	
	@ApiOperation(value = "list/{boardName}", notes = "게시판 글 수정")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.PUT, value = "list/{boardName}")
	public Map<String, Object> edit(
			@ApiParam(value = "게시판명", required = true, example = "test") 		@PathVariable(required=true) 	String 		boardName,
			@RequestBody(required = true) Board board
			)	throws Exception 
	{
		BoardVO searchVO = new BoardVO();
		searchVO.setBno(board.bno);
		
		BoardVO saveVO = freeboardService.find(searchVO);
		if(saveVO == null)
			return apiResponseUtil.makeResult(ApiDataRstCd.INTERNAL_ERR);
		
		saveVO.setBoardGrpType(boardName);
		saveVO.setBoardType(saveVO.boardType);
		saveVO.setTitle(saveVO.title);
		saveVO.setContents(saveVO.contents);
		saveVO.setRegDt(saveVO.regDt);
		saveVO.setEdtDt(dateUtil.getDatetimeDetail());
		saveVO.setFileGrpCd(saveVO.fileGrpCd);
		freeboardService.save(saveVO);

		return apiResponseUtil.makeResult(ApiDataRstCd.ADD_CMMN_DATA);
	}
	
	@ApiOperation(value = "list/{boardName}", notes = "게시판 글 삭제")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.DELETE, value = "list/{boardName}")
	public Map<String, Object> remove(
			@ApiParam(value = "게시판명", required = true, example = "test") 		@PathVariable(required=true) 	String 		boardName,
			@RequestBody(required = true) Board board
			) throws Exception 
	{
		BoardVO searchVO = new BoardVO();
		searchVO.setBno(board.bno);
		
		BoardVO saveVO = freeboardService.find(searchVO);
		if(saveVO == null)
			return apiResponseUtil.makeResult(ApiDataRstCd.INTERNAL_ERR);
		
		freeboardService.remove(saveVO);
		return apiResponseUtil.makeResult(ApiDataRstCd.REMOVE_CMMN_DATA);
	}
}
