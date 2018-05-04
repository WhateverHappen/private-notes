#字段介绍：

* 序号：Seq序号，占32位，用来标识从TCP源端向目的端发送的字节流，发起方发送数据时对此进行标记。
* 确认序号：ack序号，占32位，只有ACK标志位为1时，确认序号字段才有效，ack=Seq+1
* 标志位，共六个，即URG、ACK、PSH、RST、SYN、FIN，具体含义如下：
  * URG：紧急指针有效
  * **ACK：确认序号有效**
  * PSH：接收方应该尽快将这个报文交给应用层
  * RST：重置连接
  * **SYN：发起一个新连接**
  * **FIN：释放一个连接**

#三次握手

所谓三次握手(Three-Way Handshake)即建立TCP连接，就是指建立一个TCP连接时，需要客户端和服务端总共发送3个包以确认连接的建立。在socket编程中，这一过程由客户端执行connect来触发，整个流程如下图所示：

![img](D:\Idea\IntelliJ IDEA 2017.1.1\workspace\private-notes\网络基础\20170309185827079.png)

1. 第一次握手：Client将标志位SYN置为1，随机产生一个值seq=J，并将该数据包发送给Server，CLient进入SYN_SENT状态，等待Server确认。
2. 第二次握手：Server收到数据包后，由标志位SYN=1知道Client请求建立连接，Server将标志位SYN和ACK都置为1，ack=J+1，随机产生一个值seq=K，并将该数据包发送给Client以确认连接请求，Server进入SYN_RCVD状态。
3. 第三次握手：Client收到确认后，检查ack是否为J+1，ACK是否为1，如果正确，则将标志位ACK置为1，ack=K+1，并将该数据包发送给Server，Server检查ack是否为K+1，ACK是否为1，如果正确，则连接建立成功，Client和Server进入ESTABLISHED状态，完成三次握手，随后Client与Server之间可以开始传输数据了

# SYN攻击

在三次握手过程中，Server发送SYN-ACK后，收到Client的ACK之前的TCP连接状态称为半连接(half-open connect)，此时Server处于SYN_RCVD状态，当收到ACK后，Server转入ESTABLISHED状态。

SYN攻击就是Client在短时间内伪造大量不存在的IP地址，并向Server不断地发送SYN包，Server回复确认包，并等待Client的确认，由于源地址是不存在的，因此，Server需要不断重发直至超时，这些伪造的SYN包将占用未连接队列，导致正常的SYN请求因为队列满而被丢弃，从而引起网络堵塞甚至系统瘫痪。SYN攻击是一种典型的DDOS攻击，检测SYN攻击的方式非常简单，即当Server上有大量半连接状态且源IP地址是随机的，则可以断定遭到SYN攻击了。

# 问题

## 问题1：为什么要三次握手？

三次握手的目的是建立可靠的通信信道，说道通讯，简单来说就是数据的发送与接收，二三次握手最主要的目的就是双方确认自己与对方的发送与接收机能正常。

第一次握手：Client什么都不能确认，Server确认了对方发送正常

第二次握手：Client确认了：**自己发送、接收正常，对方发送、接收正常；**Server确认了：**自己接受正常，对方发送正常**

第三次握手：Client确认了：**自己发送、接收正常，对方发送、接收正常；**Server确认了：**自己发送、接收正常，对方发送、接收正常**

## 问题2：为什么要发送特定的数据包，随便发不行吗？

三次握手的另外一个目的就是确认双方都支持TCP，告知对方用TCP传输。

第一次握手：Server猜测Client可能要建立TCP请求，但不确定，因为也可能是Client乱发了一个数据包给自己

第二次握手：通过ack=J+1，Client知道Server是支持TCP的，且理解了自己要建立TCP连接的意图

第三次握手：通过ack=K+1，Server知道Client是支持TCP的，且确实是要建立TCP连接

