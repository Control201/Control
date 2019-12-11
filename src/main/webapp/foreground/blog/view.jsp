<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">

    /**
     * 显示所以评论
     */
    function showOtherComment() {
        $(".otherComment").show();
    }

    /**
     * 重新加载验证码
     */
    function loadimage() {
        document.getElementById("randImage").src = "${pageContext.request.contextPath}/image.jsp?" + Math.random();
    }

    /**
     * 关键字查询
     * @param keyWord
     */
    function query1(keyWord) {
        $("#q1").val(keyWord);
        $("#queryForm").submit();
    }

    /**
     * 提交评论
     */
    function submitData() {
        var content = $("#content").val();
        var imageCode = $("#imageCode").val();
        if (content == null || content == '') {
            alert("系统提示:请输入评论内容");
        } else if (imageCode == null || imageCode == '') {
            alert("系统提示:请填写验证码");
        } else {
            $.post("${pageContext.request.contextPath}/comment/save.do",
                {'content': content, 'imageCode': imageCode, 'blog.id': '${blog.id}'},
                function (result) {
                    if (result.success) {
                        window.location.reload();
                        alert("评论已提交成功,审核通过后显示！");
                    } else {
                        alert(result.errorInfo);
                    }
                }, "json");
        }
    }



</script>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/blog_show_icon.png"/>
        博客信息
    </div>
    <div>
        <div class="blog_title"><h3><strong>${blog.title}</strong></h3>
            <div style="padding-left: 400px;padding-bottom: 20px;padding-top: 10px">
                <div class="bshare-custom">
                    <a title="分享到QQ空间" class="bshare-qzone">QQ空间</a>
                    <a title="分享到新浪微博" class="bshare-sinaminiblog">新浪微博</a>
                    <a title="分享到人人网" class="bshare-renren">人人网</a>
                    <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis">更多平台</a>
                    <script type="text/javascript" charset="UTF-8"
                            src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script>
                    <script type="text/javascript" charset="UTF-8"
                            src="http://static.bshare.cn/b/bshareC0.js"></script>
                </div>
            </div>
            <div class="blog_info">
                发布时间：【<fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>】
                &nbsp;&nbsp;博客类别：${blog.blogType.typeName}
                &nbsp;&nbsp;点击量：${blog.clickHit}
                &nbsp;&nbsp;评论数：${blog.replyHit}
            </div>
            <div class="blog_content">
                ${blog.content}
            </div>
        </div>

        <div class="blog_keyWord">
            <font><strong>关键字：</strong></font>
            <c:choose>
                <c:when test="${keyWords==null}">
                    &nbsp;&nbsp;无
                </c:when>
                <c:otherwise>
                    <form id="queryForm" action="${pageContext.request.contextPath}/blog/q.html" method="post">
                        <input type="hidden" id="q1" name="q"/>
                        <c:forEach var="keyWord" items="${keyWords}">
                            &nbsp;&nbsp;<a  href="javascript:query1('${keyWord}')" >${keyWord}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="blog_lastAndNextPage">
            ${pageCode}
        </div>
    </div>
</div>


<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/comment_icon.png">
        发表评论
    </div>
    <div class="publish_comment">
        <div>
            <textarea rows="3" style="width:100%" id="content" name="content" placeholder="来说两句吧。。。"></textarea>
        </div>
        <div class="verCode">
            验证码：<input type="text" name="imageCode" id="imageCode" size="10"
                       onkeydown="if (event.keyCode==13) submitData()"/>
            &nbsp;<img src="${pageContext.request.contextPath}/image.jsp" name="randImage" id="randImage"
                       title="验证码图片" onclick="javascript:loadimage()" width="60" height="20" border="1"
                       align="absmiddle">
        </div>
        <br/>
        <div class="publishButton">
            <button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
        </div>
    </div>
</div>


<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/comment_icon.png">
        评论信息
        <c:if test="${commentList.size()>10}">
            <a href="javascript:showOtherComment()" style="float:right;padding-right:40px;">显示所有评论</a>
        </c:if>
    </div>
    <div class="commentDatas">
        <c:choose>
            <c:when test="${commentList.size()==0}">
                暂无评论
            </c:when>
            <c:otherwise>
                <c:forEach var="comment" items="${commentList}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index<10}">
                            <div class="comment">
                                <span>
                                    ${status.index+1}楼&nbsp;&nbsp;&nbsp;&nbsp;
                                    ${comment.userIp}&nbsp;&nbsp;&nbsp;&nbsp;
                                    ${comment.content}&nbsp;&nbsp;&nbsp;&nbsp;
                                    【<fmt:formatDate value="${comment.commentDate}" type="date"
                                                     pattern="yyyy-MM-dd HH:mm"/>】
                                </span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="otherComment">
                                <div class="comment">
                             <span>
                           ${status.index+1}楼&nbsp;&nbsp;&nbsp;&nbsp;
                           ${comment.userIp}&nbsp;&nbsp;&nbsp;&nbsp;
                           ${comment.content}&nbsp;&nbsp;&nbsp;&nbsp;
                               【<fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>】
                             </span>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>






