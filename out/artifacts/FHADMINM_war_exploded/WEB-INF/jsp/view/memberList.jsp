<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/view/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<body>
<!--
    作者：caikunhai@126.com
    时间：2017-01-20
    描述：列表展示
-->
<div class="container" style="margin-top: 20px;">
    <div class="well" style="background-color: #fff;">
        <span class="glyphicon glyphicon-home"> ${typeName}</span>
    </div>
    <div class="list-group">
        <c:forEach items="${memberList}" var="obj" varStatus="vs">
            <a href="${obj.linkUrl}" target="_blank" class="list-group-item" title="${obj.name}"><label style="width: 20px;">${vs.index+1}.</label>&nbsp;&nbsp;${obj.name}</a>
        </c:forEach>
    </div>
    <ul class="pager" style="text-align: center;">
        <li><a href="${sysPath}/znzz/memberList?type=${type}&&page=${page-1}&&pageSize=12">上一页</a></li>
        <li>${page}</li>
        <li><a href="${sysPath}/znzz/memberList?type=${type}&&page=${page+1}&&pageSize=12">下一页</a></li>
    </ul>
</div>
<script>
    $(function(){
    	//页面打开展示在顶部
        parent.scrollTo(0,0);
        //调整页面高度
        setTimeout("changeH()","100");
    });
    function changeH(){
        var iframe =window.parent.document.getElementById("iframe");
        iframe.style.height=$("#body").height()+30;
    }
</script>
</body>
</html>