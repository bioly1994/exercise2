package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class BusinessServiceWithCallable {
	private final ExecutorService executorService;	
	private final NumericHelper helper;
	List<Future<Integer>> callableList=new ArrayList<Future<Integer>>();
	
	public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
		this.executorService = executorService;
		this.helper = helper;
	}
	
	/**
	 * Calculates a sum of 100 random numbers.
	 * Random numbers are returned by helper.nextRandom method.
	 * Each random number is calculated asynchronously.
	 * @return sum of 100 random numbers.
	 */
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {	
		long result = 0;
		// TODO Task: 
		// 1. create 100 Callable objects that invoke helper.nextRandom in their call() method.
		// 2. submit all Callable objects to executorService (executorService.submit or executorService.invokeAll)
		// 3. sum up the results - each random number can be retrieved using future.get() method.
		// 4. return the computed result.
		for(int i=0;i<100;i++){
			Callable<Integer> callable=new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					return helper.nextRandom();
				}
			};
			Future<Integer> submit=executorService.submit(callable);
			callableList.add(submit);
		}
		for(Future<Integer> future:callableList){
			result+=future.get();
		}
		return result;
	}
}
