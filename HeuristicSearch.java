import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public abstract class HeuristicSearch<E extends Comparable<E>> {
	
	//protected abstract E state;
	
	abstract boolean test(E e);
	
	abstract ArrayList<E> children(E e);
	
	protected PriorityQueue<E> options;	
	
	public HeuristicSearch(E e) {
		options = new PriorityQueue<E>();
		options.add(e);
	}
	//Collections.reverseOrder
	public HeuristicSearch(E e, Comparator<E> c) {
		options = new PriorityQueue<E>(11, c);
		options.add(e);
	}
	
	//trying to minimize the heuristic
	public E heuristicMinDFS() {
		while (!options.isEmpty()) {
			E top = options.poll();
			if (test(top)) {
				return top;
			}
			options.addAll(children(top));
		}
		return null;//solution not found
	}

	//trying to maximize the heuristic
	//DO: add a time!
	E heuristicMaxDFS(long ms) {
		E best = null;
		long startTime = System.nanoTime();
		while (!options.isEmpty() && System.nanoTime()-startTime<1000*ms) {
			E top = options.poll();
			if (test(top)) {
				if ((best == null) || (top.compareTo(best)==1)) {
					best = top;
				}
			} else {
				options.addAll(children(top));
			}
		}
		return best;
	}
}