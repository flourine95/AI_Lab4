import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        root.setH(0);
        root.setG(0);
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH).thenComparing(Node::getLabel));
        frontier.add(root);
        // explored arraylist
        List<Node> explored = new ArrayList<>();
//        Set<Node> explored = new HashSet<>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }
            explored.add(current);
            // lay duoc chi phi
            List<Edge> children = current.getChildren();
            for (Edge child : children) {
                Node node = child.getEnd();
                if (!explored.contains(node) && !frontier.contains(node)) {
                    node.setParent(current);
                    node.setG(current.getG() + child.getWeight());
                    frontier.add(node);
                }
                // a star
                else if (frontier.contains(node) && node.getG() > current.getG() + child.getWeight()) {
                    frontier.remove(node);
                    node.setG(current.getG() + child.getWeight());
                    node.setParent(current);
                    frontier.add(node);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}
