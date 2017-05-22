package dev.sgp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCollaborateursController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupère la valeur d'un paramètre dont le nom est matricule
		String matricule = req.getParameter("matricule");
		String titre = req.getParameter("titre");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		if (matricule==null || titre==null || nom==null || prenom==null){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les informations de matricule, titre, nom et prénom sont obligatoires");
		}

		// code HTML écrit dans le corps de la réponse
		StringBuilder builder = new StringBuilder();
		builder.append("<h1>").append("Edition de collaborateur").append("</h1>");
		builder.append("<ul>");
		builder.append("<li>matricule=").append(matricule).append("</li>");
		builder.append("<li>titre=").append(titre).append("</li>");
		builder.append("<li>nom=").append(nom).append("</li>");
		builder.append("<li>prénom=").append(prenom).append("</li>");
		builder.append("</ul>");
		resp.getWriter().write(builder.toString());
	}
}
