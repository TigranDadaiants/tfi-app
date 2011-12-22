package ru.sstu.cnr.core;

import ru.sstu.properties.core.Property;

/**
 * <code>RecognizerSettings</code> class contains settings for
 * car number recognizer engine.
 *
 * @author Denis_Murashev
 * @since CNR 1.0
 */
public class RecognizerSettings {

	@Property("min.band.height")
	private int minBandHeight;

	@Property("band.peak.foot")
	private float bandPeakFoot;

	@Property("invert.color.threshold")
	private float invertColorThreshold;

	@Property("rough.symbol.peak.foot")
	private float roughSymbolPeakFoot;

	@Property("symbol.dispersion.threshold")
	private float symbolDispersionThreshold;

	@Property("symbol.peak.value.threshold")
	private float symbolPeakValueThreshold;

	@Property("symbol.peak.area.threshold")
	private float symbolPeakAreaThreshold;

	@Property("black.white.threshold")
	private float blackWhiteThreshold;

	@Property("fine.symbol.peak.foot")
	private float fineSymbolPeakFoot;

	@Property("min.symbol.width")
	private int minSymbolWidth;

	@Property("rus.letter.threshold")
	private float rusLetterThreshold;

	/**
	 * @return the minBandHeight
	 */
	public int getMinBandHeight() {
		return minBandHeight;
	}

	/**
	 * @param minBandHeight the minBandHeight to set
	 */
	public void setMinBandHeight(int minBandHeight) {
		this.minBandHeight = minBandHeight;
	}

	/**
	 * @return the bandPeakFoot
	 */
	public float getBandPeakFoot() {
		return bandPeakFoot;
	}

	/**
	 * @param bandPeakFoot the bandPeakFoot to set
	 */
	public void setBandPeakFoot(float bandPeakFoot) {
		this.bandPeakFoot = bandPeakFoot;
	}

	/**
	 * @return the invertColorThreshold
	 */
	public float getInvertColorThreshold() {
		return invertColorThreshold;
	}

	/**
	 * @param invertColorThreshold the invertColorThreshold to set
	 */
	public void setInvertColorThreshold(float invertColorThreshold) {
		this.invertColorThreshold = invertColorThreshold;
	}

	/**
	 * @return the roughSymbolPeakFoot
	 */
	public float getRoughSymbolPeakFoot() {
		return roughSymbolPeakFoot;
	}

	/**
	 * @param roughSymbolPeakFoot the roughSymbolPeakFoot to set
	 */
	public void setRoughSymbolPeakFoot(float roughSymbolPeakFoot) {
		this.roughSymbolPeakFoot = roughSymbolPeakFoot;
	}

	/**
	 * @return the symbolDispersionThreshold
	 */
	public float getSymbolDispersionThreshold() {
		return symbolDispersionThreshold;
	}

	/**
	 * @param symbolDispersionThreshold the symbolDispersionThreshold to set
	 */
	public void setSymbolDispersionThreshold(float symbolDispersionThreshold) {
		this.symbolDispersionThreshold = symbolDispersionThreshold;
	}

	/**
	 * @return the symbolPeakValueThreshold
	 */
	public float getSymbolPeakValueThreshold() {
		return symbolPeakValueThreshold;
	}

	/**
	 * @param symbolPeakValueThreshold the symbolPeakValueThreshold to set
	 */
	public void setSymbolPeakValueThreshold(float symbolPeakValueThreshold) {
		this.symbolPeakValueThreshold = symbolPeakValueThreshold;
	}

	/**
	 * @return the symbolPeakAreaThreshold
	 */
	public float getSymbolPeakAreaThreshold() {
		return symbolPeakAreaThreshold;
	}

	/**
	 * @param symbolPeakAreaThreshold the symbolPeakAreaThreshold to set
	 */
	public void setSymbolPeakAreaThreshold(float symbolPeakAreaThreshold) {
		this.symbolPeakAreaThreshold = symbolPeakAreaThreshold;
	}

	/**
	 * @return the blackWhiteThreshold
	 */
	public float getBlackWhiteThreshold() {
		return blackWhiteThreshold;
	}

	/**
	 * @param blackWhiteThreshold the blackWhiteThreshold to set
	 */
	public void setBlackWhiteThreshold(float blackWhiteThreshold) {
		this.blackWhiteThreshold = blackWhiteThreshold;
	}

	/**
	 * @return the fineSymbolPeakFoot
	 */
	public float getFineSymbolPeakFoot() {
		return fineSymbolPeakFoot;
	}

	/**
	 * @param fineSymbolPeakFoot the fineSymbolPeakFoot to set
	 */
	public void setFineSymbolPeakFoot(float fineSymbolPeakFoot) {
		this.fineSymbolPeakFoot = fineSymbolPeakFoot;
	}

	/**
	 * @return the minSymbolWidth
	 */
	public int getMinSymbolWidth() {
		return minSymbolWidth;
	}

	/**
	 * @param minSymbolWidth the minSymbolWidth to set
	 */
	public void setMinSymbolWidth(int minSymbolWidth) {
		this.minSymbolWidth = minSymbolWidth;
	}

	/**
	 * @return the rusLetterThreshold
	 */
	public float getRusLetterThreshold() {
		return rusLetterThreshold;
	}

	/**
	 * @param rusLetterThreshold the rusLetterThreshold to set
	 */
	public void setRusLetterThreshold(float rusLetterThreshold) {
		this.rusLetterThreshold = rusLetterThreshold;
	}
}
