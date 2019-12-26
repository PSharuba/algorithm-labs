package graphs.lab1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Vertex implements Serializable {
    private static int nextNumberOfVertex = 0;

    public int name;
    public Integer mark;
    public ArrayList<Vertex> adjacentVertices;
    public Vertex prevVertex;

    public Vertex() {
        this.name = nextNumberOfVertex++;
        this.mark = null;
        adjacentVertices = new ArrayList<>();
    }

    public void addAdjacentVertex(Vertex vertex) {
        adjacentVertices.add(vertex);
    }

    public void removeAdjacentVertex(Vertex vertex) {
        adjacentVertices.remove(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return name == vertex.name &&
                Objects.equals(adjacentVertices, vertex.adjacentVertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, adjacentVertices);
    }
}
