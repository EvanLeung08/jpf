<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退单记录</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        .datagrid-body td,.datagrid-body th{ text-align: center; }
        #searchForm td:nth-child(odd) { text-align: right; }
        #searchForm td:nth-child(even) { text-align: left; }
        #searchForm td { width: 5%;  }
    </style>
    <script>
        $(function () {
            var toolbar = [
                {
                    text:'通过审核',
                    iconCls:'icon-ok',
                    handler:function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        if ( rows[0].singlestatus !== 0 ){
                            $.messager.alert('消息提示','此订单已处理，请不要重复处理','info');
                            return false;
                        }
                        var id = rows[0].id;
                        var tdorderid = rows[0].tdorderid;
                        var orderid = rows[0].orderid;
                        $.post("checkOk",{id:id, tdorderid:tdorderid, orderid:orderid,},function (msg) {
                            if (msg.retCode != '0000') {
                                $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                return false;
                            } else {
                                $.messager.alert('消息提示','操作成功!','info');
                                $('#dg').datagrid('reload');
                            }
                        })
                    }
                },{
                    text:'驳回审核',
                    iconCls:'icon-no',
                    handler:function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        if ( rows[0].singlestatus !== 0 ){
                            $.messager.alert('消息提示','此订单已处理，请不要重复处理','info');
                            return false;
                        }
                        $("#opeCon").window('setTitle','驳回理由').window('refresh').window('open');
                    }
                },{
                    text:'重新退款',
                    iconCls:'icon-redo',
                    handler:function(){
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        if ( rows[0].singlestatus !== 4 ){
                            $.messager.alert('消息提示','只能对退款失败的订单发起重新退款','info');
                            return false;
                        }
                        var id = rows[0].id;
                        var orderid = rows[0].orderid;
                        $.post("rerefund",{id:id,orderid:orderid},function(msg){
                            if (msg.retCode != '0000') {
                                $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                return false;
                            } else {
                                $.messager.alert('消息提示','操作成功!','info');
                                $('#dg').datagrid('reload');
                            }
                        })
                    }
                }
            ]
            $("#dg").datagrid({
                title:'订单列表',
                toolbar:toolbar,
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                electOnCheck:true,
                columns:[[
                    {field:'id', title:'ID', width:'3%'},
                    {field:'tdorderid', title:'退单ID', width:'10%'},
                    {field:'orderid', title:'订单ID', width:'10%'},
                    {field:'tdorderprice', title:'订单金额', width:'5%', formatter:formatPrice},
                    //{field:'mtsid', title:'商户ID', width:'5%'},
                    {field:'merchName', title:'商户名称', width:'5%'},
                    {field:'singletype', title:'退单类型', width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '支付后退款';
                            }else if ( value == 2 ){
                                return '未支付撤单';
                            }
                        }},
                    {field:'operateContent', title:'审核信息', width:'35%', formatter:formatJSONOpCon},
                    {field:'singlestatus', title:'退款状态', width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '待审核';
                            }else if ( value == 1 ){
                                return '<span style="color:blue">审核成功，等待退款</span>';
                            }else if ( value == 2 ){
                                return '<span style="color:red">审核驳回</span>';
                            }else if ( value == 3 ){
                                return '<span style="color:green">退款成功</span>';
                            }else if ( value == 4 ){
                                return '<span style="color:red">退款失败</span>';
                            }
                        }},
                    {field:'refundContent', title:'第三方返回内容', width:'35%'},
                    {field:'addtime', title:'添加时间', width:'13%', formatter:formatDateStr}
                ]]
            })

            $('#opeCon').window({
                width:'400px',
                height:'300px',
                closed:true,
                minimizable:false,
                iconCls:'icon-no',
                modal:true
            });

            // 点击驳回弹窗的关闭
            $("#opCancelBtn").linkbutton({
                onClick : function () {
                    $('#opeCon').window('close');
                }
            })

            // 点击驳回弹窗的确定
            $("#opSubmitBtn").linkbutton({
                onClick : function () {
                    var rows = $("#dg").datagrid('getSelections');
                    var id = rows[0].id;
                    var tdorderid = rows[0].tdorderid;
                    var orderid = rows[0].orderid;
                    var operateContent = $("#operateContent").val();
                    if ( operateContent.indexOf('"') > -1 ){
                        alert('请将内容中的双引号""替换成中文版的双引号“”');
                        return false;
                    }
                    $.post("checkNo",{id:id, tdorderid:tdorderid, orderid:orderid, operateContent:operateContent},function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                            $("#opeCon").window('close');
                            return false;
                        } else {
                            $.messager.alert('消息提示','操作成功!','info');
                            $('#dg').datagrid('reload');
                            $("#opeCon").window('close');
                        }
                    })
                }
            })

            // 点击搜索按钮
            $('#searchBtn').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#dg').datagrid('reload', postData);
                }
            });

            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });
        })
    </script>
</head>
<body>
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'" style="padding: 20px;">
        <form id="searchForm" method="post">
            <table cellpadding="5" width="100%">
                <tr>
                    <td>退单ID：</td>
                    <td><input id="tdorderid" name="tdorderid" class="easyui-textbox" type="text" /></td>
                    <td>订单ID：</td>
                    <td><input id="orderid" name="orderid" class="easyui-textbox" type="text" ></td>
                    <td>商户ID：</td>
                    <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" /></td>
                </tr>
                <tr>
                    <td>退单类型：</td>
                    <td>
                        <select name="singletype" id="singletype" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="1">支付后退单</option>
                            <option value="2">未支付退单</option>
                        </select>
                    </td>
                    <td>退款状态：</td>
                    <td>
                        <select id="singlestatus" name="singlestatus" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="0">待审核</option>
                            <option value="1">审核成功，等待退款</option>
                            <option value="2">审核驳回</option>
                            <option value="3">退款成功</option>
                            <option value="4">退款失败</option>
                        </select>
                    </td>
                    <td></td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td>添加时间：</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                               name="addtimeStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                               name="addtimeEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <td></td>
                    <td>

                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br>
    <table id="dg"></table>
    <%--驳回理由窗口--%>
    <div id="opeCon" class="easyui-window" title="驳回理由" style="padding: 10px;">
        <div region="center" border="false" style="padding: 10px;">
            <form method="post" id="opeConForm" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td>驳回理由：</td>
                        <td><textarea id="operateContent" name="operateContent" data-options="required:true" style="width: 200px; height: 150px;"></textarea></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="opSubmitBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="opCancelBtn" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
</body>
</html>
