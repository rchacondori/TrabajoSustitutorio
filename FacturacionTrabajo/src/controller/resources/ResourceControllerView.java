package controller.resources;

import java.io.IOException;

import javax.jdo.PersistenceManager;
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
public class ResourceControllerView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Facturacion a;
			if( request.getParameter("resourceId") != null ){
				Key k = KeyFactory.createKey(Facturacion.class.getSimpleName(), new Long(request.getParameter("resourceId")).longValue());
				a = pm.getObjectById(Facturacion.class, k);
			}else{
				a = new Facturacion("","","","");
			}

			request.setAttribute("resource", a);

			RequestDispatcher dispatcher =
					getServletContext().getRequestDispatcher("/WEB-INF/Views/Resources/view.jsp");
			dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Facturacion.class.getSimpleName(), new Long(request.getParameter("resourceId")).longValue());
		Facturacion a = pm.getObjectById(Facturacion.class, k);

		//a.setUrl(request.getParameter("url"));
		//a.setStatus(new Boolean(request.getParameter("state")));

		response.sendRedirect("/resources");
	}
}
