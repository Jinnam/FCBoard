package web;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import service.Board;
import service.BoardDao;
 
@WebServlet("/board/boardModify")
public class BoardModifyServlet extends HttpServlet {
    private BoardDao boardDao;
    // 수정 폼
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("boardNo") == null) {
            response.sendRedirect(request.getContextPath()+"/board/boardList");
        } else {
            int boardNo = Integer.parseInt(request.getParameter("boardNo"));
            System.out.println("boardModify param boardNo:"+boardNo);
            boardDao = new BoardDao();
            Board board = boardDao.selectBoardByKey(boardNo);
            request.setAttribute("board", board);
            request.getRequestDispatcher("/board/boardModify.jsp").forward(request, response);
        }
    }
 
    // 수정 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("euc-kr");
        if(request.getParameter("boardNo") == null || request.getParameter("boardPw") == null) {
            response.sendRedirect(request.getContextPath()+"/board/boardList.jsp");
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
                response.sendRedirect(request.getContextPath()+"/board/boardView?boardNo="+boardNo);
            } else {
                response.sendRedirect(request.getContextPath()+"/board/boardModify?boardNo="+boardNo);
            }
        }
    }
}