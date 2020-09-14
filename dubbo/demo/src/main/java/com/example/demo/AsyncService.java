package com.example.demo;

import java.util.concurrent.CompletableFuture;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20190606| 曾凡平| 创建</li>
 */
public interface AsyncService {
	CompletableFuture<String> sayHello(String name);
}
