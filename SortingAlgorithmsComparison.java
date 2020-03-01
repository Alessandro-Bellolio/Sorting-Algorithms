package sortingalgorithmscomparison;

import java.util.Random;
import java.util.Scanner;

class SortingAlgorithmsComparison {

    public static long intercambios = 0, comparaciones = 0;

    public static void main(String[] args) {
        int n, c, elec, elec2;
        Scanner in = new Scanner(System.in);
        String s = "a";
        long time_start = 0, time_end = 0;

        System.out.println("Ingrese la cantidad de números a ordenar.");
        n = in.nextInt();

        int array[] = new int[n];
        System.out.println(
                "Desea que esta cantidad de numeros esté ordenada de forma: 1. Ascendente 2. Descendente 3. Aleatorio");
        elec2 = in.nextInt();
        if (elec2 == 1) {
            array = arregloOrdenadoA(n);
        } else if (elec2 == 2) {
            array = arregloOrdenadoD(n);
        } else if (elec2 == 3) {
            array = arregloAleatorio(n);
        }

        for (c = 0; c < n; c++) {
            System.out.print(array[c] + ", ");
        }

        System.out.println(
                "Escoga el algoritmo a usar para ordenar: \n1. Bubble sort  2.Selection sort 3. Insertion sort \n4. Quick sort 5. Merge sort");
        elec = in.nextInt();
        if (elec == 1) {
            intercambios = comparaciones = 0;

            time_start = System.currentTimeMillis();
            bubble_sort(array, array.length);
            time_end = System.currentTimeMillis();

            s = "Bubble";

        } else if (elec == 2) {
            intercambios = comparaciones = 0;

            time_start = System.currentTimeMillis();
            selection_sort(array, array.length);
            time_end = System.currentTimeMillis();

            s = "Selection";

        } else if (elec == 3) {
            intercambios = comparaciones = 0;

            time_start = System.currentTimeMillis();
            insertion_sort(array, array.length);
            time_end = System.currentTimeMillis();

            s = "Insertion";

        } else if (elec == 4) {
            intercambios = comparaciones = 0;

            time_start = System.currentTimeMillis();
            quick_sort(array, 0, array.length - 1);
            time_end = System.currentTimeMillis();

            s = "Quick";

        } else if (elec == 5) {
            intercambios = comparaciones = 0;

            time_start = System.currentTimeMillis();
            merge_sort(array, 0, array.length - 1);
            time_end = System.currentTimeMillis();

            s = "Merge";

        }

        for (c = 0; c < n; c++) {
            System.out.print(array[c] + ", ");
        }

        System.out.println("\nDemoró " + (time_end - time_start) + " milisegundos para ordenar " + n
                + " datos numéricos, utilizando " + s + " sort");
        System.out.println("Intercambios: " + intercambios + " Comparaciones: " + comparaciones);
    }

    public static void bubble_sort(int array[], int n) {
        for (int c = 0; c < (n - 1); c++) {
            for (int d = 0; d < n - c - 1; d++) {
                comparaciones = comparaciones + 1;
                if (array[d] > array[d + 1]) /*
                                              * For descending order use <
                                              */ {
                    int swap = array[d];
                    array[d] = array[d + 1];
                    array[d + 1] = swap;
                    intercambios = intercambios + 1;
                }
            }

        }

    }

    public static void selection_sort(int array[], int n) {
        for (int x = 0; x <= n - 2; x++) {
            int indexMin = x;
            for (int y = x + 1; y <= n - 1; y++) {
                comparaciones = comparaciones + 1;
                if (array[indexMin] > array[y]) {
                    indexMin = y;

                }
            }
            int temp = array[x];
            array[x] = array[indexMin];
            array[indexMin] = temp;
            intercambios = intercambios + 1;
        }
    }

    public static void insertion_sort(int array[], int n) {
        boolean ban;
        int temp;
        for (int i = 1; i <= n - 1; i++) {
            int j = i - 1;
            int B = array[i];
            ban = false;
            while (!ban && (j >= 0)) {
                comparaciones = comparaciones + 1;
                if (array[j] > B) {
                    intercambios = intercambios + 1;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    j--;
                } else {
                    ban = true;
                }
            }
        }
    }

    public static void quick_sort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                comparaciones = comparaciones + 1;
                j--;
            }
            if (i <= j) {
                if (i != j) {
                    intercambios = intercambios + 1;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                i++;
                j--;
            }
        }
        // recursively sort two sub parts
        if (low < j) {
            quick_sort(arr, low, j);
        }
        if (high > i) {
            quick_sort(arr, i, high);
        }
    }

    public static void merge_sort(int array[], int lo, int n) {
        int low = lo;
        int high = n;
        comparaciones = comparaciones + 1;
        if (low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        merge_sort(array, low, middle);
        merge_sort(array, middle + 1, high);
        int end_low = middle;
        int start_high = middle + 1;
        while ((lo <= end_low) && (start_high <= high)) {
            comparaciones = comparaciones + 1;
            if (array[low] < array[start_high]) {
                low++;
            } else {
                int Temp = array[start_high];
                for (int k = start_high - 1; k >= low; k--) {

                    array[k + 1] = array[k];
                }
                intercambios = intercambios + 1;
                array[low] = Temp;
                low++;
                end_low++;
                start_high++;
            }
        }
    }

    // arreglo del 1 hasta tamaño ordenado ascendentemente
    public static int[] arregloOrdenadoA(int tamano) {
        int array[];
        array = new int[tamano];
        for (int i = 0; i < tamano; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // arreglo del tamaño hasta 1 ordenado descendentemente
    public static int[] arregloOrdenadoD(int tamano) {
        int array[];
        array = new int[tamano];
        for (int i = 0; i < tamano; i++) {
            array[i] = tamano - i;
        }
        return array;
    }

    // arreglo aleatorio
    public static int[] arregloAleatorio(int tamano) {
        int array[];
        array = new int[tamano];
        Random rnd = new Random(15);
        for (int i = 0; i < tamano; i++) {
            array[i] = (int) (rnd.nextDouble() * tamano + 1);
        }
        return array;
    }
}