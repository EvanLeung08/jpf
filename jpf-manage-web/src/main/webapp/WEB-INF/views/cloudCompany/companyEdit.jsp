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
            <h2 align="center">修改基本信息</h2>
            <input type="hidden" id="id_m" name="id" value="${cloudCompanyInfo.id}">
            <input type="hidden" id="merchNo" name="merchNo" value="${cloudCompanyInfo.merchNo}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">公司名称：</td>
                    <td>
                        <input id="name" name="name" data-options="required:true,validType:'chinese'" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${cloudCompanyInfo.name}"/>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">商户别名：</td>
                    <td>
                        <input id="merchName" name="merchName" data-options="required:true" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${cloudCompanyInfo.merchName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人姓名：</td>
                    <td>
                        <input id="phonename" data-options="required:true,validType:'chinese'" name="phonename" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${cloudCompanyInfo.phonename}"/>
                    </td>
                    </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人电话：</td>
                    <td>
                        <input id="phone" name="phone" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.phone}" data-options="required:true,validType:'phoneRex'"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">联系人地址：</td>
                    <td>
                        <input id="addressPerson" data-options="required:true" data-options="required:true,validType:'chinese'"  name="addressPerson" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.addressPerson}" />
                    </td>
                </tr>
                <tr>
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
                        <c:if  test="${cloudCompanyInfo.type == 1 }">代理公司</c:if>
                        <c:if  test="${cloudCompanyInfo.type == 0 }">业务公司</c:if>
                    <input type="hidden" name="type" value="<c:if  test="${cloudCompanyInfo.type == 1 }">1</c:if> <c:if  test="${cloudCompanyInfo.type == 0 }">0</c:if>">
                    </select>


                    </td>
                      </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">纳税人类型：</td>
                    <td>
                        <select id="taxpayertype" name="taxpayertype" data-options="required:true" class="easyui-combobox"
                                style="width:120px;">

                            <option value="01" <c:if  test="${cloudCompanyInfo.taxpayertype == '01' }">selected</c:if>>一般纳税人</option>
                            <option value="02" <c:if  test="${cloudCompanyInfo.taxpayertype == '02' }">selected</c:if>>小规模纳税人</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">纳税人识别号：</td>
                    <td>
                        <input id="tin" name="tin" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.tin}" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                    <td>
                        <select id="attestation" name="attestation"data-options="required:true" class="easyui-combobox"
                                style="width:120px;">

                            <option value="0" <c:if  test="${cloudCompanyInfo.attestation eq 0 }">selected</c:if>>未认证</option>
                            <option value="1" <c:if  test="${cloudCompanyInfo.attestation eq 1 }">selected</c:if>>已认证</option>
                        </select>
                    </td>
                </tr>
                <tr>
                      <td style="text-align: right;background-color: #f1f1f1;">服务费：</td>
                      <td>
                          <input  id="salesRate" name="salesRate" type="text" style="width:150px" class="easyui-numberbox" precision="3"
                                 value="<c:if  test="${cloudCompanyInfo.type == 1 }">${cloudCompanyInfo.agentRate}</c:if><c:if  test="${cloudCompanyInfo.type == 0 }">${cloudCompanyInfo.salesRate}</c:if>" data-options="required:true" precision="2"/><span style="color: #FF2F2F"> 注：当为0.00时不收取服务费</span>
                      </td>
                </tr>
                <tr>
                      <td style="text-align: right;background-color: #f1f1f1;">登录状态：</td>
                      <td>
                          <select id="status" name="status" data-options="required:true" class="easyui-combobox"
                                  style="width:120px;">

                              <option value="1" <c:if  test="${cloudCompanyInfo.status eq 1 }">selected</c:if>>正常</option>
                              <option value="-1" <c:if  test="${cloudCompanyInfo.status eq -1 }">selected</c:if>>禁闭</option>
                          </select>
                      </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                    <td>

                        <p>上传文件：  <input type="file" name="file" id="file1"></p>
                        <input type="button" value="上传" onclick="doUploadY()"/>
                        <div  id="apy"><img style="width: 200px;height: 200px;" src="${cloudCompanyInfo.bslicense}"></div>
                     <input id="bslicense" name="bslicense" type="hidden" style="width:150px"
                               required="true" value="${cloudCompanyInfo.bslicense}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">营业执照编号：</td>
                    <td>
                        <input id="certificate" name="certificate" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.certificate}" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">企业资质：</td>
                    <td>

                            <p>上传文件： <input type="file" name="file" id="file2"></p>
                            <input type="button" value="上传" onclick="doUpload()"/>


                    <div  id="ap"><img style="width: 200px;height: 200px;" src="${cloudCompanyInfo.aptitude}"></div>
                        <input id="aptitude" name="aptitude" type="hidden" style="width:150px"
                               required="true" value="${cloudCompanyInfo.aptitude}" />
                    </td>
                </tr>
            </table>
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
            <table cellpadding=3 class="table table-bordered" align="center">
                <h2 align="center">客户支持</h2>
                <tr>

                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">客户经理：</td>
                    <td>
                        <input id="serviclinkuser" data-options="validType:'chinese'" name="serviclinkuser" type="text" style="width:150px"
                               class="easyui-textbox" value="${cloudCompanyInfo.serviclinkuser}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                    <td>
                        <input id="linkphone" data-options="validType:'phoneRex'" name="linkphone" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.linkphone}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人邮箱：</td>
                    <td>
                        <input id="phoneemail" name="phoneemail" data-options="validType:'email'" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.phoneemail}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">单位注册地址：</td>
                    <td>
                        <input id="address" name="address" type="text" style="width:150px" class="easyui-textbox"
                               value="${cloudCompanyInfo.address}" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" align="center">
                <h2 align="center">对公账户</h2>


                <tr>

                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">对公账户：</td>
                    <td>
                        <input  data-options="required:true,validType:'intOrFloat '" id="bankno" name="bankno" type="text" style="width:150px"
                               class="easyui-textbox" value="${cloudCompanyBankInfo.bankno}"/>
                    </td>
                </tr>

                <tr>

                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">开户名称：</td>
                    <td>
                        <input id="accountName" data-options="required:true,validType:'chinese'"   data-options="required:true,validType:'chinese'" name="accountName" type="text" style="width:150px"
                               class="easyui-textbox" value="${cloudCompanyBankInfo.accountName}"/>
                    </td>
                </tr>
                <td style="text-align: right;background-color: #f1f1f1;">开户银行类型：</td>
                <td>
                    <select id="banktype_m" name="banktype" data-options="required:true"  class="easyui-combobox" data-options="required:true" style="width:100px;">
                    </select>
                </td>


                <tr>

                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">开户行全称：</td>
                    <td>
                        <input id="banksubname" name="banksubname" data-options="required:true,validType:'chinese'" data-options="required:true"  type="text" style="width:150px"
                               class="easyui-textbox" value="${cloudCompanyBankInfo.banksubname}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行开户名称：</td>
                    <td>
                        <input id="bankid" name="bankid" data-options="required:true,validType:'chinese'" data-options="required:true"  type="text" class="easyui-textbox" style="width:175px;" value="${cloudCompanyBankInfo.bankname}" data-options="required:true">
                    </td>
                </tr>
                <tr>

                    <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                    <td>
                        <select id="bankProvince_m" name="bankProvince" class="easyui-combobox" style="width:100px;" data-options="required:true">
                        </select>
                    </td>
                </tr>
                <tr>

                    <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                    <td>
                        <select id="bankCity_m" name="bankCity" class="easyui-combobox" style="width:100px;" data-options="required:true">
                        </select>
                    </td>
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
        },
        intOrFloat : {// 验证整数或小数
            validator : function(value) {
                return /^\d+(\.\d+)?$/i.test(value);
            },
            message : '请输入数字，并确保格式正确'
        },
        chinese : {// 验证中文
            validator : function(value) {
                return /^[\u0391-\uFFE5]+$/i.test(value);
            },
            message : '请输入中文'
        },engOrChineseAndLength : {// 可以是中文或英文
            validator : function(value) {
                var len = $.trim(value).length;
                if (len >= param[0] && len <= param[1]) {
                    return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
                }
            },
            message : '请输入中文或英文'
        },
    });

    function initData() {
        $('#status_p').combobox('select', '${rolesInfo.status}');
        <%--$('#paytype').combobox('select', '${rolesInfo.zftype}');--%>
        $('#province_m').combobox('select', '${cloudCompanyBankInfo.province}');
        $('#city_m').combobox('select', '${cloudCompanyBankInfo.city}');
        <%--$('#attestation_m').combobox('select', '${merchantInfo.attestation==true?0:1}');--%>
        $('#banktype_m').combobox('select', '${cloudCompanyBankInfo.banktype}');
        $('#bankProvince_m').combobox('select', '${cloudCompanyBankInfo.province}');
        $('#bankCity_m').combobox('select', '${cloudCompanyBankInfo.city}');
        $('#isHeadShop').combobox('select', '${isHeadShop}');
    }
    function doUploadY() {
        var formData = new FormData();
        formData.append('file', $('#file1')[0].files[0]);
        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                var yc=   '<img width="200px" height="200px" src="'+returndata+'"/>';
                $("#apy").html(yc);
                $("#bslicense").val(returndata);
            },
            error: function (returndata) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }
    function doUpload() {
        var formData = new FormData();
        formData.append('file', $('#file2')[0].files[0]);
        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (ret) {
                var c=   '<img width="200px" height="200px" src="'+ret+'"/>';
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

        $("#bankid").combobox({
            url : '../param/getBankAll',
            valueField : 'id',
            textField : 'paybankname',
            onLoadSuccess : function () {
                $('#bankid').combobox('setValue', '${cloudCompanyBankInfo.bankid}');
            }
        });
        $('#editForm #province_m').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#editForm #city_m').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#editForm #bankProvince_m').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#editForm #bankCity_m').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#editForm #banktype_m').combobox({
            url:'../param/getType?pid=17',
            valueField:'catid',
            textField:'cat'
        });


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
