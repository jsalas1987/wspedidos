package edu.ucla.wspedido.controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ucla.wspedido.dao.ClienteDao;
import edu.ucla.wspedido.dao.PedidoDao;
import edu.ucla.wspedido.dao.ProductoDao;
import edu.ucla.wspedido.dominio.Cliente;
import edu.ucla.wspedido.dominio.Pedido;
import edu.ucla.wspedido.dominio.Producto;

@Controller
@RequestMapping("pedido")
public class ServicioPedido {
	@Autowired
	PedidoDao daoPedido;
	@Autowired
	ProductoDao daoProducto;
	
	@RequestMapping(value="/insertar",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody Map guardar(@RequestParam("descripcion") String descripcion, @RequestParam("cantidad") Integer cantidad, @RequestParam("unidad") String unidad,
						@RequestParam("fecha") String fecha, @RequestParam("dias") Integer dias) {
		Pedido pedido = new Pedido();
		pedido.setDescripcion(descripcion);
		pedido.setCantidad(cantidad);
		pedido.setUnidad(unidad);
		pedido.setFecha(new Date());
		pedido.setDiasEntrega(dias);
		Map mapa = new HashMap<String,Object>(); 
		if (pedido.getId()!=null) {
			mapa.put("exito", "1");
			mapa.put("idPedido", pedido.getId());
		} else {
			mapa.put("exito", "0");
		}
		return mapa;
		
	}
	
	@RequestMapping(value="/modificar/{id}/{descripcion}/{cantidad}/{unidad}/{fecha}/{dias}/{idproducto}",produces = "application/json", method = RequestMethod.PUT)
	public @ResponseBody Map guardar(@PathVariable("id") String id,@PathVariable("descripcion") String descripcion, @PathVariable("cantidad") String cantidad, @PathVariable("unidad") String unidad,
			@PathVariable("fecha") String fecha, @PathVariable("dias") String dias, @PathVariable("idproducto") String idproducto) {
		Pedido pedido = daoPedido.findOne(Integer.valueOf(id));
		pedido.setDescripcion(descripcion);
		pedido.setCantidad(Integer.valueOf(cantidad));
		pedido.setUnidad(unidad);
		pedido.setFecha(new Date());
		pedido.setDiasEntrega(Integer.valueOf(dias));
		Producto producto = daoProducto.findOne(Integer.valueOf(id));
		pedido.setProducto(producto);
		Map mapa = new HashMap<String,Object>(); 
		if (pedido.getId()!=null && producto!=null) {
			mapa.put("exito", "1");
		} else {
			mapa.put("exito", "0");
		}
		return mapa;

	}
	
	@RequestMapping(value="/buscar",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> buscar() {	
		return daoPedido.findAll();
	}
	
	@RequestMapping(value="/buscar/{id}",produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
	public @ResponseBody Pedido buscar(@PathVariable Integer id) {	
		return daoPedido.findOne(id);
	}
	
	@RequestMapping(value="/eliminar/{id}",produces = "application/json", method = RequestMethod.DELETE)
	public @ResponseBody Map eliminar(@PathVariable Integer id) {	
		daoPedido.delete(id);
		Map mapa = new HashMap<String,Object>(); 
		mapa.put("exito", "1");
		return mapa;
	}


}
