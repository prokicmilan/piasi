package rs.ac.bg.etf.pm160695.presentation.qt.filter;

import java.io.IOException;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.Test;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

@WebFilter(
		urlPatterns = {
			"/pages/qt/test/testSolve.xhtml",
		})
public class TestSolveDateUserFilter implements Filter {

	@Inject
	private TestSolutionDao testSolutionDao;
	
	@Inject
	private TestDao testDao;
	
	@Inject
	private CurrentUserBean currentUserBean;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String tqId = request.getParameter("tqId");
		if (tqId != null && !tqId.isBlank()) {
			try {
				Test t = testDao.find(Long.parseLong(tqId));
				if (t != null && 
					((t.getDatumOd().isAfter(LocalDate.now()) || 
					 t.getDatumDo().isBefore(LocalDate.now())) || 
					testSolutionDao.testAlreadySolvedByUser(t, currentUserBean.getUlogovaniKorisnik()))) {
					response.sendError(403);
				}
			} catch (NumberFormatException e) {
			}
		}
		
		chain.doFilter(request, response);
	}

}
