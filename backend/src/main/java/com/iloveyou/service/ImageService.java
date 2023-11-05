package com.iloveyou.service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveyou.entity.Image;
import com.iloveyou.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;

	private static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}
		try {
			outputStream.close();
		} catch (Exception e) {
		}
		return outputStream.toByteArray();
	}

	private static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
		} catch (Exception exception) {
		}

		return outputStream.toByteArray();
	}

	public ResponseEntity<String> setImage(MultipartFile file) {
		try {
			Image image = imageRepository.save(Image.builder()
					.name(file.getOriginalFilename())
					// .type(file.getContentType())
					.bytes(compressImage(file.getBytes())).build());

			ObjectMapper mapper = new ObjectMapper();
			return ResponseEntity.ok(mapper.writeValueAsString(new ImageResponse(image.getId())));
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}

	public String getImage(Long id) {
		Optional<Image> data = imageRepository.findById(id);
		byte[] image = decompressImage(data.get().getBytes());
		String base64Image = Base64.getEncoder().encodeToString(image);
		return base64Image;
	}

	@SuppressWarnings("unused")
	private class ImageResponse {
		final Long id;

		public ImageResponse(Long id) {
			this.id = id;
		}

		public Long getId() {
			return this.id;
		}
	}
}
