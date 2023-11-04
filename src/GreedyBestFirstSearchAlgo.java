import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        root.setH(0);
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH).thenComparing(Node::getLabel));
        frontier.add(root);
        List<Node> explored = new ArrayList<>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }
            explored.add(current);
            List<Edge> children = current.getChildren();
            for (Edge child : children) {
                Node node = child.getEnd();
                if (!explored.contains(node) && !frontier.contains(node)) {
                    node.setParent(current);
                    node.setG(current.getG() + child.getWeight());
                    frontier.add(node);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH).thenComparing(Node::getLabel));
        frontier.add(NodeUtils.findNode(root, start));
        List<Node> explored = new ArrayList<>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }
            explored.add(current);
            for (Node child : current.getChildrenNodes()) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setParent(current);
                    child.setG(current.getG() + child.getH());
                    frontier.add(child);
                }
            }
        }
        return null;
    }
}
