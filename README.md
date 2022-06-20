## 项目仅供内部开发者使用

后端项目基于spring cloud alibaba微服务框架

### 功能介绍

### 目录结构描述
#### 组织机构设计图
![组织机构](doc/img/组织管理.png)

## 启动说明

### 开发规范

```
** 代码请遵循驼峰式命名法

** 公共方法请封装,并且加注释注解

** 项目内所有方法均标注好注释,方便你我他

** 尽量不写sql，都用mybatis-plus，难以实现的复杂sql，不要应用oracle特有的函数，需要mysql也能执行，sql编写必须实现预编译，防止sql注入

** 先设计数据库表和字段，每个字段都需要详细的解释

** 确定业务逻辑后，写与前端商量接口参数和返回结构，把controller层方法先实现，定义好参数和模拟写好数据返回结构，能够快速生成swagger文档以便前后端开发同时进行

```

### GIT管理规范（简单版，适用于访问git缓慢的网络环境，不能频繁的提交，每次提交是完整功能模块的）

```
假设开发分支为origin/1.0-dev，则需要在本地以它为远程分支创建一个本地分支1.0-dev

任何自己的修改在本地分支进行，每个代码的提交需要描述清楚内容，
尽量保证每次提交是某个完整的功能且能项目编译通过的

（最重要的）在push自己分支代码到远程分支之前，保证是能够编译运行的代码，
功能上是有更新的，需要先rebase下origin/1.0-dev远程分支的代码，
保证自己的分支是在最新代码的前提上修改的

如果rebase后出现代码冲突就解决冲突，和冲突代码提交人员进行沟通，保留正确的代码

rebase成功后，push本地提交到远程分支，编译工具Terminal输入git push比较快

这时就已经完成和主开发分支的代码合并，并且不会存在冲突，可以继续本地分支进行开发

```