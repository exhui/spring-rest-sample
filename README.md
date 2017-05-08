Spring REST Sample
=================

## 功能介绍

以Spring mvc为核心的REST风格的Web服务基础样例。

## 技术栈

- Java SDK 1.7
- Maven 4
- Spring 4, Spring MVC 4
- Spring Security 4
- JPA 2.0, Hibernates 5
- MySQL
- Rest API, Json
- Markdown

## 开发工具推荐

- IDEA 2017: J2EE项目开发
- Postman: 测试开发API接口
- Markdown工具: 文档编写

## 开发环境准备

### 1 Java SDK

建议使用 JDK 7 或 JDK 8，请在官方网站下载，并根据官方指引安装配置。

- [官方下载地址](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html)
- [官方安装说明](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

### 2 Tomcat

建议使用 8.0.x Core，请在官方网站下载。

- [官方下载地址](http://tomcat.apache.org/download-80.cgi)

### 3 Maven

- [官方地址](http://maven.apache.org)
- [官方安装说明](http://maven.apache.org/install.html)

## 编译

mvn成功执行之后，会在根目录的target下生成war包

```bash
$ mvn clean #清除旧的文件
$ mvn package -Dmaven.test.skip=true #重新编译
```

## 直接部署到Tomcat

### 部署Tomcat

执行以下命令将wat包部署到tomcat下

```bash
cp target/spring-rest-sample.war /path/to/tomcat/webapps/sample.war
```

#### 启动tomcat

```bash
$ cd /path/to/tomcat
$ sudo ./bin/shutdown.sh # 关闭
$ sudo ./bin/startup.sh # 启动
```

## 使用IDEA部署

** 1. 创建Tomcat服务器 **

依次点击菜单 Run->Edit Configurations -> `+`号 -> Tomcat Server -> Local

** 2. 配置Tomcat服务器 **

`Server` 项的配置如下：

- `Name`，设服务器名称
- `Application server`, 点击Configure，设置Tomcat服务器
- `Open broswer`, 打开的URL地址默认是http://localhost:8080/。一般情况下，在Deployment设置工程发布目录，就会自动改变。
- `VM Option`, 如果出现乱码问题，设置参数-Dfile.encoding=UTF8

`Deployment`项的配置如下:

- `+` 号 -> Artifact... -> 选择发布的工程

## Mac启用80端口说明

Mac默认禁止1024以下的端口，需要通过PF防火墙配置，添加80端口。

创建防火墙配置文件

```bash
$ sudo vim /etc/pf.anchors/devel
```

添加80端口对8080端口的访问许可，内容如下：

```
rdr pass on lo0 inet proto tcp from any to any port 80 -> 127.0.0.1 port 8080
```

修改配置文件

```bash
$ sudo vim /etc/pf.conf
```

修改内容如下

```
scrub-anchor "com.apple/*"
nat-anchor "com.apple/*"
rdr-anchor "com.apple/*"
rdr-anchor "devel" #添加配置
dummynet-anchor "com.apple/*"
anchor "com.apple/*"
load anchor "com.apple" from "/etc/pf.anchors/com.apple"
load anchor "devel" from "/etc/pf.anchors/devel" #添加配置
```

立即生效。**生效后，无需配置Ngix。**

```bash
$ sudo pfctl -ef /etc/pf.conf
```

如果希望长期有效，可以按以下方式修改配置文件。**由于苹果安全限制，最新版本Sierra不支持以下修改**。

```bash
$ sudo vim /System/Library/LaunchDaemons/com.apple.pfctl.plist
```

修改内容如下

```xml
<array>
	<string>pfctl</string>
	<!-- 下面就是修改的启动参数 -->
	<string>-ef</string>
	<string>/etc/pf.conf</string>
</array>
```

## 工程配置文件

所有配置文件都依据Maven一般规则放在resources目录下

### app.properties

app配置文件，目前没有任何参数。

### database.properties

数据库配置文件。主要配置数据库连接，hibernate。

### log4j.properties

log4j的日志配置文件。






