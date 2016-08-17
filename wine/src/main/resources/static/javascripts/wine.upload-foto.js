var Wine = Wine || {};

Wine.UploadPhoto = (function() { 
	
	function UploadPhoto() {
		this.uploadDrop = $('#upload-drop');
		this.containerFoto = $('.js-container-foto');
	}
	
	UploadPhoto.prototype.iniciar = function() {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/photos/' + this.uploadDrop.data('code'),
			complete: onUploadCompleted.bind(this), 
			beforeSend: addCsrfToken
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	function addCsrfToken(xhr) {
		var header = $('input[name=_csrf_header]').val();
		var token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}
	
	function onUploadCompleted(photo) {
		this.uploadDrop.addClass('hidden');
		this.containerFoto.prepend('<img src="' + photo.uri + '" class="img-responsive" style="margin: auto"/>');
	}
	
	return UploadPhoto;
	
})();

$(function() {
	
	var uploadPhoto = new Wine.UploadPhoto();
	uploadPhoto.iniciar();
	
});