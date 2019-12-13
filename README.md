# Control
Java--基于SSM的个人博客

# 1.前言
&nbsp;&nbsp;本项目是SSM的博客系统，目前只支持发表个人博客，管理个人博客相关内容，供他人以游客身份进行浏览，搜索，评论等功能。
# 2.所用技术
 1. 使用Spring5+SpringMVC+Mybatis3架构
 2. 采用Mysql数据库
 3. 使用Maven管理项目
 4. 使用Shiro作为项目安全框架
 5. 使用Lucene作为全文检索，支持restful风格
 6. 前台网页使用主流的Bootstrap3 UI框架
 7. 后台管理使用主流易用的EasyUI轻量级框架
 8. 数据库连接池使用的是阿里巴巴的Druid
 9. 在线编辑器使用了百度的UEditor，支持单图，多图上传，支持截图上传，支持代码高亮特性
# 3.项目效果图展示
## 1.后台部分
- 登录后台界面：使用Shiro进行安全管理，对前台输入的密码进行加密运算，然后与数据库（数据库存入密码已加密）中的进行比较。成功后才能登入后台系统。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191212111158786.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIwMDk4NA==,size_16,color_FFFFFF,t_70)
 -  博客系统后台：其分为博客及类别管理，评论管理等几大模块。这里只展示了博客管理中“写博客功能”。先写入博客标题，然后选择博客类型，将博客内容填入百度的富文本编辑器中，最后加上关键字（方便前台Lucene全文检索），点击发布博客按钮即可发布博客。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191212111643603.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIwMDk4NA==,size_16,color_FFFFFF,t_70)
## 2.前台展示部分
- 首页展示：可观看博客内容的相关信息(分页展示)，和博主的相关信息，右上角引入天气插件模块，有其他想法可以另做更换。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019121211261620.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIwMDk4NA==,size_16,color_FFFFFF,t_70)
- 查看博客界面：显示博客具体内容，关键字(Lucene全文检索)，链接分享，游客发表评论，查看所有评论(内容太多，没截屏出来哦~)，
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191212113746432.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIwMDk4NA==,size_16,color_FFFFFF,t_70)

- 搜索博客：采用Lucene全文检索，搜索关键字采用高亮显示。这里不仅可以关键字查询，还可以输入博客相关内容查询哦（其他搜索条件可另外填加）。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191212113005289.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIwMDk4NA==,size_16,color_FFFFFF,t_70)


相关本文链接：https://blog.csdn.net/weixin_44200984/article/details/103500601


