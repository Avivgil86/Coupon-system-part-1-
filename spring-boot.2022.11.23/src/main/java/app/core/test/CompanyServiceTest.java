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
import app.core.imple.CompanyServiceImpl;
import app.core.security.ClientType;
import app.core.security.LoginManager;
import app.core.service.CompanyService;

@Component
@Order(2)
public class CompanyServiceTest implements CommandLineRunner {
	@Autowired
	private LoginManager loginManager;

	private CompanyService companyService = null;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==============COMPANY TEST==============\n=======================================");
		logInTest();
		System.out.println("========================================================");
		addNewCouponTest();
		System.out.println("========================================================");
		updateCoupon();
		System.out.println("========================================================");
		deleteCoupon();
		System.out.println("========================================================");
		getAllCouponsByCompany();
		System.out.println("========================================================");
		getAllCouponsCompanyByCategory();
		System.out.println("========================================================");
		getAllCouponsCompanyByPrice();
		System.out.println("========================================================");
		getCompanyDetails();
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");

		

	}

	public void logInTest() {
		try {
			companyService = (CompanyServiceImpl) loginManager.logIn("nike@gmail.com", "1234", ClientType.COMPANY);
			System.out.println("Company login succeeded");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void addNewCouponTest() {
		Company company = new Company(1, "Nike", "nike@gmail.com", "1234");
		Company company1 = new Company(2, "Adidas", "adidas@gmail.com", "1234");
		try {
			Coupon coupon = new Coupon(company, "marvel", "bookMarvel", LocalDate.of(2022, 12, 12),
					LocalDate.of(2023, 11, 30), 100, 50, "dog", Category.FOOD);
			Coupon coupon1 = new Coupon(company, "disny", "bookDisny", LocalDate.of(2022, 12, 12),
					LocalDate.of(2023, 11, 30), 100, 50, "cat", Category.ELECTRICITY);
			Coupon coupon2 = new Coupon(company, "DC", "DCbooks", LocalDate.of(2022, 12, 12),
					LocalDate.of(2023, 11, 30), 100, 50, "Spider", Category.VACETION);
			Coupon coupon3 = new Coupon(company1, "Gil", "GilNadlan", LocalDate.of(2022, 12, 12),
					LocalDate.of(2023, 11, 30), 100, 50, "Daddy", Category.VACETION);
			companyService.addCoupon(coupon);
			companyService.addCoupon(coupon1);
			companyService.addCoupon(coupon2);
			companyService.addCoupon(coupon3);
			System.out.println("Adding a coupon succeeded");

		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateCoupon() {
		Company company = new Company(0, "Nike", "nike@gmail.com", "1234");
		Coupon coupon2 = new Coupon(1, company, "Moses", "dinner", LocalDate.of(2022, 11, 27),
				LocalDate.of(2022, 11, 29), 100, 50, "Spider", Category.FOOD);
		try {
			companyService.updateCoupon(coupon2);
			System.out.println("Coupon update successful");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteCoupon() {
		try {
			companyService.deleteCoupon(2);
			System.out.println("The coupon was successfully deleted");
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllCouponsByCompany() {
		System.out.println("Get all coupons by company Succeeded:");
		for (Coupon coupon : companyService.getAllcouponsCompany()) {
			System.out.println(coupon);
		}

	}

	public void getAllCouponsCompanyByCategory() {
		System.out.println("Get all coupon by category succeeded:");
		for (Coupon coupon : companyService.getAllcouponsCompanyByCategory(Category.FOOD)) {
			System.out.println(coupon);
		}

	}

	public void getAllCouponsCompanyByPrice() {
		System.out.println("Get all coupon by price succeeded:");
		for (Coupon coupon : companyService.getAllcouponsCompanyByMaxPrice(50)) {
			System.out.println(coupon);
		}
	}

	public void getCompanyDetails() {
		System.out.println("Get company details succeeded:");
		try {
			System.out.println(companyService.getCompanyDetails());
		} catch (CouponSystemExceptions e) {
			System.out.println(e.getMessage());

		}
	}

}
