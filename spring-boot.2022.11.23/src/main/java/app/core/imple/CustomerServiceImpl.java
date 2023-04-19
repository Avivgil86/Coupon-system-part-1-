package app.core.imple;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entity.Category;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.service.ClientService;
import app.core.service.CustomerService;

@Scope("prototype")
@Service
@Transactional
public class CustomerServiceImpl extends ClientService implements CustomerService {

	private int customerId;

	/*
	 * @param email,password
	 * 
	 * @return true
	 * 
	 * @throws DictionaryException if email or password not valid
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemExceptions {
		if (customerRepos.existsByEmailAndPassword(email, password) == false) {
			throw new CouponSystemExceptions("email or password not valid");
		}
		this.customerId = customerRepos.findByEmail(email).getId();
		return true;
	}

	/*
	 * @param coupon
	 * 
	 * @throws CouponSystemExceptions if customer not find with this id
	 */
	@Override
	public void purchaseCoupon(Coupon coupon) throws CouponSystemExceptions {
		Coupon couponFromData = couponRepos.findById(coupon.getId())
				.orElseThrow(() -> new CouponSystemExceptions("Coupon with this id not found "));
		Customer customerFromData = customerRepos.findById(this.customerId)
				.orElseThrow(() -> new CouponSystemExceptions("Customer with this id not found "));
		if (customerFromData.getCoupons().contains(coupon)) {
			throw new CouponSystemExceptions("this coupon already purchased");
		}
		if (couponFromData.getEndDate().isAfter(LocalDate.now())) {
			throw new CouponSystemExceptions("this coupon is expired");
		}
		if (couponFromData.getAmnout() == 0) {
			throw new CouponSystemExceptions("this coupon amount is 0");
		}
		couponFromData.setAmount(couponFromData.getAmnout() - 1);
		couponRepos.addPurchaseCoupon(customerId, coupon.getId());
		couponRepos.saveAndFlush(couponFromData);
	}

	/*
	 * @return all coupons customer from date
	 * 
	 * @throws CouponSystemExceptions if this customer doesn't exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomer() throws CouponSystemExceptions {

		return couponRepos.findPurchaseCouponsByCustomerId(customerId);
	}

	/*
	 * @param category
	 * 
	 * @return customer all coupons By Category
	 * 
	 * @throws CouponSystemExceptions if this customr doesnt exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomerByCategory(Category category) throws CouponSystemExceptions {

		return couponRepos.findByCustomerIdAndCategory(category.name(), customerId);

	}

	/*
	 * @param price
	 * 
	 * @return customer all coupons By Price
	 * 
	 * @throws CouponSystemExceptions if this customer doesnt exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomerByPrice(int price) throws CouponSystemExceptions {

		return couponRepos.findByCustomerIdAndPrice(price, customerId);

	}

	/*
	 * @return customer
	 * 
	 * @throws CouponSystemExceptions if this customer doesn't exists
	 */
	@Override
	public Customer getCustomerDetails() throws CouponSystemExceptions {
		Customer customer = customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		return customer;
	}

}
