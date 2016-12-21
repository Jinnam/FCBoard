package web;

import javax.servlet.http.HttpServletRequest;

import service.Board;
import service.BoardDao;

public class BoardAddController implements Controller {

	public String execute(HttpServletRequest request){
		String view;
		String boardPw = request.getParameter("boardPw");
        System.out.println("param boardPw:"+boardPw);
        String boardTitle = request.getParameter("boardTitle");
        System.out.println("param boardTitle:"+boardTitle);
        String boardContent = request.getParameter("boardContent");
        System.out.println("param boardContent:"+boardContent);
        String boardUser = request.getParameter("boardUser");
        System.out.println("param boardUser:"+boardUser);
         
        Board board = new Board();
        board.setBoardPw(boardPw);
        board.setBoardTitle(boardTitle);
        board.setBoardContent(boardContent);
        board.setBoardUser(boardUser);
         
        BoardDao boardDao = new BoardDao();
        boardDao.insertBoard(board);
        
        view = "redirect : "+request.getContextPath()+"/board/boardList.do";
        return view;
	}
}
