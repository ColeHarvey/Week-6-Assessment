package controller;

import java.io.IOException;
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
 * Servlet implementation class editListDetailsServlet
 */
@WebServlet("/editCollectionDetailsServlet")
public class editCollectionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCollectionDetailsServlet() {
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
		// TODO Auto-generated method stub
		ListCollectionHelper dao = new ListCollectionHelper();
		ListComicHelper lih = new ListComicHelper();
		CollectorHelper sh = new CollectorHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		CollectionDetails listToUpdate = dao.searchForCollectionDetailsById(tempId);
		
		String newListName = request.getParameter("collectionName");
		
		String shopperName = request.getParameter("shopperName");
		//find our add the new shopper
		Collector newCollector = sh.findCollector(shopperName);
		
		try {
			//items are selected in list to add
			String[] selectedItems = request.getParameterValues("allItemsToAdd");
			List <ListComic> selectedItemsInList = new ArrayList<ListComic>(); 
			
			for (int i = 0; i < selectedItems.length; i++) {
				System.out.println(selectedItems[i]);
				ListComic c = lih.searchForItemById(Integer.parseInt(selectedItems[i]));
				selectedItemsInList.add(c);
			}
			listToUpdate.setListOfComics(selectedItemsInList);
		} catch (NullPointerException ex) {
			//no items selected in list - set to an empty list
			List<ListComic> selectedItemsInList = new ArrayList<ListComic>();
			listToUpdate.setListOfComics(selectedItemsInList);
		}
		
		
		dao.updateCollection(listToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllCollectionsServlet").forward(request, response);
	}

}
