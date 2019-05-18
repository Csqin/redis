package it.mode.web.servlet;
import it.mode.service.ProvinceService;
import it.mode.service.impl.ProvinceServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1.调用service方法查询所有省份
            ProvinceService p=new ProvinceServiceImp();
            String json = p.findAll_Json();
             //3.响应数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
