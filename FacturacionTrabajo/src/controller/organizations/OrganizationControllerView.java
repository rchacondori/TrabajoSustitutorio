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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Organization;

@SuppressWarnings("serial")
public class OrganizationControllerView extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Organization a;
		if( request.getParameter("Id") != null ){
			Key k = KeyFactory.createKey(Organization.class.getSimpleName(), new Long(request.getParameter("Id")).longValue());
			a = pm.getObjectById(Organization.class, k);
		}else{
			a = null;
		}

		request.setAttribute("organization", a);

		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/WEB-INF/Views/Organizations/view.jsp");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Organization.class.getSimpleName(), new Long(request.getParameter("Id")).longValue());
		Organization a = pm.getObjectById(Organization.class, k);
		/*
		a.setMail(request.getParameter("mail"));
		a.setGenero(new Boolean(request.getParameter("genero")));
		a.setRolId(new Long(request.getParameter("rolId")));
		System.out.println(request.getParameter("state"));
		a.setStatus(new Boolean(request.getParameter("state")));
		 */
		response.sendRedirect("/organizations");
	}
}
