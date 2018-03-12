<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
<body>
<div style="width: 100%" border=""0>
    <div style="width: 50%;height: 100%;float: left">
        <div style="width:100%;">
            <jsp:include page="game_list.jsp"></jsp:include>
        </div>
    </div>
    <div style="width: 50%;height: 800px;;float: right">
        <iframe name="_treeFrame" id="_treeFrame" frameborder="0" src="<%=basePath%>wl/gameTxt/list.do?gameId=${gameId}&type=1" style="width:100%;height:100%;"></iframe>
    </div>
</div>
<%--<table style="width:100%;" border="0">
    <tr>
        <td style="width:50%;" valign="top">

        </td>
        <td style="width:50%;" valign="top">

        </td>
    </tr>
</table>--%>

<script type="text/javascript">
    $(top.hangge());

</SCRIPT>
</body>
</html>