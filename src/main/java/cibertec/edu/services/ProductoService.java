package cibertec.edu.services;

import java.io.InputStream;
import java.util.List;

import cibertec.edu.models.Producto;

public interface ProductoService {
	
	public List<Producto> getAllProductos();
	
	public InputStream getReportProductos() throws Exception;

}
