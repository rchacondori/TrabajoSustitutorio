package controller.organizations;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Organization;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;


@SuppressWarnings("serial")
public class OrganizationControllerLogin extends HttpServlet{
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		if(user == null){
			response.sendRedirect(us.createLoginURL("/organizations/login"));
		}else{
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from " + Organization.class.getName() + " where mail=='"+user.getEmail()+"' && status==true";
			
			List<Organization> uSearch = (List<Organization>) pm.newQuery(query).execute();
			
			if(uSearch.isEmpty()){
				//response.sendRedirect(us.createLoginURL("/organizations/login"));
			}else{
				request.setAttribute("organization", uSearch.get(0));
				RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/Organizations/login.jsp");
				dp.forward(request, response);
			}
			
		}	
	}
	
}
