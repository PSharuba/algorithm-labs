package graphs.lab1;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();


        // для раскрасок
/*
        for (int i = 0; i < 6; i++)
            graph.addVertex();
        System.out.println(graph.toString());

        graph.addArc(0,1);
        graph.addArc(0,3);
        graph.addArc(2,1);
        graph.addArc(5,2);
        graph.addArc(5,4);
        graph.addArc(3,4);
        graph.addArc(4,1);

        System.out.println(graph.toString());
        System.out.println("\nОбход в ширину\n"+graph.BFS(graph.vertices.get(0)).toString());
        System.out.println("\nРаскраски:\n"+graph.paint().toString());
        */
        // для путей
        for (int i = 0; i < 7; i++)
            graph.addVertex();
        System.out.println(graph.toString());

        graph.addArc(0,1);
        graph.addArc(0,3);
        graph.addArc(2,1);
        graph.addArc(5,2);
        graph.addArc(5,4);
        graph.addArc(3,4);
        graph.addArc(4,1);
        graph.addArc(4,0);
        graph.addArc(3,1);
        graph.addArc(5,1);
        graph.addArc(5,6);
        graph.addArc(4,6);

        System.out.println(graph.toString());
        Vertex start = graph.vertices.get(1);
        Vertex end = graph.vertices.get(4);
        System.out.println("Пути с непересекающимися вершинами 1->4:");
        System.out.println(graph.getWaysAsString(graph.getAllWays(start,end,true)));
        System.out.println("\nПути с непересекающимися ребрами 1->4:");
        System.out.println(graph.getWaysAsString(graph.getAllWays(start,end,false)));
    }
}
