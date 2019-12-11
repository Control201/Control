<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>写博客页面</title>
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
    <script type="text/javascript" charset="GBK"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="GBK"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
    <%--手动加载语言，避免有时加载语言导致编译失败--%>
    <script type="text/javascript" charset="GBK"
            src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">
        /**
         * 发布博客
         */
        function submitData() {
            var title = $("#title").val();
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            var content = UE.getEditor('editor').getContent();
            var keyWord = $("#keyWord").val();
            if (title == null || title == '') {
                $.messager.alert("系统提示", "请输入标题!");
            } else if (blogTypeId == null || blogTypeId == '') {
                $.messager.alert("系统提示", "请输入博客类别！")
            } else if (content == null || content == '') {
                $.messager.alert("系统提示", "请输入内容")
            } else {
                //提交数据
                $.post("${pageContext.request.contextPath}/admin/blog/save.do",
                    {
                        'title': title,
                        'blogType.id': blogTypeId,
                        'content': content,
                        'contentNoTag':UE.getEditor('editor').getContentTxt(),
                        'summary': UE.getEditor('editor').getContentTxt().substr(0, 155),
                        'keyWord': keyWord
                    },
                    function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "博客发布成功！");
                        } else {
                            $.messager.alert("系统提示", "博客发布失败！");
                        }
                    }, "json");
            }
        }
    </script>
</head>
<body style="margin: 10px">

<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
    <table cellspacing="20px">
        <tr>
            <td width="80px">博客标题：</td>
            <td><input type="text" id="title" name="title" style="width: 400px;"/></td>
        </tr>
        <tr>
            <td>所属类别：</td>
            <td>
                <select class="easyui-combobox" id="blogTypeId" name="blogType.id" style="width: 154px;"
                        editable="false" panelHeight="auto">
                    <option value="">请选择博客类别</option>
                    <c:forEach var="blogType" items="${blogTypeCountList}">
                        <option value="${blogType.id}">${blogType.typeName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>博客内容：</td>
            <td>
                <script id="editor" type="text/plain" style="width: 100% ;height: 500px;"></script>
            </td>
        </tr>
        <tr>
            <td>关键字：</td>
            <td><input type="text" id="keyWord" name="keyWord" style="width: 400px;"/>
                &nbsp;(多个关键字中间使用空格隔开)
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <a href="javascript:submitData()" class="easyui-linkbutton"
                   data-options="iconCls:'icon-submit'">发送博客</a>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor("editor");
</script>
</body>
</html>