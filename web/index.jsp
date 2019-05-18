<%--
  Created by IntelliJ IDEA.
  User: ShmilyCSQ
  Date: 2019/5/15
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>province</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
          //发送ajax请求
          $.get("provinceServlet",{},function (date) {
              //0.获取select标签
              var province=$("#province");
              //1.遍历list集合
              $(date).each(function () {
                  //2.创建option标签
                  var option="<option name='"+this.id+"'>"+this.name+"</option>";
                  //3.追加option标签
                  province.append(option);
              })

          });
      })
    </script>
  </head>
  <body>
            <select id = "province">
                  <option>--请选择省份--</option>
            </select>
  </body>
</html>
