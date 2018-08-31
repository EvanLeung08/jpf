<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商户充值信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div id="formDiv1" class="easyui-panel"  style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="auditForm" method="post" enctype="multipart/form-data">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值公司：</td>
                    <td>
                        <input id="companyName" name="companyName" type="text"  data-options="required:true"
                               width="120" class="easyui-textbox"/>
                        <input id="companyId" name="companyId" type="hidden"  />
                        <a id="searchCompany" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">选取商户</a>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值金额：</td>
                    <td>
                        <input id="money" name="money" type="text" value="1"  data-options="required:true"
                               missingMessage=""  width="120" class="easyui-numberbox" precision="2"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">付款凭证：</td>
                    <td>
                        <p>上传文件： <input type="file" name="file" id="file2"></p>
                                     <input type="button" value="上传" onclick="doUpload()"/>
                        <div id="ap"></div>
                        <input id="imgUrl" name="imgUrl" type="hidden" style="width:150px"  required="true" value="" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"     style=" height: 30px; line-height: 30px;">
        <a id="saveBtn_audit" class="easyui-linkbutton" icon="icon-ok"    href="javascript:void(0)">确定</a>
    </div>
    <div id="companys"></div>
</div>
<!-- /添加弹出窗口 -->

<script>
    function doUpload() {
        var file = $("#file2").val();
        if( file == "" ){
            $.messager.alert('提示', '请先选择要上传文件!', 'info');
            return false;
        }
        var formData = new FormData();
        formData.append('file', $('#file2')[0].files[0]);console.log(formData);
        $.ajax({
            url: '../shopCompanyCharge/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (ret) {
                var c =   '<img width="200px" height="200px" src="'+ret+'"/>';
                $("#ap").html(c);
                $("#imgUrl").val(ret);
            },
            error: function (ret) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }

    $(function () {
        // 选取公司按钮
        $("#searchCompany").linkbutton({
            onClick:function(){
                $('#companys').window("open").window('refresh', '../shopCompanyCharge/chargeCompanys').window('setTitle','选取公司');
            }
        });



        // 选取公司弹窗大小
        $('#companys').window({
            width:'1024px',
            height:'550px',
            closed:true,
            modal:true
        });

        // 点击确定
        $("#saveBtn_audit").linkbutton({
            onClick : function () {
                if ( $("#companyId").val() == ""  ){
                    $.messager.alert('提示', '请选择公司', 'info');
                    return false;
                }
                // 判断上传文件
                var file = $("#imgUrl").val();
                if( file == "" ){
                    $.messager.alert('提示', '请先选择要上传文件!', 'info');
                    return false;
                }
                var type = file.slice(file.lastIndexOf(".")+1,file.length);
                var checkType = type.toUpperCase();
                /*if( checkType !== "XLS" && checkType !== "XLSX" ){
                    $.messager.alert('提示', '所选的文件格式不正确!', 'info');
                    return false;
                }*/
                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '../shopCompanyCharge/add/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if ( msg.retCode  != '0000' ) {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $('#infoDiv').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });

            }
        })

    })
</script>
</body>
</html>