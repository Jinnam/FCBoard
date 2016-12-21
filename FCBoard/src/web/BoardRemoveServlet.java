package web;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import service.Board;
import service.BoardDao;
 
@WebServlet("/board/boardRemove")
public class BoardRemoveServlet extends HttpServlet {
    private BoardDao boardDao;
    // 삭제시 비밀번호를 확인하는 폼
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("boardNo") == null) {
            response.sendRedirect(request.getContextPath()+"/board/boardList.jsp");
        } else {
            request.getRequestDispatcher("/board/boardRemove.jsp").forward(request, response);
        }
    }
    
    // 삭제
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("boardNo") == null || request.getParameter("boardPw") == null) {
            response.sendRedirect(request.getContextPath()+"/board/boardList.jsp");
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
                response.sendRedirect(request.getContextPath()+"/board/boardList");
            } else {
                response.sendRedirect(request.getContextPath()+"/board/boardRemove?boardNo="+boardNo);
            }
        }
    }
}
