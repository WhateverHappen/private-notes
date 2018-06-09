配置springmvc时，报错，实际mapping已经写了，错误截图如下： 

![img](20170218141126251.png) 

后来发现是工程的web.xml位置配置错误，因为我之前换过根目录位置。  

修改方法：  

打开Project Structure界面，Modules>Web>Deployment descriptor，修改正确的位置即可，如下图： 

![è¿éåå¾çæè¿°](20170218141528576.png) 