package kaptainwutax.reverser;

import kaptainwutax.seedutils.lcg.LCG;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.util.Mth;

public class Rotation {

	private static final int MASK_31 = (int)Mth.mask(31);
	private static final LCG SKIP = LCG.JAVA.combine(2);

	public static int getModel(int posX, int posY, int posZ, int totalWeight, MCVersion version) {
		long seed = getRenderingSeed(posX, posY, posZ);

		if(version.isOlderThan(MCVersion.v1_13)) {
			return Math.abs((int)seed) % totalWeight;
		} else {
			return (int)(SKIP.nextSeed(seed ^ LCG.JAVA.multiplier) >> 16 & MASK_31) % totalWeight;
		}
	}

	public static long getRenderingSeed(int posX, int posY, int posZ) {
		long seed = (long)(posX * 3129871) ^ (long)posZ * 116129781L ^ (long)posY;
		seed = seed * seed * 42317861L + seed * 11L;
		return seed >> 16;
	}

}
