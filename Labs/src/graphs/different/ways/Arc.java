package graphs.different.ways;

import graphs.lab1.Vertex;

public class Arc {
    public Vertex firstVertex;
    public Vertex secondVertex;

    public Arc(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public boolean isVertexPresent(Vertex vertex) {
        return vertex.equals(firstVertex) || vertex.equals(secondVertex);
    }

    public boolean isArcPresent(Vertex start, Vertex stop) {
        return (start.equals(firstVertex) && stop.equals(secondVertex)) ||
                (start.equals(secondVertex) && stop.equals(firstVertex));
    }
}
