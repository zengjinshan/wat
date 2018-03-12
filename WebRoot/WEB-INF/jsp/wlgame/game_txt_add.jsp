<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <!-- jsp文件头和头部 -->
    <%@ include file="../system/index/top.jsp"%>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <form action="wl/gameTxt/saveOrUpdate.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="id" id="id" value="${pd.id}"/>
                            <input type="hidden" id="gameId" name="gameId" value="${gameId}">
                            <input type="hidden" id="phones" name="phones" value="${hasPhones}">
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <%--   <tr>
                                           <td style="width:50px;text-align: right;padding-top: 13px;">图片:</td>
                                           <td>
                                               <c:if test="${pd == null || pd.PATH == '' || pd.PATH == null }">
                                                   <input type="file" id="tp" name="tp" onchange="fileType(this)"/>
                                               </c:if>
                                               <c:if test="${pd != null && pd.PATH != '' && pd.PATH != null }">
                                                   <a href="<%=basePath%>uploadFiles/uploadImgs/${pd.PATH}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.PATH}" width="210"/></a>
                                                   <input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.PATH}','${pd.PICTURES_ID }');" />
                                                   <input type="hidden" name="tpz" id="tpz" value="${pd.PATH }"/>
                                               </c:if>
                                           </td>
                                       </tr>--%>
                                    <tr>
                                        <td style="width:50px;text-align: right;padding-top: 13px;">名称:</td>
                                        <td style="width: 80%">
                                            <input type="text" name="name" id="name" value="${pd.name}" maxlength="30" placeholder="这里输入名称" title="名称" style="width:98%;"/>
                                        <td style="width: 5%"></td>
                                    </tr>
                                        <tr>
                                            <td style="width:50px;text-align: right;padding-top: 13px;">手机:</td>
                                            <td style="vertical-align:top;padding-left:2px;">
                                               <%-- <select class="chosen-select form-control"  id="phoneId" data-placeholder="请选择手机" style="vertical-align:top;width: 120px;">
                                                    <c:forEach items="${phones}" var="var" varStatus="vs">
                                                        <option value="${var.id}" <c:if test="${var.id==phoneId}">selected</c:if>>${var.phone_no}</option>
                                                    </c:forEach>
                                                </select>--%>
                                                   <a class="btn btn-mini btn-purple"  onclick="editRights('${pd.userId }');"><i class="icon-pencil"></i>选择手机</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:50px;text-align: right;padding-top: 13px;">是否有效:</td>
                                            <td>
                                                <select  id="validFlag" title="状态">
                                                    <option value="1" <c:if test="${pd.valid_flag == '1' }">selected</c:if> >是</option>
                                                    <option value="0" <c:if test="${pd.valid_flag == '0' }">selected</c:if> >否</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <c:if test="${pd != null &&  pd.files != null }">
                                            <c:forEach items="${pd.files}" var="var" varStatus="vs">
                                                <tr>
                                                    <td colspan="2">
                                                        <label>${var.file_name}</label>
                                                        <input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${var.path}','${var.id }');" />
                                                    </td>
                                                    <c:if test="${vs.index==0}">
                                                        <td style="width: 8%;text-align: center;padding-top: 13px;"><a style="cursor: pointer" href="javascript:addFIle();"><b>+</b></a></td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${pd==null || pd.files== null}">
                                            <tr>
                                                <td colspan="2">
                                                    <input type="file" id="tp" name="tp" onchange="fileType(this)"/>
                                                </td>
                                                <td style="width: 8%;text-align: center;padding-top: 13px;"><a style="cursor: pointer" href="javascript:addFIle();"><b>+</b></a></td>
                                            </tr>
                                        </c:if>
                                       <%-- <tr>
                                            <td colspan="2">
                                                <c:if test="${pd==null ||  pd.files ==null}">
                                                    <input type="file" id="tp" name="tp" onchange="fileType(this)"/>
                                                </c:if>

                                                <c:if test="${pd != null &&  pd.files != null }">
                                                    <label>${pd.files.file_name}</label>
                                                    <input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.files.path}','${pd.files.id }');" />
                                                    <input type="hidden" name="tpz" id="tpz" value="${pd.files.path }"/>
                                                </c:if>
                                            </td>
                                            <td style="width: 8%;text-align: center;padding-top: 13px;"><a style="cursor: pointer" href="javascript:addFIle();"><b>+</b></a></td>
                                        </tr>--%>
                                   <%-- <tr>
                                        <td style="text-align: center;" colspan="2">

                                        </td>
                                    </tr>--%>
                                </table>
                                <div align="center">
                                    <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
                                    <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                                </div>
                            </div>
                            <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
                        </form>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<!-- 页面底部js¨ -->
<%@ include file="../system/index/foot.jsp"%>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- 上传控件 -->
<script src="static/ace/js/ace/elements.fileinput.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript">

    $(top.hangge());

    $(function() {
        //上传
        $("input[name='tp']").ace_file_input({
            no_file:'请选择文件 ...',
            btn_choose:'选择',
            btn_change:'更改',
            droppable:false,
            onchange:null,
            thumbnail:false, //| true | large
            whitelist:'txt',
            //blacklist:'gif|png|jpg|jpeg'
            //onchange:''
            //
        });
    });

    function editRights(userId){
        var phones=$("#phones").val();
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag = true;
        diag.Title = "手机选择";
        diag.URL = '<%=basePath%>wl/game/phoneList.do?userId='+userId+"&phones="+phones;
        diag.Width = 320;
        diag.Height = 450;
        diag.CancelEvent = function(){ //关闭事件
            diag.close();
        };
        diag.show();
    }
    //保存
    function save(){
        if(typeof $("input[name='tp']") !='undefined'){
            for(var i=0;i<$("input[name='tp']").length;i++){
                if($("input[name='tp']")[i].value==""){
                    $("input[name='tp']").eq(i).tips({
                        side:3,
                        msg:'请选文件',
                        bg:'#AE81FF',
                        time:3
                    });
                    return false;
                }
            }
        }
         /*   if($("#tp").val()=="" || document.getElementById("tp").files[0] =='请选择文件'){

                $("#tp").tips({
                    side:3,
                    msg:'请选文件',
                    bg:'#AE81FF',
                    time:3
                });
                return false;
            }*/
        var form=new FormData($("#Form")[0]);
        var phoneIds=$("#phones").val();
        var validFlag=$("#validFlag option:selected").val();
        form.append("phoneIds",phoneIds);
        form.append("validFlag",validFlag);
        $.ajax({
            url:"<%=basePath%>wl/gameTxt/saveOrUpdate",
            type:"POST",
            data:form,
            dataType:"JSON",
            processData: false,  // 告诉jQuery不要去处理发送的数据
            contentType: false ,  // 告诉jQuery不要去设置Content-Type请求头
            success:function(data){
                if(data.success){
                    $("#msg").tips({
                        side:3,
                        msg:'提交成功',
                        bg:'#68B500',
                        time:4
                    });
                }else {
                    $("#msg").tips({
                        side:3,
                        msg:'提交失败',
                        bg:'#FF0000',
                        time:6
                    });
                }
                //``
                $("#zhongxin").hide();
                $("#zhongxin2").show();
                top.Dialog.close();
            }
        });
          /*$("#Form").submit();*/
    }

    //过滤类型
    function fileType(obj){
        var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
        if(fileType != '.txt'){
            $("#tp").tips({
                side:3,
                msg:'请上传TXT格式的文件',
                bg:'#AE81FF',
                time:3
            });
            $("#tp").val('');
            document.getElementById("tp").files[0] = '请选择文件';
        }
    }

    //删除图片
    function delP(PATH,id){
        if(confirm("确定要删除文件？")){
            var url = "wl/gameTxt/deltp.do?PATH="+PATH+"&id="+id+"&guid="+new Date().getTime();
            $.get(url,function(data){
                if(data=="success"){
                    alert("删除成功!");
                    document.location.reload();
                }
            });
        }
    }

    function addFIle(){
        var tr="<tr><td colspan=\"2\">" +
                "<input type=\"file\" name=\"tp\" onchange=\"fileType(this)\"/>" +
        "</td>" +
                "</tr>";
        $("#table_report").append(tr);
        $("input[name='tp']").ace_file_input({
            no_file:'请选择文件 ...',
            btn_choose:'选择',
            btn_change:'更改',
            droppable:false,
            onchange:null,
            thumbnail:false, //| true | large
            whitelist:'txt',
            //blacklist:'gif|png|jpg|jpeg'
            //onchange:''
            //
        });
        $("#table_report").show();
    }


</script>
</body>
</html>