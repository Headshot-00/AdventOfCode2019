package Day3;

import java.util.ArrayList;
import java.util.Arrays;

public class ElectricalPanel {

    private ArrayList<Line> wire1H;
    private ArrayList<Line> wire1V;
    private ArrayList<Line> wire2H;
    private ArrayList<Line> wire2V;


    /**
     * Constructor initializes the arrays.
     */
    public ElectricalPanel() {
        wire1H = new ArrayList<>();
        wire1V = new ArrayList<>();
        wire2H = new ArrayList<>();
        wire2V = new ArrayList<>();
    }

    public void walk(String[] s, int index) {
        int[] coords = {0, 0};
        for(String st : s) {
            StringBuilder sb = new StringBuilder(st);
            int direction, length;

            char d = sb.charAt(0);
            sb.deleteCharAt(0);

            switch(d) {
                case 'U':
                    direction = Line.NORTH;
                    break;
                case 'D':
                    direction = Line.SOUTH;
                    break;
                case 'R':
                    direction = Line.EAST;
                    break;
                case 'L':
                    direction = Line.WEST;
                    break;
                default:
                    throw new IllegalStateException("Not a direction " + d);
            }
            length = Integer.parseInt(sb.toString());
            coords = addLine(index, coords, direction, length);
        }
    }

    public ArrayList<int[]> findIntersections() {
        ArrayList<int[]> intersections = new ArrayList<>();

        int l = wire1H.size();
        int k = wire2V.size();

        checkIntersections(intersections, l, k, wire1H, wire2V);

        l = wire2H.size();
        k = wire1V.size();

        checkIntersections(intersections, l, k, wire2H, wire1V);

        int[] origin = {0, 0};
        for(int i = 0; i < intersections.size(); i++) {
            int[] a = intersections.get(i);
            if(Arrays.equals(a, origin)) {
                intersections.remove(i);
                i--;
            }
        }

        return intersections;
    }

    private void checkIntersections(ArrayList<int[]> intersections, int l, int k, ArrayList<Line> wire1, ArrayList<Line> wire2) {
        for(int i = 0; i < l; i++) {
            Line line1 = wire1.get(i);
            for(int j = 0; j < k; j++)  {
                Line line2 = wire2.get(j);
                int[] intersection = Line.getCollisions(line1, line2);
                if(intersection != null)
                    intersections.add(intersection);
            }
        }
    }

    /**
     * Add a new Line to the wire
     * @param index The wire the line should be added to
     * @param startCoords The starting coordinates of the Line
     * @param direction The direction of the Line
     * @param length The length of the Line
     */
    private int[] addLine(int index, int[] startCoords, int direction, int length) {
        ArrayList<Line> wire;
        if(index == 1) {
            if(direction == Line.NORTH || direction == Line.SOUTH) {
                wire = wire1V;
            } else {
                wire = wire1H;
            }
        } else {
            if(direction == Line.NORTH || direction == Line.SOUTH) {
                wire = wire2V;
            } else {
                wire = wire2H;
            }
        }
        Line l = new Line(startCoords, direction, length);
        wire.add(l);
        return l.getEndCoords();
    }
}
