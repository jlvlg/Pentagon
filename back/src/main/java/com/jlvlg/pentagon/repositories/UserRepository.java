package com.jlvlg.pentagon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.User;

/**
 * Defines methods to read and write to the User database
 * @author Lucas
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByAuth_Username(String username);
}
