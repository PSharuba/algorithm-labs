package graphs.different.ways;

import graphs.lab1.Vertex;

import java.util.ArrayList;

public class GraphAdjList extends GraphMain {
    public ArrayList<Vertex> vertices;

    //public ArrayList<ArrayList<Boolean>> adjacencyMatrix;
    public ArrayList<ArrayList<Vertex>> adjacencyList;

    public int vertexNumber;

    public GraphAdjList() {
        vertexNumber = 0;
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    public GraphAdjList(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            vertices.add(new Vertex());
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addVertex() {
        vertices.add(new Vertex());
        vertexNumber++;
        adjacencyList.add(new ArrayList<>());
        ;
    }

    public void removeVertex(int vertexNumberInGraph) {
        Vertex vertexToRemove = vertices.get(vertexNumberInGraph);
        for (Vertex v : vertexToRemove.adjacentVertices) {
            removeArc(vertexNumberInGraph, vertices.indexOf(v));
        }
        adjacencyList.remove(vertexNumberInGraph);
        vertices.remove(vertexToRemove);
        vertexNumber--;
        for (ArrayList<Vertex> list : adjacencyList)
            for (Vertex v : list)
                if (v.equals(vertexToRemove)) {
                    list.remove(vertexToRemove);
                    break;
                }
    }

    public void addArc(int firstVertexNumberInGraph, int secondVertexNumberInGraph) {
        Vertex firstVertex = vertices.get(firstVertexNumberInGraph);
        Vertex secondVertex = vertices.get(secondVertexNumberInGraph);
        firstVertex.addAdjacentVertex(secondVertex);
        secondVertex.addAdjacentVertex(firstVertex);
        adjacencyList.get(firstVertexNumberInGraph).add(secondVertex);
        adjacencyList.get(secondVertexNumberInGraph).add(firstVertex);
    }

    public void removeArc(int firstArcVertexNumber, int secondArcVertexNumber) {
        Vertex firstVertex = vertices.get(firstArcVertexNumber);
        Vertex secondVertex = vertices.get(secondArcVertexNumber);
        firstVertex.removeAdjacentVertex(secondVertex);
        secondVertex.removeAdjacentVertex(firstVertex);
        adjacencyList.get(firstArcVertexNumber).remove(secondVertex);
        adjacencyList.get(secondArcVertexNumber).remove(firstVertex);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (ArrayList<Vertex> list : adjacencyList) {
            builder.append(i++).append("\t|");
            for (Vertex v : list)
                builder.append(v.name).append(" ");
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        GraphAdjList graph = new GraphAdjList();

        for (int i = 0; i < 6; i++) {
            graph.addVertex();
        }

        graph.addArc(0, 1);
        graph.addArc(0, 3);
        graph.addArc(2, 1);
        graph.addArc(5, 2);
        graph.addArc(5, 4);
        graph.addArc(3, 4);
        graph.addArc(4, 1);

        System.out.println("graphAdjList\n" + graph.toString() + "\n");
    }
}
