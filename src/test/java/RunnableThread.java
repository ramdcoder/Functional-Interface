
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RunnableThread {

    private static Logger logger = LoggerFactory.getLogger((RunnableThread.class));

    // Inner class for better grouping purposes
    public final class Navigation
    {
        final Map<String, Runnable> navMap = new HashMap<>();

        public Navigation() {
            navMap.put("landing", this::landing);
            navMap.put("products", this:: products);
            navMap.put("category", this:: category);
            navMap.put("mybag", this:: mybag);
            navMap.put("checkout", this:: checkout);
        }

        public void addPageToMap(String page, Runnable runnable) {
            navMap.put(page, runnable);
            logger.info(String.format("Added '%s' page to navMap", page));
        }

        public void navToPage(String page) {
            navMap.get(page).run();
        }

        private void landing() {
            System.out.println("this is landing page");
        }

        private void products() {
            System.out.println("this is products page");
        }

        private void category() {
            System.out.println("this is category page");
        }

        private void mybag() {
            System.out.println("this is mybag page");
        }

        private void checkout() {
            System.out.println("this is checkout page");
        }

        private void toys() {
            System.out.println("this is toys page");
        }
    }

    @Test
    public void NavigationTest() {
        Navigation navigation = new Navigation();
        navigation.navToPage("landing");
        navigation.navToPage("checkout");

        navigation.addPageToMap("toys", new Navigation()::toys);
        navigation.navToPage("toys");
        navigation.addPageToMap("gifts", new RunnableThread()::gifts);
        navigation.navToPage("gifts");
    }

    private void gifts() {
        System.out.println("this is gifts page");
    }

    @Test
    public void runnableWithThreads() throws InterruptedException {
        // *** Runnables cannot take parameters, so lambda expression x -> {x}; is not allowed. Unless some overriding??
        // the Runnable functional interface's run() method will perform whatever action is specified in lambda expression
        Runnable runnable = () -> {
            for(int i=0; i < 10; i++)
                logger.info("Runnable run() method performed print action");
        };

        Runnable runnable2 = () -> {
            for(int i=0; i < 10; i++)
                logger.info("Runnable run() method performed print action");
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is what is defined/overridden for run() of runnable3");
            }
        };

        new Thread(runnable3).start();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        logger.info(String.format("Thread id: %s | Thread name: %s", thread1.getId(), thread1.getName()));
        logger.info(String.format("Thread id: %s | Thread name: %s", thread2.getId(), thread2.getName()));
        logger.info("Before thread1.start() > thread1 isAlive() = " + thread1.isAlive());
        logger.info("Before thread2.start() > thread2 isAlive() = " + thread1.isAlive() + "\n");

        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        if(!thread1.isAlive() && !thread2.isAlive()) {
            logger.info("\n");
            logger.info("Threads are dead!");
        }
        else {
            logger.info("\n");
            logger.info("Threads are alive");
        }
    }
}
