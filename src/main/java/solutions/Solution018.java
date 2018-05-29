package solutions;

import lib.AbstractSolution;
import static lib.PEUtils.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 * the maximum total from top to bottom is 23.
 *
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 *
 * That is, 3 + 7 + 4 + 9 = 23.
 *
 * Find the maximum total from top to bottom of the triangle below:
 *
 * 75
 * 95 64
 * 17 47 82
 * 18 35 87 10
 * 20 04 82 47 65
 * 19 01 23 75 03 34
 * 88 02 77 73 07 63 67
 * 99 65 04 28 06 16 70 92
 * 41 41 26 56 83 40 80 70 33
 * 41 48 72 33 47 32 37 16 94 29
 * 53 71 44 65 25 43 91 52 97 51 14
 * 70 11 33 28 77 73 17 78 39 68 17 57
 * 91 71 52 38 17 14 91 43 58 50 27 29 48
 * 63 66 04 68 89 53 67 30 73 16 69 87 40 31
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 *
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
 * However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be
 * solved by brute force, and requires a clever method! ;o)
 */
public class Solution018 extends AbstractSolution {
    @Override
    public String run() {
        String numberString =
        "75\n"+
        "95 64\n"+
        "17 47 82\n"+
        "18 35 87 10\n"+
        "20 04 82 47 65\n"+
        "19 01 23 75 03 34\n"+
        "88 02 77 73 07 63 67\n"+
        "99 65 04 28 06 16 70 92\n"+
        "41 41 26 56 83 40 80 70 33\n"+
        "41 48 72 33 47 32 37 16 94 29\n"+
        "53 71 44 65 25 43 91 52 97 51 14\n"+
        "70 11 33 28 77 73 17 78 39 68 17 57\n"+
        "91 71 52 38 17 14 91 43 58 50 27 29 48\n"+
        "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n"+
        "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23\n";

        // Turn the number string into a 2D array of nodes
        Node[][] graph = Arrays.stream(numberString.split("\n"))
                .map((s) -> Arrays.stream(s.split(" "))
                        .map((n) -> new Node(Integer.parseInt(n)))
                        .toArray(Node[]::new))
                .toArray(Node[][]::new);

        // Connect the nodes in the graph
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                Node node = graph[i][j];

                // Down
                try { node.addNextNode(graph[i+1][j]); }
                catch (Exception e) {}

                // Down-Right
                try { node.addNextNode(graph[i+1][j+1]); }
                catch (Exception e) {}
            }
        }

        List<List<Node>> complete = new ArrayList<>();
        List<List<Node>> incomplete = new ArrayList<>();
        incomplete.add(new ArrayList<>(Arrays.asList(graph[0][0])));

        while (incomplete.size() > 0) {
            List<Node> currPath = incomplete.remove(0);
            Node lastNode = currPath.get(currPath.size()-1);
            List<Node> nextNodes = lastNode.getNextNodes();
            if (nextNodes.size() > 0) {
                for (int i = 0; i < nextNodes.size(); i++) {
                    List<Node> nextPath = new ArrayList<>(currPath);
                    nextPath.add(nextNodes.get(i));
                    incomplete.add(nextPath);
                }
            } else {
                complete.add(currPath);
            }
        }

        int max = complete.stream().map((path) -> path.stream()
                .mapToInt((Node n) -> (Integer) n.value)
                .reduce(0, (a,b) -> a + b))
                .max(Integer::compareTo)
                .get();

        return max + "";
    }
}
