import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BacktrackingAVL extends AVLTree {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingAVL() {
		super();
	}

	// You are to implement the function Backtrack.
	public void Backtrack() {
		if (!stack.isEmpty()) {
			Object[] bt = stack.pop();
			Node x = (AVLTree.Node) bt[1];
			boolean isRoot = x == root;
			if (bt[0] == ImbalanceCases.NO_IMBALANCE) {// backtracking the inserted node.
				if (isRoot)
					root = null;
				else {
					Node father = x.parent;
					if (father.left == x)
						father.left = null;
					else
						father.right = null;
					updateSizes(x.parent);
				}
			}else {
				if (bt[0] == ImbalanceCases.LEFT_LEFT) {
					Node XFather = x.parent;
					Node y = rotateLeft(x);
					if (isRoot) {
						root = y;
					} else { // x isn't root
						if (x.equals(XFather.left))
							XFather.left = y;
						else
							XFather.right = y;

					}
				} else if (bt[0] == ImbalanceCases.RIGHT_RIGHT) {
					Node XFather = x.parent;
					Node y = rotateRight(x);
					if (isRoot) {
						root = y;
					} else { // x isn't root
						if (x.equals(XFather.left))
							XFather.left = y;
						else
							XFather.right = y;
					}

				} else if (bt[0] == ImbalanceCases.LEFT_RIGHT) {
					Node XFather = x.parent;
					Node y = rotateLeft(x);
					if (isRoot) {
						root = y;
					} else { // x isn't root
						if (x.equals(XFather.left))
							XFather.left = y;
						else
							XFather.right = y;
					}
					y.left = rotateRight(x);

				} else if (bt[0] == ImbalanceCases.RIGHT_LEFT) {
					Node XFather = x.parent;
					Node y = rotateRight(x);
					if (isRoot) {
						root = y;
					} else { // x isn't root
						if (x.equals(XFather.left))
							XFather.left = y;
						else
							XFather.right = y;
					}
					y.right = rotateLeft(x);
				}
				Backtrack();
				while (x != null) {
					x.updateHeight();
					x = x.parent;
				}
			}
		}
	}

	private void updateSizes(Node node) {
		while (node != null) {
			node.size--;
			node = node.parent;
		}
	}

	// Change the list returned to a list of integers answering the requirements
	public static List<Integer> AVLTreeBacktrackingCounterExample() {
		List<Integer> list = new LinkedList<Integer>();
		list.add(3);
		list.add(2);
		list.add(1);
		return list;
	}

	public int Select(int index) {
		if (root == null)
			throw new NoSuchElementException();
		return select(index, root);
		
	}

	private int select(int index, Node node) {
		int leftSize = 0;
		if (node.left != null)
			leftSize = node.left.size;
		int r = leftSize + 1;
		if (r == index)
			return node.value;
		else if (index < r)
			return select(index, node.left);
		else
			return select((index - r), node.right);
	}

	public int Rank(int value) {
		return rank(root, value);
	}

	private int rank(Node node, int value) {
		int leftSize = 0;
		if (node.left != null)
			leftSize = node.left.size;
		if (node.value == value)
			return leftSize + 1;
		else if (node.value > value)
			return rank(node.left, value);
		else
			return rank(node.right, value) + leftSize + 1;
	}

}
