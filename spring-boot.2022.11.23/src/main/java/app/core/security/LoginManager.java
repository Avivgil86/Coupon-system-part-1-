package app.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.AdminServiceImpl;
import app.core.imple.CompanyServiceImpl;
import app.core.imple.CustomerServiceImpl;
import app.core.service.ClientService;
import app.core.service.CompanyService;
import app.core.service.CustomerService;

@Service
public class LoginManager {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private ApplicationContext ctx;

	/*
	 * @param email, password, clinetType
	 * 
	 * @return company or customer or admin
	 * 
	 * @throws CouponSystemExceptions if the email or password not match;
	 */
	public ClientService logIn(String email, String password, ClientType clientType) throws CouponSystemExceptions {
		switch (clientType) {
		case ADMINISTRATOR: {
			if (adminService.login(email, password)) {

				return adminService;
			}
			break;
		}
		case COMPANY: {
			CompanyServiceImpl company = ctx.getBean(CompanyServiceImpl.class);
			if (company.login(email, password)) {

				return (ClientService) company;
			}
			break;
		}
		case CUSTOMER: {
			CustomerServiceImpl customer = ctx.getBean(CustomerServiceImpl.class);

			if (customer.login(email, password)) {
				return (ClientService) customer;
			}
			break;
		}
		}

		throw new CouponSystemExceptions("Login manager fail ");

	}
}
