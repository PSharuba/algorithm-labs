package graphs.different.ways;

import graphs.lab1.Vertex;

import java.util.ArrayList;

public class GraphArcsList extends GraphMain {
    public ArrayList<Vertex> vertices;
    public ArrayList<Arc> arcs;

    public int vertexNumber;

    public GraphArcsList() {
        vertexNumber = 0;
        vertices = new ArrayList<>();
        arcs = new ArrayList<>();
    }

    public GraphArcsList(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new ArrayList<>();
        arcs = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            vertices.add(new Vertex());
        }
    }

    public void addVertex() {
        vertices.add(new Vertex());
        vertexNumber++;
    }

    public void removeVertex(int vertexNumberInGraph) {
        Vertex vertexToRemove = vertices.get(vertexNumberInGraph);
        for (Vertex v : vertexToRemove.adjacentVertices) {
            removeArc(vertexToRemove, v);
        }
        vertices.remove(vertexToRemove);
        vertexNumber--;
    }

    public void addArc(int firstVertexNumberInGraph, int secondVertexNumberInGraph) {
        Vertex firstVertex = vertices.get(firstVertexNumberInGraph);
        Vertex secondVertex = vertices.get(secondVertexNumberInGraph);
        firstVertex.addAdjacentVertex(secondVertex);
        secondVertex.addAdjacentVertex(firstVertex);
        arcs.add(new Arc(firstVertex, secondVertex));
    }

    public void removeArc(Vertex first, Vertex second) {
        for (Arc arc : arcs) {
            if (arc.isArcPresent(first, second)) arcs.remove(arc);
        }
        first.removeAdjacentVertex(second);
        second.removeAdjacentVertex(first);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Arc arc : arcs) {
            builder.append("(").append(arc.firstVertex.name)
                    .append(";").append(arc.secondVertex.name)
                    .append(")\n");
        }
        return builder.toString();

    }

    public static void main(String[] args) {
        GraphArcsList graph = new GraphArcsList();

        for (int i = 0; i < 6; i++)
            graph.addVertex();

        graph.addArc(0, 1);
        graph.addArc(0, 3);
        graph.addArc(2, 1);
        graph.addArc(5, 2);
        graph.addArc(5, 4);
        graph.addArc(3, 4);
        graph.addArc(4, 1);


        System.out.println("graphArcsList\n" + graph.toString() + "\n");
    }
}
