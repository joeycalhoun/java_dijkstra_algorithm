/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_dijkstra_algorithm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Joey
 */
public class GraphGenerator {

    String[] textGraph;
    int pointer = 0;
    Node[] graph;
    int num_nodes;
    Random rng = new Random();
    String file_path;

    public GraphGenerator(int num_nodes, String file_path) {
        this.num_nodes = num_nodes;
        this.graph = new Node[num_nodes];
        this.textGraph = new String[num_nodes * num_nodes];
        filePathValidation(file_path);
    }
    
    public void filePathValidation(String path)
    {
        if(path.endsWith(".txt"))
        {
            this.file_path = path;
        }
        else{
            this.file_path = path+".txt";
        }
    }
    public void generateNodes() {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Node(i, num_nodes);
        }
        for (int i = 0; i < graph.length; i++) {
            generateSingleNode(i);
        }
    }

    public void generateSingleNode(int nodeID) {
        int maxConnections = num_nodes / 4;
        int numConnections = checkConnections(graph[nodeID]);
        if (numConnections < maxConnections) {
            int desiredConnections = rng.nextInt(maxConnections-numConnections)+2;
            for (int i = 0; i < desiredConnections; i++) {
                int newConnection = rng.nextInt(num_nodes);
                int newWeight = rng.nextInt(100);
                while (graph[nodeID].getConnection(newConnection) > 0 || newConnection == nodeID) {
                    newConnection = rng.nextInt(num_nodes);
                }
                graph[nodeID].setConnection(newConnection, newWeight);
                graph[newConnection].setConnection(nodeID, newWeight);
            }
        }
    }

    public int checkConnections(Node nodeToCheck) {
        int numConnections = 0;
        for (int i = 0; i < num_nodes; i++) {
            if (nodeToCheck.getConnection(i) > 0) {
                numConnections++;
            }
        }
        return numConnections;
    }

    public void parseAllNodes() {
        for (int i = 0; i < num_nodes; i++) {
            parseNode(i);
        }
    }

    public void parseNode(int nodeID) {
        for (int i = nodeID; i < num_nodes; i++) {
            if (parseConnection(nodeID, i) != null) {
                textGraph[pointer] = parseConnection(nodeID, i);
                pointer++;
            }
        }
    }

    public String parseConnection(int nodeID, int destID) {
        String newLine = "";
        if (graph[nodeID].getConnection(destID) > 0) {
            newLine = nodeID + " " + destID + " " + graph[nodeID].getConnection(destID);
            return newLine;
        } else {
            return null;
        }
    }

    public void printParsedNodes() {
        for (int i = 0; i < textGraph.length; i++) {
            if (textGraph[i] != null) {
                System.out.println(textGraph[i]);
            }
        }
    }
    
    public void saveParsedNodes() throws FileNotFoundException, UnsupportedEncodingException {
        
        try (PrintWriter writer = new PrintWriter(file_path, "UTF-8")) {
            for (int i = 0; i < textGraph.length; i++) {
                if (textGraph[i] != null) {
                    writer.println(textGraph[i]);
                }
            }
            for(int i = 0; i<graph.length; i++)
            {
                writer.println("Node "+i+" has "+checkConnections(graph[i])+" connections.");
            }
        }
        System.out.println("Complete.");
    }
}
