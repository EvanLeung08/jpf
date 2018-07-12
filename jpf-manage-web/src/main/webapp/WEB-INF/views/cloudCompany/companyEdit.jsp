<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>公司信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="editForm" method="post" enctype="multipart/form-data"
              accept="image/gif, image/jpeg,image/jpg, image/png">
            <input type="hidden" id="id_m" name="id" value="${cloudCompanyInfo.id}">
            <input type="hidden" id="merchNo" name="merchNo" value="${cloudCompanyInfo.merchNo}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">公司名称：</td>
                    <td>
                        <input id="name" name="name" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${cloudCompanyInfo.name}"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人姓名：</td>
                    <td>
                        <input id="phonename" name="phonename" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${cloudCompanyInfo.phonename}"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人电话：</td>
                    <td>
                        <input id="phone" name="phone" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.phone}" data-options="required:true,validType:'phoneRex'"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">邮　箱：</td>
                    <td>
                        <%--<input id="linkemail" name="linkemail" readonly type="text" style="width:150px;color:--%>
<%--#ff2d25" class="easyui-textbox" value="${cloudCompanyInfo.linkemail}" data-options="prompt:'Enter a valid email.',required:true,validType:'email'"/>--%>
                        <span>${cloudCompanyInfo.linkemail}</span>
                    </td>


                </tr>
                <tr>
                  <%--  <td style="text-align: right;background-color: #f1f1f1;">商户号：</td>
                    <td>
                        <input id="merch_no" name="merch_no" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value=""/>
                    </td>--%>

                    <td style="text-align: right;background-color: #f1f1f1;">企业类型：</td>
                    <td>
                        <c:if  test="${cloudCompanyInfo.type eq 1 }">代理公司</c:if>
                        <c:if  test="${cloudCompanyInfo.type eq 0 }">业务公司</c:if>
                    <input type="hidden" name="type" value="<c:if  test="${cloudCompanyInfo.type eq 1 }">1</c:if> <c:if  test="${cloudCompanyInfo.type eq 0 }">0</c:if>">
                    </select>


                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                    <td>
                        <select id="attestation" name="attestation" data-options="required:true"
                                style="width:120px;">

                            <option value="0" <c:if  test="${cloudCompanyInfo.attestation eq 0 }">selected</c:if>>未认证</option>
                            <option value="1" <c:if  test="${cloudCompanyInfo.attestation eq 1 }">selected</c:if>>已认证</option>
                        </select>
                    </td>
                      <td style="text-align: right;background-color: #f1f1f1;">服务费：</td>
                      <td>
                          <input  id="salesRate" name="salesRate" type="text" style="width:150px" class="easyui-numberbox"
                                 value="<c:if  test="${cloudCompanyInfo.type eq 1 }">${cloudCompanyInfo.agentRate}</c:if><c:if  test="${cloudCompanyInfo.type eq 0 }">${cloudCompanyInfo.salesRate}</c:if>" data-options="required:true" precision="2"/><span style="color: #FF2F2F"> 注：当为0.00时不收取服务费</span>
                      </td>
                      <td style="text-align: right;background-color: #f1f1f1;">登录状态：</td>
                      <td>
                          <select id="status" name="status" data-options="required:true"
                                  style="width:120px;">

                              <option value="1" <c:if  test="${cloudCompanyInfo.status eq 1 }">selected</c:if>>正常</option>
                              <option value="-1" <c:if  test="${cloudCompanyInfo.status eq -1 }">selected</c:if>>禁闭</option>
                          </select>
                      </td>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                    <td>

                        <p>上传文件：  <input type="file" name="file"></p>
                        <input type="button" value="上传" onclick="doUploadY()"/>
                        <div  id="apy"><img style="width: 200px;height: 200px;" src="${pageContext.request.contextPath}/resources/${cloudCompanyInfo.bslicense}"></div>
                     <input id="bslicense" name="bslicense" type="hidden" style="width:150px"
                               required="true" value="${cloudCompanyInfo.bslicense}"/>
                    </td>

                    <td style="text-align: right;background-color: #f1f1f1;">企业资质：</td>
                    <td>

                            <p>上传文件： <input type="file" name="file"></p>
                            <input type="button" value="上传" onclick="doUpload()"/>


                    <div  id="ap"><img style="width: 200px;height: 200px;" src="${pageContext.request.contextPath}/resources/${cloudCompanyInfo.aptitude}"></div>
                        <input id="aptitude" name="aptitude" type="hidden" style="width:150px"
                               required="true" value="${cloudCompanyInfo.aptitude}" />
                    </td>
            <%--    <td style="text-align:right; background-color: #f1f1f1;">账户通知方式：</td>
                <td style="text-align:left">
                              &lt;%&ndash;      <span class="radioSpan">
                                        <input type="radio" name="tipsType" value="1" checked="checked">邮件</input>
                                      &lt;%&ndash;  <input type="radio" name="tipsType" value="2">短信</input>&ndash;%&gt;
                             </span>&ndash;%&gt;
                        <select id="tipstype" name="tipstype" data-options="required:true"
                                style="width:120px;">

                            <option value="1" selected="selected">邮件</option>
                            <option value="2">短信</option>
                        </select>


                </td>--%>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>

                <tr>

                    <%--     <td style="text-align:right;">短信发送：</td>
                         <td style="text-align:left">
                                 <span class="radioSpan">
                                     <input type="radio" name="issms" value="0">不发送</input>
                                     <input type="radio" name="issms" value="2">发送</input>
                                 </span>
                         </td>--%>
                  <%--  <td style="text-align: right;background-color: #f1f1f1;">开户名称：</td>
                    <td>
                        <input id="bankname" name="bankname" type="text" style="width:150px" class="easyui-textbox"
                                value=""/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">对公账户：</td>
                    <td>
                        <input id="bankno" name="bankno" type="text" style="width:150px" class="easyui-textbox"
                                value=""/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">开户银行：</td>
                    <td>
                        <input id="banktype" name="banktype" type="text" style="width:150px" class="easyui-textbox"
                                value=""/>
                    </td>--%>
                        <td style="text-align: right;background-color: #f1f1f1;">客户经理：</td>
                        <td>
                            <input id="serviclinkuser" name="serviclinkuser" type="text" style="width:150px"
                                   class="easyui-textbox" value="${cloudCompanyInfo.serviclinkuser}"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                        <td>
                            <input id="linkphone" name="linkphone" type="text" style="width:150px" class="easyui-textbox"
                                   value="${cloudCompanyInfo.linkphone}"/>
                        </td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
              <!--上传图片、文件的代码-->
           <%--   <form action="${pageContext.request.contextPath}/cloudCompany/upload" method="post" enctype="multipart/form-data" >
                  <input type="file" name="file">
                  <input type="text" name="filesss" value="${pageContext.request.contextPath}/uploads">
                  <input type="submit">
              </form>
              <!--接收-->
              <img src="${pageContext.request.contextPath}/${img}"/>--%>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_m" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>

    //自定义验证
    $.extend($.fn.validatebox.defaults.rules, {
        phoneRex: {
            validator: function(value){
                var rex=/^1[3-8]+\d{9}$/;
                //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
                //电话号码：7-8位数字： \d{7,8
                //分机号：一般都是3位数字： \d{3,}
                //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
                var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                if(rex.test(value)||rex2.test(value))
                {
                    // alert('t'+value);
                    return true;
                }else
                {
                    //alert('false '+value);
                    return false;
                }

            },
            message: '请输入正确电话或手机格式'
        }
    });

    function initData() {
        $('#status_p').combobox('select', '${rolesInfo.status}');
        <%--$('#paytype').combobox('select', '${rolesInfo.zftype}');--%>
    }
    function doUploadY() {
        var formData = new FormData($( "#editForm" )[0]);
        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                var yc=   '<img width="200px" height="200px" src="${pageContext.request.contextPath}/resources/'+returndata+'"/>';
                $("#apy").html(yc);
                $("#bslicense").val(returndata);
            },
            error: function (returndata) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }
    function doUpload() {
        var formData = new FormData($( "#editForm" )[0]);
        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (ret) {
                var c=   '<img width="200px" height="200px" src="${pageContext.request.contextPath}/resources/'+ret+'"/>';
                $("#ap").html(c);
                $("#aptitude").val(ret);
            },
            error: function (ret) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }

    $(function () {
        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $("#saveBtn_m").linkbutton({
            onClick: function () {
                var isValid = $("#editForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#editForm').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '../cloudCompany/edit/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'error');
                            $('#infoDiv').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $('#cancelBtn_m').linkbutton({
            onClick: function () {
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>