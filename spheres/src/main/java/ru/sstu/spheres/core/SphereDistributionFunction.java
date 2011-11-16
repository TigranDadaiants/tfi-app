package ru.sstu.spheres.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SphereDistributionFunction {

	private static final int SIGMA_COUNT = 2;
	private static final int NEIGHBOURHOOD = 1;

	private SphereRecognizerSettings config;
	private float[][][] data;
	private int radiusCount;
	private float threshold;
	private Map<Integer, List<Offset>> offsetsCache
			= new HashMap<Integer, List<Offset>>();

	public SphereDistributionFunction(SphereRecognizerSettings config,
			int width, int height) {
		this.config = config;
		radiusCount = config.getMaxRadius() - config.getMinRadius() + 1;
		threshold = (float) (2 * Math.PI * config.getThreshold());
		data = new float[width][height][radiusCount];
	}

	public List<Sphere> findPeaks() {
		float max = 0.0f;
		List<Sphere> peaks = new ArrayList<Sphere>();
		int shift = NEIGHBOURHOOD;
		for (int i = shift; i < data.length - shift; i++) {
			for (int j = shift; j < data[0].length - shift; j++) {
				for (int k = shift; k < data[0][0].length - shift; k++) {
					if (data[i][j][k] > max) {
						max = data[i][j][k];
					}
					if (isPeak(i, j, k)) {
						peaks.add(new Sphere(i, j, indexToRadius(k)));
						System.out.format("Max value is %f at (%d, %d) with radius %d%n",
									data[i][j][k], i, j, indexToRadius(k));
					}
				}
			}
		}
		System.out.println(" Max = " + max);
		return peaks;
	}

	private boolean isPeak(int x, int y, int r) {
		if (data[x][y][r] < threshold) {
			return false;
		}
		for (int i = x - NEIGHBOURHOOD; i <= x + NEIGHBOURHOOD; i++) {
			for (int j = y - NEIGHBOURHOOD; j <= y + NEIGHBOURHOOD; j++) {
				for (int k = r - NEIGHBOURHOOD; k < r; k++) {
					if (data[i][j][k] >= data[x][y][r]) {
						return false;
					}
				}
				for (int k = r; k <= r + NEIGHBOURHOOD; k++) {
					if (data[i][j][k] > data[x][y][r]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void addValue(int x, int y) {
		for (int r = 0; r < radiusCount; r++) {
			List<Offset> offsets = getOffsets(r);
			for (Offset o : offsets) {
				addValue(x + o.dx, y + o.dy, r, o.weight);
			}
		}
	}

	private void addValue(int x, int y, int r, float value) {
		if (x < 0 || x >= data.length) {
			return;
		}
		if (y < 0 || y >= data[0].length) {
			return;
		}
		data[x][y][r] += value;
	}

	private int indexToRadius(int index) {
		return config.getMinRadius() + index;
	}

	private List<Offset> getOffsets(int index) {
		List<Offset> cache = offsetsCache.get(index);
		if (cache == null) {
			cache = new ArrayList<Offset>();
			int r = indexToRadius(index);
			calculateOffset(0, 0, r, cache);
			int max = (int) Math.ceil(r + SIGMA_COUNT * config.getSigma());
			for (int x = 1; x <= max; x++) {
				for (int y = 0; y <= x; y++) {
					calculateOffset(x, y, r, cache);
				}
			}
			offsetsCache.put(index, cache);
		}
		return cache;
	}

	private void calculateOffset(int dx, int dy, int r, List<Offset> offsets) {
		float dr = (float) (r - Math.sqrt(dx * dx + dy * dy));
		if (Math.abs(dr) > SIGMA_COUNT * config.getSigma()) {
			return;
		}
		float sigma2 = config.getSigma() * config.getSigma();
		float weight = (float) Math.exp(-dr * dr / sigma2) / r;
		Offset offset = new Offset();
		offset.dx = dx;
		offset.dy = dy;
		offset.weight = weight;
		offsets.add(offset);
		if (dx == 0 && dy == 0) {
			return;
		}
		if (dx == 0) {
			offset = new Offset();
			offset.dx = 0;
			offset.dy = -dy;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = dy;
			offset.dy = 0;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = -dy;
			offset.dy = 0;
			offset.weight = weight;
			offsets.add(offset);
			return;
		}
		if (dx == dy) {
			offset = new Offset();
			offset.dx = dx;
			offset.dy = -dy;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = -dx;
			offset.dy = dy;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = -dx;
			offset.dy = -dy;
			offset.weight = weight;
			offsets.add(offset);
			return;
		}
		offset = new Offset();
		offset.dx = dy;
		offset.dy = dx;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = dx;
		offset.dy = -dy;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = -dy;
		offset.dy = dx;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = -dx;
		offset.dy = dy;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = dy;
		offset.dy = -dx;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = -dx;
		offset.dy = -dy;
		offset.weight = weight;
		offsets.add(offset);
		offset = new Offset();
		offset.dx = -dy;
		offset.dy = -dx;
		offset.weight = weight;
		offsets.add(offset);
	}

	private static class Offset {
		private int dx;
		private int dy;
		private float weight;
	}
}
