package com.bits.dissertation;

import com.bits.dissertation.data.DataSource;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.es.ESUrlBuilder;
import com.bits.dissertation.generators.GeneratorFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class DataGeneratorRunner {

    public static void main(String[] args) {
        DataGeneratorRunner runner = new DataGeneratorRunner();
        runner.generateUsageData();
    }

    private void generateUsageData() {
        System.out.println("Deleting all the events");
        try {
            new ESManager<>(ESUrlBuilder.USER_PROFILE_EVENT_URL).deleteAll();
        } catch (IOException e) {
            System.err.println("Unable to delete so returning");
            return;
        }
        System.out.println("All the old records deleted");
        DataSource.INSTANCE.getUsers()
                .forEach(user -> {
                    System.out.println("Generating events for the user " + user);
                    ExecutorService executorService = Executors.newFixedThreadPool(10);
                    GeneratorFactory.INSTANCE.generators(user);
                    List<? extends Future<?>> futures = GeneratorFactory.INSTANCE.generators(user).stream()
                            .map(executorService::submit)
                            .collect(Collectors.toList());
                    waitOnFuture(futures);
                });
    }

    private void waitOnFuture(List<? extends Future<?>> futures) {
        futures.stream().filter(future -> {
            try {
                return future != null && future.get() != null;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return false;
            }
        }).forEach(future -> {
            try {
                System.out.println("Error in execution" + ((Future) future).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
