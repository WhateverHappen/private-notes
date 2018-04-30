# 继承Thread类

## 继承Thread类创建多线程——单线程

下面的代码是一个死循环，但是不会执行main方法里面的循环语句，而是执行run（）里面的语句，这是因为该程序是一个单线程程序，当调用MyThread类的run（）方法时，遇到死循环，循环一直进行。因此，MyThread类的打印语句将永远执行，而main（）方法中的打印语句无法得到执行。

```
package test;  

public class example {
	public static void main(String[] args){
		MyThread myThread=new MyThread();
		myThread.run();
		while(true){ 
			System.out.println("Main方法在运行");
		}
	} 
}

class MyThread{
	public void run(){
		while(true){
			System.out.println("MyThread类的run()方法在运行"); 
		} 
	}
} 
```

## 通过继承Thread类实现多线程

如果希望1中的两个循环打印语句都能够执行的话，那么就需要实现多线程。为此jdk提供了一个多线程类Thread，通过继承Thread类，并重写Thread类中的run（）方法便可以实现多线程（目的：将自定义代码存储在run方法，让线程运行。<font color=red>**run仅仅是一个对象调用的方法，并不会启动一个新的线程**</font>）。在Thread类中，提供了一个start（）方法用于启动新线程，线程启动后，系统就会自动调用run方法。

```
package test;  
public class example { 
	public static void main(String[] args){ 
		MyThread myThread=new MyThread();  
		myThread.start(); 
		while(true)  {  
			System.out.println("Main方法在运行"); 
		}  
	}  
}  

class MyThread extends Thread{ 
	public void run(){ 
		while(true){  
        	System.out.println("MyThread类的run()方法在运行"); 
		}
	}
}  
```

```
Demo d = new Demo();//创建好一个线程。
//d.start();//开启线程并执行该线程的run方法。
d.run();//仅仅是对象调用方法。而线程创建了，并没有运行
```

# 实现Runnable接口创建多线程

由于Java只支持单继承，因此一个类一旦继承了某个父类就无法再继承Thread类，为了克服这种弊端，Thread类提供了另外一种构造方法Thread（Runnable target），其中Runnable是一个接口，她只有一个run方法。方通过Thread（Runnable target）构造方法创建一个线程对象时，只需该方法传递一个实现了Runnable接口的实例对象，这样创建的线程将调用实现了Runnable接口中的run方法作为运行代码，而不需要调用Thread类中的run方法。

```
package test;  

public class example { 
	public static void main(String[] args){
		MyThread myThread=new MyThread(); 
         Thread thread=new Thread(myThread);  
		thread.start();  
		while(true){  
			System.out.println("Main方法在运行"); 
		}  
	}  
}  

class MyThread implements Runnable{
	public void run(){  
		while(true){ 
			System.out.println("MyThread类的run()方法在运行"); 
		}  
	}  
} 
```

实现Runnable接口相对于继承Thread类来说，有如下的显著优势：

1. 适合多个相同代码的线程去处理同一个资源的情况
2. 可以避免由于Java的单继承特性带来的局限
3. 增强了程序的健壮性，代码能够被多个线程共享，代码与数据是独立的



继承和实现接口的区别：

1. 当使用继承的时候，主要是为了不必重新开发，并且在不比了解实现细节的情况下，拥有了父类我们所需要的特征。他也有很大的一个缺点，就是如果我们的类已经从一个类继承（如小程序必须继承自Applet类），则无法再继承Thread类
2. Java只能单继承，因此如果是采用继承Thread的方法，那么在以后进行代码重构的时候可能会遇到问题，因为你无法继承别的类了，在其他的方面，两者之间并没有什么太大的区别
3. implement Runnable实现向接口，扩展性等方面比extends Thread好
4. 使用Runnable接口来实现多线程使得我们能够在一个类中包容所有的代码，有利于封装，他的缺点在于，我们只能使用一套代码，若想创建多个线程并使各个线程执行不同的代码，则仍必须额外创建类，如果这样的话，在大多数情况下也许还不如直接使用多个类分别继承Thread来的紧凑