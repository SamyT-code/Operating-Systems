import java.util.*;

class Q2 {

	private static int numFrames;

	public static void main(String[] args) {
		Random random = new Random();
		numFrames = random.nextInt(7) + 1;
		int[] refString = new int[12];
		for (int i = 0; i < refString.length; i++) {
			refString[i] = random.nextInt(10);
		}

		System.out.println("FIFO sur " + Arrays.toString(refString) + ", nombre de cadre pages = " + numFrames);
		System.out.println("nombre de défauts de page par FIFO: " + FIFO(refString));
		System.out.println();
		System.out.println("LRU sur " + Arrays.toString(refString) + ", nombre de cadre pages = " + numFrames);
		System.out.println("nombre de défauts de page par LRU: " + LRU(refString));

	}

	private static int FIFO(int[] refString) {
		int defauts = 0;
		List<Integer> res = new ArrayList<Integer>();
		Set<Integer> ram = new LinkedHashSet<>();

		for (int num : refString) {
			// System.out.println("RAM: " + Arrays.toString(ram.toArray()));
			if (ram.size() < numFrames) {
				if (!ram.contains(num)) {
					ram.add(num);
					res.add(num);
					defauts += 1;
				}
			} else {
				if (!ram.contains(num)) {
					int answer = res.remove(0);
					ram.remove(answer);
					ram.add(num);
					res.add(num);
					defauts += 1;
				}
			}
		}

		return defauts;
	}

	private static int LRU(int[] refString) {
		int defauts = 0;
		List<Integer> stack = new ArrayList<Integer>();
		List<Integer> ram = new ArrayList<Integer>();

		for (int num : refString) {
			// System.out.println("RAM: " + Arrays.toString(ram.toArray()));
			// System.out.println("Stack: " + Arrays.toString(stack.toArray()));
			if (ram.size() < numFrames) {
				if (!ram.contains(num)) {
					ram.add(num);
					stack.add(num);
					defauts += 1;
				}
			} else {
				if (!ram.contains(num)) {
					int answer = stack.get(0);
					int index = ram.indexOf(answer);

					// remove answer
					ram.remove(index);
					stack.remove(0);

					ram.add(index, num);
					stack.add(num);
					defauts += 1;
				} else {
					stack.remove(stack.indexOf(num));
					stack.add(num);
				}
			}
		}

		return defauts;
	}
}