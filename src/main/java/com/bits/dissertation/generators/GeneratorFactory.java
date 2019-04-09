package com.bits.dissertation.generators;

import com.bits.dissertation.data.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneratorFactory {

    public static GeneratorFactory INSTANCE = new GeneratorFactory();

    private GeneratorFactory() {}

    public List<Runnable> generators(String user) {
        List<Runnable> generators = new ArrayList<Runnable>();
        List<String> apis = getRandomApiSample();
        generators.add(new RatingEventGenerator(user, apis));
        generators.add(new AccessTokenEventGenerator(user, apis));
        generators.add(new TryOutEventGenerator(user, apis));
        generators.add(new SubscribeEventGenerator(user, apis));
        generators.add(new PostEventGenerator(user, apis));
        generators.add(new CommentEventGenerator(user, apis));
        generators.add(new LikeEventGenerator(user, apis));
        generators.add(new VisitEventGenerator(user, apis));
        generators.add(new ExportEventGenerator(user, apis));
        generators.add(new DownloadSDKEventGenerator(user, apis));
        return generators;
    }

    private List<String> getRandomApiSample() {
        List<String> apis = new ArrayList<>(DataSource.INSTANCE.getAPIs());
        int limit = Math.round(Math.round(apis.size() * 0.25));
        return IntStream.range(0, limit)
                .mapToObj(i -> apis.remove(new Random().nextInt(apis.size())))
                .collect(Collectors.toList());
    }
}
