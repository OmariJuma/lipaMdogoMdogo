package com.example.lipaMdogoMdogo.repository;

import com.example.lipaMdogoMdogo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface UserRepository extends JpaRepository<User, UUID> {
}
