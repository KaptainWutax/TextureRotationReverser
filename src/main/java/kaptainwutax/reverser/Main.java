package kaptainwutax.reverser;

import kaptainwutax.seedutils.mc.MCVersion;

public class Main {

	public static void main(String[] args) {
		Search search = new Search(MCVersion.v1_15,
				Search.info(new Pos(0, 0, 0), Block.NETHERRACK, 12),
				Search.info(new Pos(0, 1, 0), Block.NETHERRACK, 9),
				Search.info(new Pos(20, 50, 11), Block.STONE, 1),
				Search.info(new Pos(2, 44, -18), Block.DIRT, 0),
				Search.info(new Pos(2, 44, -17), Block.DIRT, 3)
		);

		search.startNaiveSearch(new Pos(-100, 0, -100), new Pos(100, 0, 100), (x, y, z) -> {
			System.out.format("(%d, %d, %d) \n", x, y, z);
			return true;
		});
	}

}
