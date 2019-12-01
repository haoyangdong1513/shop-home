<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/bootstrap/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/datatable/DataTables-1.10.18/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/fileInput/css/fileinput.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/ztree/css/bootstrapStyle/bootstrapStyle.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/commons/bootstarpValidator/css/bootstrapValidator.min.css"/>

<script src="<%=request.getContextPath()%>/commons/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootstrap/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/datatable/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/datatable/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/dateFormat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootstarpValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/commons/bootstarpValidator/js/language/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/fileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/fileInput/js/locales/zh.js"></script>
<!-- 加载js文件-->
<!-- 全局ajax-->
<script>
    $.ajaxSetup({
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        complete:function(XMLHttpRequest,textStatus){
            var code=XMLHttpRequest.getResponseHeader("sessionTimeOut");
            if(code != null && code=="8100"){
                window.location.href="<%=request.getContextPath()%>/sessionTimeout.jsp";
            }
            var right=XMLHttpRequest.getResponseHeader("NORIGHT");
            if(right != null && right=="8004"){
                window.location.href="<%=request.getContextPath()%>/noRight.jsp";
            }
        }
    });
</script>