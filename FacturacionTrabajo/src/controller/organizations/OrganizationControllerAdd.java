package controller.organizations;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Organization;

@SuppressWarnings("serial")
public class OrganizationControllerAdd extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();

		final PersistenceManager pm = PMF.get().getPersistenceManager();

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Organizations/add.jsp");
		rd.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Organization a = new Organization(
					request.getParameter("name"),
					request.getParameter("mail")
					);
			pm.makePersistent(a);
			response.sendRedirect("/organizations");
		}catch (Exception e) {
			System.out.println("error");
			System.out.println(e);
		} finally {
			pm.close();
		}
	}
}
