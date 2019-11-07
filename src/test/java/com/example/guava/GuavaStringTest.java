package com.example.guava;


import com.google.common.base.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author: GuanBin
 * @date: Created in 下午10:43 2019/11/3
 */
public class GuavaStringTest {
    //连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();

    public static void main(String[] args) throws ExecutionException {
//        test1();
//        test();
//        ayyayListMultimap();
//        hashBigmap();
//        hashBasedTable();
//        function();
//        preconditions();
//        cache();
        callBack();
    }

    private static void test1() {
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println(join);

        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(" a, , b,, ");

        split.forEach(s -> {
            System.out.println(s);
        });

        String abc = CharMatcher.DIGIT.retainFrom("abc");
        System.out.println(abc);

        List<Integer> list = Ints.asList(1, 2, 3, 4, 4);

        System.out.println(Ints.join(",", 1, 2, 3, 4, 5, 6));
    }

    private static void test() {

        //数组合并
        int[] newArray = Ints.concat(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4});
        System.out.println(newArray.length);

        //数组转化为集合
        List<Integer> list = Ints.asList(newArray);

        //集合转化为数组
        int[] ints = Ints.toArray(list);

        //数组最大值
        int max = Ints.max(newArray);

        //数组最小值
        int min = Ints.min(newArray);

        boolean contains = Ints.contains(newArray, 4);

        //无序可以重复的  list有序可重复，set无序不可重复、‘’
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("b");
        multiset.add("c");
        System.out.println(multiset.size());
        System.out.println(multiset.count("a"));


        //list1不可变属性设置
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        List<String> readOnlyList = Collections.unmodifiableList(list1);
        list1.add("c");
        readOnlyList.add("d");//java.lang.UnsupportedOperationException
        System.out.println(list1.size());

        //ImmutableList是不可变的list
        List<String> immutableList = ImmutableList.of("a", "b", "c");
        immutableList.add("d"); //java.lang.UnsupportedOperationException
        ImmutableList<String> copyList = ImmutableList.copyOf(list1);
        copyList.add("d");//java.lang.UnsupportedOperationException

        //不可变map
        Map<String, String> immutableMap = ImmutableMap.of("a", "v1", "b", "v2", "c", "v3");
        immutableMap.put("d", "d1");//java.lang.UnsupportedOperationException

    }

    //支持key:List的情况
    private static void ayyayListMultimap() {
        ArrayListMultimap<Object, Object> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("a", 1);
        arrayListMultimap.put("a", 2);
        arrayListMultimap.put("a", 3);
        arrayListMultimap.put("b", 4);
        System.out.println(arrayListMultimap.get("a"));
    }

    //value不可以重复
    private static void hashBigmap() {
        HashBiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put("a", 1);
        hashBiMap.put("a", 2);
        hashBiMap.put("d", 3);//java.lang.IllegalArgumentException: value already present: 2
        System.out.println(hashBiMap.get("a"));
        System.out.println(hashBiMap.inverse().get(2));//inverse把value当做key
    }

    //实现复合map
    private static void hashBasedTable() {
        HashBasedTable<String, String, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("1", "tom", "90");
        hashBasedTable.put("2", "tom1", "90");
        hashBasedTable.put("3", "tom2", "90");
        hashBasedTable.put("4", "tom3", "90");
        hashBasedTable.put("4", "tom3", "90");
        Set<Table.Cell<String, String, String>> cells = hashBasedTable.cellSet();
        cells.forEach(cell -> {
            System.out.println(cell.getRowKey() + cell.getColumnKey() + cell.getValue());
        });

        System.out.println(hashBasedTable.rowKeySet());
        System.out.println(hashBasedTable.columnKeySet());
        System.out.println(hashBasedTable.row("1"));
        System.out.println(hashBasedTable.column("tom"));

    }

    //function
    private static void function() {
        ArrayList<String> strings = Lists.newArrayList("abcdefghijk", "aklklklk");
        Function f1 = new com.google.common.base.Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.length() < 5 ? s : s.substring(0, 5);
            }
        };
        Function f2 = new com.google.common.base.Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };


        Collection<String> transform = Collections2.transform(strings, Functions.compose(f1, f2));

        transform.forEach(s -> {
            System.out.println(s);
        });

    }

    //function
    private static void preconditions() {

        int age = 18;
        Preconditions.checkNotNull("");
//        Preconditions.checkArgument(age>18,"you can't play the game");
//        Preconditions.checkArgument(age>18,"%s can't play the %s","you","game");//java.lang.IllegalArgumentException: you can't play the game


        //jdk 自带的
        String a = "a";
        String b = "b";
        //consumer是T：入参类型；没有出参
        Consumer<String> consumer = p -> System.out.println(p);
        //若a不为null，则打印出a
        Optional.ofNullable(a).ifPresent(consumer);

        HashMap<Object, Object> map = null;
        HashMap<Object, Object> defaultMap = Maps.newHashMap();
        defaultMap.put("a", "b");
        //若map为null,则取默认值defaultMap
        HashMap<Object, Object> map1 = com.google.common.base.Optional.fromNullable(map).or(defaultMap);

        map1.forEach((k, v) -> {
            System.out.println(String.format("k is %s, value is %s", k, v));
        });
    }

    //定义缓存策略并实现缓存
    private static void cache() throws ExecutionException {
        LoadingCache<String, String> cahceBuilder = CacheBuilder
                .newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS) //最后一次访问2秒后失效
                .expireAfterWrite(2, TimeUnit.SECONDS)  //写入2秒后失效
                .refreshAfterWrite(3, TimeUnit.SECONDS)  //写入3秒后，刷新缓存
                .maximumSize(10000L)//缓存容量
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue = "hello " + key + "!";
                        return strProValue;
                    }
                });
        System.out.println(cahceBuilder.apply("begincode"));  //hello begincode!
        System.out.println(cahceBuilder.get("begincode")); //hello begincode!
        System.out.println(cahceBuilder.get("wen")); //hello wen!
        System.out.println(cahceBuilder.apply("wen")); //hello wen!
        System.out.println(cahceBuilder.apply("da"));//hello da!
        cahceBuilder.put("begin", "code");
        System.out.println(cahceBuilder.get("begin")); //codexxxx

    }

    private static void callBack() {
        //线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //定义监听器
        ListeningExecutorService listeningDecorator = MoreExecutors.listeningDecorator(pool);
        ListenableFuture listenableFuture = listeningDecorator.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                //随机产生一个>=0但<3的随机整数
                if (new Random().nextInt(3) == 2) {
                    throw new NullPointerException();
                }
                return 1;
            }
        });

        //监听结果的具体处理逻辑
        FutureCallback futureCallback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer o) {
                System.out.println(o);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        };

        //监听处理绑定
        Futures.addCallback(listenableFuture, futureCallback);

    }


}
