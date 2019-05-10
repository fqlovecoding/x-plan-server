package com.ffq.order;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/{id}/{num}/order")
	public void order(@PathVariable("id") String id, @PathVariable("num") Integer num) {
		orderService.order(id, num);
	}

	@RequestMapping("/{id}/{num}/horder")
	public void horder(@PathVariable("id") String id, @PathVariable("num") Integer num) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(200);
		int clientNum = 100;
		final CountDownLatch cdl = new CountDownLatch(clientNum);
		for (int i = 0; i < clientNum; i++) {
			service.execute(() -> {
				try {
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				orderService.order(id, num);
				semaphore.release();
			});
			cdl.countDown();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
