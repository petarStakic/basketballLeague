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

function formImgChanged() {
	
	console.log("inside formImgChanged");
	var uploadFormData = new FormData();
	uploadFormData.append("file", imgFileControl.files[0]);
	
	$.ajax({
		url: 'http://localhost:8080/file-tmp/upload',
		data: uploadFormData,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data)
		{
			console.log("Success! Returned data: " + data);
			$('#form-img').attr('src', data);
			$('#changeFlag').val('changed');
		}
	});
}