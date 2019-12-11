<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客管理页面</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        /**
         * 返回博客类型名称
         * @param val
         * @param row
         * @returns {*}
         */
        function formatBlogType(val, row) {
            return val.typeName;
        }


        /**
         *点击标题弹出用户预览页面
         */
        function formatTitle(val ,row) {
            return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.id+".html'>"+val+"</a>";
        }


        /**
         * 根据标题查询
         */
        function searchBlog() {
            $("#dg").datagrid('load', {"title": $("#s_title").val()});
        }

        /**
         * 修改博客
         */
        function openBlogModiyfTab() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条需要修改的博客！");
                return;
            }
            var row = selectedRows[0];
            window.parent.openTab("修改博客", "modifyBlog.jsp?id=" + row.id, "icon-writeblog");
        }

        /**
         * 删除博客
         */
        function deleteBlog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择需要删除的博客！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].id);
            }
            //数据中join添加字符分隔转换成字符串 便于数据传到后台，在分割字符串为数据
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r){
                    $.post("${pageContext.request.contextPath}/admin/blog/delete.do",{ids:ids},function (result) {
                        if (result.success){
                            $.messager.alert("系统提示","数据已经删除成功！");
                            $("#dg").datagrid("reload");//刷新
                        }else{
                            $.messager.alert("系统提示","数据已经删除失败！");
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body style="margin: 10px">
<table id="dg" title="博客列表" class="easyui-datagrid" fitcolumns="true"
       pagination="true" rownumbers="true" fit="true" toolbar="#tb"
       url="${pageContext.request.contextPath}/admin/blog/list.do">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="title" value="标题" width="200" align="center" formatter="formatTitle">标题</th>
        <th field="releaseDate" width="50" align="center">发布时间</th>
        <th field="blogType" width="50" align="center" formatter="formatBlogType">博客类型</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openBlogModiyfTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    &nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if (event.keyCode=13) searchBlog()"/>
    <a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>

</body>
</html>