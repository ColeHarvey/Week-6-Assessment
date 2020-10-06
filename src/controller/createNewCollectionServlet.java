package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CollectionDetails;
import model.Collector;
import model.ListComic;

/**
 * Servlet implementation class createNewListServlet
 */
@WebServlet("/createNewCollectionServlet")
public class createNewCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createNewCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListComicHelper lih = new ListComicHelper();
		String collectionName = request.getParameter("collectionName");
		System.out.println("Collection Name: " + collectionName);
		

		String collectorName = request.getParameter("collectorName");
		LocalDate ld;
		
		String[] selectedComics = request.getParameterValues("allComicsToAdd");
		List<ListComic> selectedComicInList = new ArrayList <ListComic>();
		//make sure something was selected - otherwise we get a null pointer exception
		if (selectedComics != null && selectedComics.length > 0)
		{
			for(int i= 0; i<selectedComics.length;i++) {
				System.out.println(selectedComics[i]);
				ListComic c = lih.searchForItemById(Integer.parseInt(selectedComics[i]));
				selectedComicInList.add(c);
			}
		}
		Collector collector = new Collector(collectorName);
		CollectionDetails sld = new CollectionDetails(collectionName, collector, selectedComicInList);
		sld.setListOfComics(selectedComicInList);
		CollectionDetailsHelper slh = new CollectionDetailsHelper();
		slh.insertNewCollectionDetails(sld);
		
		System.out.println("Success!");
		System.out.println(sld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllComicsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
