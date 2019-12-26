package graphs.different.ways;

import graphs.lab1.Vertex;

import java.util.ArrayList;
import java.util.Map;

public class GraphIncList extends GraphMain {
    public ArrayList<Vertex> vertices;
    public ArrayList<ArrayList<Arc>> incidenceList;

    public int vertexNumber;

    public GraphIncList() {
        vertexNumber = 0;
        vertices = new ArrayList<>();
        incidenceList = new ArrayList<>();
    }

    public GraphIncList(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new ArrayList<>();
        incidenceList = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            vertices.add(new Vertex());
            incidenceList.add(new ArrayList<>());
        }
    }

    public void addVertex() {
        vertices.add(new Vertex());
        vertexNumber++;
        incidenceList.add(new ArrayList<>());
    }

    public void removeVertex(int vertexNumberInGraph) {
        Vertex vertexToRemove = vertices.get(vertexNumberInGraph);
        for (Vertex v : vertexToRemove.adjacentVertices) {
            removeArc(vertexToRemove, v);
        }
        incidenceList.remove(vertexNumberInGraph);
        vertices.remove(vertexToRemove);
        vertexNumber--;
        for (ArrayList<Arc> list : incidenceList)
            for (Arc arc : list)
                if (arc.isVertexPresent(vertexToRemove)) {
                    list.remove(arc);
                }
    }

    public void addArc(int firstVertexNumberInGraph, int secondVertexNumberInGraph) {
        Vertex firstVertex = vertices.get(firstVertexNumberInGraph);
        Vertex secondVertex = vertices.get(secondVertexNumberInGraph);
        firstVertex.addAdjacentVertex(secondVertex);
        secondVertex.addAdjacentVertex(firstVertex);
        incidenceList.get(firstVertexNumberInGraph).add(new Arc(firstVertex, secondVertex));
        incidenceList.get(secondVertexNumberInGraph).add(new Arc(firstVertex, secondVertex));
    }

    public void removeArc(Vertex first, Vertex second) {
        first.removeAdjacentVertex(second);
        second.removeAdjacentVertex(first);
        for (ArrayList<Arc> list : incidenceList) {
            for (Arc arc : list) {
                if (arc.isArcPresent(first, second)) list.remove(arc);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (ArrayList<Arc> list : incidenceList) {
            builder.append(i++).append("\t|");
            for (Arc arc : list)
                builder.append("(").append(arc.firstVertex.name)
                        .append(";").append(arc.secondVertex.name)
                        .append(") ");
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        GraphIncList graph = new GraphIncList();

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

        System.out.println("graphIncList\n" + graph.toString() + "\n");
    }
}
