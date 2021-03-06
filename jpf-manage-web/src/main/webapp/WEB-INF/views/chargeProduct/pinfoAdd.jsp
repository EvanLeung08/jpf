<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品基础信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="editForm" method="post" enctype="multipart/form-data" accept="image/gif, image/jpeg,image/jpg, image/png">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>添加</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品类型：</td>
                        <td width="300px">
                            <select editable="false" id="typeId" name="typeId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="typeName" id="typeName" value="" />
                        </td>
                        <td width="100px;">
                            <input id="typeName_add" name="typeName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_typeName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">供应商：</td>
                        <td>
                            <select editable="false" id="supplierId" name="supplierId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="supplierName" id="supplierName" value="" />
                        </td>
                        <td>
                            <input id="supplierName_add" name="supplierName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_supplierName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">品牌：</td>
                        <td>
                            <select editable="false" id="brandId" name="brandId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="brandName" id="brandName" value="" />
                        </td>
                        <td>
                            <input id="brandName_add" name="brandName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_brandName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    $(function () {
        $("#typeId").combobox({
            url : '../chargeProduct/getChargeProductType',
            valueField : 'id',
            textField : 'typeName',
            onSelect: function () {
                var typeName = $("#typeId").combobox("getText");
                $("#typeName").val(typeName)
            }
        });


        $("#supplierId").combobox({
            url : '../chargeProduct/getChargeSuppliers',
            valueField : 'id',
            textField : 'supplierName',
            onSelect: function () {
                var supplierName = $("#supplierId").combobox("getText");
                $("#supplierName").val(supplierName)
            }
        });

        $("#brandId").combobox({
            url : '../chargeProduct/getChargeBrandList',
            valueField : 'id',
            textField : 'brandName',
            onSelect: function () {
                var brandName = $("#brandId").combobox("getText");
                $("#brandName").val(brandName)
            }
        });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        // setTimeout("initData()", 500);

        $('#cancelBtn_m').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });

        $("#saveBtn_supplierName").linkbutton({
            onClick: function () {
                var supplierName = $("#supplierName_add").combobox("getText");
                if ( !supplierName )
                {
                    $.messager.alert('消息提示', '请填写[供应商名称]！', 'error');
                    return false;
                }
                var param = {};
                param["supplierName"] = supplierName;
                $.ajax({
                    type: 'post',
                    url: 'supplier/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $("#supplierId").combobox('reload',"../chargeProduct/getChargeSuppliers");
                            $("#supplierName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $("#saveBtn_typeName").linkbutton({
            onClick: function () {
                var typeName = $("#typeName_add").combobox("getText");
                if ( !typeName )
                {
                    $.messager.alert('消息提示', '请填写[商品类型]！', 'error');
                    return false;
                }
                var param = {};
                param["typeName"] = typeName;
                $.ajax({
                    type: 'post',
                    url: 'producttype/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            // $('#infoDiv').window('close');
                            // $('#dg').datagrid('reload');
                            $("#typeId").combobox('reload','../chargeProduct/getChargeProductType');
                            $("#typeName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $("#saveBtn_brandName").linkbutton({
            onClick: function () {
                var brandName = $("#brandName_add").combobox("getText");
                if ( !brandName )
                {
                    $.messager.alert('消息提示', '请填写[品牌名称]！', 'error');
                    return false;
                }
                var param = {};
                param["brandName"] = brandName;
                $.ajax({
                    type: 'post',
                    url: 'brand/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            // $('#infoDiv').window('close');
                            // $('#dg').datagrid('reload');
                            $("#brandId").combobox('reload','../chargeProduct/getChargeBrandList');
                            $("#brandName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

    });

</script>
</body>
</html>
