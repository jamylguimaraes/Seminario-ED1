package local.classes;
import local.tads.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinter {

	    public static <T extends Comparable<?>> void printNode(BinaryNode root) {
	        int maxLevel = BTreePrinter.maxLevel(root);

	        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	    }

	    private static <T extends Comparable<?>> void printNodeInternal(List<BinaryNode> list, int level, int maxLevel) {
	        if (list.isEmpty() || BTreePrinter.isAllElementsNull(list))
	            return;

	        int floor = maxLevel - level;
	        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

	        BTreePrinter.printWhitespaces(firstSpaces);

	        List<BinaryNode> newNodes = new ArrayList<BinaryNode>();
	        for (BinaryNode node : list) {
	            if (node != null) {
	                System.out.print(node.value);
	                newNodes.add(node.leftChild);
	                newNodes.add(node.rightChild);
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }

	            BTreePrinter.printWhitespaces(betweenSpaces);
	        }
	        System.out.println("");

	        for (int i = 1; i <= endgeLines; i++) {
	            for (int j = 0; j < list.size(); j++) {
	                BTreePrinter.printWhitespaces(firstSpaces - i);
	                if (list.get(j) == null) {
	                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
	                    continue;
	                }

	                if (list.get(j).leftChild != null)
	                    System.out.print("/");
	                else
	                    BTreePrinter.printWhitespaces(1);

	                BTreePrinter.printWhitespaces(i + i - 1);

	                if (list.get(j).rightChild != null)
	                    System.out.print("\\");
	                else
	                    BTreePrinter.printWhitespaces(1);

	                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
	            }

	            System.out.println("");
	        }

	        printNodeInternal(newNodes, level + 1, maxLevel);
	    }

	    private static void printWhitespaces(int count) {
	        for (int i = 0; i < count; i++)
	            System.out.print(" ");
	    }

	    private static <T extends Comparable<?>> int maxLevel(BinaryNode node) {
	        if (node == null)
	            return 0;

	        return Math.max(BTreePrinter.maxLevel(node.leftChild), BTreePrinter.maxLevel(node.rightChild)) + 1;
	    }

	    private static <T> boolean isAllElementsNull(List<T> list) {
	        for (Object object : list) {
	            if (object != null)
	                return false;
	        }

	        return true;
	    }

}
