package liuyubobo;

import java.lang.reflect.Method;

public class SortTestHelper {

    //产生一个随机数组
    public static Integer[] randomArray(int n ,int rangeL, int rangeR){
        if (rangeL > rangeR){
            throw new IllegalArgumentException("rangL can not be bigger than rangR");
        }

        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Integer((int)(Math.random()*(rangeR-rangeL) + rangeL));
        }
        return array;
    }

    /**
     * 产生一个顺序度可控的数组
     * @param n 数组大小
     * @param swapTime 交换次数，为0是是有序数组，值越大数组越混乱
     * @return
     */
    public static Integer[] neralyOrderedArr(int n, int swapTime){
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++){
            arr[i] = new Integer(i);
        }

        for (int i = 0; i < swapTime; i++){
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);

            Integer temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    // 打印数组
    public static void printArray(Object[] args) {

        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    // 判断是否有序，单调不减
    public static boolean isSorted(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++){
            if (arr[i].compareTo(arr[i+1]) > 0) return false;
        }
        return true;
    }

    //测试sortClassName所对应排序算法排序arr数组的正确性与消耗时间
    public static void testSort(String sortClassName, Comparable[] arr){

        try {
            //包全名
            Class sortClass = Class.forName(sortClassName);
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});

            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            //method.invoke(Object owner,Object ...args) owner对象中带有参数args的method方法 方法为静态时，owner为null
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            if (!isSorted(arr)){
                throw new IllegalStateException(sortClassName+": failed");
            }

            System.out.println(sortClass.getSimpleName() + ": " + (endTime-startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 返回一个有序数组，单调增
    public static Integer[] orderedArray(int n){
        return neralyOrderedArr(n, 0);
    }

    // 返回一个单调递减数组
    public static Integer[] inverstArray(int n){
        Integer[] arr = orderedArray(n);

        for (int i = n/2-1; i >= 0; i--){
            Integer t = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = t;
        }
        return arr;
    }

    // 打乱一个数组
    public static Integer[] shuffleArray(Integer[] arr){
        int n = arr.length;

        for (int i = 0; i < n; i++){
            int j = (int) (Math.random()*(n-i)) + i;

            Integer t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
        return arr;
    }
}
