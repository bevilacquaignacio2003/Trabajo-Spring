package com.example.cursoapi.service;

import com.example.cursoapi.model.Curso;
import com.example.cursoapi.model.Estudiante;
import com.example.cursoapi.repository.CursoRepository;
import com.example.cursoapi.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;

    public CursoService(CursoRepository cursoRepository, EstudianteRepository estudianteRepository) {
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso asignarEstudiante(Long cursoId, Long estudianteId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(estudianteId);

        if(cursoOpt.isPresent() && estudianteOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            Estudiante estudiante = estudianteOpt.get();
            curso.getEstudiantes().add(estudiante);
            estudiante.getCursos().add(curso);

            estudianteRepository.save(estudiante);
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso o Estudiante no encontrado");
        }
    }

    public List<Curso> cursosDeEstudiante(Long estudianteId) {
        return estudianteRepository.findById(estudianteId)
                .map(est -> List.copyOf(est.getCursos()))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
}
