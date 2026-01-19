package structures;

public class Graph<T> {
    private DoublyLinkedList<T> vertices;
    private DoublyLinkedList<Edge<T>> edges;

    public static class Edge<E> {
        public E source;
        public E destination;

        public Edge(E source, E destination) {
            this.source = source;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return source + " -> " + destination;
        }
    }

    public Graph() {
        this.vertices = new DoublyLinkedList<>();
        this.edges = new DoublyLinkedList<>();
    }

    public void addVertex(T vertex) {
        if (!vertices.contains(vertex)) {
            vertices.insert(vertex);
        }
    }

    public void addEdge(T source, T destination) {
        if (vertices.contains(source) && vertices.contains(destination)) {
            edges.insert(new Edge<>(source, destination));
        }
    }

    public void removeVertex(T vertex) {
        vertices.remove(vertex);
        // Supprimer toutes les arêtes liées à ce sommet
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            if (edge.source.equals(vertex) || edge.destination.equals(vertex)) {
                edges.remove(edge);
                i--;
            }
        }
    }

    public void removeEdge(T source, T destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            if (edge.source.equals(source) && edge.destination.equals(destination)) {
                edges.remove(edge);
                return;
            }
        }
    }

    public boolean hasEdge(T source, T destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            if (edge.source.equals(source) && edge.destination.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public SinglyLinkedList<T> getAdjacents(T vertex) {
        SinglyLinkedList<T> adjacents = new SinglyLinkedList<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            if (edge.source.equals(vertex)) {
                adjacents.insert(edge.destination);
            }
        }
        return adjacents;
    }

    public SinglyLinkedList<T> findPathDFS(T start, T end) {
        SinglyLinkedList<T> path = new SinglyLinkedList<>();
        Stack<T> stack = new Stack<>();
        DoublyLinkedList<T> visited = new DoublyLinkedList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (current.equals(end)) {
                path.insert(current);
                return path;
            }
            if (!visited.contains(current)) {
                visited.insert(current);
                path.insert(current);

                SinglyLinkedList<T> adjacents = getAdjacents(current);
                for (int i = 0; i < adjacents.size(); i++) {
                    T adjacent = adjacents.get(i);
                    if (!visited.contains(adjacent)) {
                        stack.push(adjacent);
                    }
                }
            }
        }
        return null;
    }

    public SinglyLinkedList<T> findPathBFS(T start, T end) {
        SinglyLinkedList<T> path = new SinglyLinkedList<>();
        Queue<T> queue = new Queue<>();
        DoublyLinkedList<T> visited = new DoublyLinkedList<>();

        queue.enqueue(start);
        visited.insert(start);

        while (!queue.isEmpty()) {
            T current = queue.dequeue();
            path.insert(current);

            if (current.equals(end)) {
                return path;
            }

            SinglyLinkedList<T> adjacents = getAdjacents(current);
            for (int i = 0; i < adjacents.size(); i++) {
                T adjacent = adjacents.get(i);
                if (!visited.contains(adjacent)) {
                    visited.insert(adjacent);
                    queue.enqueue(adjacent);
                }
            }
        }
        return null;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }
}
