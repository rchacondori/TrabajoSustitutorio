package controller.resources;

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
import model.entity.Facturacion;

@SuppressWarnings("serial")
public class ResourceControllerAdd extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Resources/add.jsp");
			rd.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
			Facturacion a = new Facturacion(request.getParameter("ruc"),request.getParameter("social"),request.getParameter("direccion"),uGoogle.getEmail());
			pm.makePersistent(a);
			response.sendRedirect("/resources");
		}catch (Exception e) {
			System.out.println("error");
			System.out.println(e);
		} finally {
			pm.close();
		}
	}
}
