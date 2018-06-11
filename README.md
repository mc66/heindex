# 开发环境搭建

## jdk8
本项目使用jdk8开发，请安装jdk8最新版的。

## maven配置
本项目使用maven管理依赖。
需要添加`lib`路径下的jar包到本地仓库：

```bash
mvn install:install-file -Dfile=lib/chubot-utils-1.0.jar -DgroupId=cmri.cashcow -DartifactId=chubot-utils -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=lib/spring-zcommon-1.0.jar -DgroupId=cmri.um -DartifactId=spring-zcommon -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
```

配置maven使用阿里仓库。

```xml
<mirrors>
    <mirror>
      <id>ali-maven</id>
      <name>ali-yun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
</mirrors>
```
## 数据库初始化
创建数据库、用户、密码

```sql
CREATE DATABASE IF NOT EXISTS heindex;

grant all on heindex.* to 'heuser'@'127.0.0.1' identified by 'CmrI123';
grant all on heindex.* to 'heuser'@'localhost' identified by 'CmrI123';
grant all on heindex.* to 'heuser'@'%' identified by 'CmrI123';
```