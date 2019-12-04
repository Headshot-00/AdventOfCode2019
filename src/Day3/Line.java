package Day3;

public class Line {

    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    private int[] startCoords;
    private int direction, length;


    /**
     * Constructor takes three arguments to describe the line.
     * @param startCoords int[] containing the starting coordinates of the line.
     * @param direction One of Line.NORTH, Line.SOUTH, Line.EAST or Line.WEST.
     * @param length Length of the line.
     */
    public Line(int[] startCoords, int direction, int length) {
        this.startCoords = startCoords;
        this.direction = direction;
        this.length = length;
    }

    /**
     * Method for getting the starting coordinates of the line
     * @return the starting coordinates
     */
    public int[] getStartCoords() {
        return startCoords;
    }


    /**
     * Method for getting the end coordinates of the line
     * @return the end coordinates
     */
    public int[] getEndCoords() {
        int[] endCoords = startCoords.clone();
        int[] direction = translateDirection();
        endCoords[direction[0]] += direction[1]*length;
        return endCoords;
    }

    /**
     * Internal method for translating the direction to a usable format.
     * @return Returns an int[] with useful values.
     */
    private int[] translateDirection() {
        switch (direction) {
            case Line.NORTH:
                return new int[]{1, 1};
            case Line.SOUTH:
                return new int[]{1, -1};
            case Line.EAST:
                return new int[]{0, 1};
            case Line.WEST:
                return new int[]{0, -1};
            default:
                throw new IllegalStateException("Not a direction: " + direction);
        }
    }

    /**
     * Calculates intersection between orthogonal lines
     * @param hLine Horizontal line
     * @param vLine Vertical line
     * @return The coordinates of the collision in the format [x, y] if lines collide, otherwise returns null.
     */
    public static int[] getCollisions(Line hLine, Line vLine) {
        int[][] hLineCoords = new int[2][];
        hLineCoords[0] = hLine.getStartCoords();
        hLineCoords[1] = hLine.getEndCoords();

        int[][] vLineCoords = new int[2][];
        vLineCoords[0] = vLine.getStartCoords();
        vLineCoords[1] = vLine.getEndCoords();

        if(vLineCoords[0][0] != vLineCoords[1][0] || hLineCoords[0][1] != hLineCoords[1][1])
            throw new IllegalStateException("Lines are in wrong order!");

        if(vLineCoords[0][0] < Math.min(hLineCoords[0][0],hLineCoords[1][0]) || vLineCoords[0][0] > Math.max(hLineCoords[0][0],hLineCoords[1][0]))
            return null;
        if(hLineCoords[0][1] < Math.min(vLineCoords[0][1],vLineCoords[1][1]) || hLineCoords[0][1] > Math.max(vLineCoords[0][1],vLineCoords[1][1]))
            return null;
        int[] collCoords = new int[2];
        collCoords[0] = vLineCoords[0][0];
        collCoords[1] = hLineCoords[0][1];
        return collCoords;
    }
}
