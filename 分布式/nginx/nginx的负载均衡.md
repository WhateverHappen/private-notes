# 什么是负载均衡

一台服务器单位时间内访问量越大，服务器压力就越大，当超过一定程度时服务器将会崩溃。为了避免服务器崩溃，让用户有更好的体验，我们通过负载俊航的方式来分担服务器压力。

我们可以搭建很多服务器，组成一个服务器集群，当用户访问网站时，将不同用户的访问请求引向不同的服务器，保证服务器压力的分担，避免服务器崩溃。

# 负载均衡的几种常用方式

## 1、轮询（默认）

每个请求**按时间顺序逐一分配到**不同的后端服务器，如果后端服务器done掉能自动剔除。

```
upstream backserver {
    server 192.168.0.14;
    server 192.168.0.15;
}
```

## 2、权重（weight）

指定轮询几率，weight和访问比率成正比，权重越高，被访问的概率越大。用于后端服务器性能不均的情况

```
upstream backserver {
    server 192.168.0.14 weight=3;    #处理30%访问请求
    server 192.168.0.15 weight=7;    #处理70%访问请求
}
```

## 3、ip_hash

该方法根据ip计算出对应的hash值，然后再根据hash值选择对应的服务器，用于解决session问题。假定用户已经访问了某个服务器，当用户再次访问时，会将请求通过hash算法，自动定位到该服务器。

```
upstream backserver {
    ip_hash;
    server 192.168.0.14:88;
    server 192.168.0.15:80;
}
```

## 4、fair

按后端服务器的响应时间分配请求，响应时间短的优先分配

```
upstream backserver {
    server server1;
    server server2;
    fair;
}
```

## 5、url_hash

按访问url的hash结果来分配请求，使每个url定向到同一个后端服务器，后端服务器为缓存时比较有效

```
upstream backserver {
    server squid1:3128;
    server squid2:3128;
    hash $request_uri;
    hash_method crc32;
}
```

## 6、least_conn

最少链接，在选择时，谁的链接数量少，就选择谁

```
upstream mynginxserver {
    least_conn;
        server 192.168.1.183:8888;
        server 192.168.1.183:8080;
}
```

# 服务器的状态设置

* down表示当前server暂时不参与负载
* weight默认为1。weight越大，负载权重越大
* `max_fails`：允许请求失败次数，默认为1.当超过最大次数时，返回`proxy_next_upstream`模块定义的错误
* `fail_timeout:`最大请求失败次失败后，暂停的时间
* backup：其他所有的非backup server down掉或者忙的时候，请求backup server。所以这台server压力会最轻。