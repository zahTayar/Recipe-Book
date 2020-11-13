package Recipe;

import java.util.Comparator;

public class SortByInfo implements Comparator<Recipe> {

	@Override
	public int compare(Recipe o1, Recipe o2) {
		return o1.getInfo().compareTo(o2.getInfo());
	}

}
