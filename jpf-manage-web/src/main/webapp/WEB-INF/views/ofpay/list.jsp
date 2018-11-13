<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){
            var toolbar = [

            ];

            $("#dg").datagrid({
                title : '账务明细列表',
                toolbar:toolbar,
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                url : 'list',
                columns : [[
                    //{field:'id', title:'编号',width:'6%'},
                    {field:'detailId', title:'流水号',width:'14%'},
                    {field:'inOutType', title:'收支类型',width:'6%'},
                    {field:'fundsType', title:'业务类型',width:'6%'},
                    {field:'inComeCost', title:'收入金额',width:'6%'},
                    {field:'expenditureCost', title:'支出金额',width:'6%'},
                    {field:'leftBalance', title:'账户余额',width:'14%'},
                    {field:'billId', title:'业务单号',width:'14%'},
                    {field:'occurTime', title:'修改时间',width:'14%'}
                    //{field:'occurTime', title:'修改时间',width:'14%',formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#edit').window({
                width:'400px',
                height:'200px',
                closed:true,
                modal:true
            });

            $('#searchBtn').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#dg').datagrid('reload', postData);
                }
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#CompanyChargeft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="50%">
                        <tr>
                            <td>创建时间：</td>
                            <td>
                                <input type="text" class="Wdate" style="width:158px;" id="startAddTime_s"
                                       name="startAddTime" value="${starttime}"
                                       onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endAddTime_s\');}',startDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})"/>
                                -
                                <input type="text" class="Wdate" style="width:158px;" id="endAddTime_s"
                                       name="endAddTime"  value="${endtime}"
                                       onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startAddTime_s\');}',startDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})"/>
                            </td>
                            <td>接口商家：</td>
                            <td >
                                <select id="paymenttype" name="paymenttype" class="easyui-combobox" style="width: 70px;">
                                    <option value="">全部</option>
                                    <option value="0">收入</option>
                                    <option value="1">支出</option>
                                </select>
                            </td>
                        </tr>
                        <input type="hidden" name="page" value="1" />
                    </table>
                </form>
            </div>
        </div>
        <div id="CompanyChargeft" style="padding:5px;">
            <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
            <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
        </div>
    </div>
    <table id="dg"></table>
    <div id="edit"></div>
</body>
</html>