package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import service.Board;
import service.BoardDao;

public class BoardListController implements Controller{

	public String execute(HttpServletRequest request){
		String view;
		int currentPage = 1;
        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        
        BoardDao boardDao = new BoardDao();
        int totalRowCount = boardDao.selectTotalBoardCount();
        int pagePerRow = 10; 
        int beginRow = (currentPage-1)*pagePerRow;
        int lastPage = totalRowCount/pagePerRow;
        if(totalRowCount%pagePerRow != 0) {
            lastPage++;
        }
        List<Board> list = boardDao.selectBoardListPerPage(beginRow, pagePerRow);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalRowCount", totalRowCount);
        request.setAttribute("pagePerRow", pagePerRow);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("list", list);
        view = "/board/boardList.jsp";
        
        return view;
	}
}
