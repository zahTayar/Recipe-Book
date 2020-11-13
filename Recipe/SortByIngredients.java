package Recipe;

import java.util.Comparator;

public class SortByIngredients implements Comparator<Recipe> {

	@Override
	public int compare(Recipe o1, Recipe o2) {
		return o1.getIngredients().compareTo(o2.getIngredients());
	}

}
