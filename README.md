#代码生成
**参考实现[keta-custom](mailto:ketayao@gmail.com)**

基于 *spring springmvc mybatis* 平台的代码生成工具

##功能及约束
**可完成以下功能**

* 读取数据库表结构根据表结构生成相应的实体类
* 生成数据访问层(dao)操作代码以及对应的mybatis配置文件
* 生成service层所需要的基本方法
* 生成界面控制层(controller)所需要的基本方法
* 生成数据库信息列表展示、信息删除、信息批量删除、数据添加、数据修改web界面

**表结构约束**

* 有且仅有一个主键

##使用方法
1. 修改template_settings.properties配置文件内相应的配置项

```
tpl.table.name=[需要生成的表名称，必须配置]
tpl.package.name=[生成的实体类等所在的包，必须配置]
tpl.class.name=[对应的实体类的名称，可选配置，不配置根据表明自动生成]
tpl.function.name=[添加、删除时的信息提示，必须配置]
tpl.request.mapping=[URL访问路径，可选配置不配置将使用`/`]
```
2. 运行代码生成工具

```
执行runner.Gen主方法即可，将生成的src下的文件拷贝到源代码目录，将views下文件拷贝到模板文件根目录即可
如果jetbrick-template.properties配置文件内jetx.import.classes配置项不包含生成的实体类所在包路径，需添加
```
