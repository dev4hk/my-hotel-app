package org.example.myhotelweb.repository;

import org.example.myhotelweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}