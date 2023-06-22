package cibertec.edu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.models.Producto;

@Repository
public interface ProductoDao extends CrudRepository<Producto, Long>{

}
