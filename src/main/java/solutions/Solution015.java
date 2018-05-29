package solutions;

import lib.AbstractSolution;
import static lib.PEUtils.Node;

/*
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 *
 *  ddddddddddddddddddddddddh.           MMMMMMMMMMMMm----------/            oMMMMMMMMMMMMo----------/
 * `s-----------s----------sM+           s----------MN          +            o----------oMo          +`
 * `+           +          oM+           +          NN          +            o          +Mo          +`
 * `+           +          oM+           +          NN          +            o          +Mo          +`
 * `+           +          oM+           +          NN          +            o          +Mo          +`
 * `+           +          oM+           +          NN          +            o          +Mo          +`
 * `o           +          oM+           o          MMssssssssssd            o          oMo          +`
 * `o...........o----------yM+           s..........ydyyyyyyyyyyMm           o..........oMy----------s`
 * `+           o       .--sMs---`       +           o      `---NM---.       o          +Mo          +`
 * `+           o       -NMMMMMMd`       +           o       yMMMMMMM+       o          +Mo          +`
 * `+           o        -NMMMMd`        +           o        yMMMMM+        o          +Mo          +`
 * `+           o         :MMMd`         +           o         hMMM/         o          +Mo `o:`     +`
 * `+           o          /Md`          +           o         `dM/          o          +Mo `MMMdo:` +`
 *  /-----------+-----------s`           +-----------+----------+/           :----------/dNmNMMMMMMNdh
 *                                                                                        ``.MMMMNh+-
 *                                                                                          `Nh+-
 *
 * MN----------:s-----------o           NN----------s-----------s           +Ms----------s-----------s
 * MN           +           o           MN          o           +           +Mo          o           +`
 * MN           +           o           MN          o           +           +Mo          o           +`
 * MN           +           o           MN          o           +           +Mo          o           +`
 * MN           +           o           MN          o           +           +Mo          o           +`
 * MN           o           o           MN          o           +           +Mo          o           +`
 * NMNNNNNNNNNNNMNNNNNNNNNNMd-          NMNNNNNNNNNNMM----------s           +Ms----------o-----------s`
 * `+           o          oM+           +          NN          +           +Mo          +           +`
 * `+           o       /mmNMNmmd.       +          NN          +           +Mo          +           +`
 * `+           o        oMMMMMN-        +          NN          +           +Mo          +           +`
 * `+           o         sMMMN-         +          NN  -       +           +Mo          +   -       +`
 * `+           o          hMN-          +          NN .MNho-   +           +Mo          +  `MNho-   +`
 *  o-----------s-----------m-           s----------mMsyMMMMMNhoy           +MdssssssssssdssyMMMMMNhoy`
 *                                                   yyyMMMMMMdo:           .oyyyyyyyyyyyyyyyMMMMMMdo:
 *                                                     .MMNy/.                              `MMNy/.
 *                                                     `o-                                  `o-
 *
 * How many such routes are there through a 20×20 grid?
 */
public class Solution015 extends AbstractSolution {

    @Override
    public String run() {
        int gridSize = 20;

        // e.g., A 2x2 grid needs 3x3 nodes to represent its paths.
        Node<Long>[][] graph = new Node[gridSize + 1][gridSize + 1];

        // Greate unconnected graph
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = new Node<Long>();
                graph[i][j].value = 0L;
            }
        }

        // Create graph connections: only right and down
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                Node<Long> node = graph[i][j];
                try {
                    Node<Long> right = graph[i][j+1];
                    node.addNextNode(right);
                }
                catch (Exception e) {}

                try {
                    Node<Long> down = graph[i+1][j];
                    node.addNextNode(down);
                }
                catch (Exception e) {}
            }
        }

        graph[0][0].value = 1L;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                Node<Long> curr = graph[i][j];
                if (curr.value == 0) {
                    curr.value = curr.getPrevNodes().stream()
                            .mapToLong((n) -> (Long) n.value)
                            .reduce(0, (a,b) -> a + b);
                }
            }
        }

        return graph[graph.length-1][graph[graph.length-1].length-1].value + "";
    }
}
