package web;

import javax.servlet.http.HttpServletRequest;

import service.Board;
import service.BoardDao;

public class BoardModifyController implements Controller {

	@Override
	public String execute(HttpServletRequest request) {
		String view;
		 if(request.getParameter("boardNo") == null || request.getParameter("boardPw") == null) {
	        	view = "redirect : "+request.getContextPath()+"/board/boardList.jsp";
	        } else {
	            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	            System.out.println("boardModifyAction param boardNo :"+boardNo);
	            String boardPw = request.getParameter("boardPw");
	            System.out.println("boardModifyAction param boardPw :"+boardPw);
	            String boardTitle = request.getParameter("boardTitle");
	            System.out.println("boardModifyAction param boardTitle :"+boardTitle);
	            String boardContent = request.getParameter("boardContent");
	            System.out.println("boardModifyAction param boardContent :"+boardContent);
	 
	            Board board = new Board();
	            board.setBoardNo(boardNo);
	            board.setBoardPw(boardPw);
	            board.setBoardTitle(boardTitle);
	            board.setBoardContent(boardContent);
	            
	           BoardDao boardDao = new BoardDao();
	            int rowCount = boardDao.updateBoard(board);
	            if(rowCount > 0){
	            	view = "redirect : "+request.getContextPath()+"/board/boardView.do?boardNo="+boardNo;
	            } else {
	            	view = "redirect : "+request.getContextPath()+"/board/boardModify.do?boardNo="+boardNo;
	            }
	        }return view;

	}

}
