<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/view/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<style type="text/css">
			body{font-family: 微软雅黑;}
			a{text-decoration:none;color: #000;}
		</style>
	</head>
	<body style="margin-top: 15px;">
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：轮播广告
        -->
		<div class="container">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
				<c:forEach items="${banners}" var="obj" varStatus="v">
					<c:if test="${v.index eq '0'}">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					</c:if>
					<c:if test="${v.index ne '0'}">
						<li data-target="#myCarousel" data-slide-to="1"></li>
					</c:if>
				</c:forEach>
				</ol>
				
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${banners}" var="obj" varStatus="v">
						<c:if test="${v.index eq '0'}">
							<div class="item active"><img src="${sysPath}/uploadFiles/uploadImgs/${obj.url}" rel="${obj.remark}" title="${obj.remark}" alt="${obj.remark}" style="width: 100%;height: 320px;"></div>
						</c:if>
						<c:if test="${v.index ne '0'}">
							<div class="item"><img src="${sysPath}/uploadFiles/uploadImgs/${obj.url}" rel="${obj.remark}" title="${obj.remark}" alt="${obj.remark}"  style="width: 100%;height: 320px;"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：基地介绍栏目
        -->
		<div class="container" style="border: 0;box-shadow: #fff;margin-top: 15px;padding: 0;">
		  <div class="panel panel-default col-sm-9" style="border: 0;border-radius: 0;box-shadow: 0px 0px 0px 0px #fff;">
		    <div class="panel-heading" style="padding-right: -15px;"><span style="color:#0a3f79;font-weight:bold;">实训基地</span><a style="float:right;" href="${sysPath}/znzz/informationList?type=1&&page=1&&pageSize=12">更多</a></div>
		    <div class="panel-body" style="padding: 0;padding-top: 15px;">
		    	<c:forEach items="${trains}" var="obj">
		    	<div class="col-sm-4">
			      <a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.easyIntroduce}"><img src="${sysPath}/uploadFiles/uploadImgs/${obj.enclosureUrl}" alt="${obj.title}" width="100%" height="172"></a>
			      <p style="margin-top: 12px;"><strong>${obj.title}</strong></p>
			      <div style="font-size: 12px;">
					  <c:if test="${fn:length(obj.easyIntroduce)>58}">${fn:substring(obj.easyIntroduce, 0, 58)}...</c:if>
					  <c:if test="${fn:length(obj.easyIntroduce)<=58}">${obj.easyIntroduce}</c:if>
				  </div>
			    </div>	
		    	</c:forEach>
		    </div>
		  </div>

			<!--
        	作者：caikunhai@126.com
        	时间：2017-02-09
        	描述：资讯中心
        -->
			<div class="panel panel-default col-sm-3" style="border: 0px;box-shadow: 0px 0px 0px 0px #fff;">
				<div class="panel-heading"><span style="color:#0a3f79;font-weight:bold;">资讯中心</span><a style="float:right;" href="${sysPath}/znzz/informationList?type=4&&page=1&&pageSize=12">更多</a></div>
				<div class="panel-body" style="padding: 15px 0 0 0;">
					<div class="list-group" style="border: 0px;border-radius: 0;">
						<c:forEach items="${news}" var="obj" varStatus="vs">
							<c:if test="${vs.count<8}">
								<c:if test="${fn:length(obj.title)>14}">
									<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${fn:substring(obj.title, 0, 14)}...</a>
								</c:if>
								<c:if test="${fn:length(obj.title)<=14}">
									<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${obj.title}</a>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>
		
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：第二大模块
        -->
		<div class="container" style="border: 0px;box-shadow: #fff;outline:none;padding: 0;">
			<!-- 成果展示 -->
		  	<div class="panel panel-default col-sm-4" style="border: 0px;box-shadow: 0px 0px 0px 0px #fff;">
			    <div class="panel-heading"><span style="color:#0a3f79;font-weight:bold;">成果展示</span><a style="float:right;" href="${sysPath}/znzz/informationList?type=3&&page=1&&pageSize=12">更多</a></div>
			    <div class="panel-body" style="padding: 15px 0 0 0;">
			    	<div class="list-group" style="border: 0px;border-radius: 0;">
			    	<c:forEach items="${achievements}" var="obj" varStatus="vs">
						<c:if test="${vs.count<9}">
							<c:if test="${fn:length(obj.title)>20}">
								<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${fn:substring(obj.title, 0, 20)}...</a>
							</c:if>
							<c:if test="${fn:length(obj.title)<=20}">
								<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${obj.title}</a>
							</c:if>
						</c:if>
				    </c:forEach>
				    </div>
			    </div>
			</div>
			<!-- 合作伙伴 -->
		    <div class="panel panel-default col-sm-4" style="border: 0px;box-shadow: 0px 0px 0px 0px #fff;">
			    <div class="panel-heading"><span style="color:#0a3f79;font-weight:bold;">合作伙伴</span><a style="float:right;" href="${sysPath}/znzz/informationList?type=2&&page=1&&pageSize=12">更多</a></div>
			    <div class="panel-body" style="padding: 15px 0 0 0;">
			    	<div class="list-group">
			    	<c:forEach items="${partners}" var="obj" varStatus="vs">
						<c:if test="${vs.count<9}">
							<c:if test="${fn:length(obj.title)>20}">
								<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${fn:substring(obj.title, 0, 20)}...</a>
							</c:if>
							<c:if test="${fn:length(obj.title)<=20}">
								<a href="${sysPath}/znzz/detail?id=${obj.id}" title="${obj.title}" class="list-group-item" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${obj.title}</a>
							</c:if>
						</c:if>
				    </c:forEach>
				    </div>
			    </div>
			</div>
			<!-- 会员风采 -->
			<div class="panel panel-default col-sm-4" style="border: 0px;box-shadow: 0px 0px 0px 0px #fff;">
			    <div class="panel-heading"><span style="color:#0a3f79;font-weight:bold;">会员风采</span><a style="float:right;" href="${sysPath}/znzz/memberList?type=5&&page=1&&pageSize=12">更多</a></div>
			    <div class="panel-body" style="padding: 15px 0 0 0;">
			    	<div class="list-group">
			    	<c:forEach items="${members}" var="obj" varStatus="vs">
			    		<c:if test="${vs.count<6}">
							<c:if test="${fn:length(obj.name)>20}">
								<a href="${obj.linkUrl}" class="list-group-item" target="_blank" title="${obj.name}" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${fn:substring(obj.name, 0, 20)}...</a>
							</c:if>
							<c:if test="${fn:length(obj.name)<=20}">
								<a href="${obj.linkUrl}" class="list-group-item" target="_blank" title="${obj.name}" style="border: 0;background-color: #f5f5f5;"><span style="color:#bfbfbf ">■</span> ${obj.name}</a>
							</c:if>
			    		</c:if>
				    </c:forEach>
				    </div>
			    </div>
			</div>
		</div>
		
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：合作院校
        -->
		<div class="container">
		  <div class="panel panel-default">
		    <div class="panel-heading"><span style="color:#0a3f79;font-weight:bold;">合作院校</span></div>
		    <div class="panel-body" style="color: #bfbfbf;">
		    <c:forEach items="${schools}" var="obj" varStatus="v">
				<c:if test="${v.index==0}">
					<a href="${obj.linkUrl}" target="_blank" style="margin-right: 5px;">${obj.name}</a>
				</c:if>
				<c:if test="${v.index>0}">
					<span style="font-size: 16px;margin-right: 5px;">|</span><a href="${obj.linkUrl}" target="_blank" style="margin-right: 5px;">${obj.name}</a>
				</c:if>
			</c:forEach>
		    </div>
		  </div>
		</div>
		
	</body>
</html>
