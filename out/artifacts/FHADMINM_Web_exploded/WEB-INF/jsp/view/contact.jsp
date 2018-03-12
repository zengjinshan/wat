<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/view/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=IGVyjIUwZetstYEFZIcoS8IERWGckQW2"></script>
    <style type="text/css">
        body{font-family: 微软雅黑;}
    </style>
</head>
<body style="height:300px;">
<!--
    作者：caikunhai@126.com
    时间：2017-01-20
    描述：联系我们
-->
<div class="container" style="height: 500px;margin-top: 20px;">
    <div class="well" style="background-color: #fff;">
        <span class="glyphicon glyphicon-home"> 联系我们</span>
    </div>
    <div class="col-sm-5" style="margin-top: 20px;">
        <p><h2 style="line-height: 56px;">福建工业合作协会</h2></p>
        <p><h4>联系地址：厦门市思明区观音山世纪大厦1108</h4></p>
        <p><h4>联系电话：88888888888</h4></p>
        <p><h4>联系邮箱：xxxxx@qq.com</h4></p>
    </div>
    <div class="col-sm-7">
        <div id="allmap" style="width: 100%;height:400px;overflow: hidden;margin:0;font-family:"微软雅黑";"></div>
</div>
</div>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(118.203605,24.493702);
    var mk = new BMap.Marker(point);
    map.centerAndZoom(point,18);
    map.addOverlay(mk);
    map.panTo(point);
    //map.enableScrollWheelZoom();
    //调整页面高度
    var iframe =window.parent.document.getElementById("iframe");
    iframe.style.height='560px';
</script>
</body>
</html>