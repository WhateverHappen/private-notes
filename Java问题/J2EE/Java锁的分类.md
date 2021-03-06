Java中锁分类大致如下：

+ 公平锁/非公平锁
+ 可重入锁
+ 独享锁/共享锁
+ 互斥锁/读写锁
+ 乐观锁/悲观锁
+ 分段锁
+ 偏向锁/轻量级锁/重量级锁
+ 自旋锁

# 公平锁/非公平锁

公平锁是指多个线程按照申请锁的顺序来获取锁。

非公平锁是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程优先获取锁。有可能，会造成优先级反转或者饥饿现象。

对于Java `ReentrantLock`而言，通过构造函数指定该锁是否是公平锁，默认是非公平锁。非公平锁的优点在于吞吐量比公平锁大。对于`synchronized`而言，也是一种非公平锁。由于其并不像`ReentrantLock`是通过AQS的来实现线程调度，所以并没有任何办法使其变成公平锁。

# 可重入锁

可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。可重入锁的一个好处是可一定程度避免死锁。

```
synchronized void setA() throws Exception{
    Thread.sleep(1000);
    setB();
}

synchronized void setB() throws Exception{
    Thread.sleep(1000);
}
```

上面的代码就是一个可重入锁的一个特点，如果不是可重入锁的话，setBack可能不会被当前线程执行，可能造成死锁。

# 独享锁/共享锁

独享锁是指该锁一次只能被一个线程所持有。

共享锁是指该锁可以被多个线程所持有。

`ReentrantLock`是独享锁。Lock的另一个实现类`ReadWriteLock`，其读锁是共享锁，写锁是独享锁。

# 互斥锁/读写锁

上面讲的独享锁/共享锁就是一种广义的说法，互斥锁/读写锁就是具体的实现。
互斥锁在Java中的具体实现就是`ReentrantLock`
读写锁在Java中的具体实现就是`ReadWriteLock`

# 乐观锁/悲观锁

乐观锁与悲观锁不是指具体的什么类型的锁，而是指看待并发同步的角度。

悲观锁认为对于同一个数据的并发操作，一定是会发生修改的，哪怕没有修改，也会认为修改。因此对于同一个数据的并发操作，悲观锁采取加锁的形式。悲观的人为，不加锁的并发操作一定会出问题。适合**写操作非常多的场景**。

乐观锁则认为对于同一个数据的并发操作，是不会发生修改的。在更新数据的时候，会采用尝试更新。乐观的认为，不加锁的并发操作是没有问题的。适合**读操作较多的场景**。

# 分段锁

分段锁其实是一种锁的设计，并不是具体的一种锁，对于`ConcurrentHashMap`中的分段锁称为Segment，类似于HashMap的结构，即内部拥有一个Entry数组，数组中的每个元素又是一个链表；同时又是一个ReentrantLock

当需要put元素的时候，并不是对整个HashMap进行加锁，而是先通过hashcode来知道数据要放在哪个分段中，然后对这个分段进行加锁，所以当多线程put的时候，只要不是放在一个分段中，就实现了真正的并行的插入。

**但是，在统计size的时候，就是获取HashMap全局信息的时候，就需要获取所有的分段锁才能统计**。

分段锁的设计目的是细化锁的粒度，当操作不需要更新这个数组的时候，就仅仅针对数组中的一项进行枷锁操作。

# 偏向锁/轻量级锁/重量级锁

这三种锁是指针对synchronized的锁的状态。

偏向锁是指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁。降低获取锁的代价。

轻量级锁是指当锁时偏向锁的时候，被另一个线程所访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形式尝试获取锁，不会阻塞，提高性能。

重量级锁是指当锁为轻量级锁的时候，另一个线程虽然是自旋，但自旋不会一直持续下去，当自旋一定次数的时候，还没有获取到锁，就会进入阻塞，该锁膨胀为重量级锁。重量级锁会让其他申请的线程进入阻塞，性能降低。

# 自旋锁

在Java中，自旋锁是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。

