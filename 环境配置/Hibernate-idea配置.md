在IDEA中，通过右侧的Database完成与数据库的连接

![1524582408650](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582408650.png)

然后在Persistence中完成数据库与model层中各实例的映射

![1524582636805](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582636805.png)

![1524582645448](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582645448.png)

而后，在hibernate.cfg.xml中完成对数据库的配置

![1524582690391](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582690391.png)

其中，mydemo为所==连接的数据库名称==，若不添加数据库名称，则会报"database not select"错误

![1524582794783](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582794783.png)

需要设立数据库的连接，如果数据库有密码，则需要进行设置。

![1524582827569](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582827569.png)

此设置用于设定在执行时是否需要在控制台显示sql语句

![1524582858489](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582858489.png)

在进行数据处理时，首先通过Configuration完成对cfg配置的读取，然后建立session工厂，并开始事务

![1524582914337](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\环境配置\1524582914337.png)

在完成对数据的操作后，将事务提交并将session以及sessionFactory关闭