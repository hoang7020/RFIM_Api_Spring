package vn.com.rfim_api.algorithm;

import vn.com.rfim_api.persistences.entities.Shelf;

import java.util.List;

public class ShelfGraphConverter {

    private int[][] graph;
    private List<Shelf> shelves;

    public ShelfGraphConverter(List<Shelf> shelves) {
        this.shelves = shelves;
        graph = new int[shelves.size()][shelves.size()];
    }

    public int[][] drawGraph(int columnDistance, int rowDistance) {
        for (int i = 0; i < shelves.size(); i++) {
            for (int j = 0; j < shelves.size(); j++) {
                int absX = Math.abs(shelves.get(i).getCoorX() - shelves.get(j).getCoorX());
                int absY = Math.abs(shelves.get(i).getCoorY() - shelves.get(j).getCoorY());
//                if ((absX + absY) == 0) {
//                    graph[i][j] = 0;
//                } else if ((absX + absY) == 1) {
//                    graph[i][j] = 1;
//                } else {
//                    graph[i][j] = 0;
//                }
                if(absX == 0 && absY != 0) {
                   if (absY <= 1) {
                       graph[i][j] = absY * columnDistance;
                   } else {
                       graph[i][j] = 0;
                   }
                } else if (absX != 0 && absY == 0){
                    if (absX <= 1) {
                        graph[i][j] = absX * rowDistance;
                    } else {
                        graph[i][j] = 0;
                    }
                } else {
                    graph[i][j] = 0;
                }
            }
        }
        return graph;
    }

    public int convertCoordinateToPosition(List<Shelf> shelves, Shelf shelf) {
//        int position = (shelf.getCoorX() - 1) * size + (shelf.getCoorY() - 1);
        int position = shelves.indexOf(shelf);
        return position;
    }
}
