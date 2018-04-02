/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_dijkstra_algorithm;

/**
 *
 * @author Joey
 */
public class Node {
    private int node_id;
    private int[] connections;
    public Node(int node_id, int possible_connections){
        this.node_id = node_id;
        this.connections = new int[possible_connections];
    }
    
    public int getNodeID()
    {
        return this.node_id;
    }
    
    public void setConnection(int nodeID, int weight)
    {
        connections[nodeID] = weight;
    }
    
    public int getConnection(int nodeID)
    {
        return connections[nodeID];
    }
}
