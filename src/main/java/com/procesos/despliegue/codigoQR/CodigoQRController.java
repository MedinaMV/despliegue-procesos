package com.procesos.despliegue.codigoQR;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@RestController
@RequestMapping("qr_encoder")
@CrossOrigin("*")
public class CodigoQRController {

	@GetMapping(value = "/generate-qr/{text}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> generateQRCode(@PathVariable String text) throws Exception {
		int width = 300;
		int height = 300;
		String textoCodificado = Base64.getEncoder().encodeToString(text.getBytes());

		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix matrix = writer.encode(textoCodificado, BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToStream(matrix, "PNG", stream);

		byte[] imageBytes = stream.toByteArray();

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
	}
	
	@GetMapping("/saludo")
	public String saludar() {
		return "Antonio Apruebanos";
	}
}
