package com.example.cursoapi.repository;

import com.example.cursoapi.model.Curso;
import com.example.cursoapi.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByEstudiantesContaining(Estudiante estudiante);
}
