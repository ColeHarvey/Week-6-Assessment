package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListComic;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editComicServlet")
public class EditComicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComicServlet() {
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
		
		String writer = request.getParameter("writer");
		String artist = request.getParameter("artist");
		String publisher = request.getParameter("publisher");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		ListComic itemToUpdate = dao.searchForItemById(tempId);
		itemToUpdate.setWriter(writer);
		itemToUpdate.setArtist(artist);
		itemToUpdate.setPublisher(publisher);
		
		dao.updateItem(itemToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllComicsServlet").forward(request,response);
	}

}
