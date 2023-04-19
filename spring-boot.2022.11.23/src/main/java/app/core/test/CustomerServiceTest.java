package app.core.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.CustomerServiceImpl;
import app.core.security.ClientType;
import app.core.security.LoginManager;
import app.core.service.CustomerService;

@Component
@Order(3)
public class CustomerServiceTest implements CommandLineRunner {
	@Autowired
	private LoginManager loginManager;
	private CustomerService customerService = null;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==============CUSTOMER TEST==============\n=======================================");
		logInTest();
		System.out.println("========================================================");
		purchaseCouponTest();
		System.out.println("========================================================");
		getAllCouponsByCustomerTest();
		System.out.println("========================================================");
		getAllCustomerCouponsByCategoryTest();
		System.out.println("========================================================");
		getAllCustomerCouponsByPriceTest();
		System.out.println("========================================================");
		getAllCustomerDetails();
		System.out.println("========================================================");

	}

	public void logInTest() {
		try {
			customerService = (CustomerServiceImpl) loginManager.logIn("avivgil@gmail.com", "1234",
					ClientType.CUSTOMER);
			System.out.println("Customer login succeeded");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void purchaseCouponTest() {
		Company company = new Company(1, "Nike", "nike@gmail.com", "1234");
		Coupon coupon = new Coupon(1, company, "marvel", "book", LocalDate.of(2022, 11, 27), LocalDate.of(2022, 11, 28),
				100, 50, "dog", Category.FOOD);
		try {
			customerService.purchaseCoupon(coupon);
			System.out.println("Purchase coupon succeeded");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllCouponsByCustomerTest() {
		System.out.println("Get all coupons succeeded");
		try {
			for (Coupon coupon : customerService.getAllCouponsCustomer()) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllCustomerCouponsByCategoryTest() {
		System.out.println("Get all coupons by category succeeded");
		try {
			for (Coupon coupon : customerService.getAllCouponsCustomerByCategory(Category.FOOD)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllCustomerCouponsByPriceTest() {
		System.out.println("Get all coupons by price succeeded");
		try {
			for (Coupon coupon : customerService.getAllCouponsCustomerByPrice(50)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());

		}
	}

	public void getAllCustomerDetails() {
		System.out.println("Get customer details succeeded");
		try {
			System.out.println(customerService.getCustomerDetails());
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());

		}
	}

}
