package com.vittorhonorato.caderno_dev.repository;

import com.vittorhonorato.caderno_dev.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
}
