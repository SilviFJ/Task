package com.banana.proyectostareas.controller;

import com.banana.proyectostareas.exception.ProyectoNotfoundException;
import com.banana.proyectostareas.exception.TareaNotfoundException;
import com.banana.proyectostareas.model.Proyecto;
import com.banana.proyectostareas.model.Tarea;
import com.banana.proyectostareas.service.ProyectoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TareaController {
    @Autowired
    private ProyectoTareaService proyectoTareaService;

    // Historia de usuario 2.Como usuario anónimo quiero poder añadir tareas a uno de mis
    // proyectos para tener una lista ordenada de trabajos.

    @PostMapping("/proyectos/{idProyecto}/tareas")
    public ResponseEntity<Proyecto> añadirTareaAProyecto(@PathVariable Long idProyecto, @RequestBody Tarea tarea){
        try {
            Proyecto proyectoActualizado = proyectoTareaService.anadeTareaAProyecto(idProyecto, tarea);
            return new ResponseEntity<>(proyectoActualizado, HttpStatus.OK);
        } catch (ProyectoNotfoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Historia de usuario 4.Como usuario anónimo quiero ver la lista ordenada de tareas de
    // un proyecto para ejecutarlas en orden.

    @GetMapping("/proyectos/{idProyecto}/tareas")
    public ResponseEntity<List<Tarea>> obtenerTareasDelProyecto(@PathVariable Long idProyecto){
        try{
            List<Tarea> tareas = proyectoTareaService.obtenerTareasDelProyecto(idProyecto);
            return new ResponseEntity<List<Tarea>>(tareas, HttpStatus.OK);
        } catch (ProyectoNotfoundException e){
            return new ResponseEntity<List<Tarea>>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e){
            return new ResponseEntity<List<Tarea>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Historia de usuario 5.Como usuario quiero marcar una tarea como completada para
    // olvidarme de ella.

    @PostMapping("/tareas/{idTarea}/completada")
    public ResponseEntity<Tarea> marcarTareaComoCompletada(@PathVariable Long idTarea){
        try{
            Tarea tarea = proyectoTareaService.marcarTareaHecha(idTarea);
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } catch (TareaNotfoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
