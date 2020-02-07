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
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

@WebFilter(
		urlPatterns = {
			"/pages/qt/questionaire/questionaireSolutionDetails.xhtml"
		})
public class QuestionaireSolutionDetailsUserFilter implements Filter {
	
	@Inject
	private QuestionaireSolutionDao questionaireSolutionDao;
	
	@Inject
	private CurrentUserBean currentUserBean;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (!request.isUserInRole("admin")) {
			String tsId = request.getParameter("tsId");
			if (tsId != null && !tsId.isBlank()) {
				try {
					QuestionaireSolution s = questionaireSolutionDao.find(Long.parseLong(tsId));
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
