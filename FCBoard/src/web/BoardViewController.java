package web;

import javax.servlet.http.HttpServletRequest;

import service.Board;
import service.BoardDao;

public class BoardViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request) {
		String view;
		if(request.getParameter("boardNo") == null) {
			view = "redirect : "+request.getContextPath()+"/board/boardList.do";
        } else {
            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
            BoardDao boardDao = new BoardDao();
            Board board = boardDao.selectBoardByKey(boardNo);
            request.setAttribute("board", board);
            view = "/board/boardView.jsp";
        }
		return view;
	}

}
