# Java异常架构图

![1525066246090](1525066246090.png)

1. Throwable

   Throwable是Java语言中所有错误或异常的超类。

   Throwable包含两个子类：Error和Exception。它们通常用于指示发生了异常情况。

   Throwable包含了其线程创建时线程执行堆栈的快照，它提供了printStackTrace（）等接口用于获取堆栈跟踪数据等信息。

2. Exception

   Exception及其子类是Throwable的一种形式，他指出了合理的应用程序想要捕获的条件。

3. RuntimeException

   RuntimeException是那些可能在Java虚拟机正常运行期间抛出的异常的超类。编译器不会检查RuntimeException异常。例如，除数为零时，抛出ArithmeticException异常。RuntimeException是ArithmeticException的超类。当代码发生除数为零的情况时，倘若既没有通过throws声明抛出ArithmeticException异常，也没有通过try…catch…处理该异常，也能通过编译。这就是我们所说的“编译器不会检查RuntimeException异常”。如果代码会产生RuntimeException，则需要通过修改代码进行避免。

4. Error

   和Exception一样，Error也是Throwable的子类。它用于只是合理的应用程序不应该试图捕获的严重问题，大多数这样的错误都是异常条件。和RuntimeException一样，编译器也不回检查Error。Java将可抛出（Throwable）的结构分为三种类型：被检查的异常（Checked Exception），运行时异常（RuntimeException）和错误（Error）。

   1. 运行时异常

      定义：RuntimeException及其子类都被称为运行时异常。特点：Java编译器不会检查他。但我们可以通过throws进行声明抛出，也可以通过try-catch对他进行捕获处理。

   2. 被检查的异常

      定义：Exception类本身，以及Exception的子类中除了RuntimeException之外的其他子类。特点：Java编译器会检查他。此类异常，要么通过throws进行声明抛出，要么通过try-catch进行捕获处理，否则不能通过编译。

   3. 错误

      定义：Error类及其子类。特点：和运行时异常一样，编译器也不会对错误进行检查。当资源不足、约束失败、或是其他程序无法继续运行的条件发生时，就产生错误。程序本身无法修复这些错误。



# OOM

1. OutOfMemoryError异常

   除了程序计数器外，虚拟机内存的其他几个运行时区域都有发生OutOfMemoryError（OOM）异常的可能。

   Java Heap溢出

   一般的异常信息：java.lang.OutOfMemoryError:Java heap spacess

   Java堆用户存储对象实例，我们只要不断的创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，就会在对象数量达到最大堆容量限制后产生内存溢出异常。

   出现这种异常，一般手段是先通过内存映像分析工具（如Eclipse Memory Analyzer）对dump出来的堆转存快照金子那个分析，重点是确认内存中的对象是否是必要的，先分清是因为内存泄漏（Memory Leak）还是内存溢出（Memory Overflow）。

   如果是内存泄漏，可进一步通过工具查看泄露对象到GC Roots的引用链。于是就能找到泄露对象时通过怎样的路径与GC Roots相关联并导致垃圾收集器无法自动回收。

   如果不存在泄露，那就应该检查虚拟机的参数（-Xmx与-Xms）的设置是否适当。

2. 虚拟机栈和本地方法栈溢出

   如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。  

   如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。

   当栈的大小越大，可分配的线程数就越少。

3. 运行时常量池溢出

   异常信息：java.lang.OutOfMemoryError:PermGen space

   如果要向运行时常量池中添加内容，最简单的做法就是使用String.intern()这个Native方法。该方法的作用是：如果池中已经包含一个等于此String的字符串，则返回代表池中这个字符串的String对象；否则，将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。由于常量池分配在方法区内，我们可以通过-XX：PerSize和-XX：MaxPermSize限制方法区的大小，从而间接限制其中常量池的容量。

4. 方法区溢出

   方法区用于存放Class的相关信息，如类名、访问修饰符、常量池、字段描述、方法描述等。

   异常信息：java.lang.OutOfMemoryError:PermGen space

   方法区溢出也是一种常见的内存溢出异常，一个类如果要被垃圾收集器回收，判定条件是很苛刻的。在经常动态生成大量Class的应用中，要特别注意这点。

