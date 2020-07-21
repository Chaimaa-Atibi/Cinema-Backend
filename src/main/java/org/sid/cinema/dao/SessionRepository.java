package org.sid.cinema.dao;

import org.sid.cinema.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public interface SessionRepository extends JpaRepository<Session, Long> {
}
