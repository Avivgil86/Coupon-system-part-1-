package app.core.imple;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.service.AdminService;
import app.core.service.ClientService;
@Scope("prototype")
@Service
@Transactional
public class AdminServiceImpl extends ClientService implements AdminService {

	/*
	 * @param email,password
	 * 
	 * @return true
	 * 
	 * @throws CouponSystemExceptions if admin email or password not valid
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemExceptions {
		if ((email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) == false) {
			throw new CouponSystemExceptions("admin email or password not valid");
		}
		return true;
	}

	/*
	 * @param company
	 * 
	 * @throws CouponSystemExceptions if the company already exist by name or email
	 */
	@Override
	public void addCompany(Company company) throws CouponSystemExceptions {
		if (companyRepos.existsByName(company.getName())) {
			throw new CouponSystemExceptions("Already exist by name");
		}
		if (companyRepos.existsByEmail(company.getEmail())) {
			throw new CouponSystemExceptions("Already exist by email");
		}
		companyRepos.save(company);
	}

	/*
	 * @param company
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exists
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemExceptions {
		Company companyToUpdate = companyRepos.findById(company.getId())
				.orElseThrow(() -> new CouponSystemExceptions("this company doesnt exists"));
		companyToUpdate.setEmail(company.getEmail());
		companyToUpdate.setPassword(company.getPassword());
		System.out.println("company updated done");
		companyRepos.saveAndFlush(companyToUpdate);
	}

	/*
	 * @param companyId
	 */
	private void deletePurchaseCompany(int companyId) throws CouponSystemExceptions {
		List<Coupon> couponsToDelete = couponRepos.findAllCompanyCouponsByCompanyId(companyId);
		for (Coupon coupon : couponsToDelete) {
			couponRepos.deleteAllPurchaseCouponsById(coupon.getId());
		}
	}

	/*
	 * @param companyId
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exists
	 */
	@Override
	public void deleteCompany(int companyId) throws CouponSystemExceptions {
		if (companyRepos.existsById(companyId)) {
			deletePurchaseCompany(companyId);
			companyRepos.deleteById(companyId);
		} else {
			throw new CouponSystemExceptions("this company doesnt exists");

		}
	}

	/*
	 * @return all the companies
	 */
	@Override
	public List<Company> getAllCompanies() {
		return companyRepos.findAll();
	}

	/*
	 * @param companyId
	 * 
	 * @return company by id
	 */
	@Override
	public Company getCompanyById(int companyId) throws CouponSystemExceptions {
		return companyRepos.findById(companyId).orElseThrow(() -> new CouponSystemExceptions("company not exists"));
	}

	/*
	 * @param customer
	 * 
	 * @throws CouponSystemExceptions if the customer is alreadry exists
	 */
	@Override
	public Customer addCustomer(Customer customer) throws CouponSystemExceptions {
		if (!customerRepos.existsById(customer.getId())) {
			return customerRepos.save(customer);
		} else {
			throw new CouponSystemExceptions("this customer is alreadry exists");

		}
	}

	/*
	 * @param customer
	 * 
	 * @throws CouponSystemExceptions if the customer not exists
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponSystemExceptions {
		Customer customerToUpdate = customerRepos.findById(customer.getId())
				.orElseThrow(() -> new CouponSystemExceptions(" this customer doesnt exists"));
		customerToUpdate.setEmail(customer.getEmail());
		customerToUpdate.setFirstName(customer.getFirstName());
		customerToUpdate.setLastName(customer.getFirstName());
		customerToUpdate.setPassword(customer.getPassword());
		customerRepos.saveAndFlush(customerToUpdate);
	}

	/*
	 * @param customerId
	 * 
	 * @throws CouponSystemExceptions if the customer not exists
	 */
	@Override
	public void deleteCustomer(int customerId) throws CouponSystemExceptions {
		customerRepos.findById(customerId).orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		couponRepos.deleteAllPurchaseCustomerCouponsById(customerId);
		customerRepos.deleteById(customerId);
	}

	/*
	 * @return all customers
	 */
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepos.findAll();
	}

	/*
	 * @param customerId
	 * 
	 * @return customer
	 * 
	 * @throws CouponSystemExceptions if the specified customer id not exists
	 */
	@Override
	public Customer getOneCustomerById(int customerId) throws CouponSystemExceptions {
		Customer customer = customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		return customer;
	}

}
