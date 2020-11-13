package Recipe;


import java.io.Serializable;
import java.util.Comparator;

public class Recipe implements Comparable<Recipe>, Serializable{
	
	/**
	 * @author Grigory Shaulov
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String ingredients;
	private String info;
	private int calories;
	public static boolean isSortByName = true;
	
	/**
	 * @param name: name of recipe
	 * @param ingredients: list of ingredients 
	 * @param info: to do
	 * @param calories: calories
	 * @throws Exception: parameters can not be null
	 */
	public Recipe(String name, String ingredients, String info, int calories) throws Exception {
		setName(name);
		setIngredients(ingredients);
		setInfo(info);
		setCalories(calories);
	}

	/**
	 * @return: Name of recipe
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if(name == null) throw new Exception("Name Can Not Be Null!");
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) throws Exception {
		if(ingredients == null) throw new Exception("Ingredients Can Not Be Null!");
		this.ingredients = ingredients;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) throws Exception {
		if(info == null) throw new Exception("Info Can Not Be Null!");
		this.info = info;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) throws Exception {
		if(calories <= 0) throw new Exception("Calories Can Not Be Zero or Negative!");
		this.calories = calories;
	}

	
	@Override
	public String toString() {
		return "[" + name + ":\tIngredients: " + ingredients + "\tInfo:" + info + "\tCalories: " + calories	+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Recipe r) {
		if(isSortByName)
			return name.compareTo(r.name);
		else
			return calories-r.calories;
	}
	
	class SortByName implements Comparator<Recipe>{

		@Override
		public int compare(Recipe a, Recipe b) {
			return a.getName().compareTo(b.getName());
		}
		
	}
	
	
}
