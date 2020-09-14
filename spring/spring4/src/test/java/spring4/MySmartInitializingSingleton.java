package spring4;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20200914| 曾凡平| 创建</li>
 */
public class MySmartInitializingSingleton  implements SmartInitializingSingleton {
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("单例Bean实例化完成了");
	}
}
