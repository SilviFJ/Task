package com.banana.proyectostareas.controller;

import com.banana.proyectostareas.model.Proyecto;
import com.banana.proyectostareas.service.ProyectoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProyectoController {

    @Autowired
    private ProyectoTareaService proyectoTareaService;

    // Historia de usuario 1.Como usuario anónimo quiero poder crear proyectos, para poder
    // gestionar una lista asociada de tareas.

    @PostMapping("/proyectos")
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        try{
            Proyecto proyectoCreado = proyectoTareaService.crearProyecto(proyecto);
            return new ResponseEntity<>(proyectoCreado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Historia de usuario 3.Como usuario anónimo quiero poder mostrar la lista de mis
    // proyectos para decidir cuál gestionar.

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> obtenerProyectos() {
        try{
            List<Proyecto> proyectos = proyectoTareaService.obtenerProyectos();
            return new ResponseEntity<List<Proyecto>>(proyectos, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<List<Proyecto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}