package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CollectionDetails;

/**
 * Servlet implementation class viewAllListsServlet
 */
@WebServlet("/viewAllCollectionsServlet")
public class viewAllCollectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAllCollectionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CollectionDetailsHelper slh = new CollectionDetailsHelper();
		List<CollectionDetails> abc = slh.getCollections();
		request.setAttribute("allCollections", abc);
		if(abc.isEmpty()) {
			request.setAttribute("allCollections", " ");
		}
		getServletContext().getRequestDispatcher("/collection-by-user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
