package edu.ucla.wspedido.controlador;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ucla.wspedido.dao.ProductoDao;
import edu.ucla.wspedido.dominio.Producto;

@Controller
@RequestMapping("producto")
public class ServicioProducto {
	@Autowired
	ProductoDao daoProducto;
	
	@RequestMapping(value="/insertar",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody Map guardar(@RequestParam("codigo") String codigo, @RequestParam("nombre") String nombre, @RequestParam("precio") String precio,
						@RequestParam("costo") String costo, @RequestParam("existencia") String existencia) {
		Producto producto = new Producto();
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setPrecio(Double.parseDouble(precio));
		producto.setCosto(Double.parseDouble(costo));
		producto.setExistencia(Integer.valueOf(existencia));
		daoProducto.save(producto);
		Map mapa = new HashMap<String,Object>(); 
		if (producto.getId()!=null) {
			mapa.put("exito", "1");
			mapa.put("idProducto", producto.getId());
		} else {
			mapa.put("exito", "0");
		}
		return mapa;
		
	}
	
	@RequestMapping(value="/modificar/{id}/{codigo}/{nombre}/{precio}/{costo}/{existencia}",produces = "application/json", method = RequestMethod.PUT)
	public @ResponseBody Map guardar(@PathVariable("id") Integer id, @PathVariable("codigo") String codigo, @PathVariable("nombre") String nombre, @PathVariable("precio") String precio,
						@PathVariable("costo") String costo, @PathVariable("existencia") String existencia) {
		
		Producto producto = daoProducto.findOne(id);
		producto.setId(id);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setPrecio(Double.valueOf(precio));
		producto.setCosto(Double.valueOf(costo));
		producto.setExistencia(Integer.valueOf(existencia));
		daoProducto.save(producto);
		Map mapa = new HashMap<String,String>(); 
		mapa.put("exito", "1");
		
		return mapa;
	}
	
	@RequestMapping(value="/buscar",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List<Producto> buscar() {	
		return daoProducto.findAll();
	}
	
	@RequestMapping(value="/buscar/{id}",produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
	public @ResponseBody String buscar(@PathVariable Integer id) {	
		Producto prod = daoProducto.findOne(id);
		StringWriter w = new StringWriter();
		if (prod==null) {
			prod = new Producto();
		}
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Producto.class);
			Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(prod, w);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w.toString();
	}
	
	@RequestMapping(value="/eliminar/{id}",produces = "application/json", method = RequestMethod.DELETE)
	public @ResponseBody Map eliminar(@PathVariable Integer id) {	
		daoProducto.delete(id);
		Map mapa = new HashMap<String,Object>(); 
		mapa.put("exito", "1");
		return mapa;
	}
	

}
