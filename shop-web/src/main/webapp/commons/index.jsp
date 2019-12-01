<%--
  Created by IntelliJ IDEA.
  User: l
  Date: 2019/10/15
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-3.3.1.js"></script>
</head>
<body>
    <div>
        <form method="post">
           用户名： <input id="userName"><br>
           登录密码:<input type="password" id="password"><br>
           <input type="button" onclick="login()" value="登录">
            <button class="btn" onclick="zhuce()">注册</button>
        </form>
    </div>
    <script>
        function login() {
            var userName=$("#userName").val();
            var password=$("#password").val();
            if (userName==null||userName==""){
                alert("账号不能为空");
                return false;
            }
            if (password==null||password==""){
                alert("密码不能为空");
                return false;
            }
            $.ajax({
                url:"<%=request.getContextPath()%>/loginController/login",
                dataType:"json",
                type:"post",
                data:{
                    "userName":userName,
                    "password":password
                },
                success:function (result) {
                    if (result.success==true) {
                        alert(result.message);
                        location.href="<%=request.getContextPath()%>/userController/toUserList";
                    }else {
                        alert(result.message);
                    }
                },
                error:function () {
                    alert("访问失败");
                }
            })
        }
    </script>
</body>
</html>
