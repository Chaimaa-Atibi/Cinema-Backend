package org.sid.cinema.dao;

import org.sid.cinema.entity.ProjectionMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public interface ProjectionRepository extends JpaRepository<ProjectionMovie, Long> {
}
