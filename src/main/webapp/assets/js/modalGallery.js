let click = false;
var modal;
var body = document.getElementById('body');
function openModal(id) {
	modal = document.getElementById(id);
	if (click == false) {
		modal.style.display = "flex";
		click = true;
	}
	window.scrollTo(0, 0);
	body.style.overflowY="hidden";
}
function closeModal(id){
	modal = document.getElementById(id);
	modal.style.display = "none";
	click = false;
	body.style.overflowY="auto";
}
