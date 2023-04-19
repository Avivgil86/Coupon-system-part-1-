package app.core.service;

import java.util.List;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;

public interface CompanyService {
	void addCoupon(Coupon coupon) throws CouponSystemExceptions;

	void updateCoupon(Coupon coupon) throws CouponSystemExceptions;

	void deleteCoupon(int couponId)  throws CouponSystemExceptions;

	List<Coupon> getAllcouponsCompany();

	List<Coupon> getAllcouponsCompanyByCategory(Category category);

	List<Coupon> getAllcouponsCompanyByMaxPrice(int price);


	Company getCompanyDetails() throws CouponSystemExceptions;

}
