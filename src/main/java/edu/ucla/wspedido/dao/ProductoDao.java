package edu.ucla.wspedido.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucla.wspedido.dominio.Producto;

public interface ProductoDao extends JpaRepository<Producto, Integer> {

}
