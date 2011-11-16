package ru.sstu.spheres.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DistributionFunction {

	private static final int SIGMA_COUNT = 2;
	private static final int NEIGHBOURHOOD = 1;

	/**
	 * Empirically calculated norma.
	 */
	private static final float NORMA = 11.088f;

	private SphereRecognizerSettings config;
	private float radius;
	private float[][] data;
	private List<Offset> cache = null;

	public DistributionFunction(SphereRecognizerSettings config, float radius,
			int width, int height) {
		this.config = config;
		this.radius = radius;
		data = new float[width][height];
	}

	public List<Sphere> findPeaks(List<Sphere> spheres) {
		float max = 0.0f;
		int shift = NEIGHBOURHOOD;
		for (int i = shift; i < data.length - shift; i++) {
			for (int j = shift; j < data[0].length - shift; j++) {
				if (data[i][j] > max) {
					max = data[i][j];
				}
				if (isPeak(i, j)) {
					Sphere sphere = new Sphere(i, j, radius);
					sphere.setProbability(data[i][j]);
					updatePeaks(sphere, spheres);
				}
			}
		}
		System.out.println(" Max = " + max);
		return spheres;
	}

	private boolean isPeak(int x, int y) {
		if (data[x][y] < config.getThreshold()) {
			return false;
		}
		return data[x][y] > data[x - 1][y]
				&& data[x][y] > data[x + 1][y]
				&& data[x][y] > data[x][y - 1]
				&& data[x][y] > data[x][y + 1];
	}

	private void updatePeaks(Sphere peak, List<Sphere> peaks) {
		Iterator<Sphere> iterator = peaks.iterator();
		while (iterator.hasNext()) {
			Sphere sphere = iterator.next();
			if (peak.isSame(sphere, config.getSigma())) {
				if (peak.getProbability() > sphere.getProbability()) {
					iterator.remove();
				} else {
					return;
				}
			}
		}
		peaks.add(peak);
	}

	public void addValue(int x, int y) {
		List<Offset> offsets = getOffsets();
		for (Offset o : offsets) {
			addValue(x + o.dx, y + o.dy, o.weight);
		}
	}

	private void addValue(int x, int y, float value) {
		if (x < 0 || x >= data.length) {
			return;
		}
		if (y < 0 || y >= data[0].length) {
			return;
		}
		data[x][y] += value;
	}

	private List<Offset> getOffsets() {
		if (cache == null) {
			cache = new ArrayList<Offset>();
			calculateOffset(0, 0, cache);
			int max = (int) Math.ceil(radius + SIGMA_COUNT * config.getSigma());
			for (int x = 1; x <= max; x++) {
				for (int y = 0; y <= x; y++) {
					calculateOffset(x, y, cache);
				}
			}
		}
		return cache;
	}

	private void calculateOffset(int dx, int dy, List<Offset> offsets) {
		float dr = (float) (radius - Math.sqrt(dx * dx + dy * dy));
		if (Math.abs(dr) > SIGMA_COUNT * config.getSigma()) {
			return;
		}
		float sigma2 = config.getSigma() * config.getSigma();
		float weight = (float) Math.exp(-dr * dr / sigma2)
				/ radius / NORMA / config.getSigma();
		Offset offset = new Offset();
		offset.dx = dx;
		offset.dy = dy;
		offset.weight = weight;
		offsets.add(offset);
		if (dx == 0 && dy == 0) {
			return;
		}
		if (dy == 0) {
			offset = new Offset();
			offset.dx = -dx;
			offset.dy = 0;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = 0;
			offset.dy = dx;
			offset.weight = weight;
			offsets.add(offset);
			offset = new Offset();
			offset.dx = 0;
			offset.dy = -dx;
			offset.weight = weight;
			offsets.add(offset);
		} else if (dx == dy) {
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
		} else {
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
	}

	public static void main(String[] args) {
		SphereRecognizerSettings config = new SphereRecognizerSettings();
		float average = 0.0f;
		int count = 0;
		for (int sigma = 1; sigma <= 10; sigma++) {
			config.setSigma(3);
			for (int r = 5; r <= 100; r++) {
				DistributionFunction function = new DistributionFunction(config,
						r, 0, 0);
				List<Offset> offsets = function.getOffsets();
				float sum = 0;
				for (Offset o : offsets) {
					sum += o.weight;
				}
				System.out.println("Sum: " + sum);
				average += sum;
				count++;
			}
		}
		average /= count;
		System.out.println("Average: " + average);
	}

	private static class Offset {
		private int dx;
		private int dy;
		private float weight;
	}
}
