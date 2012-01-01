package ru.sstu.cnr.patterns;

import ru.sstu.ocr.core.CharacterPattern;
import ru.sstu.ocr.core.CharacterType;

/**
 * <code>Latin</code> enumeration contains patterns for Latin letters.
 *
 * @author Denis_Murashev
 */
public enum Latin implements CharacterPattern {

	/**
	 * Letter A.
	 */
	LETTER_A('A', new float[][] {
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{+1.0f, +1.0f, -1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
	}),

	/**
	 * Letter B.
	 */
	LETTER_B('B', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +0.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +0.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Letter E.
	 */
	LETTER_E('E', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
	}),

	/**
	 * Letter K.
	 */
	LETTER_K('K', new float[][] {
		{+1.0f, -1.0f, -1.0f, +0.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, +1.0f, -1.0f},
		{+1.0f, +0.0f, +1.0f, -1.0f, -1.0f},
		{+1.0f, +1.0f, +1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, +1.0f, +0.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, +1.0f, +0.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
	}),

	/**
	 * Letter M.
	 */
	LETTER_M('M', new float[][] {
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, -1.0f, +1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, +1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, +1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
	}),

	/**
	 * Letter H.
	 */
	LETTER_H('H', new float[][] {
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
	}),

	/**
	 * Letter O.
	 */
	LETTER_O('O', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Letter P.
	 */
	LETTER_P('P', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
	}),

	/**
	 * Letter C.
	 */
	LETTER_C('C', new float[][] {
		{-1.0f, +1.0f, +1.0f, +1.0f, +0.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
		{+1.0f, +0.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, +1.0f, +1.0f, -1.0f},
	}),

	/**
	 * Letter T.
	 */
	LETTER_T('T', new float[][] {
		{+1.0f, +1.0f, +1.0f, +1.0f, +1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
	}),

	/**
	 * Letter Y.
	 */
	LETTER_Y('Y', new float[][] {
		{+1.0f, -1.0f, -1.0f, -1.0f, +1.0f},
		{+1.0f, +0.0f, -1.0f, +0.0f, +1.0f},
		{-1.0f, +1.0f, -1.0f, +1.0f, -1.0f},
		{-1.0f, +1.0f, +1.0f, +0.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, +1.0f, +0.0f, -1.0f, -1.0f},
		{+0.0f, +1.0f, -1.0f, -1.0f, -1.0f},
	}),

	/**
	 * Letter X.
	 */
	LETTER_X('X', new float[][] {
		{+0.5f, -1.0f, -1.0f, -1.0f, +1.0f},
		{-1.0f, +1.0f, -1.0f, +0.5f, +0.5f},
		{-1.0f, +0.5f, +1.0f, +1.0f, -1.0f},
		{-1.0f, -1.0f, +1.0f, -1.0f, -1.0f},
		{-1.0f, +0.0f, +1.0f, +0.0f, -1.0f},
		{-1.0f, +1.0f, -1.0f, +1.0f, -1.0f},
		{+1.0f, -1.0f, -1.0f, -1.0f, -1.0f},
	});

	private final char character;
	private final float[][] pattern;

	/**
	 * @param character character
	 * @param pattern   character pattern
	 */
	private Latin(char character, float[][] pattern) {
		this.character = character;
		this.pattern = pattern;
	}

	@Override
	public char getCharacter() {
		return character;
	}

	@Override
	public float[][] getPattern() {
		return pattern.clone();
	}

	@Override
	public CharacterType getType() {
		return CharacterType.LATIN;
	}
}
