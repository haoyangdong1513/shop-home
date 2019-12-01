<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/20
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    a.ss:link{color:Blue} /* 初始样式 */
    a.ss:visited{ color: #452046} /* 定义已访问过链接的样式 */
    a.ss:hover {color: #88af9f
    } /* 定义鼠标移到链接的样式 */
</style>
<link type="text/css" href="<%=request.getContextPath()%>/js/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/bootstrap/bootstrap/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/js/js/jquery.min.js"></script>
<body>
<!--左侧菜单-->
<div class="sec-mainL left">
    <div class="mainL-hd-box">
        <h2 class="mainL-hd" onclick="toHome()"><a href="#">购物返彩贝商家</a></h2>
    </div>
    <script>
            function toHome(){
                var strs = "<div id=\"headDiv\" style=\"padding-top: 10px;\"></div><div id=\"imgDiv\" style=\"margin-top: 20px;\"><img width=\"1000px\" height=\"400px\" src=\"http://dongsir1521.oss-cn-beijing.aliyuncs.com/image/56.jpg?Expires=1889691593&OSSAccessKeyId=LTAI4FgwwkvekGZSZUfrp4zL&Signature=rMeKuz%2FnfrvhfBf39k7LSTV%2FxXw%3D\"/></div>";
                $("#rights").html(strs);
            }
    </script>
    <!--菜单列表-->
    <ul class="sec-mainNav" id="cai">
        <li>
            <h3>数码家电</h3>
            <div class="menu-tab">
                <a href="">京东</a>
                <a href="">易迅</a>
                <a href="">苏宁易购</a>
                <div class="fix"></div>
            </div>
            <span class="menu-more">更多</span>
            <!--二级菜单-->
            <div class="menu-panel">
                <div class="menu-panel-hd">
                    <h4>热门分类</h4>
                    <div class="sub-group">
                        <a href="">京东</a>
                        <a href="">易迅</a>
                        <a href="">苏宁易购</a>
                        <a href="">新蛋商城</a>
                        <a href="">手机数码</a>
                        <a href="">摄像影音</a>
                        <a href="">耳麦音响</a>
                        <a href="">国美在线</a>
                    </div>
                </div>
                <div class="menu-panel-bd">
                    <ul>
                        <li>
                            <a href=""><img src="<%=request.getContextPath()%>/js/img/0.png" /></a>
                        </li>
                        <li>
                            <a href=""><img src="<%=request.getContextPath()%>/js/img/0 (1).png" /></a>
                        </li>
                        <li>
                            <a href=""><img src="<%=request.getContextPath()%>/js/img/0 (2).png" /></a>
                        </li>
                        <li>
                            <a href=""><img src="<%=request.getContextPath()%>/js/img/0 (3).png" /></a>
                        </li>
                        <li>
                            <a href=""><img src="<%=request.getContextPath()%>/js/img/0.jpg" /></a>
                        </li>
                    </ul>
                </div>
                <a href="" class="menu-panel-btn">
                    <span>查看全部商家</span>
                    <em></em>
                </a>
            </div>
        </li>

    </ul>

</div>

<%--右侧数据信息--%>
<div id="rights" >
    <div id="headDiv" style="padding-top: 10px;"></div>
    <hr/>
    <div id="imgDiv" style="margin-top: 20px;float: left;" width="500px">
        <%--<img width="1000px" height="400px" src="http://dongsir1521.oss-cn-beijing.aliyuncs.com/image/56.jpg?Expires=1889691593&OSSAccessKeyId=LTAI4FgwwkvekGZSZUfrp4zL&Signature=rMeKuz%2FnfrvhfBf39k7LSTV%2FxXw%3D"/>--%>
    </div>

        <%--<hr/>
        <div style="border:1px solid #8c8c8c ;margin-left:10px;margin-right:10px;margin-top:10px;float: left">
            <img width="200px" height="150px"   src="http://dongsir1521.oss-cn-beijing.aliyuncs.com/image/56.jpg?Expires=1889691593&OSSAccessKeyId=LTAI4FgwwkvekGZSZUfrp4zL&Signature=rMeKuz%2FnfrvhfBf39k7LSTV%2FxXw%3D"/><br/>
            <center><b>商品名称：</b><font size="2px" color="#006400">阿萨德</font>
                <br/><b>价格：</b><font size="2px" color="red">2333.3</font></center>
            <img  width="30px" src="https://dongsir1521.oss-cn-beijing.aliyuncs.com/3.jpg?Expires=1574837064&OSSAccessKeyId=TMP.hf9HH2T4d7UJvie6Rgq2J8PGEEyfWWt6KnRGjTL4GqgG2Ma3LnrzaiDjsoGSThg1mpGDESDezhbHpBedJiTQxkXXEAyGriw6STq4vwx9xvbtj9WmXqCyN6wkhkJUwN.tmp&Signature=S9nws1wDdAXZFd%2BeRcGGVJ8tPBI%3D"/>
        </div>--%>
                    <div class="panel panel-default" style="margin-top: 120px;">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            商品列表展示
                            <button onclick="toCart()" class="btn btn-primary" type="button">
                                查看购物车 <span class="badge" id="cartNum">0</span>
                            </button>
                        </div>
                            <div id="commDiv" style="float: left;width: 900px;">
                         </div>

                </div>



</div>

</body>
<script>
        $(function(){

            var token = sessionStorage.getItem("token");
            $.ajaxSetup({ //发送请求前触发
                complete: function (xhr) {
                    var code=xhr.getResponseHeader("NOTOKEN");
                    var mes=xhr.getResponseHeader("MES");
                   // alert(code);
                   // alert(mes);
                    if(code != null && code=="6001"){
                        window.location.href="login.html";
                    }
                },
                beforeSend: function (xhr) { //可以设置自定义标头
                    console.log("ajax beforesend token:", token)
                    xhr.setRequestHeader('token', token);
                }
            })

            $.ajax({
                url:"http://localhost:9001/brandType/queryBrandTypeAll",
                type:"get",
                dataType:"json",
                async:false,
                data:{},
                success:function(result){
                    var str = "";
                    for(var i = 0 ; i < result.length; i++) {
                        var brandss = result[i].brands3;//所有品牌的数组
                        var imgList = result[i].imgs;

                        var stra = "";//显示三个品牌
                        var count1= 0;
                        for (var c = 0 ; c<brandss.length ; c++){
                            if (count1 >=3 ){
                                break;
                            }
                            count1= count1+1;
                            stra += "<a href=''>"+brandss[c].name+"</a>" ;
                        }

                        var all = "";//获取所有品牌
                       for (var j = 0 ; j<brandss.length ; j++){
                           all += "<a href='#' onclick='toProduct("+brandss[j].id+")'>"+brandss[j].name+"</a>" ;
                        }

                       var imgUrls = "";//照片
                        var count = 0 ;
                       for(var y = 0 ; y <imgList.length ; y++){
                           if (count >=4 ){
                               break;
                           }
                           count = count+1;
                           imgUrls +="<li><a href=''><img height='60px' src='"+imgList[y]+"'/></a></li>"
                       }

                        str+="<li onclick='toBrandAll("+result[i].id+",1)'>";
                        str+="<h3>"+result[i].name+"</h3>";
                        str+="<div class=\"menu-tab\">";
                        str+=stra;
                        str+="<div class=\"fix\"></div>";
                        str+="</div>";
                        str+="<span class=\"menu-more\">更多</span>";
                        str+="<div class=\"menu-panel\">";
                        str+="<div class=\"menu-panel-hd\">";
                        str+="<h4>热门分类</h4>";
                        str+="<div class=\"sub-group\">";
                        str+=all;
                        str+="</div>";
                        str+="</div>";
                        str+="<div class=\"menu-panel-bd\">";
                        str+="<ul>";
                        str+=imgUrls;
                        str+="</ul>";
                        str+="</div>";
                        str+="<a href=\"#\" onclick='toBrandAll("+result[i].id+",1)' class=\"menu-panel-btn\">";
                        str+="<span>查看全部商家</span>";
                        str+="<em></em>";
                        str+="</a>";
                        str+="</div>";
                        str+="</li>";
                    }
                    $("#cai").html(str);
                        var $top = $('.sec-mainNav').offset().top + $('.sec-mainNav').height()
                        //左侧导航动画
                        $('.sec-mainNav li').on('mouseenter', function() {
                            var $height = $(this).offset().top + $(this).find('.menu-panel').outerHeight();
                            $(this).find('.menu-panel').show();
                            if($height - $top >= 0) {
                                $(this).find('.menu-panel').css({
                                    top: -($height - $top) + 'px'
                                })
                            }
                        });
                        $('.sec-mainNav li').on('mouseleave', function() {
                            $(this).find('.menu-panel').hide();
                        });
                },
                error:function(){
                    alert("");
                }
            });

            /**
             * 获取用户的购物车个数
             */
           if (token != null){
               $.ajax({
                   url:"http://localhost:9005/cart/cartCount",
                   type:"get",
                   dataType:"json",
                   async:false,
                   data:{},
                   success:function(result){
                       if (result.code == 200){
                           $("#cartNum").html(result.data);
                       }
                   }
               });
           }
        });
</script>
<script>
    //类型id
    function toBrandAll(id,index){
        $.ajax({
            url:"http://localhost:9001/brand/queryBrandPage",
            type:"post",
            dataType:"json",
            async:false,
            data:{"pageIndex":index,"brandtype":id},
            success:function(result){
                var list = result.list;
                var imgs = "";
                var names = "&nbsp;&nbsp;&nbsp;&nbsp;";
                var heads = "<div>&nbsp;&nbsp;<font size=\"4\" color=\"red\">热门分类</font><br/></div>";
                for (var i = 0 ; i<list.length ; i ++){
                    imgs+="<div style=\"margin-left:10px;margin-right:10px;margin-top:10px;width: 100px;height: 100px;float: left\">\n" +
                        "        <img width=\"100px\" height=\"50px\" onclick='toProduct("+list[i].id+",null,"+id+")'  src=\""+list[i].imgurl+"\"/><br/>\n" +
                        "    </div>";
                    names+="<a class='ss' style='font-size:15px;' onclick='toProduct("+list[i].id+",this,"+id+")' href=\"#\">"+list[i].name+"</a>&nbsp;&nbsp;&nbsp;";
                }
                $("#headDiv").html(heads+names);
                $("#imgDiv").html(imgs);
            },
            error:function(){
                alert("查询错");
            }
        });

        $.ajax({
            url:"http://localhost:9003/pro",
            type:"get",
            dataType:"json",
            async:false,
            data:{
                "pageIndex":index,
                "typeId":id
            },
            success:function(result){
                var lists = result.list;
                var productStrs="";
                for (var i = 0 ; i <lists.length;i++){
                    productStrs+="<div style=\"margin-left:10px;margin-right:10px;margin-top:10px;float: left\">";
                    productStrs+="<img width=\"200px\" height=\"150px\"   src=\""+lists[i].mianImg+"\"/><br/>";
                    productStrs+="<center><b>商品名称：</b><font size=\"2px\" color=\"#006400\">"+lists[i].name+"</font>";
                    productStrs+="<br/><b>价格：</b><font size=\"2px\" color=\"red\">"+lists[i].price+"</font></center>"
                    productStrs+="<img onclick='checkToken("+lists[i].id+")' width='30px' src='https://dongsir1521.oss-cn-beijing.aliyuncs.com/3.jpg'/>"
                    productStrs+="</div>";
                }
                $("#commDiv").html("<hr/>"+productStrs);
            },
            error:function(){
                alert("");
            }
        });



    }

    /**
     * 商品展示
     * @id 品牌id
     */
    function toProduct(id,obj,typeId){
       //alert(id);
        var siblings = getSiblings(obj);
        for(var i =0 ; i<siblings.length; i++){
            $(siblings).attr("style","border:none;font-size:15px;");
        }
        $(obj).attr("style","border:3px solid pink;font-size:15px;");
        $.ajax({
            url:"http://localhost:9003/pro",
            type:"get",
            dataType:"json",
            data:{
                "brandId":id,
                "typeId":typeId
            },
            success:function(result){
                var lists = result.list;
                var productStrs="";
                for (var i = 0 ; i <lists.length;i++){
                    productStrs+="<div style=\"margin-left:10px;margin-right:10px;margin-top:10px;float: left\">";
                    productStrs+="<img width=\"200px\" height=\"150px\"   src=\""+lists[i].mianImg+"\"/><br/>";
                    productStrs+="<center><b>商品名称：</b><font size=\"2px\" color=\"#006400\">"+lists[i].name+"</font>";
                    productStrs+="<br/><b>价格：</b><font size=\"2px\" color=\"red\">"+lists[i].price+"</font></center>"
                    productStrs+="<img onclick='checkToken("+lists[i].id+")' width='30px' src='\n" +
                        "https://dongsir1521.oss-cn-beijing.aliyuncs.com/3.jpg'/>"
                    productStrs+="</div>";
                }
                $("#commDiv").html("<hr/>"+productStrs);
            },
            error:function(){
                alert("");
            }
        });
    }
    //获取所有的兄弟节点数组
    function getSiblings(elem){
        var r = [];
        var n = elem.parentNode.firstChild;
        for(; n; n = n.nextSibling) {
            if(n.nodeType === 1 && n !== elem) {
                r.push(n);
            }
        }
        return r;
    }


    /**
     * 添加购物车
     * @param proId
     */
    function checkToken(proId){
        $.ajax({
            url:"http://localhost:9005/cart",
            type:"post",
            dataType:"json",
            data:{"proId":proId},
            success:function(result){
                if(result.code == 200){
                    //alert(result.data);
                    $("#cartNum").html(result.data);
                }
            }
        });
    }

    /**
     * 查询购物车
     */
    function toCart(){
        $.ajax({
            url:"http://localhost:9005/cart",
            type:"get",
            dataType:"json",
            data:{},
            success:function(result){
                if (result.code ==200){
                    window.open("carts.html");
                }
            }
        });
    }
</script>
</html>
