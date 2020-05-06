package kaptainwutax.reverser;

import kaptainwutax.seedutils.mc.MCVersion;

public class Search {

	private final MCVersion version;
	private final Info[] infos;

	public Search(MCVersion version, Info... infos) {
		this.version = version;
		this.infos = infos;
	}

	public static Info info(Pos pos, Block block, int model) {
		return new Info(pos, block, model);
	}

	public static Info info(int posX, int posY, int posZ, Block block, int model) {
		return new Info(new Pos(posX, posY, posZ), block, model);
	}

	public void startNaiveSearch(Pos min, Pos max, PosBooleanSupplier supplier) {
		int minX = Math.max(Math.min(min.x, max.x), -30000000);
		int minY = Math.max(Math.min(min.y, max.y), 0);
		int minZ = Math.max(Math.min(min.z, max.z), -30000000);

		int maxX = Math.min(Math.max(min.x, max.x), 30000000);
		int maxY = Math.min(Math.max(min.y, max.y), 255);
		int maxZ = Math.min(Math.max(min.z, max.z), 30000000);

		for(int x = minX; x <= maxX; x++) {
			for(int y = minY; y <= maxY; y++) {
				for(int z = minZ; z <= maxZ; z++) {
					boolean mismatch = false;

					for(Info info: this.infos) {
						if(!info.test(x, y, z, this.version)) {
							mismatch = true;
							break;
						}
					}

					if(!mismatch && !supplier.shouldContinue(x, y, z)) {
						return;
					}
				}
			}
		}
	}

	public static class Info {
		private final Pos pos;
		private final Block block;
		private final int model;

		private Info(Pos pos, Block block, int model) {
			this.pos = pos;
			this.block = block;
			this.model = model;

			if(model < 0 || model >= this.block.getTotalWeight()) {
				throw new RuntimeException("Model " + model + " is invalid for block with weight " + this.block.getTotalWeight());
			}
		}

		public boolean test(int x, int y, int z, MCVersion version) {
			return Rotation.getModel(this.pos.x + x, this.pos.y + y, this.pos.z + z,
					this.block.getTotalWeight(), version) == this.model;
		}
	}

	@FunctionalInterface
	public interface PosBooleanSupplier {
		boolean shouldContinue(int x, int y, int z);
	}

}
