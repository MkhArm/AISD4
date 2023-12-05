import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private AbstractBinaryTree<E> left;
    private AbstractBinaryTree<E> right;
    private AbstractBinaryTree<E> parent;

    public BinaryTree(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public AbstractBinaryTree<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTree<E> eBinaryTree) {
        this.parent = eBinaryTree;
    }

    public void setLeft(AbstractBinaryTree<E> left) {
        this.left = left;
        if (left != null) {
            left.setParent(this);
        }
    }

    public void setRight(AbstractBinaryTree<E> right) {
        this.right = right;
        if (right != null) {
            right.setParent(this);
        }
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        sb.append(key.toString()).append("\n");
        if (left != null) {
            sb.append(left.asIndentedPreOrder(indent + 1));
        }
        if (right != null) {
            sb.append(right.asIndentedPreOrder(indent + 1));
        }
        return sb.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null) {
            result.addAll(left.preOrder());
        }
        if (right != null) {
            result.addAll(right.preOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.inOrder());
        }
        result.add(this);
        if (right != null) {
            result.addAll(right.inOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.postOrder());
        }
        if (right != null) {
            result.addAll(right.postOrder());
        }
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (left != null) {
            left.forEachInOrder(consumer);
        }
        consumer.accept(key);
        if (right != null) {
            right.forEachInOrder(consumer);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> depthFirstTraversal() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        depthFirstTraversal(this, result);
        return result;
    }

    private void depthFirstTraversal(AbstractBinaryTree<E> node, List<AbstractBinaryTree<E>> result) {
        if (node == null) {
            return;
        }

        result.add(node);

        depthFirstTraversal(node.getLeft(), result);
        depthFirstTraversal(node.getRight(), result);
    }

    @Override
    public List<AbstractBinaryTree<E>> breadthFirstTraversal() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        Queue<AbstractBinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            AbstractBinaryTree<E> node = queue.poll();
            if (node != null) {
                result.add(node);
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }

        return result;
    }

    public List<E> convertToLinkedListWithHashing() {
        Map<Integer, List<E>> levelMap = new HashMap<>();
        convertToLinkedListWithHashing(this, 0, levelMap);

        List<E> result = new ArrayList<>();
        boolean leftToRight = true;

        for (int level = 0; level < levelMap.size(); level++) {
            if (leftToRight) {
                result.addAll(levelMap.get(level));
            } else {
                List<E> reversed = new ArrayList<>(levelMap.get(level));
                Collections.reverse(reversed);
                result.addAll(reversed);
            }
            leftToRight = !leftToRight;
        }

        return result;
    }

    private void convertToLinkedListWithHashing(AbstractBinaryTree<E> node, int level, Map<Integer, List<E>> levelMap) {
        if (node == null) {
            return;
        }

        if (!levelMap.containsKey(level)) {
            levelMap.put(level, new ArrayList<>());
        }
        levelMap.get(level).add(node.getKey());

        convertToLinkedListWithHashing(node.getLeft(), level + 1, levelMap);
        convertToLinkedListWithHashing(node.getRight(), level + 1, levelMap);
    }

    public List<E> convertToLinkedListWithSpiralTraversal() {
        List<E> result = new ArrayList<>();
        Deque<AbstractBinaryTree<E>> deque = new LinkedList<>();
        boolean leftToRight = true;

        deque.add(this);

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<E> levelList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                AbstractBinaryTree<E> node = deque.pollFirst();

                if (leftToRight) {
                    levelList.add(node.getKey());
                } else {
                    levelList.add(0, node.getKey());
                }

                if (node.getLeft() != null) {
                    deque.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    deque.add(node.getRight());
                }
            }

            result.addAll(levelList);
            leftToRight = !leftToRight;
        }

        return result;

    }

}
