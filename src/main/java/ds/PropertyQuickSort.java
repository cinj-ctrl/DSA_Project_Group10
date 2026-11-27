/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds;

import model.Property;
import java.util.*;

/**
 *
 * @author GRRD
 */
public class PropertyQuickSort {
    public static void sort(List<Property> properties, boolean ascending) {
        quickSort(properties, 0, properties.size() - 1, ascending);
    }

    private static void quickSort(List<Property> list, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(list, low, high, ascending);
            quickSort(list, low, pi - 1, ascending);
            quickSort(list, pi + 1, high, ascending);
        }
    }

    private static int partition(List<Property> list, int low, int high, boolean ascending) {
        double pivot = list.get(high).getPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            boolean condition = ascending ? (list.get(j).getPrice() <= pivot) : (list.get(j).getPrice() >= pivot);
            if (condition) {
                i++;
                Property temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Property temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
