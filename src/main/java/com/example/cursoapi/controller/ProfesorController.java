package com.example.cursoapi.controller;

import com.example.cursoapi.model.Profesor;
import com.example.cursoapi.service.ProfesorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> listarProfesores() {
        return profesorService.listarProfesores();
    }

    @PostMapping
    public Profesor crearProfesor(@RequestBody Profesor profesor) {
        return profesorService.guardarProfesor(profesor);
    }
}
