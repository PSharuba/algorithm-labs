package graphs.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph implements Serializable {
    public ArrayList<Vertex> vertices;

    public ArrayList<ArrayList<Boolean>> adjacencyMatrix;

    public int vertexNumber;

    public Graph() {
        vertexNumber = 0;
        vertices = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>();
    }

    public Graph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            vertices.add(new Vertex());
            adjacencyMatrix.add(new ArrayList<>());
        }
        for (int i = 0; i < vertexNumber; i++)
            for (int j = 0; j < vertexNumber; j++) {
                adjacencyMatrix.get(i).add(false);
            }
    }

    public void addVertex() {
        vertices.add(new Vertex());
        vertexNumber++;
        adjacencyMatrix.add(new ArrayList<>());
        for (int i = 0; i < vertexNumber; i++)
            while (adjacencyMatrix.get(i).size() < vertexNumber)
                adjacencyMatrix.get(i).add(false);
    }

    public void removeVertex(int vertexNumberInGraph) {
        Vertex vertexToRemove = vertices.get(vertexNumberInGraph);
        for (Vertex v : vertexToRemove.adjacentVertices) {
            removeArc(vertexNumberInGraph, vertices.indexOf(v));
        }
        adjacencyMatrix.remove(vertexNumberInGraph);
        vertices.remove(vertexToRemove);
        vertexNumber--;
        for (int i = 0; i < vertexNumberInGraph; i++) {
            adjacencyMatrix.get(i).remove(vertexNumberInGraph);
        }
    }

    public void addArc(int firstVertexNumberInGraph, int secondVertexNumberInGraph) {
        Vertex firstVertex = vertices.get(firstVertexNumberInGraph);
        Vertex secondVertex = vertices.get(secondVertexNumberInGraph);
        firstVertex.addAdjacentVertex(secondVertex);
        secondVertex.addAdjacentVertex(firstVertex);
        adjacencyMatrix.get(firstVertexNumberInGraph).set(secondVertexNumberInGraph, true);
        adjacencyMatrix.get(secondVertexNumberInGraph).set(firstVertexNumberInGraph, true);
    }

    public void removeArc(int firstArcVertexNumber, int secondArcVertexNumber) {
        Vertex firstVertex = vertices.get(firstArcVertexNumber);
        Vertex secondVertex = vertices.get(secondArcVertexNumber);
        firstVertex.removeAdjacentVertex(secondVertex);
        secondVertex.removeAdjacentVertex(firstVertex);
        adjacencyMatrix.get(firstArcVertexNumber).set(secondArcVertexNumber, false);
        adjacencyMatrix.get(secondArcVertexNumber).set(firstArcVertexNumber, false);
    }

    public Map<Integer, String> paint() {
        Map<Integer, String> paints = new HashMap<>();
        Map<Integer, Integer> marks = BFS(vertices.get(0));
        marks.forEach((key, value) -> {
            if (value % 2 == 0) {
                paints.put(key, "Red");
            } else {
                paints.put(key, "Blue");
            }
        });
        return paints;
    }

    public Map<Integer, Integer> BFS(Vertex startVertex) {
        for (Vertex v : vertices)
            v.mark = null;
        int mark = 0;
        Map<Integer, Integer> BFSMap = new HashMap<>();
        BFSMap.put(startVertex.name, mark);
        startVertex.mark = mark++;
        markAdjacent(startVertex, BFSMap, mark);
        return BFSMap;
    }

    private void markAdjacent(Vertex vertex, Map<Integer, Integer> map, int mark) {
        if (vertex.adjacentVertices.size() == 0) return;
        boolean needMarking = false;
        for (Vertex v : vertex.adjacentVertices)
            if (v.mark == null) {
                needMarking = true;
                break;
            }
        if (needMarking) {
            for (Vertex v : vertex.adjacentVertices) {
                if (v.mark == null) {
                    map.put(v.name, mark);
                    v.mark = mark;
                }
            }
            for (Vertex v : vertex.adjacentVertices) {
                markAdjacent(v, map, mark + 1);
            }
        }
    }

    public boolean getShortestPath(Graph graph, Vertex start, Vertex end) {
        //if (graph.vertices.indexOf(start) == -1 || graph.vertices.indexOf(end) == -1) return false;
        for (Vertex v : graph.vertices)
            v.prevVertex = null;
        start.prevVertex = start;
        markAdjacentWithPath(start, end);
        return end.prevVertex != null;

    }

    private void markAdjacentWithPath(Vertex vertex, Vertex end) {
        if (vertex.adjacentVertices.size() == 0 || end.prevVertex != null) return;
        boolean needMarking = false;
        for (Vertex v : vertex.adjacentVertices)
            if (v.prevVertex == null) {
                needMarking = true;
                break;
            }
        if (needMarking) {
            for (Vertex v : vertex.adjacentVertices) {
                if (v.prevVertex == null) {
                    v.prevVertex = vertex;
                    if (v.equals(end)) return;
                }
            }
            for (Vertex v : vertex.adjacentVertices) {
                markAdjacentWithPath(v, end);
            }
        }
    }

    public ArrayList<Vertex> getPath(Vertex end) {
        ArrayList<Vertex> path = new ArrayList<>();
        if (end.prevVertex != null) {
            path.add(end);
            Vertex next = end.prevVertex;
            while (true) {
                path.add(0, next);
                if (next.equals(next.prevVertex)) break;
                next = next.prevVertex;
            }
        }
        return path;
    }

    public ArrayList<ArrayList<Vertex>> getAllWays(Vertex startVertex, Vertex endVertex, boolean byVertices) {
        ArrayList<ArrayList<Vertex>> ways = new ArrayList<>();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(this);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Graph cloneGraph = (Graph) ois.readObject();
            Vertex start = cloneGraph.vertices.get(vertices.indexOf(startVertex));
            Vertex end = cloneGraph.vertices.get(vertices.indexOf(endVertex));


            while (getShortestPath(cloneGraph, start, end)) {
                ways.add(getPath(end));
                ArrayList<Vertex> way = ways.get(ways.size() - 1);
                //printWay(way);
                for (int i = 0; i < way.size() - 1; i++) {
                    int firstVertexNumber = cloneGraph.vertices.indexOf(way.get(i));
                    int secondVertexNumber = cloneGraph.vertices.indexOf(way.get(i + 1));
                    cloneGraph.removeArc(firstVertexNumber, secondVertexNumber);
                }
                if (byVertices) {
                    for (int i = 1; i < way.size() - 1; i++)
                        cloneGraph.vertices.remove(way.get(i));
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ways;
    }

    public String getWaysAsString(ArrayList<ArrayList<Vertex>> ways) {
        StringBuilder builder = new StringBuilder();
        for (ArrayList<Vertex> way : ways) {
            builder.append("Way: ");
            for (int i = 0; i < way.size(); i++) {
                builder.append(way.get(i).name);
                if (i != way.size() - 1) builder.append(" -> ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void printWay(ArrayList<Vertex> way) {
        StringBuilder builder = new StringBuilder();
        builder.append("Way: ");
        for (int i = 0; i < way.size(); i++) {
            builder.append(way.get(i).name);
            if (i != way.size() - 1) builder.append(" -> ");
        }
        builder.append("\n");
        System.out.println(builder.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("   ");
        for (int i = 0; i < vertexNumber; i++) {
            builder.append(i).append("      ");
        }
        builder.append("\n");
        for (int i = 0; i < vertexNumber; i++) {
            builder.append(i).append(" |");
            for (int j = 0; j < vertexNumber; j++) {
                builder.append(adjacencyMatrix.get(i).get(j)).append(", ");
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}