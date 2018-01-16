package com.monzag;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(getForm("", ""));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String celsiusDegreeTxt = req.getParameter("celsiusDegree");

        try {
            Double celsiusDegree = Double.parseDouble(celsiusDegreeTxt);
            Double convertResult = DegreeConvertor.convertCelsiusToFahreinheit(celsiusDegree);
            resp.getWriter().write(getForm(celsiusDegree.toString(), convertResult.toString()));

        }catch (NumberFormatException e) {
            resp.getWriter().write("Incorrect input, try again");
            resp.setStatus(400);
        }
    }

    private String getForm(String celsiusDegree, String result) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/form.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("celsiusDegree", celsiusDegree);
        model.with("resultFahrenheit", result);

        return template.render(model);
    }
}
