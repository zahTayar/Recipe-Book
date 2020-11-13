package Recipe;


import java.util.Comparator;

public class SortByCalories implements Comparator<Recipe> {

	@Override
	public int compare(Recipe o1, Recipe o2) {
		return o1.getCalories()- o2.getCalories();
	}

}
