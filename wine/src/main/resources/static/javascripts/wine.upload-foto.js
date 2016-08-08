$(function(){
	var uploadDrop = $('#upload-drop');
	
	var settings = {
		type: 'json',
		filelimit: 1,
		allow: '*.(jpg|jpeg|png)',
		action: '/photos/' + uploadDrop.data('code'),
		complete: function(photo){
			console.log("....resonse", photo.name);
		}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop(uploadDrop, settings);

});