package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListComic;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListComicHelper dao = new ListComicHelper();
		String act = request.getParameter("doThisToItem");
		
		//after all changes, we redirect to viewAllItems servlet
		//the only time we don't is it they select add or edit item
		String path = "/viewAllComicsServlet";
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListComic comicToDelete = dao.searchForItemById(tempId);
				dao.deleteItem(comicToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a comic");
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListComic itemToEdit = dao.searchForItemById(tempId);
				request.setAttribute("itemToEdit", itemToEdit);
				path = "/edit-comic.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a comic");
			}
		} else if (act.equals("add")) {
			path = "/index.html";
		}
		
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
