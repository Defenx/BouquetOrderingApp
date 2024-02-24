package org.example.servlet;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.dao.BouquetDAO;
import org.example.entity.Bouquet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/bouquet"})
@NoArgsConstructor
public class BouquetServlet extends HttpServlet {
    private BouquetDAO bouquetDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bouquetDAO = (BouquetDAO) getServletContext().getAttribute("bouquetDAO");
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/index.mustache");

        List<Bouquet> bouquets = bouquetDAO.getAllBouquets();

        Map<String, Object> model = new HashMap<>();
        model.put("bouquets", bouquets);

        mustache.execute(response.getWriter(), model);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String photoUrl = request.getParameter("photoUrl");
        String description = request.getParameter("description");

        Bouquet bouquet = Bouquet.builder()
                .name(name)
                .price(price)
                .photoUrl(photoUrl)
                .description(description).build();

        bouquetDAO.addBouquet(bouquet);
        response.sendRedirect(request.getContextPath() + "/bouquet");
    }
}
