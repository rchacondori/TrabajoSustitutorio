package controller.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Facturacion;
import model.entity.Product;

@SuppressWarnings("serial")
public class ResourceControllerIndex extends HttpServlet{

	@Override
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {						  		
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Facturacion.class.getName()+" where madeBy=='" + uGoogle.getEmail()+"'";

		try{
			List<Facturacion> resources = (List<Facturacion>) pm.newQuery(query).execute();
			req.setAttribute("resources", resources);
			RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Resources/index.jsp");
			despachador .forward(req, resp);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}


