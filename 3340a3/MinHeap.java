
public class MinHeap<T> {

  int capacity;
  int currentSize;
  Node[] heap;
  int[] indexes; //will be used to decrease the distance

  public MinHeap(int capacity) {
    this.capacity = capacity;
    heap = new Node[capacity + 1];
    indexes = new int[capacity];
    heap[0] = new Node(-1, Integer.MIN_VALUE);
    currentSize = 0;
  }
  
  public MinHeap(Node[] keys) {
    this.capacity = keys.length;
    heap = new Node[capacity + 1];
    indexes = new int[capacity];
    heap[0] = new Node(-1, Integer.MIN_VALUE);
    currentSize = 0;
    
    for (int i = 0; i < keys.length; i++) {
      insert(keys[i]);
    }
  }

  public void insert(Node x) {
    currentSize++;
    int idx = currentSize;
    heap[idx] = x;
    indexes[x.getVertex()] = idx;
    bubbleUp(idx);
  }

  public void bubbleUp(int pos) {
    int parentIdx = pos / 2;
    int currentIdx = pos;
    while (currentIdx > 0 && heap[parentIdx].getDistance() > heap[currentIdx].getDistance()) {
      Node currentNode = heap[currentIdx];
      Node parentNode = heap[parentIdx];

      //swap the positions
      indexes[currentNode.getVertex()] = parentIdx;
      indexes[parentNode.getVertex()] = currentIdx;
      swap(currentIdx, parentIdx);
      currentIdx = parentIdx;
      parentIdx = parentIdx / 2;
    }
  }

  public Node delete_min() {
    Node min = heap[1];
    Node lastNode = heap[currentSize];
    //update the indexes[] and move the last node to the top
    indexes[lastNode.getVertex()] = 1;
    heap[1] = lastNode;
    heap[currentSize] = null;
    sinkDown(1);
    currentSize--;
    return min;
  }

  public void sinkDown(int k) {
    int smallest = k;
    int leftChildIdx = 2 * k;
    int rightChildIdx = 2 * k + 1;
    if (leftChildIdx < heapSize() && heap[smallest].getDistance() > heap[leftChildIdx].getDistance()) {
      smallest = leftChildIdx;
    }
    if (rightChildIdx < heapSize() && heap[smallest].getDistance() > heap[rightChildIdx].getDistance()) {
      smallest = rightChildIdx;
    }
    if (smallest != k) {
      Node smallestNode = heap[smallest];
      Node kNode = heap[k];

      //swap the positions
      indexes[smallestNode.getVertex()] = k;
      indexes[kNode.getVertex()] = smallest;
      swap(k, smallest);
      sinkDown(smallest);
    }
  }

  public void swap(int a, int b) {
    Node temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
  }

  public boolean isEmpty() {
    return currentSize == 0;
  }

  public int heapSize() {
    return currentSize;
  }
  
  public Node min_key() {
    return heap[1];
  }
  
  public int min_id() {
    return indexes[heap[1].getVertex()];
  }
  
  public Node key(int id) {
    return heap[id];
  }
  
  public boolean in_heap(int id) {
    return false;
  }
  
  public void decrease_key(int id, int new_key) {
    int index = indexes[id];
    heap[index].setDistance(new_key);
    bubbleUp(index);
  }
}
