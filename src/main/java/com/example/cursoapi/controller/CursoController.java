package com.example.cursoapi.controller;

import com.example.cursoapi.model.Curso;
import com.example.cursoapi.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    @PostMapping("/{cursoId}/estudiantes/{estudianteId}")
    public Curso asignarEstudiante(@PathVariable Long cursoId, @PathVariable Long estudianteId) {
        return cursoService.asignarEstudianteACurso(cursoId, estudianteId);
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<Curso> obtenerCursosDeEstudiante(@PathVariable Long estudianteId) {
        return cursoService.obtenerCursosDeEstudiante(estudianteId);
    }
}
