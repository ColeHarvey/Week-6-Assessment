import java.util.List;
import java.util.Scanner;

import controller.ListComicHelper;
import model.ListComic;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListComicHelper lih = new ListComicHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a writer: ");
			String writer = in.nextLine();
			System.out.print("Enter an artist: ");
			String artist = in.nextLine();
			System.out.print("Enter a publisher: ");
			String publisher = in.nextLine();
			ListComic toAdd = new ListComic(writer, artist, publisher);
			lih.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a writer to delete: ");
			String writer = in.nextLine();
			System.out.print("Enter an artist to delete: ");
			String artist = in.nextLine();
			System.out.print("Enter a publisher to delete: ");
			String publisher = in.nextLine();
			ListComic toDelete = new ListComic(writer, artist, publisher);
			lih.deleteItem(toDelete);

		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Store");
			System.out.println("2 : Search by Item");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListComic> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the store name: ");
				String storeName = in.nextLine();
				foundItems = lih.searchForItemByWriter(storeName);
				
			} else {
				System.out.print("Enter the item: ");
				String itemName = in.nextLine();
				foundItems = lih.searchForItemByArtist(itemName);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListComic l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListComic toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getArtist() + " from " + toEdit.getWriter());
				System.out.println("1 : Update Store");
				System.out.println("2 : Update Item");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Writer: ");
					String newWriter = in.nextLine();
					toEdit.setWriter(newWriter);
				} else if (update == 2) {
					System.out.print("New Artist: ");
					String newArtist = in.nextLine();
					toEdit.setArtist(newArtist);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListComic> allItems = lih.showAllComics();
			for(ListComic singleItem : allItems) {
				System.out.println(singleItem.returnItemDetails());
			}
			
		}

	}