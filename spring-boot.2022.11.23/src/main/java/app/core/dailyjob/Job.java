package app.core.dailyjob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.repos.CouponRepos;

@Component
public class Job {
	private final int timeToSleep = 1000 * 60 * 60  * 24;
	@Autowired
	private CouponRepos couponRepos;

	@Scheduled(fixedRate = timeToSleep, initialDelay = 1000 * 60)
	public void deleteExpiredCoupons() {
			couponRepos.deleteExpiredPurchaseCoupons();
			couponRepos.deleteExpiredCoupons();
		
	}
}
