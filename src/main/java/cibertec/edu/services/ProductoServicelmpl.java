package cibertec.edu.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.models.Producto;
import cibertec.edu.models.ProductoReport;
import cibertec.edu.repositories.ProductoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProductoServicelmpl implements ProductoService{

	@Autowired
	private ProductoDao productoRepository;

	@Override
	public List<Producto> getAllProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) this.productoRepository.findAll();
	}

	@Override
	public InputStream getReportProductos() throws JRException {
		
		try {
			List<Producto> listaProducto = this.getAllProductos();
			List<ProductoReport> listaData = new ArrayList<ProductoReport>();
			listaData.add(new ProductoReport());
			listaData.get(0).setProductosList(listaProducto);
			JRBeanCollectionDataSource dts = new JRBeanCollectionDataSource(listaData);
			Map<String, Object> parameters = new HashMap<>();
			JasperReport jasperReportObj = getJaserpReportCompiled();
			JasperPrint jPrint = JasperFillManager.fillReport(jasperReportObj, parameters, dts);
			InputStream result = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jPrint));
			return result;
		} catch (JRException ex) {
			throw ex;
		}
	}
	
	private JasperReport getJaserpReportCompiled() {
		try {
			InputStream studentReportStream = getClass().getResourceAsStream("/jasper/producto_report.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(studentReportStream);
			return jasperReport;
		} catch (Exception e) {
			return null;
		}
	}
	
}
