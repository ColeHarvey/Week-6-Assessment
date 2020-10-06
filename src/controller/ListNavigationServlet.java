package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CollectionDetails;

/**
 * Servlet implementation class ListNavigationServlet
 */
@WebServlet("/listnavigationServlet")
public class ListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNavigationServlet() {
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
		CollectionDetailsHelper dao = new CollectionDetailsHelper();
		String act = request.getParameter("doThisToList");
		
		if (act == null) {
			//no button has been selected
			getServletContext().getRequestDispatcher("/viewAllCollectionsServlet").forward(request, response);
		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				CollectionDetails listToDelete = dao.searchForListDetailsById(tempId);
				dao.deleteCollection(listToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllCollectionsServlet").forward(request, response);
			} 
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				CollectionDetails listToEdit = dao.searchForListDetailsById(tempId);
				request.setAttribute("listToEdit", listToEdit);
				
				ListComicHelper daoForItems = new ListComicHelper();
				
				request.setAttribute("allComics", daoForItems.showAllComics());
				
				if(daoForItems.showAllComics().isEmpty()) {
					request.setAttribute("allComics", " ");
				}
				
				getServletContext().getRequestDispatcher("/edit-collection.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllCollectionsServlet").forward(request, response);
			}
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/new-collection.jsp").forward(request, response);
		}
	}

}
