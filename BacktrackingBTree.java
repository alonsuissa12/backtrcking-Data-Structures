import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

//import BTree.Node;

//import BTree.Node;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}

	// You are to implement the function Backtrack.
	public void Backtrack() {
		if (!stack.isEmpty()) {
			List<Object[]> LL = stack.pop();
			if (LL.isEmpty())
				root = null;
			else {
				for (Object[] bt : LL) {
					Node<T> node = (BTree.Node<T>) bt[0];
					int idx = (int) bt[2];
					if (bt[1] == BTreeActionType.SPLIT) {
						merge(node);
					} else if (bt[1] == BTreeActionType.NONE) {
						if (node.isLeaf()) {
							node.removeKey(idx);
						}
					}
				}

			}
			size--; 
		}

	}

	private void merge(Node<T> node) {
		Node<T> father = node.parent;
		if (father.numOfKeys == 1) {
			root = node;
			node.parent = null;
		}

		else {
			int numberOfKeys = node.getNumberOfKeys();
			int medianIndex = numberOfKeys / 2;
			T medianValue = node.getKey(medianIndex);
			father.removeChild(father.indexOf(medianValue) + 1); // remove right
			father.removeChild(father.indexOf(medianValue)); // remove left
			father.removeKey(medianValue);// remove the key of midian value
			father.addChild(node); // add origin child

		}
	}

	// Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample() {
		List<Integer> ans = new LinkedList<Integer>();
		ans.add(1);
		ans.add(2);
		ans.add(3);
		ans.add(4);
		ans.add(5);
		ans.add(6);
		return ans;
	}

}
