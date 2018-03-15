package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.max;

public class Skyline {

    public static class Point {
        public int x;
        public int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;

        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] array) {
        ArrayList<Point> skyline = new ArrayList<>();
        if(array.length==0){
            return skyline;
        }
        if(array.length == 1){
            skyline.add(new Point(array[0].l, array[0].h));
            skyline.add(new Point(array[0].r, 0));
            return skyline;
        }
        else{
            int l=0;
            int r = array.length - 1;
            int mid = array.length/2;;

            Building[] left = Arrays.copyOfRange(array, l, mid);
            Building[] right = Arrays.copyOfRange(array, mid, r+1);

            return mergeSkylines(skyline(left), skyline(right));
        }
    }

    private static List<Point> mergeSkylines(List<Point> skyline1, List<Point> skyline2) {
        int s1_index = 0;
        int s2_index = 0;
        int h1 = 0;
        int h2 = 0;
        int count = 0;
        ArrayList<Point> skyline = new ArrayList<>();

        while (s1_index < skyline1.size() && s2_index < skyline2.size()) {
            if(skyline1.get(s1_index).x == skyline2.get(s2_index).x){
                h1 = skyline1.get(s1_index).y;
                h2 = skyline2.get(s2_index).y;
                int maxh = max(h1, h2);
                if(skyline.size() > 0){
                    if(maxh != skyline.get(count-1).y){
                        skyline.add(new Point(skyline1.get(s1_index).x, maxh));
                        count++;
                    }
                }
                else{
                    skyline.add(new Point(skyline1.get(s1_index).x, maxh));
                    count++;
                }
                s1_index++;
                s2_index++;
            }
            else if (skyline1.get(s1_index).x < skyline2.get(s2_index).x) {
                h1 = skyline1.get(s1_index).y;
                int maxh = max(h1, h2);
                if(skyline.size() > 0) {

                    if (maxh != skyline.get(count - 1).y) {
                        skyline.add(new Point(skyline1.get(s1_index).x, maxh));
                        count++;
                    }
                }
                else{
                    skyline.add(new Point(skyline1.get(s1_index).x, maxh));
                    count++;
                }
                s1_index++;
            } else {
                h2 = skyline2.get(s2_index).y;
                int maxh = max(h1, h2);
                if(skyline.size() > 0) {

                    if (maxh != skyline.get(count - 1).y) {
                        skyline.add(new Point(skyline2.get(s2_index).x, maxh));
                        count++;
                    }
                }
                else{
                    skyline.add(new Point(skyline2.get(s2_index).x, maxh));
                    count++;
                }
                s2_index++;
            }
        }

        while (s1_index < skyline1.size()) {
            skyline.add(skyline1.get(s1_index));
            s1_index++;
        }

        while (s2_index < skyline2.size()) {
            skyline.add(skyline2.get(s2_index));
            s2_index++;
        }

        return skyline;
    }

}