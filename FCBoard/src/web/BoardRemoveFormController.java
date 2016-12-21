package web;

import javax.servlet.http.HttpServletRequest;

public class BoardRemoveFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request) {
		String view;
		if(request.getParameter("boardNo") == null) {
			view = "redirect : "+request.getContextPath()+"/board/boardList.jsp";
        } else {
        	view = "/board/boardRemove.jsp";
        }
		return view;
	}

}
