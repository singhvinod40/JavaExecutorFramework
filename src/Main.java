import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService executorService = Executors.newFixedThreadPool(10);


        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        //1. execute task using execute() method
        executorService.execute(runnable);
        executorService.shutdown();

        Runnable task = () -> {
            System.out.println("runnable is an functional interface");
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("All task done");

        //callable example


        Callable<Integer> calltask =()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }

        };


        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(calltask);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);

        executor.shutdown();

    }
}