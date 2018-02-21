function rowClicked(table, value) {
	location.href = "/" + table + "/" + value + "/show";
}

function editClicked(table, value) {
	location.href = "/" + table + "/" + value +"/edit";
}

function addNewClicked(table) {
	location.href = "/" + table + "/new";
}

function deleteClicked(table, value) {
	location.href = "/" + table + "/" + value + "/deactivate";
}