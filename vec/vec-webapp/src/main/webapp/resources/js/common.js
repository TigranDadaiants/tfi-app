/*
 * Returns Y-offset.
 */
function getYOffset() {
	if (typeof(window.pageYOffset) == 'number') {
		return window.pageYOffset;
	}
	return document.documentElement.scrollTop;
}
