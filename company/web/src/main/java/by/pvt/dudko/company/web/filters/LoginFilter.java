package by.pvt.dudko.company.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class LoginFilter implements Filter {
	private static final Logger log = Logger.getLogger(LoginFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String indexURI = path + "/index";
		String startURI = path + "/start_page";
		String loginURI = path + "/login";
		String registrationURI = path + "/registration";
		String errorURI = path + "/error";
		String adminURI = path + "/admin/admin";
		String localeURI = path + "/locale";
		String pictureURI = path + "/resources/fon.jpg";

		boolean skip = (session != null) && (session.getAttribute("USER") != null);
		boolean enter = "enter".equals(request.getParameter("page"));
		boolean loginRequest = (loginURI.equals(request.getRequestURI())
				|| registrationURI.equals(request.getRequestURI()) || startURI.equals(request.getRequestURI())
				|| errorURI.equals(request.getRequestURI())) || indexURI.equals(request.getRequestURI())
				|| localeURI.equals(request.getRequestURI()) || adminURI.equals(request.getRequestURI())
				|| pictureURI.equals(request.getRequestURI());

		if (skip || loginRequest || enter) {
			chain.doFilter(request, response);
		} else {
			log.info("unauthorized entry");
			request.getRequestDispatcher("/WEB-INF/pages/start_page.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	// ...
}
