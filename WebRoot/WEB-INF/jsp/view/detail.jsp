<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/view/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<style type="text/css">
			body{font-family: 微软雅黑;}
			.main{width:860px;margin:0 auto;padding-top: 10px;}
		</style>
	</head>
	<body style="background-color: #f5f5f5;">
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：详情信息展示 
        -->
		<div class="container" style="background-color: #fff;margin-top: 20px;margin-bottom: 20px;" id="detail">
		  	<div class="main">
				<h3 style="font-size: 20px;color: #0a3f79;">${information.title}</h3>
				<p style="font-size: 12px;color: #808080;padding-top: 6px;">发布日期：<fmt:formatDate value="${information.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				<hr color="#dcdcdc" width="100%">
				<div>${information.detailIntroduce}</div>
			</div>
		</div>
		<script>
			$(function() {
				//页面打开展示在顶部
				parent.scrollTo(0,0);
                //调整页面高度
                setTimeout("changeH()","200");
			});
            function changeH(){
                var iframe =window.parent.document.getElementById("iframe");
                iframe.style.height=$("#detail").height()+50;
            }
		</script>
	</body>
</html>