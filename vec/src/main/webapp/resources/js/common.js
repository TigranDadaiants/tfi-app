/*
 * Shows modal panel.
 * @param id - modal panel id
 * @param offset - Y-offset 
 */
function showModalWindow(id, offset) {
	Richfaces.showModalPanel(id, {top: getYOffset() + offset});
}

/*
 * Returns Y-offset.
 */
function getYOffset() {
	if (typeof(window.pageYOffset) == 'number') {
		return window.pageYOffset;
	}
	return document.documentElement.scrollTop;
}
