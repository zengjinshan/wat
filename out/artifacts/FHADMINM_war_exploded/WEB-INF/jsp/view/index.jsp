<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/view/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="keywords" content="智能,智能制造,中国工业合作协会,工业合作协会,智能制造委员会">
		<meta name="description" content='智能,智能制造,中国工业合作协会,工业合作协会,智能制造委员会,中国工业合作协会智能制造委员会'>
		<title>中国工业合作协会智能制造委员会</title>
		<style type="text/css">
			body{font-family: 微软雅黑;}
			a{text-decoration:none;color: #000;}
            nav li{margin-left: 30px;}
            .navbar-default .navbar-nav>.active>a,
			.navbar-default .navbar-nav>.active>a:hover,
			.navbar-default .navbar-nav>.active>a:focus{color: #fff;background-color: #0a3f79;/*height: 110px;margin-top: -32px;padding-top: 46px;*/}
			nav{margin: 0;}
		</style>
	</head>
	<body>
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：顶部栏目
        -->
		<nav class="navbar navbar-default" style="background-color: #fff;height: 90px;">
			<div class="container">
				<div class="navbar-left" style="height: 90px;line-height: 90px;padding-top: 10px;">
					<a href="${sysPath}/znzz" target="_parent"><img src="${sysPath}/static/img/logo.png" height="70px"></a>
				</div>
				<div class="navbar-header navbar-right" style="height: 90px;line-height: 90px;">
			      <ul class="nav navbar-nav" style="padding-top: 20px;">
					  <li class="active"><a href="${sysPath}/znzz" target="_parent">网站首页</a></li>
					  <li><a href="${sysPath}/znzz/informationList?type=1&&page=1&&pageSize=12" target="iframe">实训基地</a></li>
					  <li><a href="${sysPath}/znzz/informationList?type=2&&page=1&&pageSize=12" target="iframe">合作伙伴</a></li>
					  <li><a href="${sysPath}/znzz/informationList?type=4&&page=1&&pageSize=12" target="iframe">资讯中心</a></li>
					  <li><a href="${sysPath}/znzz/gotoUrl?name=contact" target="iframe">联系我们</a></li>
			      </ul>
				</div>
			</div>
		</nav>
		
		<!--
        	作者：caikunhai@126.com
        	时间：2017-02-09
        	描述：iframe窗口，页面点击只在这里面切换
        -->
		<iframe src="${sysPath}/znzz/main" name="iframe" id="iframe" width="100%" onload="setIframeHeight(this);" frameborder="no" border="0" scrolling="no" allowtransparency="yes" style="margin-top: -20px;"></iframe>

		<%--<div style="text-align: center;">
			<a href="javascript:$('body').animate({scrollTop: '0'},1000);"><span class="glyphicon glyphicon-hand-up" style="font-size: 20px;"></span></a>
		</div>--%>
		<!--
        	作者：caikunhai@126.com
        	时间：2017-01-20
        	描述：顶部版本栏目
        -->
		<footer class="container-fluid bg-4 text-center">
		  <p style="height: 48px;line-height: 48px;font-size: 12px;">Copyright © 2017, Smartisan Digital Co., Ltd. All Rights Reserved.中国工业合作协会智能制造委员会</p>
		</footer>
		<script>
		$(function() {
			//标题点击样式
			$(".nav li").click(function () {
				$(this).addClass("active").siblings().removeClass("active");
			});

		});
		function setIframeHeight(iframe) {
			if (iframe) {
				var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
				if (iframeWin.document.body) {
					iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
				}
			}
		};
		
		window.onload = function () {
			setIframeHeight(document.getElementById('iframe'));
		};

		</script>
	</body>
</html>
