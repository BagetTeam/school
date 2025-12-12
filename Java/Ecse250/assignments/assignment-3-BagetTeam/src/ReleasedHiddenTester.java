import assignment3.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ReleasedHiddenTester {
    @Test
    @Tag("score:1")
    @DisplayName("Secret Block constructor test2")
    public void secret_block3() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(47941931);

        final Block[] b = new Block[1];

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        b[0] = new Block(4, 6);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        Block expected = new Block (0,0,0,4,6, GameColors.RED, new Block[0]);

        assertTrue(HelperMethods.equals(expected, b[0]), "Blocks are not generated correctly. " +
                "Expected one block of RED color.");
    }
    @Test
    @Tag("score:2")
    @DisplayName("Secret getBlocksToDraw() test1")
    public void secret_getBlocksToDraw1() {
        Block.gen = new Random(415135);
        Block b = new Block(0, 4);
        b.updateSizeAndPosition(128, 0, 0);
//        Block b = rb.toBlock();

        final ArrayList<BlockToDraw>[] result = new ArrayList[]{new ArrayList<>()};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        result[0] = b.getBlocksToDraw();
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(116, result[0].size(), "getBlocksToDraw() is returning incorrect number of BlocksToDraw.");

        int frameBorderBlocks = 0;
        int red = 0, green = 0, blue = 0, yellow = 0;

        for (int i = 0; i < result[0].size(); i++) {
            if (result[0].get(i).getColor() == GameColors.FRAME_COLOR && result[0].get(i).getStrokeThickness() == 3){
                frameBorderBlocks++;
            } else if (result[0].get(i).getColor() == GameColors.RED && result[0].get(i).getStrokeThickness() == 0){
                red++;
            } else if (result[0].get(i).getColor() == GameColors.GREEN && result[0].get(i).getStrokeThickness() == 0){
                green++;
            } else if (result[0].get(i).getColor() == GameColors.BLUE && result[0].get(i).getStrokeThickness() == 0){
                blue++;
            } else if (result[0].get(i).getColor() == GameColors.YELLOW && result[0].get(i).getStrokeThickness() == 0){
                yellow++;
            }
        }

        assertEquals(58, frameBorderBlocks, "getBlocksToDraw() is returning incorrect number of FRAME_COLOR blocks.");
        assertEquals(13, red, "getBlocksToDraw() is returning incorrect number of RED blocks.");
        assertEquals(13, green,"getBlocksToDraw() is returning incorrect number of GREEN blocks.");
        assertEquals(18, blue, "getBlocksToDraw() is returning incorrect number of BLUE blocks.");
        assertEquals(14, yellow, "getBlocksToDraw() is returning incorrect number of YELLOW blocks.");
    }
    @Test
    @Tag("score:2")
    @DisplayName("Secret getSelectedBlock() test3")
    public void secret_getSelectedBlock3() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(14868663);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(8, 0, 0);


        Block[] actualSelectedBlock = new Block[1];

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        actualSelectedBlock[0] = b.getSelectedBlock(0, 0, 0);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertTrue(HelperMethods.equals(actualSelectedBlock[0], b), "getSelectedBlock() is not returning the correct block.");
    }
    @Test
    @Tag("score:1")
    @DisplayName("Secret getSelectedBlock() test1")
    public void secret_getSelectedBlock1() {
        Block.gen = new Random(1314262);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(8, 4, 4);


        Block[] selectedBlock = new Block[1];

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        selectedBlock[0] = b.getSelectedBlock(1, 2, 2);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertNull(selectedBlock[0], "getSelectedBlock() is not returning null when the position is outside the block.");
    }
    @Test
    @Tag("score:1")
    @DisplayName("Secret rotate() test1")
    public void secret_rotate1() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(1245);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(8, 0, 0);
        Block toRotate = b.getSelectedBlock(4,2, 2);

        Block.gen = new Random(1245);
        Block rb = new Block(0, 2);
        rb.updateSizeAndPosition(8, 0, 0);
        Block reference = rb.getSelectedBlock(4,2, 2);


        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        toRotate.rotate(0);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e
                        );
                    }
                }, "Took too long / Infinite loop"

        );

        assertTrue (HelperMethods.equals(reference, toRotate),
                "rotate() is changing size/coordinates when the rotation is not possible.");
    }

    @Test
    @Tag("score:1")
    @DisplayName("Secret flatten() test2")
    void secret_flatten2() {
        Block.gen = new Random(9876);
        Block b = new Block(2, 2);
        b.updateSizeAndPosition(6, 10, 2);


        Color[][][] c = {null};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        c[0] = b.flatten();
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e );
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(GameColors.YELLOW, c[0][0][0], "flatten() is not returning the block colors in the correct order.");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Secret flatten() test4")
    void secret_flatten4() {
        Block.gen = new Random(777777);
        Block b = new Block(4, 8);
        b.updateSizeAndPosition(80, 5, 10);

        Color[][][] c = {null};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        c[0] = b.flatten();
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        for (Color[] colors : c[0]) {
            for (Color color : colors) {
                assertEquals(GameColors.BLUE, color,
                        "flatten() is not returning the block colors in the correct order.");
            }
        }
    }

    @Test
    @Tag("score:1")
    @DisplayName("Secret PerimeterGoal score() test1")
    void secret_PGScore1() {
        Block.gen = new Random(901931);
        Block b = new Block(0, 10);
        b.updateSizeAndPosition(1024, 0, 0);


        PerimeterGoal p = new PerimeterGoal(GameColors.YELLOW);

        int[] actual = {-1};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        actual[0] = p.score(b);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(688, actual[0], "PG score() is not returning the correct score.");
    }

    @Test
    @Tag("score:1")
    @DisplayName("Secret BlobGoal score() test1")
    void secret_BGScore1() {
        Block.gen = new Random(524133);
        Block b = new Block(0, 5);
        b.updateSizeAndPosition(1024, 0, 0);


        BlobGoal g = new BlobGoal(GameColors.RED);

        int[] score = {-1};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        score[0] = g.score(b);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(107, score[0], "BG score() is not returning the correct score.");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Secret BlobGoal score() test3")
    void secret_BGScore3() {
        Block.gen = new Random(22222);
        Block b = new Block(0, 6);
        b.updateSizeAndPosition(128, 10, 50);


        BlobGoal g1 = new BlobGoal(GameColors.YELLOW);
        BlobGoal g2 = new BlobGoal(GameColors.RED);

        int[] score = {-1};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        score[0] = g1.score(b) + g2.score(b);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e );
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(573, score[0], "BG score() is not returning the correct score.");
    }
    public static Object[] getChildren(Object b) throws NoSuchFieldException, IllegalAccessException {
        Field childrenField ;

        childrenField = Block.class.getDeclaredField("children");

        childrenField.setAccessible(true);

        return (Block[]) childrenField.get(b);

    }

    public static Color getColor(Object b) throws NoSuchFieldException, IllegalAccessException {
        Field colorField ;

        colorField = Block.class.getDeclaredField("color");

        colorField.setAccessible(true);

        return (Color) colorField.get(b);
    }

    public static int getSize (Object b) throws NoSuchFieldException, IllegalAccessException {
        Field sizeField;

            sizeField = Block.class.getDeclaredField("size");

        sizeField.setAccessible(true);

        return (int) sizeField.get(b);
    }

    public static ArrayList<Integer> getCoords (Object b) throws NoSuchFieldException, IllegalAccessException {

        Field xCoordField,yCoordField;

            xCoordField = Block.class.getDeclaredField("xCoord");
            yCoordField = Block.class.getDeclaredField("yCoord");

        xCoordField.setAccessible(true);
        yCoordField.setAccessible(true);

        return new ArrayList<>(Arrays.asList((int) xCoordField.get(b), (int) yCoordField.get(b)));
    }

    public static int getLevel (Object b){

            return ((Block) b).getLevel();
    }

    public static int getMaxDepth (Object b){
            return ((Block) b).getMaxDepth();
    }

    public static boolean equals(Object b1, Object b2) throws NoSuchFieldException, IllegalAccessException {
        if (b1 == null || b2 == null) {
            return false;
        }

        if (getSize(b1) != getSize(b2)) {
            return false;
        }

        Color c1 = getColor(b1);
        Color c2 = getColor(b2);

        if (c1 == null && c2 == null){
            // dont do anything
        } else if (c1 == null || c2 == null){
            return false;
        } else if (!c1.equals(c2)) {
            return false;
        }

        ArrayList<Integer> coords1 = getCoords(b1);
        ArrayList<Integer> coords2 = getCoords(b2);

        if (!coords1.equals(coords2)) {
            return false;
        }

        if (getLevel(b1) != getLevel(b2)) {
            return false;
        }

        if (getMaxDepth(b1) != getMaxDepth(b2)) {
            return false;
        }

        Object[] children1 = getChildren(b1);
        Object[] children2 = getChildren(b2);

        if (children1 == null || children2 == null) {
            return false;
        } else {
            return children1.length == children2.length;
        }
    }

    public static ArrayList<Object> traverse(Object b1) throws NoSuchFieldException, IllegalAccessException {

        return traverseHelper((Block) b1);

    }

    public static ArrayList<Object> traverseHelper(Object b) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Object> blocks = new ArrayList<>();
        blocks.add(b);
        if (getChildren(b) != null) {
            for (Object child : getChildren(b)) {
                blocks.addAll(traverseHelper(child));
            }
        }
        return blocks;
    }

    @Test
    @Tag("score:4")
    @DisplayName("Secret undiscoveredBlobSize test2")
    public void secret_undiscoveredBlobSize2() {
        Block.gen = new Random(777888899);
        Block rb = new Block(0, 6);
        rb.updateSizeAndPosition(256, 0, 0);

        Color[][] unitCells = rb.flatten();

        boolean[][] visited = new boolean[unitCells.length][unitCells[0].length];

        Random r = new Random(1234567);
        for (int i = 0; i < 30; i++) {
            int x = r.nextInt(unitCells.length);
            int y = r.nextInt(unitCells[0].length);
            visited[x][y] = true;
        }

        BlobGoal g = new BlobGoal(GameColors.BLUE);

        int[] size = {-1};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        size[0] = g.undiscoveredBlobSize(19, 20, unitCells, visited);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e );
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(335, size[0],
                "undiscoveredBlobSize() is not returning the correct size.");
    }
    @Test
    @Tag("score:2")
    @DisplayName("Secret undiscoveredBlobSize test3")
    public void secret_undiscoveredBlobSize3() {
        Block.gen = new Random(99998888);
        Block b = new Block(4, 9);
        b.updateSizeAndPosition(128, 10, 20);

        Color[][] unitCells = b.flatten();

        boolean[][] visited = new boolean[unitCells.length][unitCells[0].length];

        BlobGoal g = new BlobGoal(GameColors.RED);
        int[] size = {-1};

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        size[0] = g.undiscoveredBlobSize(750, 1000, unitCells, visited);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );

        assertEquals(0, size[0]);
    }
    @Test
    @Tag("score:1")
    @DisplayName("Block constructor test1")
    void BlockConstructorTest1() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(50);
        Block b = new Block(0, 2);
    
        Field childrenField = Block.class.getDeclaredField("children");
        childrenField.setAccessible(true);
    
        Block[] children = (Block[]) childrenField.get(b);
    
        assertEquals(4, children.length);
    
        Block[] urChildren = (Block[]) childrenField.get(children[0]);
        Block[] ulChildren = (Block[]) childrenField.get(children[1]);
        Block[] llChildren = (Block[]) childrenField.get(children[2]);
        Block[] lrChildren = (Block[]) childrenField.get(children[3]);
    
        assertEquals(4, urChildren.length);
        assertEquals(0, ulChildren.length);
        assertEquals(0, llChildren.length);
        assertEquals(4, lrChildren.length);
    
    }
    
    @Test
    @Tag("score:3")
    @DisplayName("Block constructor test2")
    void BlockConstructorTest2() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(4);
        Block b = new Block(0, 2);
    
        Field childrenField = Block.class.getDeclaredField("children");
        Field colorField = Block.class.getDeclaredField("color");
        childrenField.setAccessible(true);
        colorField.setAccessible(true);
    
        Block[] children = (Block[]) childrenField.get(b);
    
        ArrayList<Color> expectedColor = new ArrayList<>();
        expectedColor.add(GameColors.YELLOW);
        expectedColor.add(null);
        expectedColor.add(GameColors.YELLOW);
        expectedColor.add(GameColors.RED);
    
        ArrayList<Color> actualColor = new ArrayList<>();
    
        for (Block child : children) {
            actualColor.add((Color) colorField.get(child));
        }
    
        assertEquals(expectedColor, actualColor);  // checking if the colors at level 1 are correct
    
        Block[] ulChildren = (Block[]) childrenField.get(children[1]);
    
        assertEquals(4, ulChildren.length);
    
        List<Color> expectedColorUL = List.of(GameColors.GREEN, GameColors.RED, GameColors.GREEN, GameColors.RED);
        List<Color> actualColorUL = new ArrayList<>();
    
        for (Block child : ulChildren) {
            actualColorUL.add((Color) colorField.get(child));
        }
    
        assertEquals(expectedColorUL, actualColorUL);  // checking if the colors at level 2 are correct for the upper left child
    }
    
    @Test
    @Tag("score:3")
    @DisplayName("Block updateSizeAndPosition() test1")
    void UpdateSizeAndPositionTest1() throws NoSuchFieldException, IllegalAccessException {
        Block[] children = new Block[4];
        children[0] = new Block(0, 0, 0, 1, 2, GameColors.YELLOW, new Block[0]);
        children[1] = new Block(0, 0, 0, 1, 2, GameColors.RED, new Block[0]);
        children[2] = new Block(0, 0, 0, 1, 2, GameColors.GREEN, new Block[0]);
        children[3] = new Block(0, 0, 0, 1, 2, GameColors.RED, new Block[0]);
        Block b = new Block(0, 0, 0, 0, 2, null, children);
    
        b.updateSizeAndPosition(16, 0, 0);
    
        Field childrenField = Block.class.getDeclaredField("children");
        Field sizeField = Block.class.getDeclaredField("size");
        Field xcoordField = Block.class.getDeclaredField("xCoord");
        Field ycoordField = Block.class.getDeclaredField("yCoord");
    
        childrenField.setAccessible(true);
        sizeField.setAccessible(true);
        xcoordField.setAccessible(true);
        ycoordField.setAccessible(true);
    
        assertEquals(16, (int) sizeField.get(b));
        assertEquals(0, (int) xcoordField.get(b));
        assertEquals(0, (int) ycoordField.get(b));
    
    
        ArrayList<Integer> actualSize = new ArrayList<>();
        ArrayList<Integer> Coords = new ArrayList<>();
    
        for (Block child : (Block[]) childrenField.get(b)) {
            actualSize.add((int) sizeField.get(child));
            Coords.add((int) xcoordField.get(child));
            Coords.add((int) ycoordField.get(child));
        }
    
        List<Integer> expectedSize = List.of(8, 8, 8, 8);
        List<Integer> expectedCoords = List.of(8, 0, 0, 0, 0, 8, 8, 8);  // UL x, UL y, UR x, UR y, LL x, LL y, LR x, LR y
    
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedCoords, Coords);
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block updateSizeAndPosition() test2")
    void UpdateSizeAndPositionTest2() {
        Block b = new Block(0, 0, 0, 0, 2, null, new Block[0]);
    
        assertThrows(IllegalArgumentException.class, () -> b.updateSizeAndPosition(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> b.updateSizeAndPosition(3, 0, 0));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("Block getBlocksToDraw() test1")
    void GetBlocksToDrawTest1() {
        Block.gen = new Random(4);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(16, 0, 0);
    
        ArrayList<BlockToDraw> blocksToDraw = b.getBlocksToDraw();
    
        assertEquals(14, blocksToDraw.size());
    
        int frameCount = 0;
        int blockCount = 0;
    
        for (BlockToDraw btd : blocksToDraw) {
            if (btd.getColor() == GameColors.FRAME_COLOR) {
                frameCount++;
            } else if (btd.getStrokeThickness() == 0) {
                blockCount++;
            }
        }
    
        assertEquals(7, frameCount);
        assertEquals(7, blockCount);
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("Block getBlocksToDraw() test2")
    void GetBlocksToDrawTest2() {
        Block[] children = new Block[0];
        Block b = new Block(0, 0, 16, 0, 2, GameColors.YELLOW, children);
    
        ArrayList<BlockToDraw> blocksToDraw = b.getBlocksToDraw();
        assertEquals(2, blocksToDraw.size());
    
        for (BlockToDraw btd : blocksToDraw) {
            boolean frame = btd.getStrokeThickness() == 0 && btd.getColor() == GameColors.YELLOW;
            boolean block = btd.getStrokeThickness() == 3 && btd.getColor() == GameColors.FRAME_COLOR;
            assertTrue(frame || block);
        }
    }
    @Test
    @Tag("score:2")
    @DisplayName("Block getSelectedBlock() test1")
    void getSelectedBlock1() {
        Block b = new Block(0, 0, 0, 0, 2, null, new Block[0]);
    
        assertThrows(IllegalArgumentException.class, () -> b.getSelectedBlock(2,15,4));
        assertThrows(IllegalArgumentException.class, () -> b.getSelectedBlock(15,2,-1));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("Block getSelectedBlock() test2")
    void getSelectedBlock2() throws NoSuchFieldException, IllegalAccessException {
        Block[] children = new Block[4];
        children[0] = new Block(1, 0, 1, 1, 1, GameColors.YELLOW, new Block[0]);
        children[1] = new Block(0, 0, 1, 1, 1, GameColors.RED, new Block[0]);
        children[2] = new Block(0, 1, 1, 1, 1, GameColors.GREEN, new Block[0]);
        children[3] = new Block(1, 1, 1, 1, 1, GameColors.BLUE, new Block[0]);
    
        Block b = new Block(0, 0, 2, 0, 1, null, children);
    
        Field xCoordField = Block.class.getDeclaredField("xCoord");
        Field yCoordField = Block.class.getDeclaredField("yCoord");
        Field colorField = Block.class.getDeclaredField("color");
    
        xCoordField.setAccessible(true);
        yCoordField.setAccessible(true);
        colorField.setAccessible(true);
    
        Block res = b.getSelectedBlock(0, 0, 1);
    
        assertEquals(0, (int) xCoordField.get(res));
        assertEquals(0, (int) yCoordField.get(res));
        assertEquals(GameColors.RED, colorField.get(res));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("Block getSelectedBlock() test3")
    void getSelectedBlock3() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(4);
        Block b = new Block(0, 3);
        b.updateSizeAndPosition(16,0,0);
    
        Block res = b.getSelectedBlock(9,10, 2);
    
        Field xCoordField = Block.class.getDeclaredField("xCoord");
        Field yCoordField = Block.class.getDeclaredField("yCoord");
        Field colorField = Block.class.getDeclaredField("color");
        Field childrenField = Block.class.getDeclaredField("children");
    
        xCoordField.setAccessible(true);
        yCoordField.setAccessible(true);
        colorField.setAccessible(true);
        childrenField.setAccessible(true);
    
        assertEquals(8, (int) xCoordField.get(res));
        assertEquals(8, (int) yCoordField.get(res));
        assertNull(colorField.get(res));
        assertEquals(4, ((Block[]) childrenField.get(res)).length);
    
        List<Color> colors = List.of(GameColors.BLUE, GameColors.YELLOW, GameColors.GREEN, GameColors.YELLOW);
    
        Block[] children = (Block[]) childrenField.get(res);
    
        for (int i = 0; i < 4; i++) {
            Block child = children[i];
            assertEquals(colors.get(i), colorField.get(child));
        }
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block reflect() test1")
    void reflect1() {
        Block b = new Block(0, 0, 0, 0, 2, null, new Block[0]);
    
        assertThrows(IllegalArgumentException.class, () ->  b.reflect(2));
        assertThrows(IllegalArgumentException.class, () ->  b.reflect(-1));
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block reflect() test2")
    void reflect2() throws NoSuchFieldException, IllegalAccessException {
        Block[] children = new Block[4];
        children[0] = new Block(1, 0, 1, 1, 1, GameColors.YELLOW, new Block[0]);  // UR
        children[1] = new Block(0, 0, 1, 1, 1, GameColors.RED, new Block[0]);   // UL
        children[2] = new Block(0, 1, 1, 1, 1, GameColors.GREEN, new Block[0]); // LL
        children[3] = new Block(1, 1, 1, 1, 1, GameColors.BLUE, new Block[0]);  // LR
        Block b = new Block(0, 0, 2, 0, 1, null, children);
    
        b.reflect(0);  // reflect horizontally
    
        Field childrenField = Block.class.getDeclaredField("children");
        Field colorField = Block.class.getDeclaredField("color");
        childrenField.setAccessible(true);
        colorField.setAccessible(true);
    
        Block[] childrenLevel1 = (Block[]) childrenField.get(b);
    
        List<Color> expected = List.of(GameColors.BLUE, GameColors.GREEN, GameColors.RED, GameColors.YELLOW);
        List<Color> actual = new ArrayList<>();
    
        for (Block child : childrenLevel1) {
            actual.add((Color) colorField.get(child));
        }
    
        assertEquals(expected, actual);
    
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block rotate() test1")
    void rotate1() {
        Block b = new Block();
        assertThrows(IllegalArgumentException.class, () ->  b.rotate(2));
        assertThrows(IllegalArgumentException.class, () ->  b.rotate(-1));
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block rotate() test2")
    void rotate2() throws NoSuchFieldException, IllegalAccessException {
        Block[] children = new Block[4];
        children[0] = new Block(1, 0, 1, 1, 1, GameColors.GREEN, new Block[0]);  // UR
        children[1] = new Block(0, 0, 1, 1, 1, GameColors.BLUE, new Block[0]);   // UL
        children[2] = new Block(0, 1, 1, 1, 1, GameColors.RED, new Block[0]); // LL
        children[3] = new Block(1, 1, 1, 1, 1, GameColors.BLUE, new Block[0]);  // LR
    
        Block b = new Block(0, 0, 2, 0, 1, null, children);
    
        b.rotate(1); // rotate counter-clockwise
    
        Field childrenField = Block.class.getDeclaredField("children");
        Field colorField = Block.class.getDeclaredField("color");
        childrenField.setAccessible(true);
        colorField.setAccessible(true);
    
        Block[] childrenLevel1 = (Block[]) childrenField.get(b);
    
        List<Color> expected = List.of(GameColors.BLUE, GameColors.RED, GameColors.BLUE, GameColors.GREEN);
    
        List <Color> actual = new ArrayList<>();
        for (Block child : childrenLevel1) {
            actual.add((Color) colorField.get(child));
        }
    
        assertEquals(expected, actual);
    
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block smash() test1")
    void smash1() {
        Block b = new Block();
    
        assertFalse(b.smash());
    
        Block[] children = new Block[4];
        children[0] = new Block(1, 0, 1, 1, 1, GameColors.YELLOW, new Block[0]);  // UR
        children[1] = new Block(0, 0, 1, 1, 1, GameColors.BLUE, new Block[0]);   // UL
        children[2] = new Block(0, 1, 1, 1, 1, GameColors.GREEN, new Block[0]); // LL
        children[3] = new Block(1, 1, 1, 1, 1, GameColors.BLUE, new Block[0]);  // LR
    
        b = new Block(0, 0, 2, 1, 2, null, children);
    
        assertTrue(b.smash());
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block smash() test2")
    void smash2() throws NoSuchFieldException, IllegalAccessException {
        Block.gen = new Random(0);
        Block b = new Block(1, 2);
        b.updateSizeAndPosition(4, 0,0);
    
        b.smash();
    
        Field childrenField = Block.class.getDeclaredField("children");
        Field colorField = Block.class.getDeclaredField("color");
        childrenField.setAccessible(true);
        colorField.setAccessible(true);
    
        Block[] childrenLevel1 = (Block[]) childrenField.get(b);
    
        List<Color> expected = List.of(GameColors.BLUE, GameColors.RED, GameColors.BLUE, GameColors.YELLOW);
        List<Color> actual = new ArrayList<>();
    
        for (Block child : childrenLevel1) {
            actual.add((Color) colorField.get(child));
        }
    
        assertEquals(expected, actual);
    }
    @Test // same as the one in the pdf
    @Tag("score:2")
    @DisplayName("Block flatten() test1")
    void Blockflatten1() {
        Block.gen = new Random(2);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(16, 0,0);
    
        Color[][] c = b.flatten();
    
        Color[][] expected = new Color[][]{
                {GameColors.RED, GameColors.RED, GameColors.GREEN, GameColors.GREEN},
                {GameColors.RED, GameColors.RED, GameColors.GREEN, GameColors.GREEN},
                {GameColors.YELLOW, GameColors.YELLOW, GameColors.RED, GameColors.BLUE},
                {GameColors.YELLOW, GameColors.YELLOW, GameColors.YELLOW, GameColors.BLUE}
        };
    
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], c[i][j]);
            }
        }
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("Block flatten() test2")
    void Blockflatten2() {
        Block[] children = new Block[4];
        children[0] = new Block(8, 0, 8, 1, 1, GameColors.BLUE, new Block[0]);
        children[1] = new Block(0, 0, 8, 1, 1, GameColors.YELLOW, new Block[0]);
        children[2] = new Block(0, 8, 8, 1, 1, GameColors.RED, new Block[0]);
        children[3] = new Block(8, 8, 8, 1, 1, GameColors.GREEN, new Block[0]);
    
        Block b = new Block(0, 0, 16, 0, 1, null, children);
    
        Color[][] c = b.flatten();
    
        Color[][] expected = new Color[][]{
                {GameColors.YELLOW, GameColors.BLUE},
                {GameColors.RED, GameColors.GREEN}
        };
    
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(expected[i][j], c[i][j]);
            }
        }
    }
    
    @Test
    @Tag("score:3")
    @DisplayName("Block flatten() test3")
    void Blockflatten3() {
        Block[] children = new Block[4];
        Block[] llChildren = new Block[4];
    
        llChildren[0] = new Block(4, 8, 4, 2, 2, GameColors.RED, new Block[0]);
        llChildren[1] = new Block(0, 8, 4, 2, 2, GameColors.GREEN, new Block[0]);
        llChildren[2] = new Block(0, 12, 4, 2, 2, GameColors.GREEN, new Block[0]);
        llChildren[3] = new Block(4, 12, 4, 2, 2, GameColors.YELLOW, new Block[0]);
    
        children[0] = new Block(8, 0, 8, 1, 2, GameColors.RED, new Block[0]);
        children[1] = new Block(0, 0, 8, 1, 2, GameColors.BLUE, new Block[0]);
        children[2] = new Block(0, 8, 8, 1, 2, null, llChildren);
        children[3] = new Block(8, 8, 8, 1, 2, GameColors.YELLOW, new Block[0]);
    
        Block b = new Block(0, 0, 16, 0, 2, null, children);
    
        Color[][] c = b.flatten();
    
        Color[][] expected = new Color[][]{
                {GameColors.BLUE, GameColors.BLUE, GameColors.RED, GameColors.RED},
                {GameColors.BLUE, GameColors.BLUE, GameColors.RED, GameColors.RED},
                {GameColors.GREEN, GameColors.RED, GameColors.YELLOW, GameColors.YELLOW},
                {GameColors.GREEN, GameColors.YELLOW, GameColors.YELLOW, GameColors.YELLOW}
        };
    
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], c[i][j]);
            }
        }
    }
    
    
    @Test
    @Tag("score:1")
    @DisplayName("PerimeterGoal score() test1")
    void PGscore1() {
        Block[] children = new Block[4];
    
        children[0] = new Block(8, 0, 8, 1, 2, GameColors.GREEN, new Block[0]);
        children[1] = new Block(0, 0, 8, 1, 2, GameColors.BLUE, new Block[0]);
        children[2] = new Block(0, 8, 8, 1, 2, GameColors.RED, new Block[0]);
        children[3] = new Block(8, 8, 8, 1, 2, GameColors.YELLOW, new Block[0]);
    
        Block b = new Block(0, 0, 16, 0, 2, null, children);
    
        PerimeterGoal p = new PerimeterGoal(GameColors.RED);
        assertEquals(4, p.score(b));
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("PerimeterGoal score() test2")
    void PGscore2() {
        Block.gen = new Random(8);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(16, 0, 0);
    
        PerimeterGoal p = new PerimeterGoal(GameColors.YELLOW);
        assertEquals(3, p.score(b));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("BlobGoal undiscoveredBlobSize() test1")
    void BGBlobSize1() {
        BlobGoal g = new BlobGoal(GameColors.BLUE);
    
        Color[][] c = new Color[][]{
                {GameColors.YELLOW, GameColors.BLUE},
                {GameColors.RED, GameColors.RED}
        };
    
        assertEquals(0, g.undiscoveredBlobSize(0, 0, c, new boolean[2][2]));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("BlobGoal undiscoveredBlobSize() test2")
    void BGBlobSize2() {
        BlobGoal g = new BlobGoal(GameColors.RED);
    
        Color[][] c = new Color[][]{
                {GameColors.BLUE, GameColors.RED, GameColors.GREEN},
                {GameColors.RED, GameColors.YELLOW, GameColors.RED},
                {GameColors.RED, GameColors.YELLOW, GameColors.GREEN},
                {GameColors.RED, GameColors.RED, GameColors.YELLOW}
        };
    
        assertEquals(1, g.undiscoveredBlobSize(0, 1, c, new boolean[4][3]));
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("BlobGoal undiscoveredBlobSize() test3")
    void BGBlobSize3() {
        Block.gen = new Random(8);
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(16, 0, 0);
    
        BlobGoal g = new BlobGoal(GameColors.YELLOW);
        assertEquals(2, g.undiscoveredBlobSize(1, 1, b.flatten(), new boolean[4][4]));
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("BlobGoal score() test1")
    void BGscore1() {
        Block[] children = new Block[4];
        Block[] urChildren = new Block[4];
    
        urChildren[0] = new Block(12, 0, 4, 2, 2, GameColors.GREEN, new Block[0]);
        urChildren[1] = new Block(8, 0, 4, 2, 2, GameColors.BLUE, new Block[0]);
        urChildren[2] = new Block(8, 4, 4, 2, 2, GameColors.RED, new Block[0]);
        urChildren[3] = new Block(12, 4, 4, 2, 2, GameColors.YELLOW, new Block[0]);
    
        children[0] = new Block(8, 0, 8, 1, 2, null, urChildren);
        children[1] = new Block(0, 0, 8, 1, 2, GameColors.BLUE, new Block[0]);
        children[2] = new Block(0, 8, 8, 1, 2, GameColors.RED, new Block[0]);
        children[3] = new Block(8, 8, 8, 1, 2, GameColors.YELLOW, new Block[0]);
    
        Block b = new Block(0, 0, 16, 0, 2, null, children);
    
        BlobGoal g = new BlobGoal(GameColors.BLUE);
        assertEquals(5, g.score(b));
    }
    
    @Test
    @Tag("score:1")
    @DisplayName("BlobGoal score() test2")
    void BGscore2() {
        Block.gen = new Random(500);
        Block b = new Block(0, 3);
        b.updateSizeAndPosition(16, 0, 0);
    
        BlobGoal g = new BlobGoal(GameColors.RED);
        assertEquals(18, g.score(b));
    }
    
    }
    
    class HelperMethods {
    
    public static Object[] getChildren(Object b) throws NoSuchFieldException, IllegalAccessException {
        Field childrenField ;
    
        childrenField = Block.class.getDeclaredField("children");
    
        childrenField.setAccessible(true);
    
        return (Block[]) childrenField.get(b);
    
    }
    
    public static Color getColor(Object b) throws NoSuchFieldException, IllegalAccessException {
        Field colorField ;
    
        colorField = Block.class.getDeclaredField("color");
    
        colorField.setAccessible(true);
    
        return (Color) colorField.get(b);
    }
    
    public static int getSize (Object b) throws NoSuchFieldException, IllegalAccessException {
        Field sizeField;
    
            sizeField = Block.class.getDeclaredField("size");
    
        sizeField.setAccessible(true);
    
        return (int) sizeField.get(b);
    }
    
    public static ArrayList<Integer> getCoords (Object b) throws NoSuchFieldException, IllegalAccessException {
    
        Field xCoordField,yCoordField;
    
            xCoordField = Block.class.getDeclaredField("xCoord");
            yCoordField = Block.class.getDeclaredField("yCoord");
    
        xCoordField.setAccessible(true);
        yCoordField.setAccessible(true);
    
        return new ArrayList<>(Arrays.asList((int) xCoordField.get(b), (int) yCoordField.get(b)));
    }
    
    public static int getLevel (Object b){
    
            return ((Block) b).getLevel();
    }
    
    public static int getMaxDepth (Object b){
            return ((Block) b).getMaxDepth();
    }
    
    public static boolean equals(Object b1, Object b2) throws NoSuchFieldException, IllegalAccessException {
        if (b1 == null || b2 == null) {
            return false;
        }
    
        if (getSize(b1) != getSize(b2)) {
            return false;
        }
    
        Color c1 = getColor(b1);
        Color c2 = getColor(b2);
    
        if (c1 == null && c2 == null){
            // dont do anything
        } else if (c1 == null || c2 == null){
            return false;
        } else if (!c1.equals(c2)) {
            return false;
        }
    
        ArrayList<Integer> coords1 = getCoords(b1);
        ArrayList<Integer> coords2 = getCoords(b2);
    
        if (!coords1.equals(coords2)) {
            return false;
        }
    
        if (getLevel(b1) != getLevel(b2)) {
            return false;
        }
    
        if (getMaxDepth(b1) != getMaxDepth(b2)) {
            return false;
        }
    
        Object[] children1 = getChildren(b1);
        Object[] children2 = getChildren(b2);
    
        if (children1 == null || children2 == null) {
            return false;
        } else {
            return children1.length == children2.length;
        }
    }
    
    public static ArrayList<Object> traverse(Object b1) throws NoSuchFieldException, IllegalAccessException {
    
        return traverseHelper((Block) b1);
    
    }
    
    public static ArrayList<Object> traverseHelper(Object b) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Object> blocks = new ArrayList<>();
        blocks.add(b);
        if (getChildren(b) != null) {
            for (Object child : getChildren(b)) {
                blocks.addAll(traverseHelper(child));
            }
        }
        return blocks;
    }
    
    @Test
    @Tag("score:4")
    @DisplayName("Secret undiscoveredBlobSize test2")
    public void secret_undiscoveredBlobSize2() {
        Block.gen = new Random(777888899);
        Block rb = new Block(0, 6);
        rb.updateSizeAndPosition(256, 0, 0);
    
        Color[][] unitCells = rb.flatten();
    
        boolean[][] visited = new boolean[unitCells.length][unitCells[0].length];
    
        Random r = new Random(1234567);
        for (int i = 0; i < 30; i++) {
            int x = r.nextInt(unitCells.length);
            int y = r.nextInt(unitCells[0].length);
            visited[x][y] = true;
        }
    
        BlobGoal g = new BlobGoal(GameColors.BLUE);
    
        int[] size = {-1};
    
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        size[0] = g.undiscoveredBlobSize(19, 20, unitCells, visited);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e );
                    }
                }, "Took too long / Infinite loop"
        );
    
        assertEquals(335, size[0],
                "undiscoveredBlobSize() is not returning the correct size.");
    }
    @Test
    @Tag("score:2")
    @DisplayName("Secret undiscoveredBlobSize test3")
    public void secret_undiscoveredBlobSize3() {
        Block.gen = new Random(99998888);
        Block b = new Block(4, 9);
        b.updateSizeAndPosition(128, 10, 20);
    
        Color[][] unitCells = b.flatten();
    
        boolean[][] visited = new boolean[unitCells.length][unitCells[0].length];
    
        BlobGoal g = new BlobGoal(GameColors.RED);
        int[] size = {-1};
    
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(750), () -> {
                    try{
                        size[0] = g.undiscoveredBlobSize(750, 1000, unitCells, visited);
                    } catch (Exception e) {
                        fail("Unexpected exception: " + e);
                    }
                }, "Took too long / Infinite loop"
        );
    
        assertEquals(0, size[0]);
    }
}
