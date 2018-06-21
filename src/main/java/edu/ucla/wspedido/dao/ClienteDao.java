package edu.ucla.wspedido.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucla.wspedido.dominio.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Integer> {

}
