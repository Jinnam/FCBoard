package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Board;
import service.BoardDao;


@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	BoardDao boardDao;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String view = "";
		String path = request.getRequestURI();
		System.out.println(path + ": path");
		if("/FCBoard/board/boardAdd.do".equals(path)){
			System.out.println("add");
			if(request.getMethod().equals("GET")){
				view = "/board/boardAdd.jsp";
			}else if(request.getMethod().equals("POST")){
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
		        int rowCount = boardDao.insertBoard(board);
		        // rowCout媛� 1�씠硫� �엯�젰�꽦怨� 
		        view = "redirect : "+request.getContextPath()+"/board/boardList";
			}
		}else if("/FCBoard/board/boardList.do".equals(path)){
			System.out.println("list");
			 int currentPage = 1;
		        if(request.getParameter("currentPage") != null) {
		            currentPage = Integer.parseInt(request.getParameter("currentPage"));
		        }
		        
		        boardDao = new BoardDao();
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
		}else if("/FCBoard/board/boardModify.do".equals(path)){
			System.out.println("modify");
			if(request.getMethod().equals("GET")){
				 if(request.getParameter("boardNo") == null) {
					 view = "redirect : "+request.getContextPath()+"/board/boardList";
			        } else {
			            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			            System.out.println("boardModify param boardNo:"+boardNo);
			            boardDao = new BoardDao();
			            Board board = boardDao.selectBoardByKey(boardNo);
			            request.setAttribute("board", board);
			            view = "/board/boardModify.jsp";
			        }
			}else if(request.getMethod().equals("POST")){
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
		            
		            boardDao = new BoardDao();
		            int rowCount = boardDao.updateBoard(board);
		            if(rowCount > 0){
		            	view = "redirect : "+request.getContextPath()+"/board/boardView?boardNo="+boardNo;
		            } else {
		            	view = "redirect : "+request.getContextPath()+"/board/boardModify?boardNo="+boardNo;
		            }
		        }
			}
		}else if("/FCBoard/board/boardRemove.do".equals(path)){
			System.out.println("remove");
			if(request.getMethod().equals("GET")){
				if(request.getParameter("boardNo") == null) {
					view = "redirect : "+request.getContextPath()+"/board/boardList.jsp";
		        } else {
		        	view = "/board/boardRemove.jsp";
		        }
			}else if(request.getMethod().equals("POST")){
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
		            
		            boardDao = new BoardDao();
		            
		            if(boardDao.deleteBoard(board)==1){
		            	view = "redirect : "+request.getContextPath()+"/board/boardList";
		            } else {
		            	view = "redirect : "+request.getContextPath()+"/board/boardRemove?boardNo="+boardNo;
		            }
		        }
			}
		}else if("/FCBoard/board/boardView.do".equals(path)){
			System.out.println("view");
			if(request.getParameter("boardNo") == null) {
				view = "redirect : "+request.getContextPath()+"/board/boardList";
	        } else {
	            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	            BoardDao boardDao = new BoardDao();
	            Board board = boardDao.selectBoardByKey(boardNo);
	            request.setAttribute("board", board);
	            view = "/board/boardView.jsp";
	        }
		}
		if(view.startsWith("redirect : ")){
			response.sendRedirect(view);
		}else
			request.getRequestDispatcher(view).forward(request, response);
	}
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}*/
}
