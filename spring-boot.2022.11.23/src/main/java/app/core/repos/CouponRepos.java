package app.core.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.core.entity.Category;
import app.core.entity.Coupon;
import javax.transaction.Transactional;

@Repository
public interface CouponRepos extends JpaRepository<Coupon, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from customers_vs_coupons where customer_id = ?", nativeQuery = true)
	void deleteAllPurchaseCustomerCouponsById(int customerId);

	@Query(value = "select * from coupons where company_id = ?", nativeQuery = true)
	List<Coupon> findAllCompanyCouponsByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

	@Query(value = "select * from coupons where category = ? and id in(select coupon_id from customers_vs_coupons where customer_id = ?)", nativeQuery = true)
	List<Coupon> findByCustomerIdAndCategory(String category, int customerId);

	@Query(value = "select * from coupons where price <= ? and id in(select coupon_id from customers_vs_coupons where customer_id = ?)", nativeQuery = true)
	List<Coupon> findByCustomerIdAndPrice(int price, int customerId);

	@Query(value = "select * from coupons inner join customers_vs_coupons on coupon_id = id where customer_id = ?", nativeQuery = true)
	List<Coupon> findPurchaseCouponsByCustomerId(int customerId);

	@Query(value = "select * from coupons where price = ? and company_id = ?", nativeQuery = true)
	List<Coupon> findAllCompanyCouponsByPrice(int price, int companyId);

	@Transactional
	@Modifying
	@Query(value = "delete from customers_vs_coupons where coupon_id = ?", nativeQuery = true)
	void deleteAllPurchaseCouponsById(int couponId);

	@Transactional
	@Modifying
	@Query(value = "insert into `customers_vs_coupons`(customer_id,coupon_id) values( ? , ?);", nativeQuery = true)
	void addPurchaseCoupon(int customerId, int couponId);

	boolean existsCouponsByTitleAndCompanyId(String title, int companyId);

	boolean existsCouponsById(int CouponId);

	@Transactional
	@Modifying
	@Query(value = "delete from coupons where id = ?;", nativeQuery = true)
	void deleteCouponsById(int CouponId);

	@Query(value = "select * from coupons where end_date < now()", nativeQuery = true)
	List<Coupon> findAllCouponsThatExpired();

	@Transactional
	@Modifying
	@Query(value = "delete from coupons where end_date < now()", nativeQuery = true)
	void deleteExpiredCoupons();

	@Transactional
	@Modifying
	@Query(value = "delete from customers_vs_coupons where coupon_id in (select id from coupons where end_date < now())", nativeQuery = true)
	void deleteExpiredPurchaseCoupons();

}
