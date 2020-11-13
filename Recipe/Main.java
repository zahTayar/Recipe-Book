package Recipe;

import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		RecipeList list = new RecipeList();
		Scanner s = new Scanner(System.in);

		try {
			loadMenu(list, s);

		} catch (Exception e) {

		}

	}

	private static void loadMenu(RecipeList list, Scanner s) {
		boolean isFinished = false;
		
		while (!isFinished) {
			System.out.println("RECIPE CREATOR:");
			System.out.println("1. Add/Update Recipe");
			System.out.println("2. Remove Recipe");
			System.out.println("3. Search Recipe");
			System.out.println("4. Sort Recipe ");
			System.out.println("5. Save Recipes To File");
			System.out.println("6. Load Recipes From File");
			System.out.println("7. Print All Recipes");
			System.out.println("Choose Your Option Or Press Any Key To EXIT");
			System.out.print("Your Option: ");
			char op = s.nextLine().charAt(0);
			try {
				switch (op) {
				case '1':
					add(list, s);
					break;
				case '2':
					remove(list, s);

					break;
				case '3':
					search(list, s);
					break;
				case '4':
					sort(list, s);
					break;
				case '5':
					list.saveRecipes();
					break;
				case '6':
					list.loadRecipes();
					break;
				case '7':
					System.out.println(list);
					break;
				default:
					isFinished = true;
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	private static void sort(RecipeList list, Scanner s) {
		System.out.println("SORT RECIPES:");
		System.out.println("1. By Name");
		System.out.println("2. By Ingredients");
		System.out.println("3. By Info");
		System.out.println("4. By Calories");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		switch (op) {
		case '1':
			list.setComparator(new SortByName());
			break;
		case '2':
			list.setComparator(new SortByIngredients());
			break;
		case '3':
			list.setComparator(new SortByInfo());
			break;
		case '4':
			list.setComparator(new SortByCalories());
			break;
		default:
			break;
		}
		list.sort();
	}

	private static void search(RecipeList list, Scanner s) throws Exception {
		System.out.println("SEARCH RECIPE BY NAME:");
		System.out.println("1. By Name");
		System.out.println("2. By Ingredients");
		System.out.println("3. By Info");
		System.out.println("4. By Calories");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		Recipe r = null;
		int index = -1;
		switch (op) {
		case '1':
			r = new Recipe(getNameFromUser(s), "", "", 1);
			Collections.sort(list, new SortByName());
			index = Collections.binarySearch(list, r, new SortByName());
			r = list.get(index);
			break;
		case '2':
			r = new Recipe("", getIngredientsFromUser(s), "", 1);
			Collections.sort(list, new SortByIngredients());
			index = Collections.binarySearch(list, r, new SortByIngredients());
			r = list.get(index);
			break;
		case '3':
			r = new Recipe("", "", getInfoFromUser(s), 1);
			Collections.sort(list, new SortByInfo());
			index = Collections.binarySearch(list, r, new SortByInfo());
			r = list.get(index);
			break;
		case '4':
			r = new Recipe("", "", "", getCaloriesFromUser(s));
			Collections.sort(list, new SortByCalories());
			index = Collections.binarySearch(list, r, new SortByCalories());
			r = list.get(index);
			break;
		default:
			break;
		}
		if (r!=null)
			System.out.println(r);
		else System.out.println("Recipe Not Found!");

	}

	private static void remove(RecipeList list, Scanner s) throws Exception {
		System.out.println("REMOVE RECIPE:");
		System.out.println("1. By Name");
		System.out.println("2. By Ingredients");
		System.out.println("3. By Info");
		System.out.println("4. By Calories");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		Recipe r = null;
		int index = -1;
		switch (op) {
		case '1':
			r = new Recipe(getNameFromUser(s), "", "", 1);
			Collections.sort(list, new SortByName());
			index = Collections.binarySearch(list, r, new SortByName());
			r = list.get(index);
			break;
		case '2':
			r = new Recipe("", getIngredientsFromUser(s), "", 1);
			Collections.sort(list, new SortByIngredients());
			index = Collections.binarySearch(list, r, new SortByIngredients());
			r = list.get(index);
			break;
		case '3':
			r = new Recipe("", "", getInfoFromUser(s), 1);
			Collections.sort(list, new SortByInfo());
			index = Collections.binarySearch(list, r, new SortByInfo());
			r = list.get(index);
			break;
		case '4':
			r = new Recipe("", "", "", getCaloriesFromUser(s));
			Collections.sort(list, new SortByCalories());
			index = Collections.binarySearch(list, r, new SortByCalories());
			r = list.get(index);
			break;
		default:
			break;
		}

		if (r != null) {
			System.out.println(r);
			System.out.println("Are You Sure? y/n");
			op = s.nextLine().charAt(0);
			if (op == 'y')
				list.remove(r);
			else
				System.out.println("Operation Canceled!");
		} else
			System.out.println("Recipe Not Found!");
		list.sort();

	}

	private static void add(RecipeList list, Scanner s) throws Exception {
		System.out.println("ADD/UPDATE RECIPE:");
		int size = list.size();
		boolean isLoaded = list.add(
				new Recipe(getNameFromUser(s), getIngredientsFromUser(s), getInfoFromUser(s), getCaloriesFromUser(s)));
		if (!isLoaded)
			System.out.println("RECIPE CAN NOT BE NULL!\n");
		else if (size != list.size())
			System.out.println("Recipe Added!\n");
		else
			System.out.println("Recipe Updated!\n");
	}

	private static String getNameFromUser(Scanner s) {
		System.out.print("Enter Recipe Name: ");
		return s.nextLine();
	}

	private static String getIngredientsFromUser(Scanner s) {
		System.out.print("Enter Recipe Ingredients: ");
		return s.nextLine();
	}

	private static String getInfoFromUser(Scanner s) {
		System.out.print("Enter Recipe Info: ");
		return s.nextLine();
	}

	private static int getCaloriesFromUser(Scanner s) {
		System.out.print("Enter Recipe Calories: ");
		return Integer.parseInt(s.nextLine());
	}

}
