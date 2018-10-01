package teste;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;



public class main {
	public static void main(String[] args) {
	    VertexProvider <DefaultVertex> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <DefaultVertex,RelationshipDirectedEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipDirectedEdge(from,to,attributes);
		GmlImporter <DefaultVertex,RelationshipDirectedEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
   	    try {
   	    	gmlImporter.importGraph(graphgml, ImportGraph.readFile("/home/matheuslos/Downloads/grid.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    		
 	    
	    Set <Object> V = new HashSet <Object>(graphgml.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(graphgml.edgeSet());
	   

	    KosarajuStrongConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new KosarajuStrongConnectivityInspector <> (graphgml);
	    //algoritmo de kosaraju para detectar componentes fortementes conexos.
	    System.out.println("É possível trafegar em ambos os sentidos de um cruzamento para qualquer outro? " + k.isStronglyConnected());
	    System.out.println("Os seguintes componentes não são acessiveis entre si:");
	    for(Graph<DefaultVertex, RelationshipDirectedEdge> aux : k.getStronglyConnectedComponents()) {
	    	System.out.println(aux.vertexSet());
	    }
	    
	}
}
