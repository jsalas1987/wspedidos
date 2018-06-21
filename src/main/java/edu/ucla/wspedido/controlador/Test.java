package edu.ucla.wspedido.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("test")
public class Test {
	
	@RequestMapping(value="/insertar",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody String insertar() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		String url = "http://localhost:8080/wspedido/producto/insertar";
		MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
		values.add("codigo", "0001"); 
		values.add("nombre", "Ron diplomatico"); 
		values.add("precio", "100");
		values.add("costo", "150");
		values.add("existencia", "40");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(values, headers);
		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
		//String response = restTemplate.postForObject(url, values, String.class);
		System.out.println("Insertar: "+response);
		return response.getBody();
	}
	
	@RequestMapping(value="/modificar/{id}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody void insertar(@PathVariable Integer id) {
		String url = "http://localhost:8080/wspedido/producto/modificar/"+id+"/0001/RON DIPLOMATICO/100/150/45";
		RestTemplate restTemplate = new RestTemplate();
		
			
		restTemplate.put(url, null);
		
		
		//System.out.println("Modifcar: "+response);
	}
	
	@RequestMapping(value="/buscar/{id}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody String buscar(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/wspedido/producto/buscar/"+id;
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Buscar uno: "+response);
		
		return response;
	}
	
	@RequestMapping(value="/buscar",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody String pedirServicioGet() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/wspedido/producto/buscar";
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Buscar todos: "+response);
		return response;
	}
	
	@RequestMapping(value="/eliminar/{id}",produces = "application/json", method = RequestMethod.GET)
	public Map eliminar(@PathVariable Integer id) {
		String url = "http://localhost:8080/wspedido/producto/eliminar/"+id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url);
		Map mapa = new HashMap<String,Object>();
		mapa.put("exito", "1");
		return mapa;
	}

}
