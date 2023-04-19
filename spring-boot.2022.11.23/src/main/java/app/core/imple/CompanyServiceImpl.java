package app.core.imple;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;
import app.core.service.ClientService;
import app.core.service.CompanyService;

@Scope("prototype")
@Service
@Transactional
public class CompanyServiceImpl extends ClientService implements CompanyService {

	private int companyId;

	/*
	 * @param email,password
	 * 
	 * @return true
	 * 
	 * @throws CouponSystemExceptions if email or password not valid
	 */

	@Override
	public boolean login(String email, String password) throws CouponSystemExceptions {
		if (companyRepos.existsByEmailAndPassword(email, password) == false) {
			throw new CouponSystemExceptions("email or password not valid");
		}
		this.companyId = companyRepos.findByEmail(email).getId();
		return true;
	}

	/*
	 * @param coupon
	 * 
	 * 
	 * @throws CouponSystemExceptions if coupons with this title exists
	 */
	@Override
	public void addCoupon(Coupon coupon) throws CouponSystemExceptions {
		if (couponRepos.existsCouponsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new CouponSystemExceptions(" coupons with this title exists");
		} else {
			couponRepos.save(coupon);
		}
	}

	/*
	 * @param coupon
	 * 
	 * @return
	 * 
	 * @throws CouponSystemExceptions if coupons with this id doesn't exists
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemExceptions {
		Coupon couponToUpdate = couponRepos.findById(coupon.getId())
				.orElseThrow(() -> new CouponSystemExceptions("coupons with this id doesnt exists"));
		couponToUpdate.setAmount(coupon.getAmnout());
		couponToUpdate.setCategory(coupon.getCategory());
		couponToUpdate.setDescription(coupon.getDescription());
		couponToUpdate.setEndDate(coupon.getEndDate());
		couponToUpdate.setStartDate(coupon.getStartDate());
		couponToUpdate.setImage(coupon.getImage());
		couponToUpdate.setTitle(coupon.getTitle());
		couponToUpdate.setPrice(coupon.getPrice());
		couponRepos.saveAndFlush(couponToUpdate);
	}

	/*
	 * @param couponId
	 * 
	 * 
	 * @throws CouponSystemExceptions if this coupon doesn't exists
	 */
	@Override
	public void deleteCoupon(int couponId) throws CouponSystemExceptions {
		if (!couponRepos.existsById(couponId)) {
			throw new CouponSystemExceptions("this coupon doesnt exists");
		}
		couponRepos.deleteAllPurchaseCouponsById(couponId);
		couponRepos.deleteCouponsById(couponId);

	}

	/*
	 * 
	 * @return all company coupons
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompany() {
		return couponRepos.findAllCompanyCouponsByCompanyId(companyId);
	}

	/*
	 * @param category
	 * 
	 * @return all company coupons by category
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompanyByCategory(Category category) {
		return couponRepos.findByCompanyIdAndCategory(companyId, category);
	}

	/*
	 * @param price
	 * 
	 * @return all company coupons by price
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompanyByMaxPrice(int price) {
		return couponRepos.findAllCompanyCouponsByPrice(price, companyId);
	}

	/*
	 * @return company
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exist
	 */
	@Override
	public Company getCompanyDetails() throws CouponSystemExceptions {
		Company company = companyRepos.findById(companyId)
				.orElseThrow(() -> new CouponSystemExceptions("this company doesnt exist"));
		return company;
	}

}
