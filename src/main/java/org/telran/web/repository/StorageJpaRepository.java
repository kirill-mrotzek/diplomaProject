package org.telran.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.web.entity.Storage;

@Repository
public interface StorageJpaRepository extends JpaRepository<Storage, Long> {
}
