package cibertec.edu.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import cibertec.edu.services.ProductoServicelmpl;

public class ProductoController {
	
	@Autowired
	private ProductoServicelmpl productoService;

	@GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reporteProductos(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JRException {
		try {
			InputStream report = this.productoService.getReportProductos();
			byte[] data = report.readAllBytes();
			report.close();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_PDF);
			header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"reporte_productos.pdf\"");
			header.setContentLength(data.length);
			return new ResponseEntity<byte[]>(data, header, HttpStatus.CREATED);
		} catch (IOException ex) {
			throw new RuntimeException("IOError retornado archivo");
		}
	}
	
}
