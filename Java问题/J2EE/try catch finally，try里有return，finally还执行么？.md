try或者catch中有return语句，finally{}块中的代码也会执行。其中代码只有在try{}块中包含遇到System.exit(0)；之类的导致Java虚拟机直接退出的语句才会不执行。

当程序执行try{}遇到return时，程序会先执行return语句，但并不会立即返回——也就是把return语句要做的一切事情都准备好，也就是在将要返回、但并未返回的时候，程序把执行流程转去执行finally块，当finally块执行完成后就直接返回刚才return语句已经准备好的结果。

代码示例：

```
public class Test{
    public static void main(String[] args){
        System.out.println(new Test().test());
    }

    static int test(){
        int x = 1;
        try{
            return x;
        }finally{
            System.out.println("finally块执行:"+ (++x));
        }
    }
}
```

此时的输出结果为：finally块执行：2   1

可以看到上面程序中finally块已经执行了，而且程序执行finally块时已经把x变量增加到2了。但test（）方法返回的依然是1，这就是由return语句执行流程决定的，<font color=red>**Java会把return语句先执行完，把所有需要处理的东西都县处理完成，需要返回的值也都准备好之后，但是还未返回之前，程序流程会转去执行finally块，**</font>但此时finally块中的对x变量的修改已经不会影响return要返回的值了。

<font color=red>**如果finally中包含return语句，则try或者catch语句块中的return将无法达到。**</font>

```
public class Test{
    public static void main(String[] args){
        System.out.println(new Test().test());
    }

    static int test(){
        int x = 1;
        try{
            return x++;
        }finally{
            System.out.println("finally块执行:"+ (++x));
            return x;
        }
    }
}
```

此时输出结果为：finally块执行：3     3

正如之前所说，程序在执行return x++；时，程序会把return语句执行完成，只是等待返回，此时x的值已经是2了，但程序此处准备的返回值依然是1.接下来程序流程转去执行finally块，此时程序会再次对x自加，于是x变成了3，而且由于finally块中也有return x；语句，因此程序将会直接由这条语句返回，因此上面test（）方法将会返回3。