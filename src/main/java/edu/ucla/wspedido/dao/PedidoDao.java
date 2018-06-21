package edu.ucla.wspedido.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucla.wspedido.dominio.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Integer> {

}
