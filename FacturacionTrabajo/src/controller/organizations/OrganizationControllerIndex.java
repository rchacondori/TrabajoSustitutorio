package controller.organizations;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Organization;

@SuppressWarnings("Serial")
public class OrganizationControllerIndex extends HttpServlet{

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		final PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Organization.class.getName();

		try{
			List<Organization> users = (List<Organization>) pm.newQuery(query).execute();
			request.setAttribute("organizations", users);
			RequestDispatcher despachador = request.getRequestDispatcher("/WEB-INF/Views/Organizations/index.jsp");
			despachador .forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
