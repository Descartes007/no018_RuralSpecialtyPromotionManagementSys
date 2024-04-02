<p><h1 align="center">基于SpringBoot的乡村特产推广管理系统</h1></p>

<p> 获取sql文件 QQ: 605739993 QQ群: 377586148 </p>
<p> [个人站点: 从戎源码网](https://armycodes.com/)</p>

## 简介

> 本代码来源于网络,仅供学习参考使用!
>
> 提供1.远程部署/2.修改代码/3.设计文档指导/4.框架代码讲解等服务
>
> 前端地址：http://localhost:9527
>
> 管理员: admin 密码: 123456
>
> 商户：lisi 密码: 123456
>
> 用户：zhangsan 密码: 123456

## 项目介绍

基于SpringBoot的乡村特产推广管理系统：前端 微信小程序、Vue、ElementUI，后端 SpringBoot、Redis、Mybatis，系统角色分为：管理员、商户和用户，管理员在管理后台管理商品和笔记，对笔记进行认证，添加新管理员等；商户对商品进行发布、笔记发布等；用户根据发布的商品进行选购等。主要功能如下：

### 启动方式

- 前端：
> cd vue
>
> npm install
>
> npm run dev

- 小程序端：
> 按钮启动 | 启动微信开发者工具

- 后端：
> 按钮启动 | 右键run PoemApplication

### 管理员：

- 基本操作：登录、完善个人信息、获取个人信息、修改密码
- 用户管理：获取用户列表、筛选用户、重置用户密码、删除用户
- 商品管理：获取商品列表、筛选商品、修改商品库存、删除商品、查看商品详情
- 反馈管理：获取反馈列表、筛选反馈、查看反馈详情、删除反馈、回复
- 审核管理：获取笔记审核列表、筛选笔记审核信息、查看内容详情、审核通过、审核不通过
- 认证管理：获取认证列表、筛选认证信息、查看认证详情、上传认证材料、拒绝认证、认证
- 评分管理：获取评分列表、查看评分详情、删除评分、查看内容详情

### 商户：

- 基本操作：登录、注册、修改密码、切换城市、分享小程序、反馈问题
- 笔记模块：获取笔记列表、搜索笔记、发表笔记、获取我的笔记列表、查看笔记详情、评论笔记
- 商品模块：购买商品、查看商品详情、查看等待发货商品
- 商品模块：获取商品列表、发布商品、查看已售商品列表

### 用户：

- 基本操作：登录、注册、修改密码、切换城市、分享小程序、反馈问题
- 笔记模块：获取笔记列表、搜索笔记、发表笔记、获取我的笔记列表、查看笔记详情、评论笔记
- 商品模块：购买商品、查看商品详情、查看等待发货商品

## 环境

- <b>IntelliJ IDEA 2020.3</b>

- <b>Mysql 5.7.26</b>

- <b>NodeJs 14.17.3</b>

- <b>Maven 3.6.3</b>

- <b>JDK 1.8</b>

## 小bug解决方案：
- 删除文件夹vue中的package-lock.json文件
- 
- 重新 npm install

## 运行截图
![](screenshot/1.png)

![](screenshot/2.png)

![](screenshot/3.png)

![](screenshot/4.png)

![](screenshot/5.png)

![](screenshot/6.png)

![](screenshot/7.png)