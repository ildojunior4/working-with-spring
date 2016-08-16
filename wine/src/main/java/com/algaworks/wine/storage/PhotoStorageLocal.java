package com.algaworks.wine.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("storage-local")
@Component
public class PhotoStorageLocal implements PhotoStorage, PhotoReader {

	private Path local;

	public PhotoStorageLocal() {
		// Unix/Linux =	$HOME , Windows = USERPROFILE , Cygwin = $USERPROFILE
		this.local = getDefault().getPath(System.getenv("USERPROFILE"), ".winephoto");
		try {
			Files.createDirectories(this.local);
		} catch (IOException e) {
			throw new RuntimeException("Error creating folder to save photo", e);
		}
	}

	@Override
	public String save(MultipartFile photo) {
		String photoName = photo.getOriginalFilename();
		try {
			photo.transferTo(
					new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + photoName));
		} catch (IOException e) {
			throw new RuntimeException("Error salving photo", e);
		}
		return photoName;
	}

	@Override
	public String getUri(String photoName) {
		return "http://localhost:8080/photos/"+photoName;
	}

	@Override
	public byte[] recover(String photoName) {
		try {
			return Files.readAllBytes(this.local.resolve(photoName));
		} catch (IOException e) {
			throw new RuntimeException("Photo Recover error", e);
		}
	}

}
