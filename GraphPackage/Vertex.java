package GraphPackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vertex<T> implements VertexInterface<T>
{
    private T label;
    private ListWithListIteratorInterface<Edge> edgeList; // list of edges that starting at this vertex
    private boolean visited;                              // True if visited
    private VertexInterface<T> previousVertex;            // For use when constructing a path
    private double cost;

    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new ArrayListWithListIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    }
    @Override
    public T getLabel()
    {
        return label;
    }

    @Override
    public void visit()
    {
        visited = true;
    }

    @Override
    public void unvisit()
    {
        visited = false;
    }

    @Override
    public boolean isVisited()
    {
        return visited;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;
        if (!this.equals(endVertex))
        {
            // 'this' and endVertex are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            }

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    }

    @Override
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    }

    @Override
    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while (neighbors.hasNext() && (result == null))
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if(!nextNeighbor.isVisited())
                result = nextNeighbor;
        }
        return result;
    }

    @Override
    public void setPredecessor(VertexInterface<T> newPredecessor)
    {
        previousVertex = newPredecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    }

    @Override
    public boolean hasPredecessor()
    {
        return previousVertex != null;
    }

    @Override
    public void setCost(double newCost)
    {
        cost = newCost;
    }

    @Override
    public double getCost()
    {
        return cost;
    }

    @Override
    public boolean equals(Object other)
    {
        boolean result;

        if((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);
        }
        return result;
    }

    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;

        private NeighborIterator()
        {
            edges = edgeList.getIterator();
        }

        public boolean hasNext()
        {
            return edges.hasNext();
        }

        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();
            return nextNeighbor;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    private class WeightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;

        private WeightIterator()
        {
            edges = edgeList.getIterator();
        }

        public boolean hasNext()
        {
            return edges.hasNext();
        }

        public Double next()
        {
            Double edgeWeight;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected Edge(VertexInterface<T> endVertex)
        {
            vertex = endVertex;
            weight = 0;
        }

        protected double getWeight()
        {
            return weight;
        }

        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        }
    }
}
