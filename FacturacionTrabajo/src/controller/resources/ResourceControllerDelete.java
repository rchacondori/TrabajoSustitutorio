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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Facturacion;

@SuppressWarnings("serial")
public class ResourceControllerDelete extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k = KeyFactory.createKey(Facturacion.class.getSimpleName(), new Long(request.getParameter("resourceId")).longValue());
			Facturacion a = pm.getObjectById(Facturacion.class, k);
			pm.deletePersistent(a);
			response.sendRedirect("/resources");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}
