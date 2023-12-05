public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private BinaryTree<E> root;
    private int size;

    public MaxHeap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public BinaryTree<E> getRoot() {
        return root;
    }

    @Override
    public E peek() {
        if (root == null) {
            return null;
        }
        return root.getKey();
    }

    private void heapifyUp(BinaryTree<E> node) {
        while (node != null && node.getParent() != null &&
                (node.getKey() == null || node.getParent().getKey() == null || ((Comparable<E>) node.getKey()).compareTo(node.getParent().getKey()) > 0)) {
            swapValues(node, (BinaryTree<E>) node.getParent());
            node = (BinaryTree<E>) node.getParent();
        }
        // After swapping values, update the parent reference for the child node
        if (node != null) {
            if (node.getLeft() != null) {
                ((BinaryTree<E>) node.getLeft()).setParent(node);
            }
            if (node.getRight() != null) {
                ((BinaryTree<E>) node.getRight()).setParent(node);
            }
        }
    }

    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        BinaryTree<E> newNode = new BinaryTree<>(element);
        size++;

        if (root == null) {
            root = newNode;
        } else {
            BinaryTree<E> lastNode = getLastNode(size);
            lastNode.setLeft(newNode);
            heapifyUp(newNode);
        }
    }

    private void swapValues(BinaryTree<E> node1, BinaryTree<E> node2) {
        E temp = node1.getKey();
        node1.setKey(node2.getKey());
        node2.setKey(temp);
    }

    private BinaryTree<E> getLastNode(int size) {
        String binaryRepresentation = Integer.toBinaryString(size);
        BinaryTree<E> currentNode = root;

        for (int i = 1; i < binaryRepresentation.length(); i++) {
            char direction = binaryRepresentation.charAt(i);
            if (direction == '0') {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new BinaryTree<>(null));
                }
                currentNode = (BinaryTree<E>) currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new BinaryTree<>(null));
                }
                currentNode = (BinaryTree<E>) currentNode.getRight();
            }
        }

        return currentNode;
    }

    public void printHeapMatrix() {
        if (root == null) {
            System.out.println("Heap is empty");
            return;
        }

        int height = getHeight(root);
        int width = (int) Math.pow(2, height) - 1;
        E[][] matrix = (E[][]) new Comparable[height][width];

        fillMatrix(root, matrix, 0, 0, width - 1);

        for (E[] row : matrix) {
            for (E element : row) {
                System.out.print((element != null ? element : " ") + " ");
            }
            System.out.println();
        }
    }

    private void fillMatrix(BinaryTree<E> node, E[][] matrix, int level, int start, int end) {
        if (node == null) {
            return;
        }

        int mid = start + (end - start) / 2;
        matrix[level][(start + end) / 2] = node.getKey();

        fillMatrix((BinaryTree<E>) node.getLeft(), matrix, level + 1, start, mid - 1);
        fillMatrix((BinaryTree<E>) node.getRight(), matrix, level + 1, mid + 1, end);
    }

    private int getHeight(BinaryTree<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight((BinaryTree<E>) node.getLeft());
        int rightHeight = getHeight((BinaryTree<E>) node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null; // Куча пуста
        }

        E maxElement = root.getKey();
        BinaryTree<E> lastNode = getLastNode(size);

        if (size == 1) {
            root = null;
        } else {
            // Поменяем местами максимальный элемент с последним элементом
            swapValues(root, lastNode);

            // Удаление последнего элемента
            BinaryTree<E> parent = (BinaryTree<E>) lastNode.getParent();
            if (parent.getLeft() == lastNode) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }

            // Выполняем heapifyDown для восстановления свойства кучи
            heapifyDown(root);
        }

        size--;
        return maxElement;
    }

    private BinaryTree<E> findNodeWithValue(BinaryTree<E> node, E value) {
        if (node == null) {
            return null;
        }

        if (node.getKey() != null && node.getKey().equals(value)) {
            return node;
        }

        // Рекурсивный поиск в левом и правом поддеревьях
        BinaryTree<E> leftResult = findNodeWithValue((BinaryTree<E>) node.getLeft(), value);
        BinaryTree<E> rightResult = findNodeWithValue((BinaryTree<E>) node.getRight(), value);

        return leftResult != null ? leftResult : rightResult;
    }


    private void heapifyDown(BinaryTree<E> node) {
        while (node != null) {
            BinaryTree<E> maxChild = findMaxChild(node);

            // Если максимальный ребенок больше текущего узла, меняем их местами
            if (maxChild != null && node.getKey() != null && maxChild.getKey() != null && maxChild.getKey().compareTo(node.getKey()) > 0) {
                swapValues(node, maxChild);
                node = maxChild;
            } else {
                break;
            }
        }
    }

    private BinaryTree<E> findMaxChild(BinaryTree<E> node) {
        BinaryTree<E> leftChild = (BinaryTree<E>) node.getLeft();
        BinaryTree<E> rightChild = (BinaryTree<E>) node.getRight();

        if (leftChild == null && rightChild == null) {
            return null; // Нет дочерних узлов
        } else if (rightChild == null || leftChild.getKey().compareTo(rightChild.getKey()) > 0) {
            return leftChild; // Левый ребенок больше правого
        } else {
            return rightChild; // Правый ребенок больше или равен левому
        }
    }

    public boolean contains(E value) {
        return findNodeWithValue(root, value) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

}

