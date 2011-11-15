package ru.sstu.cnr.patterns;

import ru.sstu.ocr.core.CharacterType;
import ru.sstu.ocr.core.CharacterPattern;

/**
 * <code>Digit</code> enumeration contains patterns for digits.
 *
 * @author Denis_Murashev
 */
public enum Digit implements CharacterPattern {

	/**
	 * Digit 0.
	 */
	DIGIT_0('0', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Digit 1.
	 */
	DIGIT_1('1', new float[][] {
		{-1.0f, -1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, +1.0f, -1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
	}),

	/**
	 * Digit 2.
	 */
	DIGIT_2('2', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, +0.5f, +1.0f, -1.0f, -1.0f},
		{+0.5f, +0.5f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
	}),

	/**
	 * Digit 3.
	 */
	DIGIT_3('3', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +0.5f, +1.0f},
		{-1.0f, -1.0f, +0.5f, +0.5f, -1.0f},
		{-1.0f, -1.0f, +1.0f, +0.5f, -1.0f},
		{-1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, +0.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Digit 4.
	 */
	DIGIT_4('4', new float[][] {
		{-1.0f, -1.0f, +1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, +1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, +1.0f},
	}),

	/**
	 * Digit 5.
	 */
	DIGIT_5('5', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{-1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Digit 6.
	 */
	DIGIT_6('6', new float[][] {
		{-1.0f, -1.0f, +1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, +1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +0.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Digit 7.
	 */
	DIGIT_7('7', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +0.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +0.5f, +0.5f},
		{-1.0f, -1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, +0.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, +1.0f, +0.5f, -1.0f, -1.0f},
	}),

	/**
	 * Digit 8.
	 */
	DIGIT_8('8', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+0.2f, +1.0f, +1.0f, +1.0f, +0.2f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Digit 9.
	 */
	DIGIT_9('9', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, +0.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +0.0f, +1.0f},
		{-1.0f, -1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, +0.0f, -1.0f},
	});

	private final char character;
	private final float[][] pattern;

	/**
	 * @param character character
	 * @param pattern   character pattern
	 */
	private Digit(char character, float[][] pattern) {
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
		return CharacterType.DIGIT;
	}
}
