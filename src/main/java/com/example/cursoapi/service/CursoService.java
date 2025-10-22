package com.example.cursoapi.service;

import com.example.cursoapi.model.Curso;
import com.example.cursoapi.model.Estudiante;
import com.example.cursoapi.model.Profesor;
import com.example.cursoapi.repository.CursoRepository;
import com.example.cursoapi.repository.EstudianteRepository;
import com.example.cursoapi.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Listar todos los cursos
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    // Crear un curso con su profesor
    public Curso crearCurso(Curso curso) {
        if (curso.getProfesor() != null) {
            Optional<Profesor> prof = profesorRepository.findById(curso.getProfesor().getId());
            prof.ifPresent(curso::setProfesor);
        }
        return cursoRepository.save(curso);
    }

    // Asignar un estudiante a un curso
    public Curso asignarEstudianteACurso(Long cursoId, Long estudianteId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(estudianteId);

        if (cursoOpt.isPresent() && estudianteOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            Estudiante estudiante = estudianteOpt.get();
            curso.getEstudiantes().add(estudiante);
            return cursoRepository.save(curso);
        }
        return null;
    }

    // Obtener cursos de un estudiante
    public List<Curso> obtenerCursosDeEstudiante(Long estudianteId) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(estudianteId);
        if (estudianteOpt.isPresent()) {
            return cursoRepository.findByEstudiantesContaining(estudianteOpt.get());
        }
        return List.of();
    }
}
