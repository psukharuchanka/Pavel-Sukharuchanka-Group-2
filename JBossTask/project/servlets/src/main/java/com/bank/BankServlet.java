package by.priorbank;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.bank.ejb.BankManagerLocal;

@WebServlet(urlPatterns = "/getBank")
public class BankServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private BankManagerLocal bankManager;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("banks", bankManager.list());
		request.getRequestDispatcher("WEB-INF/bank.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
}
