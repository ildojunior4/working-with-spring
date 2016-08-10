$(function(){
	var uploadDrop = $('#upload-drop');
	var photoContainer = $('.js-container-foto');
	
	var settings = {
		type: 'json',
		filelimit: 1,
		allow: '*.(jpg|jpeg|png)',
		action: '/photos/' + uploadDrop.data('code'),
		complete: function(photo){
			uploadDrop.addClass('hidden');
			photoContainer.prepend('<img src="'+ photo.uri + '">');
		}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop(uploadDrop, settings);

});