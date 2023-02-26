import java.util.*;

public class KMeans {

    public static void main(String[] args) {

        DataSetGenerator dataSetGenerator = new DataSetGenerator();

        /** create sample data set */
        List<DataSetObject> dataSet = dataSetGenerator.createDataSet();

        /** centroids count */
        final int kValue = dataSet.get(0).getValueMap().size() + 1;

        int iteration = 1;
        boolean isCentroidFinalized = false;
        Map<Integer, List<DistanceGridValue>> distanceGridMap = new HashMap<>();
        List<DistanceGridValue> distanceGrid = null;

        while(true) {

            System.out.println("---------------------------------------------Iteration "+iteration+"---------------------------------------------\n");

            List<Point> centroids = null;

            if(iteration == 1) {
                centroids = findInitCentroids(kValue);
            } else {
                centroids =  findNewCentroids(distanceGrid, kValue);
            }

            distanceGrid = createDistanceGrid(dataSet, centroids);

            printDistanceGrid(distanceGrid);

            distanceGridMap.put(iteration, distanceGrid);

            if(iteration > 1) {
                isCentroidFinalized = isDataSetPartitioned(distanceGridMap.get(iteration - 1), distanceGridMap.get(iteration));
            }

            iteration++;

            if(isCentroidFinalized) {
                System.out.println("\nData Set has partitioned successfully at Iteration " + (iteration - 1));
                break;
            }

            System.out.println();
        }
    }

    private static boolean isDataSetPartitioned(List<DistanceGridValue> distanceGridPrev, List<DistanceGridValue> distanceGridCurrent) {

        boolean isCentroidFinalized = false;
        int sameClusteringCount = 0;

        for(int i = 0; i < distanceGridPrev.size(); i++) {

            if(distanceGridPrev.get(i).getGroupedCluster().equals(distanceGridCurrent.get(i).getGroupedCluster())) {
                sameClusteringCount++;
            }
        }

        if(distanceGridPrev.size() == sameClusteringCount) {
            isCentroidFinalized = true;
        }

        return isCentroidFinalized;
    }

    private static void printDistanceGrid(List<DistanceGridValue> distanceGrid) {

        for (DistanceGridValue distanceGridValue : distanceGrid) {

            System.out.print("Centroid ("  + distanceGridValue.getCentroid().getPointX() + "," + distanceGridValue.getCentroid().getPointY() + ")");

            System.out.print(" | Point_Name " + distanceGridValue.getPointName());
            System.out.print(" | Point X "  + distanceGridValue.getPoint().getPointX());
            System.out.print(" | Point Y "  + distanceGridValue.getPoint().getPointY());
            System.out.print(" | Distance "  + distanceGridValue.getDistance());
            System.out.print(" | Grouped_Cluster "  + distanceGridValue.getGroupedCluster());
            System.out.println();
        }
    }

    private static List<Point> findNewCentroids(List<DistanceGridValue> distanceGrid, int kValue) {

        Map<Integer, List<Point>> clusterGroups = new HashMap<>();

        int noOfClusterGroups = kValue;

        while (noOfClusterGroups > 0) {

            clusterGroups.put(noOfClusterGroups, new ArrayList<>());

            for(DistanceGridValue gridValue : distanceGrid) {

                if(gridValue.getGroupedCluster() == noOfClusterGroups) {

                    clusterGroups.get(noOfClusterGroups).add(gridValue.getPoint());
                }
            }

            noOfClusterGroups--;
        }

        Map<Integer, Point> newCentroids = new TreeMap<>();

        for (Map.Entry<Integer, List<Point>> clusterGroup : clusterGroups.entrySet()) {

            int noOfAllocatedPoints = clusterGroup.getValue().size();

            if(noOfAllocatedPoints == 0) {
                continue;
            }

            if(noOfAllocatedPoints == 1) {
                newCentroids.put(clusterGroup.getKey(), clusterGroup.getValue().get(0));
            } else {

                Double pointXSum = 0.0, pointYSum = 0.0;

                for(Point point : clusterGroup.getValue()) {

                    pointXSum = pointXSum + point.getPointX();
                    pointYSum = pointYSum + point.getPointY();

                }

                Point point = new Point(round(pointXSum / noOfAllocatedPoints, 2), round(pointYSum / noOfAllocatedPoints, 2));

                newCentroids.put(clusterGroup.getKey(), point);
            }
        }

        return new ArrayList<>(newCentroids.values());
    }

    private static List<Point> findInitCentroids(int kValue) {

        List<Point> centroids = new ArrayList<>();

        centroids.add(new Point(2d, 10d));
        centroids.add(new Point(5d, 8d));
        centroids.add(new Point(1d, 2d));

        if(kValue != centroids.size()) {
            throw new IllegalArgumentException("No of centroids should be equal to the kValue");
        }

        return centroids;
    }

    static Set<String> pointNames = new LinkedHashSet<>();

    private static List<DistanceGridValue> createDistanceGrid(List<DataSetObject> dataSet, List<Point> randomCentroids) {
        List<DistanceGridValue> distanceGrid = new ArrayList<>();

        for(DataSetObject dataSetObject : dataSet) {

            pointNames.add(dataSetObject.getName());

            for(Point centroid : randomCentroids) {

                Point point = new Point(dataSetObject.getValueMap().get("X"), dataSetObject.getValueMap().get("Y"));
                final Double distance = round(calculateDistance(point, centroid), 2);

                distanceGrid.add(new DistanceGridValue(dataSetObject.getName(), point, centroid, distance));
            }
        }

        groupCluster(distanceGrid);

        return distanceGrid;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static void groupCluster(List<DistanceGridValue> distanceGrid) {

        Map<String, List<DistanceGridValue>> distanceGridMap = new HashMap<>();

        for(String pointName : pointNames) {

            distanceGridMap.put(pointName, new ArrayList<>());

            for(DistanceGridValue gridValue : distanceGrid) {
                if(pointName.equals(gridValue.getPointName())) {
                    distanceGridMap.get(pointName).add(gridValue);
                }
            }
        }

        for (Map.Entry<String, List<DistanceGridValue>> entry : distanceGridMap.entrySet()) {

            Double min = entry.getValue().get(0).getDistance();

            for(int i = 0; i < entry.getValue().size(); i++) {
                if(entry.getValue().get(i).getDistance() < min) {
                    min = entry.getValue().get(i).getDistance();
                }
            }

            int clusterId = 0;

            for(int i = 0; i < entry.getValue().size(); i++) {
                if(entry.getValue().get(i).getDistance() == min) {
                    clusterId = i + 1;
                }
            }

            for(int i = 0; i < entry.getValue().size(); i++) {
                entry.getValue().get(i).setGroupedCluster(clusterId);
            }
        }
    }

    private static Double calculateDistance(Point point, Point centroid) {
        return Math.abs(centroid.getPointX() - point.getPointX()) + Math.abs(centroid.getPointY() - point.getPointY());
    }

    private static Double calculateDistance(double[] point, double[] centroid) {
        return Math.abs(point[0] - centroid[0]) + Math.abs(point[1] - centroid[1]);
    }
}
