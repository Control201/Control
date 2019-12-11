<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改博客页面</title>
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
         * 提交博主信息
         */
        function submitData() {
            var nickName = $("#nickName").val();
            var sign = $("#sign").val();
            var proFile = UE.getEditor("editor").getContent();
            if (nickName == null || nickName == "") {
                $.messager.alert("系统提示", "请输入昵称");
            } else if (sign == null || sign == "") {
                $.messager.alert("系统提示", "请输入个性签名");
            } else if (proFile == null || proFile == "") {
                $.messager.alert("系统提示", "请输入简介");
            } else {
                $("#profile").val(proFile);
                $("#form1").submit();
            }
        }

    </script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="个人信息" style="padding: 10px">
    <form id="form1" action="${pageContext.request.contextPath}/admin/blogger/save.do" method="post"
          enctype="multipart/form-data">
        <input type="hidden" id="id" name="id" value="${currentUser.id}"/>
        <input type="hidden" id="profile" name="profile" value="${currentUser.profile}"/>

        <table cellspacing="20px">
            <tr>
                <td width="80px">用户名：</td>
                <td><input type="text" id="userName" name="userName" style="width: 200px;"
                           value="${currentUser.userName}"/></td>
            </tr>
            <tr>
                <td>昵称：</td>
                <td><input type="text" id="nickName" name="nickName" style="width: 200px;"
                           value="${currentUser.nickName}"/></td>
            </tr>
            <tr>
                <td>个性签名：</td>
                <td><input type="text" id="sign" name="sign" style="width: 200px;" value="${currentUser.sign}"/></td>
            </tr>
            <tr>
                <td>个人头像：</td>
                <td><input type="file" id="imageFile" name="imageFile" style="width: 400px;"/></td>
            </tr>
            <tr>
                <td>个人简介：</td>
                <td>
                    <script type="text/plain" id="editor" style="width: 100%;height: 500px;"></script>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="javascript:submitData()" class="easyui-linkbutton"
                       data-options="iconCls:'icon-submit'">保存</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor("editor");
    ue.addListener("ready", function () {
        UE.getEditor("editor").setContent($("#profile").val());
        //通过ajax获取数据
        UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/find.do",{
            method:"post",
            async:false,
            data:{},
            onsuccess:function (result) {
                result = eval("("+result.responseText+")");
                UE.getEditor("editor").setContent(result.profile);
            }
        });
    });
</script>
</body>
</html>