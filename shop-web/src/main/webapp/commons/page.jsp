<%--
  Created by IntelliJ IDEA.
  User: l
  Date: 2019/10/9
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <table border="1" width="80%" bgcolor="#dcdcdc">
        <tr>
            <td align="center">
                <c:if test="${page.pageNum==1}">
                    首页
                    上一页
                </c:if>
                <c:if test="${page.pageNum>1}">
                    <a href="javascript:;" onclick="showTable(1)">首页</a>
                    <a href="javascript:;" onclick="showTable(${page.pageNum-1})">上一页</a>
                </c:if>
                当前第${page.pageNum}页
                总条数${page.totalCount}条
                总页数${page.totalPage}页
                <c:if test="${page.pageNum==page.totalPage}">
                    下一页
                    尾页
                </c:if>
                <c:if test="${page.pageNum<page.totalPage}">
                    <a href="javascript:;" onclick="showTable(${page.pageNum+1})">下一页</a>
                    <a href="javascript:;" onclick="showTable(${page.totalPage})">尾页</a>
                </c:if>

                每页显示
                <select id="pageSize" onchange="showTable(${page.pageNum})">
                    <option value="2" ${page.pageSize==2?"selected":""}>2</option>
                    <option value="5" ${page.pageSize==5?"selected":""}>5</option>
                    <option value="10" ${page.pageSize==10?"selected":""}>10</option>
                    <option value="20" ${page.pageSize==20?"selected":""}>20</option>
                </select>
                条
            </td>
        </tr>

    </table>
</center>
</body>
</html>
