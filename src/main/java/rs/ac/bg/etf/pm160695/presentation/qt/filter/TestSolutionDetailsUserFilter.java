package rs.ac.bg.etf.pm160695.presentation.qt.filter;

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


import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.QuestionaireSolution;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.boundary.TestSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.test.entity.TestSolution;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

@WebFilter(
		urlPatterns = {
			"/pages/qt/test/testSolutionDetails.xhtml"
})
public class TestSolutionDetailsUserFilter implements Filter {

	@Inject
	private CurrentUserBean currentUserBean;
	
	@Inject
	private TestSolutionDao testSolutionDao;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (!request.isUserInRole("admin")) {
			String tsId = request.getParameter("tsId");
			if (tsId != null && !tsId.isBlank()) {
				try {
					TestSolution s = testSolutionDao.find(Long.parseLong(tsId));
					if (s != null && !validateKorisnik(s)) {
							response.sendError(403);
					}
				} catch (NumberFormatException e) {
				}
			}
		}
		chain.doFilter(request, response);
	}
	
	private boolean validateKorisnik(TQSolution s) {
		return currentUserBean.getUlogovaniKorisnik().equals(s.getKorisnik());
	}
	
}
