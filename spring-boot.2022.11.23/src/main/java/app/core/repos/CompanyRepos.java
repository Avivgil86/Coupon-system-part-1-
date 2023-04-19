package app.core.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entity.Company;

@Repository
public interface CompanyRepos extends JpaRepository<Company, Integer> {

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	Company findByEmail(String email);

}
