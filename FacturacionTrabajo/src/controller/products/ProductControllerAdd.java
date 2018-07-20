package controller.products;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Product;

@SuppressWarnings("serial")
public class ProductControllerAdd extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Products/add.jsp");
		rd.forward(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		try {
			Product a = new Product(
					request.getParameter("codigoBarra"),
					request.getParameter("nombre"),
					request.getParameter("precioFinal"),
					request.getParameter("unidadId"),
					request.getParameter("categoriaId"),
					request.getParameter("almacenId"),
					request.getParameter("descripcion"),
					true,
					uGoogle.getEmail()
					);
			pm.makePersistent(a);
			response.sendRedirect("/products");
		}catch (Exception e) {
			System.out.println(e);
		} finally {
			pm.close();
		}
	}
}
