package dev.sgp.web;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCollaborateursController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String matriculeParam = req.getParameter("matricule");

		if (matriculeParam == null || "".equals(matriculeParam.trim())) {
			resp.setStatus(400);
			resp.getWriter().write("Un matricule est attendu");
		} else {
			resp.setContentType("text/html");
			resp.getWriter().write("<h1>Edition de collaborateur</h1> Matricule : " + matriculeParam);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Map<Boolean, List<String>> validationParams = validerParametres(req, "matricule", "titre", "nom", "prenom");
		resp.setCharacterEncoding("utf-8");

		if (validationParams.get(false) != null) {
			resp.setStatus(400);
			resp.getWriter().write("Les paramètres suivants sont incorrects : "
					+ validationParams.get(false).stream().collect(joining(",")));
		} else {
			resp.setStatus(201);
			resp.getWriter().write("Création d'un collaborateur avec les informations suivantes : " + validationParams
					.get(true).stream().map(p -> p + "=" + req.getParameter(p)).collect(joining(",")));
		}

	}

	private Map<Boolean, List<String>> validerParametres(HttpServletRequest req, String... params) {
		return Stream.of(params)
				.collect(groupingBy(param -> req.getParameter(param) != null && !"".equals(req.getParameter(param))));
	}
}
