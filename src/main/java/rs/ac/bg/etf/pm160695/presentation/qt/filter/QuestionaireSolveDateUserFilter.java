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

import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.boundary.QuestionaireSolutionDao;
import rs.ac.bg.etf.pm160695.business.testquestionaire.questionaire.entity.Questionaire;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

@WebFilter(
		urlPatterns = {
				"/pages/qt/questionaire/questionaireSolve.xhtml"
		})
public class QuestionaireSolveDateUserFilter implements Filter {

	@Inject
	private QuestionaireDao questionaireDao;
	
	@Inject
	private QuestionaireSolutionDao questionaireSolutionDao;
	
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
				Questionaire q = questionaireDao.find(Long.parseLong(tqId));
				if (q != null && 
					((q.getDatumOd().isAfter(LocalDate.now()) || 
					 q.getDatumDo().isBefore(LocalDate.now())) || 
					questionaireSolutionDao.questionaireAlreadySolvedByUser(q, currentUserBean.getUlogovaniKorisnik()))) {
					response.sendError(403);
				}
			} catch (NumberFormatException e) {
			}
		}
		
		chain.doFilter(request, response);
	}

}
