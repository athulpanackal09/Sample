package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer>
{
	public Users findByEmail(String email);
}

