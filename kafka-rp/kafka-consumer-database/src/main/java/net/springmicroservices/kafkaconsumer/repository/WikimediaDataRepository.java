package net.springmicroservices.kafkaconsumer.repository;

import net.springmicroservices.kafkaconsumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
