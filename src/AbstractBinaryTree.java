import java.util.List;
import java.util.function.Consumer;

public interface AbstractBinaryTree<E> {
    E getKey();
    AbstractBinaryTree<E>getLeft();
    AbstractBinaryTree<E>getRight();
    AbstractBinaryTree<E> getParent();
    void setKey(E key);
    String asIndentedPreOrder(int indent);
    List<AbstractBinaryTree<E>> preOrder();
    List<AbstractBinaryTree<E>>inOrder();
    List<AbstractBinaryTree<E>>postOrder();
    void forEachInOrder(Consumer<E> consumer);
    List<AbstractBinaryTree<E>> depthFirstTraversal();
    List<AbstractBinaryTree<E>> breadthFirstTraversal();

    void setParent(BinaryTree<E> eBinaryTree);
}

