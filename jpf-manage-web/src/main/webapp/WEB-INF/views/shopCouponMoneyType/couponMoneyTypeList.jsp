<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>金额类型列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#add').window({
                title:'新增面值',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true,
                maximized:false,//弹出窗口最大化

            });
            $('#infoDiv').window({
                title:'详情',
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true,
                maximized:true,//弹出窗口最大化

            });
            $('#infoUpdate').window({
                title:'详情',
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true,
                maximized:false,//弹出窗口最大化
            });
            var toolbar = [
                {
                    text:'新增',
                    iconCls:'icon-add',
                    handler:function(){
                        $('#add').window("open").window('refresh', '/shopCouponMoneyType/goAdd').window('setTitle','新增面值');
                    }
                },
                {
                    text:'详情',
                    iconCls:'icon-view-detail',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'info?id='+rows[0].id+'&phone='+rows[0].phone+'&dou='+rows[0].dou).window('setTitle','欣豆详情');
                    }
                },
                {
                    text : '冻结',
                    iconCls:'icon-no',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('冻结','确认冻结操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyCustomer?id='+rows[0].id+'&type=2',
                                    dataType:"json",
                                    contentType:"application/json",
                                    success : function(msg){
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error : function () {
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                })
                            }
                        })
                    }
                },
                {
                    text : '恢复',
                    iconCls:'icon-ok',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('恢复','确认恢复正常操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyCustomer?id='+rows[0].id+'&type=1',
                                    dataType:"json",
                                    contentType:"application/json",
                                    success : function(msg){
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error : function () {
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                })
                            }
                        })
                    }
                },
                {
                    text : '类型编辑',
                    iconCls:'icon-edit',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $('#infoUpdate').window("open").window('refresh', 'editCustomer/page?id='+rows[0].id+'&phone='+rows[0].phone+'&dou='+rows[0].dou).window('setTitle','编辑用户详情');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'欣券面值信息',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'/shopCouponMoneyType/list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'money',title:'面值(元)',width:'8%'},
                    {field:'status',title:'状态',width:'5%'},
                    {field:'addtime',title:'新增时间',width:'12%',formatter: formatDateStr},
                    {field:'updatetime',title:'修改时间',width:'12%',formatter: formatDateStr}
                ]]
            });

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
        });

        $(window).resize(function() {
            var width = $(window).width() - 20;
            //console.info(width);
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });

        $(window).load(function() {
            var width = $(window).width() - 20;
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });
        function goActive(id,phone,dou) {
            //var rows = $('#dg').datagrid('getSelections');
            $('#infoDiv').window("open").window('refresh', 'info?id='+id+'&phone='+phone+'&dou='+dou).window('setTitle','欣豆详情');
        }

    </script>
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>面值(元):</td>
                        <td>
                            <select name="money" id="money"   class="easyui-combobox"  style="width:120px;">
                                <option value="">--请选择--</option>
                                <option value="1">10</option>
                                <option value="2">20</option>
                            </select>
                        </td>
                        <td>状态:</td>
                        <td>
                            <select name="status" id="status"  class="easyui-combobox" style="width:120px;">
                                <option value="">--请选择--</option>
                                <option value="1">系统定义</option>
                                <option value="2">自定义</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dg"></table>
</div>
<div id="add"></div>
<div id="infoUpdate"></div>
<div id="edit"></div>
</body>
</html>