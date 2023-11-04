import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeUtils {
    public static Node findNode(Node root, String label) {
        if (root == null || root.getLabel().equals(label)) {
            return root;
        }

        for (Node child : root.getChildrenNodes()) {
            Node node = findNode(child, label);
            if (node != null) {
                return node;
            }
        }

        return null;
    }
    public static List<String> printPath(Node node) {
        if (node != null) {
            List<String> result = new ArrayList<>();
            result.add(node.getLabel());
            Node tmp;
            while ((tmp = node.getParent()) != null) {
                result.add(tmp.getLabel());
                node = tmp;
            }
            Collections.reverse(result);
            return result;
        } else
            return new ArrayList<>();
    }
}
