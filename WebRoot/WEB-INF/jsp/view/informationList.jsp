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
		<div class="container" style="margin-top: 20px;" id="body">
			<div class="well" style="background-color: #fff;">
				<span class="glyphicon glyphicon-home"> ${typeName}</span>
			</div>
		  	<div class="list-group">
		  	<c:forEach items="${informationList}" var="obj" varStatus="vs">
				<c:if test="${obj.type eq '4'}">
					<a href="${sysPath}/znzz/detail?id=${obj.id}" class="list-group-item" title="${obj.title}">
						<label style="width: 20px;">${vs.index+1}.</label>&nbsp;
						${obj.title}
						<span style="float:right"><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</a>
				</c:if>
				<c:if test="${obj.type ne '4'}">
					<a href="${sysPath}/znzz/detail?id=${obj.id}" class="list-group-item" title="${obj.title}">
						<label style="width: 20px;">${vs.index+1}.</label>&nbsp;
						${obj.title}
					</a>
				</c:if>
		    </c:forEach>
			</div>
			<ul class="pager" style="text-align: center;">
				<li><a href="${sysPath}/znzz/informationList?type=${type}&&page=${page-1}&&pageSize=12">上一页</a></li>
				<li>${page}</li>
				<li><a href="${sysPath}/znzz/informationList?type=${type}&&page=${page+1}&&pageSize=12">下一页</a></li>
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