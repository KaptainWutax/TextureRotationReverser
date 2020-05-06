package kaptainwutax.reverser;

public class Block {

	private final int totalWeight;

	private Block(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public int getTotalWeight() {
		return this.totalWeight;
	}

	public static final Block BEDROCK = new Block(4);
	public static final Block CONCRETE_POWDER = new Block(4);
	public static final Block DIRT = new Block(4);
	public static final Block GRASS_PATH = new Block(4);
	public static final Block INFESTED_STONE = new Block(4);
	public static final Block LILY_PAD = new Block(4);
	public static final Block NETHERRACK = new Block(16);
	public static final Block SAND = new Block(4);
	public static final Block RED_SAND = new Block(4);
	public static final Block STONE = new Block(4);

}
