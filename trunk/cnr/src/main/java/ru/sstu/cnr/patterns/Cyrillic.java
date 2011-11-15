package ru.sstu.cnr.patterns;

import ru.sstu.ocr.core.CharacterType;
import ru.sstu.ocr.core.CharacterPattern;

/**
 * <code>Cyrillic</code> enumeration contains patterns for Cyrillic letters.
 *
 * @author Denis_Murashev
 */
public enum Cyrillic implements CharacterPattern {

	/**
	 * Letter &#0410;.
	 */
	LETTER_0410('\u0410', new float[][] {
		{-1.0f, -0.5f, +1.0f, +0.0f, -1.0f},
		{-1.0f, +0.2f, +0.8f, +0.5f, -1.0f},
		{-0.5f, +0.8f, +0.5f, +1.0f, -0.5f},
		{+0.0f, +1.0f, -0.5f, +0.8f, +0.2f},
		{+0.5f, +0.5f, +0.5f, +0.5f, +0.5f},
		{+0.7f, +0.7f, +0.0f, +0.7f, +0.7f},
		{+1.0f, -0.5f, -1.0f, -0.5f, +1.0f},
	}),

	/**
	 * Letter &#0423;.
	 */
	LETTER_0423('\u0423', new float[][] {
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{+0.0f, +1.0f, +0.0f, +1.0f, +0.0f},
		{-1.0f, +0.0f, +1.0f, +0.0f, -1.0f},
		{+0.0f, +1.0f, +0.0f, -1.0f, -1.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, -1.0f},
	}),

	/**
	 * Letter &#0425;.
	 */
	LETTER_0425('\u0425', new float[][] {
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{+0.0f, +1.0f, +0.0f, +1.0f, +0.0f},
		{-1.0f, +0.0f, +1.0f, +0.0f, -1.0f},
		{+0.0f, +1.0f, +0.0f, +1.0f, +0.0f},
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
	});

	private final char character;
	private final float[][] pattern;

	/**
	 * @param character character
	 * @param pattern   character pattern
	 */
	private Cyrillic(char character, float[][] pattern) {
		this.character = character;
		this.pattern = pattern;
	}

	@Override
	public char getCharacter() {
		return character;
	}

	@Override
	public float[][] getPattern() {
		return pattern;
	}

	@Override
	public CharacterType getType() {
		return CharacterType.CYRILLIC;
	}
}
