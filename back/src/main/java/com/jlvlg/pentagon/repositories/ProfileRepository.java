package com.jlvlg.pentagon.repositories;

import java.util.List;
import java.util.Optional;

import com.jlvlg.pentagon.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.User;

/**
 * Defines methods to read and write to the Page database
 * @author Lucas
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByUserAndActiveTrue(User user);
	List<Profile> findByUser_Auth_UsernameContainsIgnoreCaseAndActiveTrue(String username);
	List<Profile> findByNameContainsIgnoreCaseAndActiveTrue(String name);
}
