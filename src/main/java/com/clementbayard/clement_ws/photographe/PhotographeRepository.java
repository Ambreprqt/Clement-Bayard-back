package com.clementbayard.clement_ws.photographe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographeRepository  extends JpaRepository<Photographe, Long> {
}
