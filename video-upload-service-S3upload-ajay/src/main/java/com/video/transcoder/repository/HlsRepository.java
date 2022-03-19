package com.video.transcoder.repository;

import com.video.transcoder.entity.HlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HlsRepository extends JpaRepository<HlsEntity,Long> {
}
