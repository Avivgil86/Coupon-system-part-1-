package app.core.service;

import java.util.List;

import app.core.entity.Category;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;

public interface CustomerService {
	public void purchaseCoupon(Coupon coupon) throws CouponSystemExceptions;

	public List<Coupon>  getAllCouponsCustomer() throws CouponSystemExceptions;

	public List<Coupon> getAllCouponsCustomerByCategory(Category category) throws CouponSystemExceptions;

	public List<Coupon> getAllCouponsCustomerByPrice(int price) throws CouponSystemExceptions;

	public Customer getCustomerDetails() throws CouponSystemExceptions;

}
