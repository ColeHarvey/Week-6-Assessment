package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListComic;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addComicServlet")
public class AddComicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String artist = request.getParameter("artist");
		String publisher = request.getParameter("publisher");
		ListComic li = new ListComic(writer, artist, publisher);
		ListComicHelper dao = new ListComicHelper();
		dao.insertItem(li);
		getServletContext().getRequestDispatcher("/index.html").forward(request,
				response);
		
	}

}
