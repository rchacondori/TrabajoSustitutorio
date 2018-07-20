package controller.products;

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
import model.entity.Product;

@SuppressWarnings("serial")
public class ProductControllerIndex extends HttpServlet{

	@Override
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {						  

		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query4 = "select from " + Product.class.getName() +" where madeBy=='" + uGoogle.getEmail()+"'";
		
		List<Product> products = (List<Product>) pm.newQuery(query4).execute();
		req.setAttribute("productos", products);
		RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/index.jsp");
		dp.forward(req, resp);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}


