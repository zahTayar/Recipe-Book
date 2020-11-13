package Recipe;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class RecipeList extends LinkedList<Recipe> implements Iterable<Recipe> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comparator<Recipe> comparator;

	public RecipeList() {
		super();
		this.comparator = new SortByName();
	}

	public boolean add(Recipe r) {
		if (r == null)
			return false;
		Collections.sort(this, new SortByName());
		int index = Collections.binarySearch(this, r, new SortByName());
		if (index >= 0)
			super.set(index, r);
		else
			super.add(Math.abs(index + 1), r);
		sort();
		return true;
	}

	public void sort() {
		Collections.sort(this, comparator);
	}

	public Comparator<Recipe> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Recipe> comparator) {
		this.comparator = comparator;
	}

	public Recipe remove(int index) {
		if (index >= 0 && index < size())
			return super.remove(index);
		return null;
	}

	public Recipe get(int index) {
		if (index >= 0 && index<size())
			return super.get(index);
		return null;
	}

	public void saveRecipes() {
		try (ObjectOutputStream oOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("recipes.obj")))) {
			oOut.writeInt(size());
			for (int i=0; i<size(); i++)
				oOut.writeObject(get(i));
			System.out.println(size() + " Recipes Saved!\n");
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File recipes.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Save Exception: " + e.getMessage());
		}
	}

	public void loadRecipes() {
		try (ObjectInputStream oIn = new ObjectInputStream(	new BufferedInputStream(new FileInputStream("recipes.obj")))) {
			int size = size(), count = oIn.readInt(), added = 0, updated = 0, err = 0;
			boolean isLoaded = false;
			while (count > (added+updated+err) ){
				isLoaded = add((Recipe) oIn.readObject());
				if(isLoaded) {
					if(size==size())
						updated++;
					else {
						added++;
						size++;	
					}
				}else
					err++;
			}
			if(added+updated>0)
				System.out.printf("%d Recipes Loaded! Added: %d Updated: %d\n", (added+updated), added, updated);
			else
				System.out.println("File Is Empty!");
			
		} catch (FileNotFoundException e) {
			System.out.println("Load Exception: File recipes.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File recipes.obj Not Contains Recipe Data!");
		}
	}

	@Override
	public String toString() {
		if (size() > 0) {
			String str = "Recipes:\n";
			String ch = "";
			for (int i=0; i<size(); i++) {
				if (comparator instanceof SortByName && !ch.equals(get(i).getName().substring(0, 1))) {
					ch = get(i).getName().substring(0, 1);
					str += ch.toUpperCase() + "\n";
				}
				str += get(i) + "\n";
			}
			str += "\nNumber Of Recipes: " + size() + "\n";
			return str;
		}
		return "List Is Empty.\n";
	}

	@Override
	public Iterator<Recipe> iterator() {
		return new RecipeIterator(this);
	}

	class RecipeIterator implements Iterator<Recipe> {

		private RecipeList list;
		public RecipeList getList() {
			return list;
		}

		public void setList(RecipeList list) {
			this.list = list;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		private int index;

		public RecipeIterator(RecipeList rlist) {
			this.list = rlist;
			this.index = -1;
		}

		@Override
		public boolean hasNext() {
			return (index + 1) < size();
		}

		@Override
		public Recipe next() {
			if (hasNext())
				return list.get(++index);
			return null;
		}
		
		public void remove() {
			if(index>=0 && index<list.size())
				list.remove(index);
		}

	}

}
