package app.core.service;

import java.util.List;

import app.core.entity.Company;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;

public interface AdminService {
	void addCompany(Company company) throws CouponSystemExceptions;

	void updateCompany(Company company) throws CouponSystemExceptions;

	void deleteCompany(int companyId) throws CouponSystemExceptions;

	List<Company> getAllCompanies();

	Company getCompanyById(int companyId) throws CouponSystemExceptions;

	Customer addCustomer(Customer customer) throws CouponSystemExceptions;

	void updateCustomer(Customer customer) throws CouponSystemExceptions;

	void deleteCustomer(int customerId) throws CouponSystemExceptions;

	List<Customer> getAllCustomers();

	Customer getOneCustomerById(int customerId) throws CouponSystemExceptions;

}
