package edu.ucla.wspedido.controlador;

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
import edu.ucla.wspedido.dao.ProductoDao;
import edu.ucla.wspedido.dominio.Cliente;
import edu.ucla.wspedido.dominio.Producto;

@Controller
@RequestMapping("cliente")
public class ServicioCliente {
	@Autowired
	ClienteDao daoCliente;
	
	@RequestMapping(value="/insertar",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody Map guardar(@RequestParam("rif") String rif, @RequestParam("razonSocial") String razonSocial, @RequestParam("direccion") String direccion,
						@RequestParam("telefono") String telefono) {
		Cliente cliente = new Cliente();
		cliente.setRif(rif);
		cliente.setRazonSocial(razonSocial);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		Map mapa = new HashMap<String,Object>(); 
		if (cliente.getId()!=null) {
			mapa.put("exito", "1");
			mapa.put("idCliente", cliente.getId());
		} else {
			mapa.put("exito", "0");
		}
		return mapa;
		
	}
	
	@RequestMapping(value="/modificar/{id}/{rif}/{razonSocial}/{direccion}/{telefono}",produces = "application/json", method = RequestMethod.PUT)
	public @ResponseBody Map guardar(@PathVariable("id") String id, @PathVariable("rif") String rif, @PathVariable("razonSocial") String razonSocial, @PathVariable("direccion") String direccion,
			@PathVariable("telefono") String telefono) {
		Cliente cliente = daoCliente.findOne(Integer.valueOf(id));
		cliente.setRif(rif);
		cliente.setRazonSocial(razonSocial);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		Map mapa = new HashMap<String,Object>(); 
		if (cliente.getId()!=null) {
			mapa.put("exito", "1");
		} else {
			mapa.put("exito", "0");
		}
		return mapa;

	}
	
	@RequestMapping(value="/buscar",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List<Cliente> buscar() {	
		return daoCliente.findAll();
	}
	
	@RequestMapping(value="/buscar/{id}",produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
	public @ResponseBody Cliente buscar(@PathVariable Integer id) {	
		return daoCliente.findOne(id);
	}
	
	@RequestMapping(value="/eliminar/{id}",produces = "application/json", method = RequestMethod.DELETE)
	public @ResponseBody Map eliminar(@PathVariable Integer id) {	
		daoCliente.delete(id);
		Map mapa = new HashMap<String,Object>(); 
		mapa.put("exito", "1");
		return mapa;
	}

}
