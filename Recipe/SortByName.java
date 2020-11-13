package Recipe;

import java.util.Comparator;

public class SortByName implements Comparator<Recipe> {

	@Override
	public int compare(Recipe o1, Recipe o2) {
		return o1.compareTo(o2);
	}

}
