package rs.ac.bg.etf.pm160695.presentation.korisnickisistem.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.bg.etf.pm160695.business.korisnickisistem.entity.KSKorisnik;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

@WebFilter(
	urlPatterns = {
		"/pages/korisnickisistem/korisnikProfil.xhtml", 
		"/pages/korisnickisistem/promenaLozinke.xhtml"		
})
public class KorisnickiSistemUserIdFilter implements Filter  {

	@Inject
	private CurrentUserBean currentUserBean;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (!request.isUserInRole("admin")) {
			String userId = request.getParameter("userId");
			if (userId != null && !userId.isBlank()) {
				KSKorisnik ulogovaniKorisnik = currentUserBean.getUlogovaniKorisnik();
				if (ulogovaniKorisnik != null) {
					try {
						if (!ulogovaniKorisnik.getId().equals(Long.parseLong(userId))) {
							response.sendError(403);
						}
					} catch (NumberFormatException e) {
					}
				}
			}
		}
		chain.doFilter(request, response);
		
	}

	
	
}
