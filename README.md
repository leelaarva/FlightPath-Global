# Flight Route Optimization Using Graph Algorithms

A Java command-line application that models airline networks as weighted graphs and computes efficient flight routes between cities. The application explores all possible routes between an origin and destination, then ranks them based on travel cost or flight duration using graph traversal and greedy optimization techniques.

## Features

- Models flight networks using weighted directed graphs
- Generates all valid routes between two cities
- Implements iterative Depth-First Search (DFS) for graph traversal
- Prevents cycles through visited-node tracking
- Calculates cumulative travel cost and total travel time
- Ranks routes by lowest cost or shortest travel time
- Processes multiple routing requests from input files
- Outputs ranked routes to the terminal and a text file

## Tech Stack

- Java
- Object-Oriented Programming
- Graph Data Structures
- Depth-First Search (DFS)
- Greedy Algorithms
- HashMap
- LinkedList
- Stack
- File I/O

## Project Structure

```
src/
├── Main.java
├── FlightPlanner.java
├── FlightGraph.java
├── PathFinder.java
├── HeapSorter.java
├── RequestManager.java
├── OutputWriter.java
├── Models.java
└── Request.java
```

## How It Works

The application represents each city as a graph vertex and each flight as a weighted edge containing travel cost and duration. For every routing request, an iterative DFS traverses the graph to discover all valid paths while avoiding cycles. Each path is evaluated, and the resulting routes are sorted according to the selected optimization criterion before being displayed.

## Running the Application

Compile:

```bash
javac *.java
```

Run:

```bash
java Main flights.txt requests.txt output.txt
```

## Example Output

```text
Request: Dallas -> Seattle (Lowest Cost)

1. Dallas -> Denver -> Seattle
   Cost: $320
   Time: 240 minutes

2. Dallas -> Phoenix -> Seattle
   Cost: $345
   Time: 225 minutes
```

## Algorithms & Data Structures

- Weighted Graphs
- Adjacency Lists
- Iterative Depth-First Search (DFS)
- Greedy Route Ranking
- Stack-Based Graph Traversal
- Hash Maps
- Linked Lists

## Possible Enhancements

- Dijkstra's shortest-path algorithm
- A* search for heuristic-based routing
- Randomized graph generation for benchmarking
- Performance analysis on large datasets
- Interactive visualization of flight networks
