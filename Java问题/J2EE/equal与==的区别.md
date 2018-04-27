值类型存储在内存中的堆栈，而引用类型的变量在栈中仅仅是存储引用类型变量的地址，而其本身则存储在堆中。

==与equals的主要区别是： ==常用于比较<font color=red>原生类型</font>，而equals()方法用于检查<font color=red>对象</font>的相等性。另一个不同点是：如果 ==和equals()用于比较对象，当两个引用地址相同， ==返回true。而equals()可以返回true或者false主要取决于重写实现。最常见的一个例子，字符串的比较，不用情况 ==和equals()返回不同的结果。

* ==操作比较的是两个变量的值是否相等，对于引用型变量表示的是两个变量在堆中存储的地址是否相同，即栈中的内容是否相同。
* ==比较的是<font color=red>两个对象的地址</font>，而equal比较的是<font color=red>两个对象的内容</font>



1. String中的equals和==

   ```
   public class Test{
       public static void main(String args[]){
           String s1 = "Monday";
           String s2 = "Monday";
           
           if(s1==s2)
               System.out.println("s1==s2");
           else
               System.out.println("s1!=s2");
       }
   }
   ```

   结果：s1==s2  说明：s1和s2引用同一个String对象，地址一致

2. 将程序稍作修改

   ```
   public class Test{
       public static void main(String args[]){
           String s1 = "Monday";
           String s2 = new String("Monday");
           
           if(s1==s2)
               System.out.println("s1==s2");
           else
               System.out.println("s1!=s2");

           if (s1.equals(s2))
               System.out.println("s1 equals s2");
           else
               System.out.println("s1 not equals s2");
       }
   }
   ```

   结果：s1!=s2    s1 equals s2    说明：s1和s2分别引用了两个对象，对象地址不一致，使用==判断自然不相等。

3. 字符串缓冲池

   Java程序在运行的时候，会创建一个字符串缓冲池。当使用`String s2="Monday"`这样的表达式创建字符串的时候，可能创建一个或者不创建对象，<font color=red>如果“Monday”这个字符串在Java String池中不存在，会在String池中创建一个String对象（“Monday”），然后s1指向这个内存地址，无论以后用这种方式创建多少个值为“Monday”的字符串对象，始终只有一个内存地址被分配，之后的都是String的拷贝，Java中称为“字符串驻留”，所有的字符串常量都会在编译后自动的驻留。</font>

   在第一个程序中，s1先被放到了缓冲池中，所以在s2被创建的时候，程序找到了具有相同值的s1，s2引用s1所引用的对象"Monday"。

   `String s2 = new String("Monday");` 至少创建一个对象，也可能是两个。因为用到new关键字，肯定会在heap中创建一个s2的String对象，他的value是"Monday"。同时如果这个字符串在Java String缓冲池中不存在，会在String中创建这个String对象。

   第二段程序中，使用了new操作符，在heap中创建了一个新的对象，因此使用==判断得到false的结果。

