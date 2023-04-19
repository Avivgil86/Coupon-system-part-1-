package app.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.core.entity.Company;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.AdminServiceImpl;
import app.core.security.ClientType;
import app.core.security.LoginManager;
import app.core.service.AdminService;

@Component
@Order(1)
public class AdminServiceTest implements CommandLineRunner {
	@Autowired
	private LoginManager loginManager;

	private AdminService adminservice = null;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==============ADMIN TEST==============\n=======================================");
		logInTest();
		System.out.println("========================================================");
		addCompanyTest();
		System.out.println("========================================================");
		deleteCompanyTest();
		System.out.println("========================================================");
		updateCompanyTest();
		System.out.println("========================================================");
		getAllCompaniesTest();
		System.out.println("========================================================");
		getOneCompanyTest();
		System.out.println("========================================================");
		addCustomerTest();
		System.out.println("========================================================");
		updateCustomerTest();
		System.out.println("========================================================");
		deleteCustomerTest();
		System.out.println("========================================================");
		getAllCustomersTest();
		System.out.println("========================================================");
		getOneCustomerTest();
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");

	}

	public void logInTest() {
		try {
			adminservice = (AdminServiceImpl) loginManager.logIn("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
			System.out.println("Admin login succeeded ");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void addCompanyTest() {
		try {
			Company company = new Company(0, "Nike", "nike@gmail.com", "1234");
			Company company1 = new Company(0, "Adidas", "adidas@gmail.com", "1234");
			Company company2 = new Company(0, "Sacuni", "sacuni@gmail.com", "1234");
			adminservice.addCompany(company);
			adminservice.addCompany(company1);
			adminservice.addCompany(company2);
			System.out.println("Adding a company succeeded");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteCompanyTest() {
		try {
			adminservice.deleteCompany(3);
			System.out.println("The company was successfully deleted");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateCompanyTest() {
		try {
			Company company2 = new Company(2, "Sacuni", "sacuni1234@gmail.com", "1234");
			adminservice.updateCompany(company2);
			System.out.println("Company update successful");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}

	}

	public void getAllCompaniesTest() {
		System.out.println("Get all companies succeeded:");
		for (Company company : adminservice.getAllCompanies()) {
			System.out.println(company);
		}
	}

	public void getOneCompanyTest() {
		System.out.println("Get one company succeeded:");
		try {
			System.out.println(adminservice.getCompanyById(2));
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void addCustomerTest() {
		try {
			Customer customer = new Customer(0, "aviv", "gil", "avivgil@gmail.com", "1234", null);
			Customer customer1 = new Customer(0, "lior", "levy", "liorlevy@gmail.com", "1234", null);
			Customer customer2 = new Customer(0, "asaf", "ron", "asafron@gmail.com", "1234", null);
			adminservice.addCustomer(customer);
			adminservice.addCustomer(customer1);
			adminservice.addCustomer(customer2);
			System.out.println("Adding customer succeeded");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateCustomerTest() {
		try {
			Customer customer2 = new Customer(2, "elad", "hook", "eladhook@gmail.com", "1234", null);
			adminservice.updateCustomer(customer2);
			System.out.println("Customer update successful");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteCustomerTest() {
		try {
			adminservice.deleteCustomer(3);
			System.out.println("The customer was successfully deleted");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllCustomersTest() {
		System.out.println("Get all customers succeeded ");
		for (Customer customer : adminservice.getAllCustomers()) {
			System.out.println(customer);
			;

		}
	}

	public void getOneCustomerTest() {
		System.out.println("Get one customer succeeded ");
		try {
			System.out.println(adminservice.getOneCustomerById(2));
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}

	}

}
