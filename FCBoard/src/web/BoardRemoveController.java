package web;

import javax.servlet.http.HttpServletRequest;

import service.Board;
import service.BoardDao;

public class BoardRemoveController implements Controller{
	
	public String execute(HttpServletRequest request){
		String view;
		if(request.getParameter("boardNo") == null || request.getParameter("boardPw") == null) {
			view = "redirect : "+request.getContextPath()+"/board/boardList.jsp";
        } else {
            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
            System.out.println("boardNo :"+boardNo);
            String boardPw = request.getParameter("boardPw");
            System.out.println("boardPw :"+boardPw);
            Board board = new Board();
            board.setBoardNo(boardNo);
            board.setBoardPw(boardPw);
            
            BoardDao boardDao = new BoardDao();
            
            if(boardDao.deleteBoard(board)==1){
            	view = "redirect : "+request.getContextPath()+"/board/boardList.do";
            } else {
            	view = "redirect : "+request.getContextPath()+"/board/boardRemove.do?boardNo="+boardNo;
            }
        }
		return view;
	}

}
