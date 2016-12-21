package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {

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
				Controller controller = new BoardAddController();
				view = controller.execute(request);
			}
		}else if("/FCBoard/board/boardList.do".equals(path)){
			System.out.println("list");
			 Controller controller = new BoardListController();
			 view=controller.execute(request);
		}else if("/FCBoard/board/boardModify.do".equals(path)){
			System.out.println("modify");
			if(request.getMethod().equals("GET")){
				 Controller controller = new BoardModifyFormController();
				 view = controller.execute(request);
			}else if(request.getMethod().equals("POST")){
		       Controller controller = new BoardModifyController();
		       view = controller.execute(request);
			}
		}else if("/FCBoard/board/boardRemove.do".equals(path)){
			System.out.println("remove");
			if(request.getMethod().equals("GET")){
				Controller controller = new BoardRemoveFormController();
				view = controller.execute(request);
			}else if(request.getMethod().equals("POST")){
				Controller controller = new BoardRemoveController();
				view = controller.execute(request);
			}
		}else if("/FCBoard/board/boardView.do".equals(path)){
			System.out.println("view");
			Controller controller = new BoardViewController();
			view = controller.execute(request);
			
		}
		if(view.startsWith("redirect : ")){
			view=view.substring(11);
			response.sendRedirect(view);
		}else
			request.getRequestDispatcher(view).forward(request, response);
	}
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}*/
}
