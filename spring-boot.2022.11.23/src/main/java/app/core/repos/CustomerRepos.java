package app.core.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entity.Customer;

@Repository
public interface CustomerRepos extends JpaRepository<Customer, Integer> {

	List<Customer> findByFirstName(String firstName);

	Customer findByEmail(String Email);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);
}
