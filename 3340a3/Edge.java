

public class Edge {

  private final int source;
  private final int destination;
  private final int weight;

  public Edge(int source, int destination, int weight) {
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  public int getSource() {
    return source;
  }
  
  public int getDestination() {
    return destination;
  }
  
  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(")
            .append(source).append(", ")
            .append(destination).append(", ")
            .append(weight).append(")");
    
    return sb.toString();
  }
}
