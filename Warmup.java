
public class Warmup {
	public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack) {
		// TODO: implement your code here
		int ans = -1;
		int i = 0;
		boolean found = false;
		while (i < arr.length & !found) {
			for (int j = 0; j < forward & i < arr.length & !found; j++) {
				if (arr[i] == x) {
					found = true;
					ans = i;
				}
				myStack.push(i);
				i++;
			}
			if (i != arr.length) {
				for (int j = 0; j < back & !found; j++) {
					i = (int) myStack.pop();
					if (arr[i] == x) {
						found = true;
						ans = i;
					}
				}
			}
		}
		return ans;
	}

	public static int consistentBinSearch(int[] arr, int x, Stack myStack) {
		// TODO: implement your code here
		int low = 0, high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == x) {
				return mid;			}
			else if (arr[mid] > x) {
				high = mid - 1;
				int[] a = {low, high};
				myStack.push(a);
			}
			else {
				low = mid + 1;
				int[] a = {low, high};
				myStack.push(a);
			}
			int inconsistencies = Consistency.isConsistent(arr);
				while (inconsistencies > 0) {
					int[] bt = (int[]) myStack.pop();
					low = bt[0];
					high = bt[1];
				}
		}
		return -1; 
	}

	public static void main (String[] args) {
		int[] arr = {1,2,4};
		Stack s = new Stack();
		System.out.println(consistentBinSearch(arr, 4, s));
	}

}


