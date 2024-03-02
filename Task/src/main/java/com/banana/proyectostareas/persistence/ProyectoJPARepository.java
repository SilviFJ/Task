package com.banana.proyectostareas.persistence;

import com.banana.proyectostareas.model.Proyecto;
import com.banana.proyectostareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProyectoJPARepository extends JpaRepository<Proyecto, Long> {

    public List<Proyecto> findAll() throws RuntimeException;

    public Proyecto save(Proyecto proyecto) throws RuntimeException;

    public Proyecto update(Proyecto proyecto) throws RuntimeException;

}
