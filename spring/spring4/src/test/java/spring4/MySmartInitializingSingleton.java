package spring4;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 */
public class MySmartInitializingSingleton  implements SmartInitializingSingleton {
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("单例Bean实例化完成了");
	}
}
