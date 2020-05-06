package kaptainwutax.reverser;

import java.util.Map;

public class BadMatchTable<T> {

	private final T[] pattern;
	private Map<T, Integer> table;

	public BadMatchTable(T[] pattern) {
		this.pattern = pattern;
		this.compute();
	}

	private void compute() {
		for(int i = 0; i < this.pattern.length; i++) {
			int value = this.pattern.length - i - 1;
			this.table.put(this.pattern[i], this.table.getOrDefault(this.pattern[i], 0));
		}
	}

	public double getAverageSkip() {
		return (double)this.table.values().stream().mapToInt(Integer::intValue).sum() / this.pattern.length;
	}

	public int getSkip(T value) {
		return this.table.getOrDefault(value, this.pattern.length);
	}

}
