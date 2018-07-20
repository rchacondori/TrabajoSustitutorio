package controller.products;

import java.io.IOException;
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
import model.entity.Product;

@SuppressWarnings("serial")
public class ProductControllerView extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Product a;
		if( request.getParameter("mercaderiaId") != null ){
			Key k = KeyFactory.createKey(Product.class.getSimpleName(), new Long(request.getParameter("mercaderiaId")).longValue());
			a = pm.getObjectById(Product.class, k);
		}else{
			a = new Product("0", "", "0", "0", "0", "0", "", false, "");
		}

		request.setAttribute("producto", a);

		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/view.jsp");
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Product.class.getSimpleName(), new Long(request.getParameter("mercaderiaId")).longValue());
		Product a = pm.getObjectById(Product.class, k);
		
		/*a.setCodigoBarra(request.getParameter("codigoBarra"));
		a.setNombre(request.getParameter("nombre"));
		a.setPrecioFinal(request.getParameter("precioFinal"));
		a.setUnidadId(request.getParameter("unidadId"));
		a.setCategoriaId(request.getParameter("categoriaId"));
		a.setAlmacenId(request.getParameter("almacenId"));
		a.setDescripcion(request.getParameter("descripcion"));
*/
		response.sendRedirect("/products");
	}

}
